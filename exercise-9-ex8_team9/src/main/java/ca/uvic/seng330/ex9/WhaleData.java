package ca.uvic.seng330.ex9;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

public class WhaleData {
    ArrayList<Whale> whales;

    public WhaleData() {

        whales = new ArrayList<>();

        InputStream inputStream = WhaleData.class.getClassLoader().getResourceAsStream("observations.csv");
        try {
            //see https://stackabuse.com/reading-and-writing-csvs-in-java-with-apache-commons-csv/
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new InputStreamReader(inputStream));
            for (CSVRecord record : csvParser) {

                String dateS = record.get("date");
                String speciesS = record.get("species");
                String est_sizeS = record.get("est_size");
                String gridrefS = record.get("gridref");
                String genderS = record.get("gender");

                //Convert values and check for invalid data
                int gridref = Integer.parseInt(gridrefS);
                int est_size = Integer.parseInt((est_sizeS));

                if (est_size < 0 || est_size > 1000000){
                    throw new IllegalArgumentException();
                }

                //Check for valid data in genderS. Assign enum value. Throw exception for bad data
                Whale.Gender gender;
                if (genderS.toUpperCase().equals("M") || genderS.toUpperCase().equals("MALE"))
                    gender = Whale.Gender.MALE;
                else if(genderS.toUpperCase().equals("F") || genderS.toUpperCase().equals("FEMALE"))
                    gender = Whale.Gender.FEMALE;
                else
                    throw new IllegalArgumentException();
                
                // Convert date to LocalDate. Be mindful many edge cases exist and in practice, we cannot trust the data.
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
                LocalDate date = LocalDate.parse(dateS,formatter);

                //Store the records in an ArrayList
                whales.add(new Whale(date,speciesS,est_size,gridref,gender));
            }
        } catch (IOException | IllegalArgumentException | DateTimeParseException e){
            e.printStackTrace();
        }
    }

    /**
     * returns the ArrayList of observations read from the CSV file.
     * Makes a copy first for encapsulation
     * @return copy of observations ArrayList
     */
    public ArrayList<Whale> getWhales(){
        ArrayList<Whale> obsCopy = new ArrayList<>();
        for (Whale whale : whales){
            obsCopy.add(new Whale(whale));
        }
        return obsCopy;
    }
}


