package ca.uvic.seng330.ex8.observation;

public class Observation {

    private int day;
    private int whaleQuantity;
    private WhaleSpecies species;
    private boolean inserted;


    public Observation( int whaleQuantity, WhaleSpecies species, int day ){
        this.day = day;
        this.whaleQuantity = whaleQuantity;
        this.species = species;
    }

    public boolean isInserted() {
        return inserted;
    }

    public void setInserted(boolean inserted) {
        this.inserted = inserted;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWhaleQuantity() {
        return whaleQuantity;
    }

    public void setWhaleQuantity(int whaleQuantity) {
        this.whaleQuantity = whaleQuantity;
    }

    public WhaleSpecies getSpecies() {
        return species;
    }

    public void setSpecies(WhaleSpecies species) {
        this.species = species;
    }

}
