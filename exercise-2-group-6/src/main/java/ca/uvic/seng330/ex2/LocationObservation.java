package ca.uvic.seng330.ex2;

import java.util.Date;

/**
 * Extension of Observation class with specific location field and associated methods
 */
public class LocationObservation extends SimpleObservation{

    private Location location;

    /**
     * Get Location of LocationObservation
     * @return Location field variable
     */
    public Location getLocation() {
        System.out.println("Getting Location");
        return null;
    }

    /**
     * Set Location of LocationObservation
     * @param location - Location of whale observation
     */
    public void setLocation(Location location) {
        System.out.println("Setting Location");
    }

    /**
     * Copy constructor. Copies field variables
     * @param other - LocationObservation object to be copied
     */
    public LocationObservation(LocationObservation other){
        super(other);
        if(other != null){
            this.location = new Location(other.location);
        }
    }

    public LocationObservation(){
        super();
        this.location = null;
    }

    public LocationObservation(long id, Reporter reporter, Date time, Species species, Direction direction, String conditions, String notes, Location location){
        super(id, reporter, time, species, direction, conditions, notes);
        this.location = new Location(location);

        System.out.print("at a location\n");
    }

}
