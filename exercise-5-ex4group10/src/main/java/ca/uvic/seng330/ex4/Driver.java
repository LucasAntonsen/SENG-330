
package ca.uvic.seng330.ex4;


import com.github.javafaker.Faker;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static net.andreinc.mockneat.unit.user.Emails.emails;


public class Driver {
  /**
   * Generates Data to test our Whale Monitoring Code
   * Returns Iterator for generated Observation list
   */
  public static void GeneratedDataPopulate() {


    int amount = 3;


    //Generate reporter objects with "real" data in them
    List<Reporter> reporterList = genReporters(amount);
    System.out.println("reporterList Size:" + reporterList.size());


    //Construct Whales with "real" data
    int[] whaleAges = genRandInts(amount, 1, 100);
    int[] whaleSpecies = genRandInts(amount, 0, 10);
    List<Whale> whaleList = genWhales(amount, whaleAges, whaleSpecies);
    System.out.println("whaleList Size:" + whaleList.size());


    //Sort Whales by age
    System.out.println("Whales Before Sorting: \n" + whaleList);
    Collections.sort(whaleList, new Whale.AgeComparator());
    System.out.println("\nWhales After Sorting by age: \n" + whaleList);

    //Sort Whales by name
    Collections.sort(whaleList, new Whale.NameComparator());
    System.out.println("\nWhales After Sorting by name: \n" + whaleList);

    //Sort Whales by species
    Collections.sort(whaleList, new Whale.SpeciesComparator());
    System.out.println("\nWhales After Sorting by species: \n" + whaleList);


    //Now try to do a Binary Search for a specific whale. Data is randomly generated so a
    //24 year old whale may or may not be found
    Whale searchWhale = new Whale();
    int targetAge = 24;
    System.out.println("\nSearching for a whale with age of: " + targetAge);
    searchWhale.setAge(targetAge);
    int index = Collections.binarySearch(whaleList, searchWhale, new Whale.AgeComparator());
    System.out.println("index:"+ index);
    if(index >= 0)
      System.out.println(whaleList.get(index));
    else
      System.out.println("The whale with age of : "+targetAge+" was not found");


    //Search for a Whale by its species
    Whale.Species spec = Whale.Species.BEAKED;
    System.out.println("\nSearching for a whale with species of: " + spec);
    searchWhale.setSpecies(spec);
    int index2 = Collections.binarySearch(whaleList, searchWhale, new Whale.SpeciesComparator());
    System.out.println("index2:"+ index2);
    if(index2 >= 0)
      System.out.println(whaleList.get(index2));
    else
      System.out.println("The whale with species of : "+spec+" was not found");

    System.out.println("\nGetting a list of the whale names\n");
    List<String> whaleNameList=Whale.convertWhaleListToNamesList(whaleList);
    int previewAmount = 5;
    System.out.println("Printing the First "+previewAmount+" whale names:");
    whaleNameList.stream().limit(previewAmount).forEach(System.out::println);



    //Build Observations
    double[] windSpeeds = genRandDoubles(amount, 0, 250);
    double[] sightingDistances = genRandDoubles(amount, 0, 1000);
    List<SightingState> sightingStateList = genSightingStates(amount, windSpeeds, sightingDistances);

    double[] lats = genRandDoubles(amount, -90, 90);
    double[] longs = genRandDoubles(amount, -180, 80);
    Observation.Direction[] directionList = getHeadingDirection(amount);
    LocalDateTime[] randDates = genRandDates(amount);
    String[] notes = genRandStrings(amount);
    List<Observation> obsList = genObservations(amount, reporterList,
            whaleList, sightingStateList, lats, longs, randDates, notes, directionList);

    previewAmount = 10;

    //Sort Observations by their inherit ordering of Date.
    Collections.sort(obsList);
    List<Observation> previewList = obsList.stream().limit(previewAmount).collect(Collectors.toList());
    System.out.println("previewList.size():"+previewList.size());
    System.out.println("\nObservations After Sorting by the default which is date: \n" + previewList);

    //Use Iterator functionality to easily traverse through each Whale in the most recent observation.
    System.out.println("Whales in the pod on most recent observation");
    for(Whale w : previewList.get(previewList.size() - 1)){
      System.out.println(w);
    }



  }

