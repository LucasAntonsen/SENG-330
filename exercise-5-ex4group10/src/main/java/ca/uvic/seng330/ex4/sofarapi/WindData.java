package ca.uvic.seng330.ex4.sofarapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WindData {
  public final String physicalUnit;
  public final double value;
  public final String timestamp;

  public WindData(
          @JsonProperty("physicalUnit") String physicalUnit,
          @JsonProperty("values") List<Map<String, Object>> values
  ) {
    Map<String, ?> item = values.get(0);
    this.physicalUnit = physicalUnit;
    this.value = Double.parseDouble(item.get("value").toString());
    this.timestamp = item.get("timestamp").toString();
  }

  @Override
  public String toString() {
    return "physicalUnit: " + physicalUnit + "\tvalue: " + value + "\ttimestamp: " + timestamp;
  }

}
