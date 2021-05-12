package ca.uvic.seng330.ex4.sofarapi;

import ca.uvic.seng330.ex4.*;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class StubWaveAPI implements WebApi<WaveData> {

    private Boolean checked=Boolean.FALSE; // Boolean to check if query method had been called
    private String time=""; // To mock the actual time recorded in the observation

    public Optional<WaveData> getData(){
        if(checked) // To ensure that query method is not called after getData
            return Optional.of(new WaveData("m","degree",1.41999995708466,208.0,this.time));
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
