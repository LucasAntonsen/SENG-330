package ca.uvic.seng330.ex3;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Whale observation details and associated methods
 */
public class Observation implements Comparable<Observation> {

    private User reporter;
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
    public User getReporter() {
        return reporter;
    }

    /**
     * Set Reporter of Observation
     * @param reporter - Reporter of Observation
     */
    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    /**
     * Get ObservationId of Observation
     * @return long - ObservationId number
     */
    public long getObservationId() {
        return observationId;
    }

    /**
     * Set ObservationId of Observation
     * @param observationId - ObservationId number
     */
    public void setObservationId(long observationId) {
        this.observationId = observationId;
    }

    /**
     * Get sightingTime of Observation
     * @return Date - sightingTime date
     */
    public Date getSightingTime() {
        return sightingTime;
    }

    /**
     * Set sightingTime of Observation
     * @param sightingTime - date of Observation
     */
    public void setSightingTime(Date sightingTime) {
        this.sightingTime = sightingTime;
    }

    /**
     * Get whale species seen in Observation
     * @return species - Species of whale observed
     */
    public Species getSpecies() {
        return species;
    }

    /**
     * Set whale species seen in Observation
     * @param species - Species of whale observed
     */
    public void setSpecies(Species species) {
        this.species = species;
    }

    /**
     * Get direction of whale movement in Observation
     * @return direction - Direction of observed whale movement
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set direction of whale movement in Observation
     * @param direction - Direction of observed whale movement
     */
    public void Direction(Direction direction) {
        this.direction = direction;
    }

    /**
     * Get condition of whale(s) observed during Observation
     * @return condition - Condition of whale(s) observed
     */
    public String getConditions() {
        return conditions;
    }

    /**
     * Set condition of whale(s) observed during Observation
     * @param conditions - Condition of whale(s) observed
     */
    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    /**
     * Copy constructor. Copies field variables
     * @param other - Observation object to be copied
     */
    public Observation(Observation other){
        if(other != null){
            this.reporter = new User(other.reporter);
            this.observationId = other.observationId;
            this.sightingTime = new Date(other.sightingTime.getTime());
            this.species = other.species;
            this.direction = other.direction;
            this.conditions = other.conditions;
        }
    }

    public Observation(long id, User reporter, Date time, Species species, Direction direction, String conditions){
        this.reporter = new User(reporter);
        this.observationId = id;
        this.sightingTime = new Date(time.getTime());
        this.species = species;
        this.direction = direction;
        this.conditions = conditions;
    }

    /**
     * Compares two Observation objects by their id
     * @param other Observation to compare this to
     * @return 0 if equal, <0 less than and >0 if greater than</0>
     */
    @Override
    public int compareTo(Observation other) {

        if (other == null)
            throw new NullPointerException();

        return (int) (observationId - other.observationId);
    }


    /**
     * Compares Observation by date
     */
    static class compareByDate implements Comparator<Observation> {
        public int compare(Observation obs1, Observation obs2) {

            if (obs1 == null || obs2 == null || obs1.sightingTime == null || obs2.sightingTime == null)
                throw new NullPointerException();

            return  obs1.sightingTime.compareTo(obs2.sightingTime);

        }
    }
}