  /**
   *  Generates Observations objects of the Observations class to test out Whale monitoring code.
   *  @param amount - The amount of Observations to generate
   *  @param reporters - List of reporters to use in the generated Observations
   *  @param whales - List of whales to use in the generated Observations
   *  @param sightingStates - List of SightingStates to use in the generated Observations
   *  @param lats - List of latitudes to use in the generated Observations
   *  @param longs - List of longitudes to use in the generated Observations
   *  @param longs - List of longitudes to use in the generated Observations
   *  @return -  List<Observation> - A list of Observations
   */
  private static List<Observation> genObservations(int amount, List<Reporter> reporters,
                                                   List<Whale> whales, List<SightingState> sightingStates,
                                                   double[] lats, double[] longs, LocalDateTime[] dates,
                                                   String[] notes, Observation.Direction[] directionList) {

    List<Observation> obsList = new ArrayList<Observation>();
    for (int i = 0; i < amount; i++) {
      LocalDateTime localDate = dates[i];
      Observation ob = new Observation(localDate, reporters.get(i));
      try {
        App.dependencyInjector.getMembersInjector(Observation.class).injectMembers(ob);
      } catch (Exception e){
        e.printStackTrace();
      }
      ob.setCoordinates(new Coordinates(lats[i], longs[i]));
      ob.setNote(notes[i]);
      ob.generateSightingState(sightingStates.get(i).getSightingDistance(), sightingStates.get(i).getSightingPlatform());
      ob.setHeadingDirection(directionList[i]);
      ob.addWhaleToPod(whales.get(i));
      obsList.add(ob);
    }
    return obsList;
  }

  /**
   *   Generates Reporter objects of the Reporter class to test out Whale monitoring code.
   *
   *  @param  amount    The amount of reporters to generate
   *  @return -  reporterList    A list of Reporter
   */
  private static List<Reporter> genReporters(int amount) {
    Faker faker = new Faker();
    List<Reporter> reporterList = new ArrayList<Reporter>();
    for (int i = 0; i < amount; i++) {
      Reporter r = new Reporter(faker.idNumber().hashCode(), faker.name().lastName(),
              faker.phoneNumber().cellPhone(),
              emails().domain("gmail.com").get(), faker.address().fullAddress());
      reporterList.add(r);
    }
    return reporterList;
  }

  /**
   *  Generates Whale objects of the Whale class to test out Whale monitoring code.
   *
   *  @param amount   The amount of whales to generate
   *  @param whaleAges  An array of ages
   *  @param whaleSpecies An array of whale species
   *  @return -  List<Whale> - A list of Whale
   */
  private static List<Whale> genWhales(int amount, int[] whaleAges, int[] whaleSpecies) {
    Faker faker = new Faker();
    List<Whale> whaleList = new ArrayList<Whale>();
    for (int i = 0; i < amount; i++) {
      Whale w = new Whale();
      w.setAge(whaleAges[i]);
      Random rand = new Random();
      int randomValue = rand.nextInt() % 5;
      Random rand2 = new Random();
      int randomValue2 = rand2.nextInt() % 2;
      w.setAlive(randomValue != 1);
      w.setName(faker.name().firstName());
      if (randomValue2 == 1) {
        w.setSex(Whale.Sex.MALE);
      } else {
        w.setSex(Whale.Sex.FEMALE);
      }
      w.setSpecies(Whale.Species.values()[whaleSpecies[i]]);
      whaleList.add(w);
    }
    return whaleList;
  }

