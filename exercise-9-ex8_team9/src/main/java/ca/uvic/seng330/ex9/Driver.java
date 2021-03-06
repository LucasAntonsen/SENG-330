/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ca.uvic.seng330.ex9;

import java.util.ArrayList;

public class Driver {

  public static void main(String[] args) {
    WhaleData data = new WhaleData();
    ArrayList<Whale> whaleList = data.getWhales();

      //Sort Whales by weight
      System.out.println("Whales Before Sorting: \n" + whaleList);
      Functional.BiPredicateSort(whaleList);
      System.out.println("\nWhales After Sorting: \n" + whaleList);

      // Count number of whale sightings before given date
      int num = Functional.FlatMap(whaleList, 2007, 06, 01);
      System.out.println("\nNumber of whale sightings before 2007-06-01: \n" + num);


      //Get average and max value of whale weights
      double[] result = Functional.MapAndReduce(whaleList);
      double sum = result[0];
      System.out.println("\nsum: \n" + sum);
      double max = result[1];
      System.out.println("max: \n" + max);

      //Remove orca whales from list
      System.out.println("\nobservationList Before Remove If: \n" + whaleList);
      Functional.RemoveOrca(whaleList);
      System.out.println("\nobservationList After Remove If: \n" + whaleList);
  }
}