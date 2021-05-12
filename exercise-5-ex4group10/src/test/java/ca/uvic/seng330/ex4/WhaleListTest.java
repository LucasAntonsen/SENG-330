package ca.uvic.seng330.ex4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class WhaleListTest {

    private WhaleList test;
    private Whale whale1;
    private Whale whale2;
    private Whale whale3;
    private Whale whale4;

    @BeforeEach
    void before() {

        test = new WhaleList();
        whale1 = new Whale("Larry", Whale.Species.BELUGA, 2);
        whale2 = new Whale("Charles", Whale.Species.BLUE, 45);
        whale3 = new Whale("David", Whale.Species.FIN, 4000);
        whale4 = new Whale("Barkley", Whale.Species.GREY, 10101010);
    }

    @Test
    void whaleListEmpty() {
        assertTrue(test.getWhales().isEmpty());
    }

    @Test
    void whaleListNotEmpty() {
        test.add(whale1);
        assertFalse(test.getWhales().isEmpty());
    }

    @Test
    void whaleListCopy() {
        test.add(whale1);
        test.add(whale2);
        test.add(whale3);
        test.add(whale4);

        WhaleList copy = new WhaleList(test);
        assertEquals(test.getWhales(), copy.getWhales());
    }

    @Test
    void whaleListNameSort() {
        WhaleList sortedByName = new WhaleList();
        sortedByName.add(whale4);
        sortedByName.add(whale2);
        sortedByName.add(whale3);
        sortedByName.add(whale1);

        test.add(whale1);
        test.add(whale2);
        test.add(whale3);
        test.add(whale4);

        assertNotEquals(test.getWhales(), sortedByName.getWhales());

        test.sortBy(new Whale.NameComparator());
        assertEquals(test.getWhales(), sortedByName.getWhales());
    }

    @Test
    void whaleListAgeSort() {
        WhaleList sortedByAge = new WhaleList();
        sortedByAge.add(whale1);
        sortedByAge.add(whale2);
        sortedByAge.add(whale3);
        sortedByAge.add(whale4);

        test.add(whale2);
        test.add(whale1);
        test.add(whale4);
        test.add(whale3);

        assertNotEquals(test.getWhales(), sortedByAge.getWhales());

        test.sortBy(new Whale.AgeComparator());
        assertEquals(test.getWhales(), sortedByAge.getWhales());
    }

    @Test
    void whaleListRemove() {
        test.add(whale1);
        assertFalse(test.getWhales().isEmpty());

        test.remove(whale1);
        assertTrue(test.getWhales().isEmpty());
    }

    @Test
    void whaleListClear(){
        test.add(whale2);
        test.add(whale1);
        test.add(whale4);
        test.add(whale3);
        assertFalse(test.getWhales().isEmpty());

        test.clear();
        assertTrue(test.getWhales().isEmpty());
    }

}
