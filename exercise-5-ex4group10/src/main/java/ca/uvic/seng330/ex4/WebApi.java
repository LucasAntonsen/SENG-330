package ca.uvic.seng330.ex4;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

/**
 * A description of a generic class that accesses a REST, HTML , or XML web api
 * @param <T> The object containing response data
 */
public interface WebApi <T> {

  /**
   * Get the processed data from the web
   * @return Optional.empty() if query has not yet been called, or there was an error
   *          Optional.of(T) the data returned from the web parsed to an object
   */
  Optional<T> getData();

  /**
   * Initiate a call to a WebApi. This method should delegate to onReceive and onError
   * to process the data itself.
   * @param observation An Observation that for meta data such as time and GPS location
   */
  void query(Observation observation);

  /**
   * Process valid responses from the web and generate data for the user
   * @param dataReceived a String containing valid data.
   */
  void onReceive(String dataReceived);

  /**
   * Process invalid responses from the web and generate an appropriate error message
   * @param dataReceived a String containing invalid data
   * @param code The server response code
   */
  void onError(String dataReceived, int code);

  /**
   * The error message generated if the most recent call to query was unsuccessful.
   * If {@code getData()} returns {@code Optional.empty()} after a query call this should
   * provide details why.
   * @return The current error message
   */
  String getError();


}
