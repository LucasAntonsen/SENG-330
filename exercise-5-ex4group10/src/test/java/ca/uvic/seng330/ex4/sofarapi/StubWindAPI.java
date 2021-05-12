package ca.uvic.seng330.ex4.sofarapi;

import ca.uvic.seng330.ex4.Observation;
import ca.uvic.seng330.ex4.WebApi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;


public class StubWindAPI implements WebApi<WindData> {

    private Boolean checked=Boolean.FALSE; // Boolean to check if query method had been called
    private String time=""; // To mock the actual time recorded in the observation
    private ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

    public Optional<WindData> getData(){
        if(checked){ // To ensure that query method is not called after getData
            try  {
        //read in JSON file and map data to HashMap, to simulate API call with same data
        data.add(
            new ObjectMapper()
                .readValue(
                    Files.readString(
                        Paths.get(
                            "src\\test\\java\\ca\\uvic\\seng330\\ex4\\sofarapi\\fakeData.json"),
                        StandardCharsets.US_ASCII),
                    HashMap.class));
                data.get(0).put("value", 12.0560274124146);
                data.get(0).put("timestamp", "2020-10-25T23:00:00.000Z");
            }

            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            return Optional.of(new WindData("m/s",data));
        }
        return Optional.empty();
    }

    public void query(Observation observation){
        this.checked=Boolean.TRUE;
        this.time=observation.getTime().format(DateTimeFormatter.ISO_DATE_TIME); // So that mocked time matches the observation
        return;
    }

    public void onReceive(String dataReceived){
        return;
    }

    public void onError(String dataReceived, int code){
        return;
    }

    public String getError(){
        return "";
    }
}
