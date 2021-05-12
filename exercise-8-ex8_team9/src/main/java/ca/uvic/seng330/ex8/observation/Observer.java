package ca.uvic.seng330.ex8.observation;

import java.util.Map;

/**
 * Abstract observer role for the model.
 */
interface Observer {
  void newObservation(Map<WhaleSpecies, Integer> obMap);
}

