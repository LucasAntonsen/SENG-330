package ca.uvic.seng330.ex4.sofarapi;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WaveApiTest extends SofarApiTest{


    private WaveApi wave = new WaveApi();

    @Override
    protected SofarApi getApi() {
      return wave;
    }

    @Test
    public void testParseGoodData(){
      String goodJSON = "[{\"physicalUnit\":\"m\",\"variableName\":\"significantWaveHeight\",\"dataCategory\":\"surfaceWaves\",\"values\":[{\"value\":1.74000000953674,\"timestamp\":\"2020-10-28T20:00:00.000Z\"},{\"value\":1.71999990940094,\"timestamp\":\"2020-10-28T21:00:00.000Z\"},{\"value\":1.69999992847443,\"timestamp\":\"2020-10-28T22:00:00.000Z\"},{\"value\":1.66999995708466,\"timestamp\":\"2020-10-28T23:00:00.000Z\"}],\"variableID\":\"SofarOperationalWaveModel-significantWaveHeight\"},{\"physicalUnit\":\"degree\",\"variableName\":\"meanDirection\",\"dataCategory\":\"surfaceWaves\",\"values\":[{\"value\":65,\"timestamp\":\"2020-10-28T20:00:00.000Z\"},{\"value\":65,\"timestamp\":\"2020-10-28T21:00:00.000Z\"},{\"value\":64,\"timestamp\":\"2020-10-28T22:00:00.000Z\"},{\"value\":63,\"timestamp\":\"2020-10-28T23:00:00.000Z\"}],\"variableID\":\"SofarOperationalWaveModel-meanDirection\"}]";

      wave.onReceive(goodJSON);
      assertThat("").isEqualTo(wave.getError());

      WaveData expected = new WaveData("m", "degree", 1.74000000953674, 65, "2020-10-28T20:00:00.000Z");
      assertThat(wave.getData()).isNotEmpty();
      assertThat(expected).isEqualToComparingFieldByField(wave.getData().get());
    }

    @Test
    public void testParseNoValues(){
      String response = "[{\"physicalUnit\":\"m\",\"variableName\":\"significantWaveHeight\",\"dataCategory\":\"surfaceWaves\",\"values\":[],\"variableID\":\"SofarOperationalWaveModel-significantWaveHeight\"},{\"physicalUnit\":\"degree\",\"variableName\":\"meanDirection\",\"dataCategory\":\"surfaceWaves\",\"values\":[],\"variableID\":\"SofarOperationalWaveModel-meanDirection\"}]";
      wave.onReceive(response);
      assertThat(SofarApi.NO_DATA_IN_RANGE).isEqualTo(wave.getError());
      assertThat(wave.getData()).isEmpty();
    }

    @Test
    public void testParseNoData(){
      String response = "[]";
      wave.onReceive(response);
      assertThat(SofarApi.NO_DATA_IN_RANGE).isEqualTo(wave.getError());
      assertThat(wave.getData()).isEmpty();
    }

    @Test
    public void testParseGarbledResponse(){
      String response = "[{]";
      wave.onReceive(response);
      assertThat(SofarApi.NO_DATA_IN_RANGE).isEqualTo(wave.getError());
      assertThat(wave.getData()).isEmpty();
    }




}