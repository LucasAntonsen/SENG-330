package ca.uvic.seng330.ex8.observation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The observable object.
 */
public class ObservationData {
  private ArrayList<Observer> observers = new ArrayList<>();
  private Map<WhaleSpecies, Integer> whaleCount = new HashMap<>();
  private ArrayList<Observation> observations = new ArrayList<>();

  public ArrayList<Observer> getObservers() {
    return observers;
  }
  public void setObservers(ArrayList<Observer> observers) {
    this.observers = observers;
  }
  public ArrayList<Observation> getObservations() {
    return observations;
  }
  public void setObservations(ArrayList<Observation> obs) {
     this.observations = obs;
  }
  public Map<WhaleSpecies, Integer> getWhaleCount() {
    return whaleCount;
  }
  public void setWhaleCount(Map<WhaleSpecies, Integer> whaleCount) {
    this.whaleCount = whaleCount;
  }

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  /**
   * @param species - the species. Null returns the number of all whales seen.
   */
  public int getWhaleSightings( WhaleSpecies species ){
    if ( species == null ) {
      int count = 0;
      for ( WhaleSpecies s : WhaleSpecies.values() ){
        count += this.whaleCount.get( s );
      }
      return count;
    }
    return this.whaleCount.get( species );
  }

}