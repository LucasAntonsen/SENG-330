package ca.uvic.seng330.ex4;

import ca.uvic.seng330.ex4.sofarapi.WaveData;
import ca.uvic.seng330.ex4.sofarapi.WindData;

import java.util.Objects;
import java.util.Optional;

public class SightingState {
  private WaveData waveState;
  private WindData windState;
  private int sightingDistance = 0;
  private String sightingPlatform = "";

  /**
   * Constructor
   */

  public SightingState(String sightingPlatform, int sightingDistance, WaveData waves, WindData wind) {
    this.windState = wind;
    this.waveState = waves;
    this.sightingDistance = sightingDistance;
    setSightingPlatform(sightingPlatform);
  }

  public SightingState(String sightingPlatform, int sightingDistance) {
    setSightingPlatform(sightingPlatform);
    this.sightingDistance = sightingDistance;
  }

  /**
   * Getters
   */

  public int getSightingDistance() {
    return sightingDistance;
  }

  public String getSightingPlatform() {
    return sightingPlatform;
  }

  public Optional<WaveData> getWaveState(){
    return Optional.ofNullable(waveState);
  }

  public Optional<WindData> getWindState(){
    return Optional.ofNullable(windState);
  }



  public void setSightingPlatform(String sightingPlatform) {
    this.sightingPlatform = Objects.requireNonNullElse(sightingPlatform, "");
  }


  @Override
  public String toString() {
    String str = "";
    str += "\t windState:" + windState;
    str += "\t waves: " + waveState;
    str += "\t sightingDistance:" + sightingDistance;
    str += "\t sightingPlatform:" + sightingPlatform;
    return (str);
  }
}
