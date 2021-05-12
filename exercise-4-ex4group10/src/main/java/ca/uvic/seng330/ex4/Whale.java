package ca.uvic.seng330.ex4;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Whale {

  /**
   * Storing a static counter allows for new whales to never have a conflict
   * on their id. When a whale is copied, it retains the same id.
   */
  private static int whaleCount = 0;

  private final int id;
  private String name;
  private int age;
  private boolean alive;
  private Sex sex = Sex.UNKNOWN;
  private Species species = Species.UNKNOWN;

  /**
   * Constructor
   */
  public Whale() {
    this.id = whaleCount;
    whaleCount++;
  }

  /**
   * New Constructor
   */
  public Whale(String name, Species species, int age){
    this.id = whaleCount;
    whaleCount++;
    this.name = name;
    this.species = species;
    this.age = age;
  }

  /**
   * Copy Constructor
   *
   * @pre Whale that is not null
   * @post A new Whale instance that is equal to the original
   */
  public Whale(Whale that) {
    assert that != null;
    this.id = that.id;
    this.name = that.name;
    this.age = that.age;
    this.alive = that.alive;
    this.sex = that.sex;
    this.species = that.species;
  }

  /**
   * Getters
   */
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  /**
   * Setters
   */

  public void setName(String name) {
    this.name = Objects.requireNonNullElse(name, "");
  }

  public int getAge() {
    return age;
  }

  /**
   * @param age The whale's age
   * @pre age >= 0
   */
  public void setAge(int age) {
    assert age >= 0;
    this.age = age;
  }

  public boolean isAlive() {
    return alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public Species getSpecies() {
    return species;
  }

  public void setSpecies(Species species) {
    this.species = Objects.requireNonNullElse(species, Species.UNKNOWN);
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = Objects.requireNonNullElse(sex, Sex.UNKNOWN);
  }

  /**
   * Overrides
   */


  @Override
  public String toString() {
    String str = "";
    str += "\t whale_id:" + id;
    str += "\t whale_name:" + name;
    str += "\t whale_age:" + age;
    str += "\t whale_is_alive:" + alive;
    str += "\t whale_sex:" + sex;
    str += "\t whale_species:" + species;

    return (str);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Whale) {
      return ((Whale) obj).id == this.id;
    }
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }


  /**
   * Enums
   */

  /**
   * The species of whales that can be seen in Canada.
   * Based on https://wildwhales.org/speciesid/whales/
   */
  public enum Species {
    ORCA,
    HUMPBACK,
    GREY,
    MINKE,
    RIGHT,
    SPERM,
    BEAKED,
    BLUE,
    BELUGA,
    FIN,
    SEI,
    UNKNOWN
  }

  /**
   * The sex of a whale. It may be unknown if it cannot be identified
   */
  public enum Sex {
    MALE,
    FEMALE,
    UNKNOWN
  }


  /**
   *   Converts a list of Whale objects to a list of Whale names
   *    Uses the java.util.function.Function
   *
   *    @param whaleList    A list of whales to convert
   *    @return - A list of Whale names
   */
  public static List<String> convertWhaleListToNamesList(List<Whale> whaleList){
    return whaleList.stream().map(Whale::getName).collect(Collectors.toList());
  }


  //Compare Whales by their species.
  public static class NameComparator implements Comparator<Whale> {
    @Override
    public int compare(Whale w1, Whale w2) {
      return w1.getName().compareTo(w2.getName());
    }
  }

  //Sort Whales by their Species
  public static class SpeciesComparator implements Comparator<Whale> {
    @Override
    public int compare(Whale w1, Whale w2) {
      return w1.getSpecies().compareTo(w2.getSpecies());
    }
  }

  //Compare Whales based on age, youngest first
  public static class AgeComparator implements Comparator<Whale>{
    @Override
    public int compare(Whale w1, Whale w2)
    {
      return w1.getAge() - w2.getAge();
    }
  }

}


