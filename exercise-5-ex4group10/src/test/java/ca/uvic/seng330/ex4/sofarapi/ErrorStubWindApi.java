package ca.uvic.seng330.ex4.sofarapi;

import ca.uvic.seng330.ex4.Observation;
import ca.uvic.seng330.ex4.WebApi;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.Optional;

public class ErrorStubWindApi implements WebApi<WindData> {

  @Named("errorString")
  @Inject()
  private String error;


  @Override
  public Optional<WindData> getData() {
    return Optional.empty();
  }

  @Override
  public void query(Observation observation) {

  }

  @Override
  public void onReceive(String dataReceived) {

  }

  @Override
  public void onError(String dataReceived, int code) {

  }

  @Override
  public String getError() {
    return error;
  }
}
