package ca.uvic.seng330.ex4.sofarapi;

import ca.uvic.seng330.ex4.Observation;
import ca.uvic.seng330.ex4.WebApi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class WaveApi extends SofarApi<WaveData> implements WebApi<WaveData> {

    private WaveData wave = null;

    @Override
    protected String getVariableId() {
        return "SofarOperationalWaveModel-significantWaveHeight,SofarOperationalWaveModel-meanDirection";
    }

    @Override
    protected String getUrlPath() {
        return "/SofarOperationalWaveModel/hindcast/point";
    }

    @Override
    public Optional<WaveData> getData() {
        return Optional.ofNullable(wave);
    }

    @Override
    public void query(Observation observation) {
        wave = null;
        super.query(observation);
    }

    @Override
    public void onReceive(String dataReceived) {
        WaveDataHelper height = null;
        WaveDataHelper direction = null;

        try {
            JSONArray json = new JSONArray(dataReceived);
            //combine
            if (json.length() > 1) {
                height = mapper.readValue(json.get(0).toString(), WaveDataHelper.class);
                direction = mapper.readValue(json.get(1).toString(), WaveDataHelper.class);
            }

        } catch (JsonProcessingException | JSONException e ) {
            error = NO_DATA_IN_RANGE;
            return;
        }

        if (height == null || direction == null) {
            error = NO_DATA_IN_RANGE;
            return;
        }
        if (height.value < 0) {
            error = NO_DATA_IN_RANGE;
        }

        //copy fields to waveDataCombined object
        wave = fromHelpers(height, direction);

    }

    private WaveData fromHelpers(WaveDataHelper height, WaveDataHelper direction){
        return new WaveData(height.physicalUnit, direction.physicalUnit, height.value, direction.value, height.timestamp);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class WaveDataHelper {
        public final String physicalUnit;
        public final double value;
        public final String timestamp;

        public WaveDataHelper(
                @JsonProperty("physicalUnit") String physicalUnit,
                @JsonProperty("values") ArrayList<Map<String, ?>> values
        ) {
            Map<String, ?> item = values.get(0);
            this.physicalUnit = physicalUnit;
            this.value = Double.parseDouble(item.get("value").toString());
            this.timestamp = item.get("timestamp").toString();
        }
    }
}