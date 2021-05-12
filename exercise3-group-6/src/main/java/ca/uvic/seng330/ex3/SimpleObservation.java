package ca.uvic.seng330.ex3;

import java.util.Date;

/**
 * Extension of Observation class with specific notes field and associated methods
 */
public class SimpleObservation extends Observation{

    private String notes;

    /**
     * Get notes for SimpleObservation
     * @return String - notes for whale observation
     */
    public String getNotes() {
        System.out.println("Getting Notes");
        return null;
    }

    /**
     * Set notes for SimpleObservation
     * @param notes - notes for whale observation
     */
    public void setNotes(String notes) {
        System.out.println("Setting Notes");
    }

    /**
     * Copy constructor. Copies field variables
     * @param other - SimpleObservation object to be copied
     */
    public SimpleObservation(SimpleObservation other){
        if(other != null){
            this.notes = other.notes;
        }
    }

    public SimpleObservation(long id, User reporter, Date time, Species species, Direction direction, String conditions, String notes){
        super(id, reporter, time, species, direction, conditions);
        this.notes = notes;
        System.out.print(", they noted: \"" + notes + "\" ");
    }

    public SimpleObservation(){
        super();
        this.notes = "";
    }

}
