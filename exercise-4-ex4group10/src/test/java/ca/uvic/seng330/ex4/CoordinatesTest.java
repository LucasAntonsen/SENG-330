package ca.uvic.seng330.ex4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CoordinatesTest {
  Coordinates c;

  @BeforeEach
  void init(){
    c = new Coordinates();
  }

  @Test
  void TestDistance(){
    Coordinates.setOrigin(new Coordinates(22.0, 80.0));
    c.setLatitude(30);
    c.setLongitude(85);
    Assertions.assertEquals(1019, c.distanceFromOrigin(), 1);
  }
}