  /**
   *  Generates SightingState objects of the SightingState class to test out Whale monitoring code.
   *
   *   @param amount  The amount of SightingStates to generate
   *   @param windSpeeds  A array of windspeeds to use in the generated SightingStates
   *   @param sightingDistances  A array of sightingDistances to use in the generated SightingStates
   *   @return -  ssList    A list of SightingState
   */
  private static List<SightingState> genSightingStates(int amount, double[] windSpeeds, double[] sightingDistances) {
    Faker faker = new Faker();
    List<SightingState> ssList = new ArrayList<SightingState>();
    for (int i = 0; i < amount; i++) {
      SightingState ss = new SightingState(faker.address().city(), (int)sightingDistances[i]);
      ssList.add(ss);
    }
    return ssList;
  }

  /**
   *  Generates an array of Observation.Direction to test out Whale monitoring code.
   *
   *  @param amount  The amount of Directions to generate
   *  @return -   directionList   An array of directions
   */
  private static Observation.Direction[] getHeadingDirection(int amount){
    Observation.Direction[] directionList = new Observation.Direction[amount];
    Random rand = new Random();
    for(int i = 0; i < amount; i++){
      directionList[i] = Observation.Direction.values()[rand.nextInt(8)];
    }
    return directionList;
  }

  /**
   *   Generates random dates
   *  @param amount   The amount of random dates to generate
   *  @return -  Time[] - An array of dates
   */
  private static LocalDateTime[] genRandDates(int amount) {
    LocalDateTime[] randDates = new LocalDateTime[amount];
    for (int i = 0; i < amount; i++) {
      Random rnd = new Random();
      randDates[i] = LocalDateTime.now().minusHours(rnd.nextInt(72));
    }
    return randDates;
  }



  /**
   *  Generates random strings
   *
   *  @param amount   The amount of random strings to generate
   *  @return -  randoms - An array of random strings
   */
  private static String[] genRandStrings(int amount) {
    String[] randoms = new String[amount];
    for (int i = 0; i < amount; i++) {
      byte[] array = new byte[7]; // length is bounded by 7
      new Random().nextBytes(array);
      String generatedString = new String(array, StandardCharsets.UTF_8);
      randoms[i] = generatedString;
    }
    return randoms;
  }

  /**
   *  Generates random Big Integers
   *
   *  @param amount   The amount of random BigIntegers to generate
   *  @param amount   The min BigInteger that can be generated
   *  @param amount   The max BigInteger that can be generated
   *  @return -  randoms - An array of random BigInteger
   */
  private static BigInteger[] genRandBigInts(int amount, BigInteger min, BigInteger max) {
    BigInteger[] randoms = new BigInteger[amount];
    for (int i = 0; i < amount; i++) {
      BigInteger random = min.add(BigInteger.valueOf((long) Math.random())).multiply(max.subtract(min));
      randoms[i] = random;
    }
    return randoms;
  }

  /**
   *  Generates random ints
   *
   *  @param amount   The amount of random int to generate
   *  @param amount   The min int that can be generated
   *  @param amount   The max int that can be generated
   *  @return -  randoms - An array of random int
   */
  private static int[] genRandInts(int amount, double min, double max) {
    int[] randoms = new int[amount];
    for (int i = 0; i < amount; i++) {
      int random = (int) (min + Math.random() * (max - min));
      randoms[i] = random;
    }
    return randoms;
  }

  /**
   *  Generates random Doubles
   *
   *  @param amount   The amount of random doubles to generate
   *  @param amount   The min double that can be generated
   *  @param amount   The max double that can be generated
   *  @return -  randoms - An array of random double
   */
  private static double[] genRandDoubles(int amount, double min, double max) {
    double[] randoms = new double[amount];
    for (int i = 0; i < amount; i++) {
      double random = min + Math.random() * (max - min);
      randoms[i] = random;
    }
    return randoms;
  }

}
