package ca.uvic.seng330.ex4.sofarapi;

import ca.uvic.seng330.ex4.Observation;
import ca.uvic.seng330.ex4.WebApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.Map.entry;

public abstract class SofarApi <T> implements WebApi<T> {

  private static final String root = "https://api.sofarocean.com/marine-weather/v1/models";

  private static final String token = "4db0e95eed9a6f90f6737d693478bc";
  protected String error = "";

  protected static String NO_DATA_IN_RANGE = "No valid data is available for the queried date and location";
  protected static String NO_RESPONSE = "The server did not respond in the required timeframe";
  protected static String NO_LOCATION_GIVEN = "A query cannot happen without a location for the Observation";
  protected static String NO_OBSERVATION = "A query cannot be generated without a valid Observation";
  protected static String NO_INTERNET = "A valid connection to the server could not be created";


  protected static ObjectMapper mapper = new ObjectMapper();


  @Override
  public void query(Observation observation) {

    if (observation == null){
      error = NO_OBSERVATION;
      return;
    }

    if (observation.getCoordinates() == null){
      error = NO_LOCATION_GIVEN;
      return;
    }

    Map<String, String> params = Map.ofEntries(
            entry("token", token),
            entry("longitude",  "" + observation.getCoordinates().getLongitude()),
            entry("latitude",   "" + observation.getCoordinates().getLatitude()),
            entry("start", observation.getTime().format(DateTimeFormatter.ISO_DATE_TIME)),
            entry("end", observation.getTime().plusHours(4).format(DateTimeFormatter.ISO_DATE_TIME)),
            entry("variableIDs", getVariableId())
    );


    try {
      URL url = buildUrl(root + getUrlPath(), params);

      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setConnectTimeout(8000);

      int code = conn.getResponseCode();

      if (code == 200) {
        String response = collectResponseFromStream(url.openStream());

        JSONObject json = new JSONObject(response);
        JSONArray hindcastJson = json.getJSONArray("hindcastVariables");

        onReceive(hindcastJson.toString());
      } else {
        String response = collectResponseFromStream(conn.getErrorStream());
        onError(response, code);
      }
    } catch (IOException e){
      error = NO_RESPONSE;
    } catch (URISyntaxException e) {
      error = NO_INTERNET;
    }
  }


  @Override
  public void onError(String dataReceived, int code) {
    SofarError e = null;
    try {
      e = mapper.readValue(dataReceived, SofarError.class);
      error = e.message;
    } catch (JsonProcessingException ex) {
      error = "Server responded with error code " + code;
    }
  }

  @Override
  public String getError() {
    return this.error;
  }

  private static URL buildUrl(String pURL, Map<String, String> params) throws URISyntaxException, IOException {
    URIBuilder uri = new URIBuilder(pURL);
    params.forEach(uri::addParameter);
    return uri.build().toURL();
  }

  private String collectResponseFromStream(InputStream responseStream) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(responseStream));

    StringBuilder response = new StringBuilder();
    String inputLine;
    while ((inputLine = in.readLine()) != null)
      response.append(inputLine);
    in.close();
    return response.toString();
  }

  /**
   * The list of available IDs is https://docs.sofarocean.com/marine-weather
   * @return The Sofar Api variable ID can be a comma separated list with no spaces
   */
  protected abstract String getVariableId();

  /**
   * The list is available at https://docs.sofarocean.com/marine-weather
   * @return The path that matches the given ID(s)
   */
  protected abstract String getUrlPath();

}
