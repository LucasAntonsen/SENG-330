package ca.uvic.seng330.ex4.sofarapi;

import ca.uvic.seng330.ex4.Observation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class WindApiTest extends SofarApiTest {

  private WindApi wind = new WindApi();

  @Override
  protected SofarApi getApi() {
    return wind;
  }

  @Test
  public void testParseGoodData(){
    String goodJSON = "[{\"physicalUnit\":\"m/s\",\"variableName\":\"magnitude10MeterWind\",\"dataCategory\":\"atmospheric\",\"values\":[{\"value\":11.3282270431519,\"timestamp\":\"2020-10-28T20:00:00.000Z\"},{\"value\":11.5092668533325,\"timestamp\":\"2020-10-28T21:00:00.000Z\"},{\"value\":10.6668329238892,\"timestamp\":\"2020-10-28T22:00:00.000Z\"},{\"value\":11.04283618927,\"timestamp\":\"2020-10-28T23:00:00.000Z\"}],\"variableID\":\"GFS-magnitude10MeterWind\"}]";
    wind.onReceive(goodJSON);
    assertThat("").isEqualTo(wind.getError());

    WindData expected = new WindData("m/s", Collections.singletonList(Map.of("value",
            "11.3282270431519",
            "timestamp",
            "2020-10-28T20:00:00.000Z" )));
    assertThat(wind.getData()).isNotEmpty();
    assertThat(expected).isEqualToComparingFieldByField(wind.getData().get());
  }

  @Test
  public void testParseNoValues(){
    String response = "[{\"physicalUnit\":\"m/s\",\"variableName\":\"magnitude10MeterWind\",\"dataCategory\":\"atmospheric\",\"values\":[],\"variableID\":\"GFS-magnitude10MeterWind\"}]";
    wind.onReceive(response);
    assertThat(SofarApi.NO_DATA_IN_RANGE).isEqualTo(wind.getError());
    assertThat(wind.getData()).isEmpty();
  }

  @Test
  public void testParseNoData(){
    String response = "[]";
    wind.onReceive(response);
    assertThat(SofarApi.NO_DATA_IN_RANGE).isEqualTo(wind.getError());
    assertThat(wind.getData()).isEmpty();
  }

  @Test
  public void testParseGarbledResponse(){
    String response = "[{]";
    wind.onReceive(response);
    assertThat(SofarApi.NO_DATA_IN_RANGE).isEqualTo(wind.getError());
    assertThat(wind.getData()).isEmpty();
  }
  
  @Test
  public void testInvalidData(){
    String response = "[{\"physicalUnit\":\"m/s\",\"variableName\":\"magnitude10MeterWind\",\"dataCategory\":\"atmospheric\",\"values\":[{\"value\":-11.3282270431519,\"timestamp\":\"2020-10-28T20:00:00.000Z\"}],\"variableID\":\"GFS-magnitude10MeterWind\"}]";
    wind.onReceive(response);
    assertThat(SofarApi.NO_DATA_IN_RANGE).isEqualTo(wind.getError());
    assertThat(wind.getData()).isEmpty();

  }



}
