package ca.uvic.seng330.ex3;

import java.util.*;

public class ObservationRepository implements Repository<Observation> {

    private List<Observation> observations;

    public ObservationRepository(List<Observation> observations) {
        this.observations = new ArrayList<>(observations);
    }

    public ObservationRepository() {
    }

    public ObservationRepository(ObservationRepository other) {
        if (other.observations == null)
            throw new NullPointerException("Repository to copy is null");

        this.observations = new ArrayList<>(other.observations);
    }

    /**
     * Find all the observations on a certain date
     *
     * @param date - desired date of any given observation
     * @return collection of observations submitted on the date <code>date</code>
     */
    public List<Observation> getByDate(Date date) {

        if (date == null)
            throw new NullPointerException();

        List<Observation> result = new ArrayList<>();

        for (Observation obs: observations) {
            if (obs.getSightingTime().compareTo(date) == 0) {
                result.add(new Observation(obs));
            }
        }

        if (result.size() == 0)
            throw new NoSuchElementException("No Observations dated: " + date.toString());

        return result;
    }

    public void sortByDate() {
        if (observations != null && observations.size() > 0)
            observations.sort(new Observation.compareByDate());
    }

    public void sortById() {
        if (observations != null && observations.size() > 0)
            Collections.sort(observations);
    }


    /**
     * Find all the observations on a species of whale
     *
     * @param species The desired species of whale
     * @return collection of observations on whale with species <code>species</code>
     */
    @Override
    public List<Observation> getBySpecies(Species species) {
        System.out.println("Getting Observations by Species");
        return null;
    }

    /**
     * Find a specific observation by id
     *
     * @param id the id number of the observation
     * @return a observation on whale with id <code>id</code>
     */
    @Override
    public Observation getById(long id) {

        if (id < 0 || id > observations.size())
            throw new IllegalArgumentException("Id must be greater than 0 but less than " + observations.size());

        List<Observation> sortedObservations = new ArrayList<>(observations);
        Collections.sort(sortedObservations);
        Observation search = new Observation();
        search.setObservationId(id);

        int index = Collections.binarySearch(sortedObservations, search);

        if (index < 0)
            throw new NoSuchElementException("No Element with id " + id);

        return sortedObservations.get(index);
    }

    public List<Observation> getObservations() {
        return new ArrayList<>(observations);
    }

    /**
     * Add a specific observation to the collection
     *
     * @param observation the observation to be added
     */
    public void add(Observation observation) {

        Observation copy = new Observation(observation);
        observations.add(copy);
    }

    @Override
    public Iterator<Observation> iterator() {
        return observations.iterator();
    }

}
