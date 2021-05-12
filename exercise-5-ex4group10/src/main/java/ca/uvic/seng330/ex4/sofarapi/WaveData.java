package ca.uvic.seng330.ex4.sofarapi;

public class WaveData {

  public final String waveHeightUnit;

  public final String waveDirectionUnit;
  public final double waveHeight;
  public final double waveDirection;
  public final String timestamp;

  public WaveData(String waveHeightUnit, String waveDirectionUnit, double waveHeight, double waveDirection, String timestamp) {

    this.waveHeightUnit = waveHeightUnit;
    this.waveDirectionUnit = waveDirectionUnit;
    this.waveHeight = waveHeight;
    this.waveDirection = waveDirection;
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "waveHeightUnit: " + waveHeightUnit + "\twaveDirectionUnit: " + waveDirectionUnit + "\twaveHeight: " + waveHeight
            + "\twaveDirection: " + waveDirection + "\ttimestamp: " + timestamp;
  }
}
