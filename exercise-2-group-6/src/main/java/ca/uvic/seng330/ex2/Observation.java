package ca.uvic.seng330.ex2;
import java.util.Date;

/**
 * Whale observation details and associated methods
 */
public class Observation {

    private Reporter reporter;
    private long observationId;
    private Date sightingTime;
    private Species species;
    private Direction direction;
    private String conditions;

    /**
     * Default constructor
     */
    public Observation(){
        reporter = null;
        observationId = 0;
        sightingTime = null;
        species = Species.UNKNOWN;
        direction = Direction.UNKNOWN;
        conditions = "";
    }

    /**
     * Get Reporter of Observation
     * @return reporter - Reporter of Observation
     */
    public Reporter getReporter() {
        System.out.println("Getting Reporter");
        return null;
    }

    /**
     * Set Reporter of Observation
     * @param reporter - Reporter of Observation
     */
    public void setReporter(Reporter reporter) {
        System.out.println("Setting Reporter");
    }

    /**
     * Get ObservationId of Observation
     * @return int - ObservationId number
     */
    public int getObservationId() {
        System.out.println("Getting Observation Id");
        return 0;
    }

    /**
     * Set ObservationId of Observation
     * @param observationId - ObservationId number
     */
    public void setObservationId(int observationId) {
        System.out.println("Setting Observation Id");
    }

    /**
     * Get sightingTime of Observation
     * @return Date - sightingTime date
     */
    public Date getSightingTime() {
        System.out.println("Getting Sighting Time");
        return null;
    }

    /**
     * Set sightingTime of Observation
     * @param sightingTime - date of Observation
     */
    public void setSightingTime(Date sightingTime) {
        System.out.println("Setting Sighting Time");
    }

    /**
     * Get whale species seen in Observation
     * @return species - Species of whale observed
     */
    public Species getSpecies() {
        System.out.println("Getting Species");
        return null;
    }

    /**
     * Set whale species seen in Observation
     * @param species - Species of whale observed
     */
    public void setSpecies(Species species) {
        System.out.println("Setting Species");
    }

    /**
     * Get direction of whale movement in Observation
     * @return direction - Direction of observed whale movement
     */
    public Direction getDirection() {
        System.out.println("Getting Direction");
        return null;
    }

    /**
     * Set direction of whale movement in Observation
     * @param direction - Direction of observed whale movement
     */
    public void Direction(Direction direction) {
        System.out.println("Setting Direction");
    }

    /**
     * Get condition of whale(s) observed during Observation
     * @return condition - Condition of whale(s) observed
     */
    public String getConditions() {
        System.out.println("Getting Conditions");
        return null;
    }

    /**
     * Set condition of whale(s) observed during Observation
     * @param conditions - Condition of whale(s) observed
     */
    public void setConditions(String conditions) {
        System.out.println("Setting Conditions");
    }

    /**
     * Copy constructor. Copies field variables
     * @param other - Observation object to be copied
     */
    public Observation(Observation other){
        if(other != null){
            this.reporter = new Reporter(other.reporter);
            this.observationId = other.observationId;
            this.sightingTime = new Date(other.sightingTime.getTime());
            this.species = other.species;
            this.direction = other.direction;
            this.conditions = other.conditions;
        }
    }

    public Observation(long id, Reporter reporter, Date time, Species species, Direction direction, String conditions){
        this.reporter = new Reporter(reporter);
        this.observationId = id;
        this.sightingTime = new Date(time.getTime());
        this.species = species;
        this.direction = direction;
        this.conditions = conditions;

        System.out.print("Observed a whale");
    }
}
