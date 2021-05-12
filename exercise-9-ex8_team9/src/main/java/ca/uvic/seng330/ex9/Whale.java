package ca.uvic.seng330.ex9;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.BiPredicate;

public class Whale {
    //simple data structure to house the fields from the CSV file

    public enum Gender {
        MALE,
        FEMALE
    }

    private String species;
    private int gridref;
    private int size;
    private LocalDate date;
    private Gender gender;

    public Whale(LocalDate date, String species, int size, int gridref, Gender gender) {
        this.date = date;
        this.species = species;
        this.size = size;
        this.gridref = gridref;
        this.gender = gender;
    }

    //Copy constructor
    public Whale(Whale whale) {
        this.date = whale.getDate();
        this.species = whale.getSpecies();
        this.size = whale.getSize();
        this.gridref = whale.getGridref();
        this.gender = whale.getGender();
    }

    public LocalDate getDate() {
        return date;
    }

    public int getGridref() {
        return gridref;
    }

    public int getSize() {
        return size;
    }

    public Gender getGender() {
        return gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setGridref(Integer gridref) {
        this.gridref = gridref;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String toString() {
        return "Species: " + this.species + ", Size: " + this.size + "kg, gridref: " +
                this.gridref + ", Gender: " + this.gender + ", Date: " + this.date;
    }

    /**
     * A comparator used to sort Observations by weight using a BiPredicate from java.util.BiPredicate.
     */
    public static class WeightComparator implements Comparator<Whale> {
        @Override
        public int compare(Whale w1, Whale w2)
        {
            BiPredicate<Integer, Integer> biPredicate1 = (n, s) -> n > s;

            if(biPredicate1.test(w1.getSize(), w2.getSize())) return 1;
            if(biPredicate1.test(w2.getSize(), w1.getSize())) return -1;
            return 0;
        }
    }

}
