package ca.uvic.seng330.ex9;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Functional {

    /**
     * Problem #1 from the requirements in the README
     *
     * @param whaleList - list of whale observations
     * @param criteria - a given whale species. Orca for the exercise purposes, but can be extended to other whale species
     *
     * This is used in the first two subtasks of Problem 1 regarding the anonymous class and the lambda expression.
     * For the final subtask of Problem 1 see orcaFilter() and RemoveOrca() below
     */
    public static void RemoveElementIf(List<Whale> whaleList, String criteria){
        whaleList.removeIf(o -> o.getSpecies().equals(criteria));
    }

    /**
     * Checks if whale species is orca
     *
     * @param o - whale object
     * @return comparison of whale object's species to orca
     */
    public static boolean orcaFilter(Whale o)
    { return o.getSpecies().equals("orca"); }

    /**
     * Removes all the orca whales from the list
     *
     * @param whaleList - list of whale observations
     */
    public static void RemoveOrca(List<Whale> whaleList){
        whaleList.removeIf(Functional::orcaFilter);
    }

    /**
     * Sorts the list of whales by weight
     *
     * @param whaleList - list of whale observations
     */
    public static void BiPredicateSort(List<Whale> whaleList){
        Collections.sort(whaleList, new Whale.WeightComparator());
    }

    /**
     * Counts all the whales found before the given date
     *
     * @param whaleList - list of whale observations
     * @param year - The year for the date that the flatmap will filter on
     * @param month - The month for the date that the flatmap will filter on
     * @param day - The day for the date that the flatmap will filter on
     * @return filterList - The filtered list from the FlatMap operation
     */

    public static int FlatMap(List<Whale> whaleList, int year, int month, int day) {
        List<Whale> filterList = Stream.of(whaleList) // Stream of List<Whale>
                .flatMap(List::stream)
                .filter(w -> w.getDate().isBefore(LocalDate.of(year, month, day)) )
                .collect(Collectors.toList());
        return filterList.size();
    }

    /**
     * Sums the whale sizes and finds the max size
     *
     * @param whaleList - list of whale observations
     * @return result - An array of double of length two that contains the sum and max
     */
    public static double[] MapAndReduce(List<Whale> whaleList) {
        Double sum = whaleList.stream().map(w -> w.getSize()*2.2).reduce(Double.valueOf(0), Double::sum);
        Optional<Double> max = whaleList.stream().map(w -> w.getSize()*2.2).reduce(Double::max);
        double[] result = {sum, max.get()};
        return result;
    }
    
}
