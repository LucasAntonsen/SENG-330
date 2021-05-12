package ca.uvic.seng330.ex4;

import ca.uvic.seng330.ex4.sofarapi.WaveData;
import ca.uvic.seng330.ex4.sofarapi.WindData;
import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl. NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.stream.Collectors;

import static net.andreinc.mockneat.unit.user.Emails.emails;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ObservationTest {
  Observation observation;
  Faker faker = new Faker();
  SightingState sightingstate;
  WaveData wave;
  WindData wind;


  @BeforeEach
  void setup() {
    //Use faster mocked APIs in unit tests
    Injector injector = Guice.createInjector(new MockedDependencies());
    Reporter r = new Reporter(faker.idNumber().hashCode(), faker.name().fullName(), faker.phoneNumber().cellPhone(), emails().domain("uvic.ca").get(), faker.address().fullAddress());

    observation = new Observation(LocalDateTime.now(), r);
    injector.getMembersInjector(Observation.class).injectMembers(observation);

    wave = new WaveData("N", "m/s", 14, 12, "January 12/20");
    sightingstate = new SightingState("Bridge", 40, wave, wind);
  }

  @Test
  void TestGetterSetter() {
    PojoClass pojoclass = PojoClassFactory.getPojoClass(Observation.class);
    Validator validator = ValidatorBuilder
            .create()
            .with(new  NoPublicFieldsRule())
            .with(new SetterTester())
            .with(new GetterTester())
            .build();
      validator.validate(pojoclass);
  }

  @Test
  void VerifyImmutable() {
    Whale w1 = new Whale();
    w1.setSex(Whale.Sex.MALE);
    observation.addWhaleToPod(w1);
    w1.setSex(Whale.Sex.FEMALE);
    List<Whale> whales = observation.getPod();

    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      whales.add(new Whale());
    });

    Whale w2 = new Whale();
    ArrayList<Whale> moreWhales = new ArrayList<>();
    moreWhales.add(new Whale());
    moreWhales.add(w2);

    observation.addWhalesToPod(moreWhales);
    moreWhales.clear();
    w2.setSex(Whale.Sex.FEMALE);
    Assertions.assertEquals(3, observation.getPod().size());

    Assertions.assertEquals(0, whales.stream().filter((whale -> {
      return whale.getSex() == Whale.Sex.FEMALE;
    })).count());


  }

  @Test
  void TestSortObservationByDate(){
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    Observation o2 = new Observation(LocalDate.of(2001, Month.AUGUST, 21).atStartOfDay(), mock(Reporter.class));
    Observation o3 = new Observation(LocalDate.of(2005, Month.JANUARY, 1).atStartOfDay(), mock(Reporter.class));

    List<Observation> obsList = new ArrayList<Observation>();
    obsList.add(o1);
    obsList.add(o3);
    obsList.add(o2);

    Collections.sort(obsList);
    Assertions.assertEquals(o1, obsList.get(0));
    Assertions.assertEquals(o2, obsList.get(1));
    Assertions.assertEquals(o3, obsList.get(2));

  }

  @Test
  void TestSortByReporter() {
    Faker faker = new Faker();
    Reporter r1 = new Reporter(faker.idNumber().hashCode(), "Bill Bird",
            faker.phoneNumber().cellPhone(),
            emails().domain("gmail.com").get(), faker.address().fullAddress());
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), r1);
    Reporter r2 = new Reporter(faker.idNumber().hashCode(), "Niel Ernst",
            faker.phoneNumber().cellPhone(),
            emails().domain("gmail.com").get(), faker.address().fullAddress());
    Observation o2 = new Observation(LocalDate.of(2001, Month.AUGUST, 21).atStartOfDay(), r2);


    Reporter r3 = new Reporter(faker.idNumber().hashCode(), "Kwang Moo Yi",
            faker.phoneNumber().cellPhone(),
            emails().domain("gmail.com").get(), faker.address().fullAddress());
    Observation o3 = new Observation(LocalDate.of(2005, Month.JANUARY, 1).atStartOfDay(), r3);


    List<Observation> obsList = new ArrayList<Observation>();
    obsList.add(o3);
    obsList.add(o2);
    obsList.add(o1);

    Collections.sort(obsList, Comparator.comparing(Observation::getObserver));

    Assertions.assertEquals(o1, obsList.get(0));
    Assertions.assertEquals(o2, obsList.get(2));

  }


  /**
   * Sorts the collection by comparing latitude
   * */
  @Test
  void TestSortObservationByDistance() {
      Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
      Observation o2 = new Observation(LocalDate.of(2001, Month.AUGUST, 21).atStartOfDay(), mock(Reporter.class));
      Observation o3 = new Observation(LocalDate.of(2005, Month.JANUARY, 1).atStartOfDay(), mock(Reporter.class));


      List<Observation> obsList = new ArrayList<Observation>();
      obsList.add(o1);
      obsList.add(o2);
      obsList.add(o3);

      o1.setCoordinates(new Coordinates(22.43, 80.1));
      o2.setCoordinates( new Coordinates(54.43, -67.1));
      o3.setCoordinates(new Coordinates(22.9, 79.41));

      Coordinates.setOrigin(new Coordinates());

      Observation.sortListByLocation(obsList);

      Assertions.assertEquals(o1, obsList.get(0));
      Assertions.assertEquals(o3, obsList.get(1));
      Assertions.assertEquals(o2, obsList.get(2));
  }
  @Test
  void TestSearchListByCoordinate() {
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    Observation o2 = new Observation(LocalDate.of(2001, Month.AUGUST, 21).atStartOfDay(), mock(Reporter.class));
    Observation o3 = new Observation(LocalDate.of(2005, Month.JANUARY, 1).atStartOfDay(), mock(Reporter.class));


    List<Observation> obsList = new ArrayList<Observation>();
    obsList.add(o1);
    obsList.add(o2);
    obsList.add(o3);

    o1.setCoordinates(new Coordinates(22.43, 80.1));
    o2.setCoordinates( new Coordinates(54.43, -67.1));
    o3.setCoordinates(new Coordinates(22.9, 79.41));

    Coordinates.setOrigin(new Coordinates());

    Assertions.assertEquals(Optional.of(o1), Observation.searchListByCoordinate(obsList,o1.getCoordinates()));
    Assertions.assertEquals(Optional.of(o2), Observation.searchListByCoordinate(obsList,o2.getCoordinates()));
    Assertions.assertEquals(Optional.of(o3), Observation.searchListByCoordinate(obsList,o3.getCoordinates()));
    Assertions.assertEquals(Optional.empty(), Observation.searchListByCoordinate(obsList, new Coordinates(2453,4253)));

  }

  @Test
  void TestSearchObservationsByDate(){
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    Observation o2 = new Observation(LocalDate.of(2001, Month.AUGUST, 21).atStartOfDay(), mock(Reporter.class));
    Observation o3 = new Observation(LocalDate.of(2005, Month.JANUARY, 1).atStartOfDay(), mock(Reporter.class));

    List<Observation> obsList = new ArrayList<Observation>();
    obsList.add(o1);
    obsList.add(o2);
    obsList.add(o3);

    Collections.sort(obsList);

    Observation seek = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));

    int index = Collections.binarySearch(obsList, seek);

    Assertions.assertEquals(0, index);
  }

  @Test
  void TestSearchObservationsByDateNotFound(){
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    Observation o2 = new Observation(LocalDate.of(2001, Month.AUGUST, 21).atStartOfDay(), mock(Reporter.class));
    Observation o3 = new Observation(LocalDate.of(2005, Month.JANUARY, 1).atStartOfDay(), mock(Reporter.class));

    List<Observation> obsList = new ArrayList<Observation>();
    obsList.add(o1);
    obsList.add(o2);
    obsList.add(o3);

    Collections.sort(obsList);

    Observation seek = new Observation(LocalDate.of(2020, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));

    int index = Collections.binarySearch(obsList, seek);

    Assertions.assertTrue(index<0);
  }

  @Test
  void TestFilterByRadius(){
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    Observation o2 = new Observation(LocalDate.of(2001, Month.AUGUST, 21).atStartOfDay(), mock(Reporter.class));
    Observation o3 = new Observation(LocalDate.of(2005, Month.JANUARY, 1).atStartOfDay(), mock(Reporter.class));

    o1.setCoordinates(new Coordinates(22.43, 80.1));
    o2.setCoordinates( new Coordinates(54.43, -67.1));
    o3.setCoordinates(new Coordinates(22.9, 79.41));

    Coordinates.setOrigin(new Coordinates(23, 80));

    List<Observation> obsList = new ArrayList<Observation>();
    obsList.add(o1);
    obsList.add(o2);
    obsList.add(o3);

    List<Observation> closeToTarget = obsList.stream().filter((observation1 -> observation1.getCoordinates().distanceFromOrigin() < 100)).collect(Collectors.toList());
    Assertions.assertEquals(2, closeToTarget.size());

  }

  @Test
  void TestAddWhalesToPod(){
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    Whale w1 = new Whale();
    Whale w2 = new Whale();
    ArrayList<Whale> ws = new ArrayList<Whale>();
    ws.add(w1);
    ws.add(w2);
    o1.addWhalesToPod(ws);
    Assertions.assertEquals(2, o1.getPod().size());
  }

  @Test
  void TestGenerateSightingStateNulls(){
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    o1.windApi = null;
    o1.waveApi = null;
    Assertions.assertFalse(o1.generateSightingState(100, "pier"));
  }

  @Test
  void TestSearchListByCoordinateNulls(){
    ArrayList<Observation> list = null;
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    o1.setCoordinates(new Coordinates(22.43, 80.1));
    Assertions.assertEquals(Optional.empty(), Observation.searchListByCoordinate(list, o1.getCoordinates()));
  }

  @Test
  void TestInternalTimeValue() throws NoSuchFieldException, IllegalAccessException {
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    Field privateStringField = Observation.class.getDeclaredField("time");
    privateStringField.setAccessible(true);
    LocalDateTime timevalue = (LocalDateTime) privateStringField.get(o1);
    Assertions.assertEquals(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), timevalue);
  }

  @Test
  void TestPrivateNoteValue() throws NoSuchFieldException, IllegalAccessException {
    Observation o1 = new Observation(LocalDate.of(2000, Month.AUGUST, 20).atStartOfDay(), mock(Reporter.class));
    o1.setNote("I am a gamer");
    Field privateStringField = Observation.class.getDeclaredField("note");
    privateStringField.setAccessible(true);
    String note = (String) privateStringField.get(o1);
    Assertions.assertEquals("I am a gamer", note);
  }

  //SightingState tests

  @Test
  void TestSightingState(){
    //Test get methods
    Assertions.assertEquals(40, sightingstate.getSightingDistance());
    Assertions.assertEquals("Bridge", sightingstate.getSightingPlatform());
    Assertions.assertNotNull(sightingstate.getWaveState());
    Assertions.assertNotNull(sightingstate.getWindState());

    //Test setSightingState
    sightingstate.setSightingPlatform("The Dock");
    Assertions.assertEquals("The Dock", sightingstate.getSightingPlatform());

    //Test toString
    Assertions.assertEquals(("\t windState:null\t waves: waveHeightUnit: N\twaveDirectionUnit: m/s\twaveHeight: 14.0" +
                    "\twaveDirection: 12.0\ttimestamp: January 12/20\t sightingDistance:40\t sightingPlatform:The Dock"),
            sightingstate.toString());
  }

}
