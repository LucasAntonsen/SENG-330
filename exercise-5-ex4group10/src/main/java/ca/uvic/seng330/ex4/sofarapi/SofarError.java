package ca.uvic.seng330.ex4.sofarapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SofarError {
  public final String message;

  public SofarError(@JsonProperty("message") String message){
    this.message = message;
  }

}
