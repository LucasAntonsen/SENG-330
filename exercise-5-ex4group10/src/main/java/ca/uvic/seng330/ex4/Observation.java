package ca.uvic.seng330.ex4;

import ca.uvic.seng330.ex4.sofarapi.WaveData;
import ca.uvic.seng330.ex4.sofarapi.WindData;
import com.google.inject.Inject;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;

public class Observation implements Comparable<Observation>, Iterable<Whale> {
  private final LocalDateTime time;
  private final Reporter observer;
  private Coordinates coordinates;
  private WhaleList pod;
  private String note;
  private SightingState state;
  private Direction headingDirection;

  @Inject
  WebApi<WaveData> waveApi;

  @Inject
  WebApi<WindData> windApi;


  /**
   * Constructor: Create a new observation with now whale information.
   * @pre time != null and observer != null
   * @param time     The time when the whale(s) were observed
   * @param observer The user who observed the whale(s)
   */
  public Observation(LocalDateTime time, Reporter observer) {
    assert time != null;
    assert observer != null;
    this.time = time;
    this.observer = observer;
    this.pod = new WhaleList();
  }

  /**
   * A helper constructor for searchingObservations
   * @param coordinates
   */
  private Observation(Coordinates coordinates){
    assert coordinates !=null;

    this.coordinates=coordinates;
    this.time = null;
    this.observer = null;
    this.pod = new WhaleList();
  }

  /**
   Getters
   */
  public LocalDateTime getTime() {
    return time;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public List<Whale> getPod() {
    return Collections.unmodifiableList(pod.getWhales());
  }

  public String getNote() {
    return note;
  }

  public SightingState getState() {
    return state;
  }

  public Reporter getObserver() {
    return observer;
  }

  public Direction getHeadingDirection(){
    return headingDirection;
  }

  /**
   Setters
   */

  public void setCoordinates(Coordinates c){
    this.coordinates = c;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public void setHeadingDirection(Direction headingDirection){
    this.headingDirection = headingDirection;
  }

  /**
   Public functions
   */

  public void addWhaleToPod(final Whale whale) {
    this.pod.add(whale);
  }

  public void addWhalesToPod(Collection<? extends Whale> whales){
    pod.addAll(whales);
  }
  public void addWhalesToPod(Whale ... whale){
    pod.addAll(Arrays.asList(whale));
  }


  /**
   * Create the sighting state for the Observation.
   * In order to get web data setCoordinates must be called first
   * @param sightingDistance The distance between the Reporter and the Whale
   * @param sightingPlatform The location of the Reporter at the time of the Observation
   * @return true if web data could be obtained, false otherwise
   */
  public boolean generateSightingState( int sightingDistance, String sightingPlatform){

    if (windApi == null || waveApi == null){
      state = new SightingState(sightingPlatform, sightingDistance);
      return false;
    }

    windApi.query(this);
    waveApi.query(this);
    if (windApi.getData().isPresent() && waveApi.getData().isPresent()) {
      state = new SightingState(sightingPlatform, sightingDistance, waveApi.getData().get(), (WindData) windApi.getData().get());
      return true;
    }

    state = new SightingState(sightingPlatform, sightingDistance);
    return false;
  }

  /**
   Sorting / Searching Functions
   */


  public static void sortListByLocation(List<Observation> list){

    Collections.sort(list,new CompareByLocation());
  
  }

  /**
   * Once the list is sorted by distance from the point. The only case for equality is if the distance is 0.
   * This will be the first element off the list.
   * @param list The list to search
   * @param value the coordinate to find an observation at
   * @return the index of desired value
   */
  
  public static Optional<Observation> searchListByCoordinate(List<Observation> list, Coordinates value){

    if(list == null){
      System.err.println("Null arguments supplied");
      return Optional.empty();
    }

    sortListByLocation(list);
    int index  = Collections.binarySearch(list,new Observation(value),new CompareByLocation());

    
    try{
      return Optional.ofNullable(list.get(index));
    }catch(IndexOutOfBoundsException e){
      return Optional.empty();
    }

  }
  /*
   Comparators
  */

   static class CompareByLocation implements Comparator<Observation>{

    public int compare(Observation comp1,Observation comp2){

      return comp1.getCoordinates().compareTo(comp2.getCoordinates());
    
    }
    
   }
  /**
   Overrides
   */

  @Override
  public String toString() {
    String str = "";
    str += "\t latitude:" + coordinates.getLatitude();
    str += "\t longitude:" + coordinates.getLongitude();
    str += "\t time:" + time;
    str += "\t headingDirection:" +  headingDirection;

    for (Whale whale : pod) {
      str += "\n" + whale.toString();
    }

    if(state != null){
      str += "\n\nstate:" + state.toString();
    }
    str += "\n\nobserver:" + observer.toString();

    return (str);
  }

  /**
   * The natural sorting of Observations is by their date recorded
   * @param o The Observation to compare.
   * @return The comparison of the internal dates.
   */
  @Override
  public int compareTo(Observation o) {
    return time.compareTo(o.time);
  }

  @Override
  public Iterator<Whale> iterator() {
    return pod.iterator();
  }

  @Override
  public void forEach(Consumer<? super Whale> action) {
    pod.forEach(action);
  }

  @Override
  public Spliterator<Whale> spliterator() {
    return pod.spliterator();
  }


  /**
   * Direction of the pod.
   */
  public enum Direction {
    NORTH,
    WEST,
    SOUTH,
    EAST,
    NORTHWEST,
    NORTHEAST,
    SOUTHWEST,
    SOUTHEAST,
  }

}
