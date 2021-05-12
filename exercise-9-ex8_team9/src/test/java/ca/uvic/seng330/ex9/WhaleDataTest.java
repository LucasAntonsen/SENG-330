package ca.uvic.seng330.ex9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class WhaleDataTest {

    WhaleData data;

    @BeforeEach
    public void init() {
        data = new WhaleData();
    }

    /**
     * Checks that the number of whale observations in the observations.csv file is one less than the header
     */
    @Test
    public void TestNumWhales(){
        Path path = Paths.get("src/main/resources/observations.csv");
        long lines = 0;
        try {
            lines = Files.lines(path).parallel().count();
            ArrayList<Whale> list = data.getWhales();
            int numWhales = (int)lines - 1;
            Assertions.assertEquals(list.size(), numWhales);
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Checks that the number of headers in observations.csv is equal to the number of fields of the Whale class
     */
    @Test
    public void TestNumFields(){
        try {
            BufferedReader brTest = new BufferedReader(new FileReader("src/main/resources/observations.csv"));
            String text = brTest.readLine();
            String[] parameters = text.split(",");
            String fulluQulifiedName = "ca.uvic.seng330.ex9.Whale";
            Class c = Class.forName(fulluQulifiedName);
            Field[] fields = c.getDeclaredFields();
            Assertions.assertEquals(parameters.length, fields.length);
        } catch (IOException | ClassNotFoundException e) {
            fail();
        }
    }

    /**
     * Checks that the sum of all the whale sizes is the expected result
     */
    @Test
    public void TestExpectedSizeSum(){
        ArrayList<Whale> list = data.getWhales();
        int sum = 0;
        for (Whale obs : list){
            sum += obs.getSize();
        }
        Assertions.assertEquals(179800,sum);
    }
}
