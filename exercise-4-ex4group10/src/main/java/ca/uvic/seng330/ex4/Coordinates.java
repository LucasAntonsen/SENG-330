package ca.uvic.seng330.ex4;

import java.lang.Math;
import java.util.Comparator;

public class Coordinates implements Comparator<Coordinates> {
  private String description;
  private double latitude;
  private double longitude;
  private static final String BLANK_DESCRIPTION = "";
  private static final double BLANK_COORDINATE = 0;
  private static final int MAX_CHARS = 1500;
  private static Coordinates origin = new Coordinates();
  private static final int RADIUS_OF_EARTH = 6371; // in km
  public static Coordinates getOrigin() {
    return origin;
  }

  public static void setOrigin(Coordinates a) {
    assert origin != null;
    origin = a;
  }

  //Default constructor
  public Coordinates(){
    this.description = BLANK_DESCRIPTION;
    this.latitude = BLANK_COORDINATE;
    this.longitude = BLANK_COORDINATE;
  }

  public Coordinates(double latitude, double longitude){
    this.latitude = latitude;
    this.longitude = longitude;
    this.description = BLANK_DESCRIPTION;
  }

  /**
   * Copy Constructor
   * @pre copy != null
   */
  Coordinates(Coordinates copy){
    assert copy != null;
    description = copy.description;
    latitude = copy.latitude;
    longitude = copy.longitude;
  }

  public int compare(Coordinates a, Coordinates b){
    return a.compareTo(b);
  }

  public int compareTo(Coordinates b){
    double distA = this.distanceFromOrigin();
    double distB = b.distanceFromOrigin();
    if(distA < distB) {
      return 1;
    }
    else if(distA > distB){
      return -1;
    }
    else{
      return 0;
    }
  }

  public double distanceFromOrigin(){
    //Calculate distance using the Haversine formula
    double latARadians = this.latitude * Math.PI/180;
    double latBRadians = origin.latitude * Math.PI/180;
    double longARadians = this.longitude * Math.PI/180;
    double longBRadians = origin.longitude * Math.PI/180;
    double latDiff = ((latBRadians - latARadians)/2);
    double longDiff = ((longBRadians - longARadians)/2);
    double cosLatA = Math.cos(latARadians);
    double cosLatB = Math.cos(latBRadians);
    double distance = 2*RADIUS_OF_EARTH*Math.asin(Math.sqrt(Math.pow(Math.sin(latDiff),2)+cosLatA*cosLatB*Math.pow(Math.sin(longDiff),2)));
    return distance;
  }

  public String getDescription(){
    return description;
  }

  public void setDescription(String description){
    if(description.length() > MAX_CHARS){
      //throw new IndexOutOfBoundsException("Description must be 1500 chars or less!"); potential exception.
      this.description = description.substring(0,Math.min(description.length(), MAX_CHARS));
    }
    else{
      this.description = description;
    }
  }

  public double getLatitude() {
    return latitude;
  }
  //Latitude is clamped to range of [-90, 90]. As per GoogleMaps API.
  public void setLatitude(double latitude) {
    if(latitude < -90){
      this.latitude = -90;
    }
    else if(latitude > 90){
      this.latitude = 90;
    }
    else{
      this.latitude = latitude;
    }
  }

  public double getLongitude() {
    return longitude;
  }
  //Longitude handles globe wrap for geographical positioning range[-180, 180].
  public void setLongitude(double longitude) {
    if(longitude > 180){
      this.longitude = longitude % -180;
    }
    else if(longitude < -180){
      this.longitude = longitude % 180;
    }
    else{
      this.longitude = longitude;
    }
  }

  @Override
  public String toString() {
    return "{" + latitude + ", " + longitude + "}";
  }
}