package ca.uvic.seng330.ex9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FunctionalTest {

    List<Whale> whaleList;

    /**
     * Uses the ObservationData class to fetch the default data from the resources
     */
    @BeforeEach
    void setup(){
        WhaleData data = new WhaleData();
        whaleList = data.getWhales();
    }

    /**
     * Checks BiPredicate by testing that the list after BiPredicateSort matches the expected array
     */
    @Test
    void TestBiPredicate() {
        int[] sortedWeights = {400, 2700, 2900, 3000, 26000, 27400, 27400, 29000, 29500, 31500};
        Functional.BiPredicateSort(whaleList);

        for(int i = 0; i < whaleList.size(); i++) {
            Assertions.assertEquals(sortedWeights[i], whaleList.get(i).getSize());
        }
    }

    /**
     * Checks FlatMap by testing that the it returns the expected count
     */
    @Test
    void TestFlatMap() {
        int before = whaleList.size();
        int after = Functional.FlatMap(whaleList, 2007, 06, 01);
        Assertions.assertNotEquals(before, after);
        Assertions.assertEquals(4, after);
    }

    /**
     * Checks Map and Reduce by comparing with the correct sum and max
     */
    @Test
    void TestMapAndReduce() {
        double[] result = Functional.MapAndReduce(whaleList);
        double sum = result[0];
        Assertions.assertEquals(395560, (int)sum);

        double max = result[1];
        Assertions.assertEquals(69300, (int)max);
    }

    /**
     * Checks RemoveOrca by testing the size of the list and the expected contents of the list
     */
    @Test
    public void TestRemoveOrca(){
        ArrayList<Whale> whales = new ArrayList<>();
        whales.add(new Whale(LocalDate.now(), "grey", 400, 49095312, Whale.Gender.FEMALE));
        whales.add(new Whale(LocalDate.now(), "orca", 401, 49095312, Whale.Gender.FEMALE));
        whales.add(new Whale(LocalDate.now(), "humpback", 402, 49095312, Whale.Gender.FEMALE));
        whales.add(new Whale(LocalDate.now(), "grey", 403, 49095312, Whale.Gender.FEMALE));
        whales.add(new Whale(LocalDate.now(), "orca", 404, 49095312, Whale.Gender.FEMALE));

        Assertions.assertEquals(5, whales.size());
        Functional.RemoveOrca(whales);
        Assertions.assertEquals(3, whales.size());
        Assertions.assertEquals("grey", whales.get(0).getSpecies());
        Assertions.assertEquals("humpback", whales.get(1).getSpecies());
        Assertions.assertEquals("grey", whales.get(2).getSpecies());
    }

}
