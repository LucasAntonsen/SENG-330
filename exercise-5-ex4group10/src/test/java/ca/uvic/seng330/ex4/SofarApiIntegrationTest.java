package ca.uvic.seng330.ex4;

import ca.uvic.seng330.ex4.sofarapi.WaveApi;
import ca.uvic.seng330.ex4.sofarapi.WaveData;
import ca.uvic.seng330.ex4.sofarapi.WindApi;
import ca.uvic.seng330.ex4.sofarapi.WindData;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SofarApiIntegrationTest {

    WebApi<WindData> windDataWebApi;
    Observation obs;

    WebApi<WaveData> waveDataWebApi;

    @BeforeEach
    void init(){

        //In integration tests we want to use real dependencies
        Injector injector = Guice.createInjector(new ProductionDependencies());

        windDataWebApi = new WindApi();
        obs = new Observation(LocalDateTime.now().minusDays(1), mock(Reporter.class));
        injector.getMembersInjector(Observation.class).injectMembers(obs);
        waveDataWebApi = new WaveApi();
    }



    @Test
    void testNoCoordinate(){
        windDataWebApi.query(obs);
        assertThat("A query cannot happen without a location for the Observation")
                .isEqualTo(windDataWebApi.getError());
        assertThat(windDataWebApi.getData()).isEmpty();
    }

    @Test
    void testSofarApiValid(){
        obs.setCoordinates(new Coordinates(37.0001, -152.0001));
        windDataWebApi.query(obs);
        assertThat("").isEqualTo(windDataWebApi.getError());
        assertThat(windDataWebApi.getData()).isNotEmpty();
    }

    @Test
    void testSofarApiInvalidLongitude(){
        obs.setCoordinates(new Coordinates(-137.0001, -152.0001));
        windDataWebApi.query(obs);
        assertThat(windDataWebApi.getError()).contains("Latitude value must be between -90 and 90 degrees");
        assertThat(windDataWebApi.getData()).isEmpty();
    }

    @Test
    void testQueryWindSpeed(){
        obs.setCoordinates(new Coordinates(10.5, 155.3));
        windDataWebApi.query(obs);
        System.out.println(windDataWebApi.getData().toString());
        assertThat(windDataWebApi.getData()).isNotEmpty();
    }

    @Test
    void testWaveApi(){
        obs.setCoordinates(new Coordinates(10.5, 155.3));
        waveDataWebApi.query(obs);
        System.out.println(waveDataWebApi.getData().toString());
        assertThat(waveDataWebApi.getData()).isNotEmpty();
    }

    @Test
    void apiClearsData(){
        obs.setCoordinates(new Coordinates(10.5, 155.3));
        windDataWebApi.query(obs);
        assertThat(windDataWebApi.getData()).isNotEmpty();
        obs.setCoordinates(new Coordinates(-137.0001, -152.0001));
        windDataWebApi.query(obs);
        assertThat(windDataWebApi.getError()).contains("Latitude value must be between -90 and 90 degrees");
        assertThat(windDataWebApi.getData()).isEmpty();
    }

}
