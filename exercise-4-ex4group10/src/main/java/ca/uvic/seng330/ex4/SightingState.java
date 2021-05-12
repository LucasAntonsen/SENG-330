package ca.uvic.seng330.ex4;

import java.util.Objects;

public class SightingState {
  private float windSpeed = 0;
  private int sightingDistance = 0;
  private String sightingPlatform = "";

  /**
   * Constructor
   */

  public SightingState() {
  }

  public SightingState(float wind, int sightingDist, String sightingPlatform) {
    this.windSpeed = wind;
    this.sightingDistance = sightingDist;
    setSightingPlatform(sightingPlatform);
  }

  /**
   * Getters
   */

  public float getWindSpeed() {
    return windSpeed;
  }

  /**
   * Setters
   */


  public void setWindSpeed(float windSpeed) {
    this.windSpeed = windSpeed;
  }

  public int getSightingDistance() {
    return sightingDistance;
  }

  public void setSightingDistance(int sightingDistance) {
    this.sightingDistance = sightingDistance;
  }

  public String getSightingPlatform() {
    return this.sightingPlatform;
  }

  public void setSightingPlatform(String sightingPlatform) {
    this.sightingPlatform = Objects.requireNonNullElse(sightingPlatform, "");
  }


  @Override
  public String toString() {
    String str = "";
    str += "\t windSpeed:" + windSpeed;
    str += "\t sightingDistance:" + sightingDistance;
    str += "\t sightingPlatform:" + sightingPlatform;
    return (str);
  }
}
