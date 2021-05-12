package ca.uvic.seng330.ex4.sofarapi;

import ca.uvic.seng330.ex4.Observation;
import ca.uvic.seng330.ex4.WebApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Optional;

public class WindApi extends SofarApi<WindData> implements WebApi<WindData> {

  private WindData data = null;

  @Override
  protected String getVariableId() {
    return "GFS-magnitude10MeterWind";
  }

  @Override
  protected String getUrlPath() {
    return "/GFS/hindcast/point";
  }

  @Override
  public Optional<WindData> getData() {
    return Optional.ofNullable(data);
  }

  @Override
  public void query(Observation observation) {
    data = null;
    super.query(observation);
  }

  @Override
  public void onReceive(String dataReceived) {
    try {
      JSONArray json = new JSONArray(dataReceived);
      //combine
      if (json.length() > 0) {
        data = mapper.readValue(json.get(0).toString(), WindData.class);
      }
    } catch (JsonProcessingException | JSONException e) {
      error = NO_DATA_IN_RANGE;
      return;
    }

    if (data == null){
      error = NO_DATA_IN_RANGE;
      return;
    }
    if (data.value < 0){
      error = NO_DATA_IN_RANGE;
      data = null;
    }

  }

}
