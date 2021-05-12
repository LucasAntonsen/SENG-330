package ca.uvic.seng330.ex9;

import ca.uvic.seng330.ex9.generate.GenerateData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BenchmarkTest {


    List<Whale> whaleList;
    String filename;
    int numOfRows;
    int expDuration;

    /**
     * Generates the large data file that will be used to do Benchmark testing on the code.
     * Calls the GenerateData class function GenData.
     * numOfRows and filename can be modified
     * any csv file with large in the name will not be push to Github
     */
    @BeforeEach
    void setup(){
        numOfRows = 1000000;
        expDuration = numOfRows/100; // The acceptable length of time for each test in ms.
        filename = "largeGeneratedData.csv";
        final long startTime = System.currentTimeMillis();
        GenerateData gd = new GenerateData();
        if (!gd.doesFileExist( filename)) gd.GenData(filename, numOfRows);
        whaleList = gd.FetchData(filename);
        final long endTime = System.currentTimeMillis();
        final long duration = endTime-startTime;
    }

    /**
     * Tests the BiPredicate code on a large amount of data.
     * By testing the BiPredicate with one million rows of data,
     * the code can see how the Java functional programming is performing
     */
    @Test
    void TestBiPredicate() {
        final long startTime = System.currentTimeMillis();
        Functional.BiPredicateSort(whaleList);
        final long endTime = System.currentTimeMillis();
        final long duration = endTime-startTime;
        Assertions.assertTrue((int)duration<expDuration);
    }


    /**
     * Tests the FlatMap code on a large amount of data.
     * By testing the FlatMap with one million rows of data,
     * the code can see how the Java functional programming is performing
     */
    @Test
    void TestFlatMap() {
        final long startTime = System.currentTimeMillis();
        Functional.FlatMap(whaleList, 2007, 06, 01);
        final long endTime = System.currentTimeMillis();
        final long duration = endTime-startTime;
        Assertions.assertTrue((int)duration<expDuration);
    }


    /**
     * Tests the Map and Reduce code on a large amount of data.
     * By testing the Map and Reduce with one million rows of data,
     * the code can see how the Java functional programming is performing
     */
    @Test
    void TestMapAndReduce() {
        final long startTime = System.currentTimeMillis();
        double[] result = Functional.MapAndReduce(whaleList);
        Assertions.assertTrue(result.length==2);
        final long endTime = System.currentTimeMillis();
        final long duration = endTime-startTime;
        Assertions.assertTrue((int)duration<expDuration);
    }
}