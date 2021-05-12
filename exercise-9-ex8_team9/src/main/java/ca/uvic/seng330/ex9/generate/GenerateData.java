package ca.uvic.seng330.ex9.generate;

import ca.uvic.seng330.ex9.Whale;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateData {
    final static List<String> speciesList = Arrays.asList("orca", "humpback", "grey", "minke", "right",
            "sperm", "beaked", "blue", "beluga", "fin", "sei", "unknown");

    public GenerateData(){}

    /**
     * Fetches data from a generated file and returns an observation list.
     * Parses the data as needed with casting.
     *
     * @param inputFilename - The filename to fetch the data from and put into the Observation list.
     */
    public ArrayList<Whale> FetchData(String inputFilename) {
        ArrayList<Whale> whales = new ArrayList<>();
        String userDir = System.getProperty("user.dir");
        File baseDirectory = new File(userDir);
        File inputFile = new File(baseDirectory, inputFilename);
        try {
            //see https://stackabuse.com/reading-and-writing-csvs-in-java-with-apache-commons-csv/
            InputStream is = new FileInputStream(inputFile.getAbsolutePath());
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new InputStreamReader(is));
            for (CSVRecord record : csvParser) {
                String dateS = record.get("date");
                String speciesS = record.get("species");
                String est_sizeS = record.get("est_size");
                String gridrefS = record.get("gridref");
                String genderS = record.get("gender");
                //TODO now convert these
                int gridref = Integer.parseInt(gridrefS);
                int est_size = Integer.parseInt((est_sizeS));
                Whale.Gender gender;
                if (genderS.toUpperCase().equals("M") || genderS.toUpperCase().equals("MALE"))
                    gender = Whale.Gender.MALE;
                else if(genderS.toUpperCase().equals("F") || genderS.toUpperCase().equals("FEMALE"))
                    gender = Whale.Gender.FEMALE;
                else
                    throw new IllegalArgumentException();

                // TODO remember, we can't assume these strings are safe to convert
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ENGLISH);
                LocalDate date = LocalDate.parse(dateS,formatter);
                //TODO parse the remaining and stick in a data structure amenable to Streaming
                whales.add(new Whale(date,speciesS,est_size,gridref,gender));
            }
        } catch (IOException | DateTimeParseException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return whales;
    }

    /**
     * Checks if the file exists.
     *
     * @param filename - The filename to locate in the user directory.
     */
    public boolean doesFileExist( String filename ){
        String userDir = System.getProperty("user.dir");
        File baseDirectory = new File(userDir);
        File inputFile = new File(baseDirectory, filename);
        return inputFile.exists();
    }

    /**
     * Fetches data from a generated file and returns an observation list.
     * Parses the data as needed with casting.
     *
     * @param outputFilename - The outputFilename to output the randomly generated test data to.
     * @param amountOfRows - The amount of rows of data to randomly generate.
     */
    public void GenData(String outputFilename, int amountOfRows){
        try {
            FileWriter csvWriter = new FileWriter(outputFilename);
            csvWriter.append("date,species,est_size,gridref,gender\n");

            for (int i=0; i<amountOfRows; i++) {
                int year = ThreadLocalRandom.current().nextInt(1990, 2019 + 1);
                String yearStr = year+"";
                int month = ThreadLocalRandom.current().nextInt(1, 11 + 1);
                String monthStr = month+"";
                if(month<10){
                    monthStr=0+""+month;
                }
                int day = ThreadLocalRandom.current().nextInt(1, 28 + 1);
                String dayStr = day+"";
                if(day<10){
                    dayStr=0+""+day;
                }
                String date = yearStr+""+monthStr+""+dayStr;
                int speciesIndex = ThreadLocalRandom.current().nextInt(0, speciesList.size());
                String species = speciesList.get(speciesIndex);
                String est_size = ThreadLocalRandom.current().nextInt(1, 3700 + 1)+"";
                String gridref = ThreadLocalRandom.current().nextInt(1, 59205407 + 1)+"";
                String sex = "f";
                int sexOdds = ThreadLocalRandom.current().nextInt(1, 28 + 1);
                if(sexOdds%2==0){
                    sex="m";
                }
                List<String> rowData = Arrays.asList(date, species, est_size, gridref, sex);
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
