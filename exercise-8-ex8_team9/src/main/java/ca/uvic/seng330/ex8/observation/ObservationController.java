package ca.uvic.seng330.ex8.observation;

public class ObservationController {
    ObservationData model;

    public ObservationController() {
        model = new ObservationData();
    }

    protected void notifyObservers() {
        for (Observer observer : this.model.getObservers()) {
            observer.newObservation(this.model.getWhaleCount());
        }
    }

    /**
     * Adds the current observation.
     * Function is called when the user submits the observation through the submit button.
     */
    public void addCurrentObservation() {
        if (model.getObservations().size() < 1) {
            return; //The user has not created any observations in the UI so just return
        }
        Observation currObs = model.getObservations().get(model.getObservations().size() - 1);

        this.model.getObservations().add(currObs);
        currObs.setInserted(true);

        // Keep a count of how many of each species has been seen separately so it can be returned in constant time.
        Integer quantity = currObs.getWhaleQuantity();
        Integer currAmount = this.model.getWhaleCount().get(currObs.getSpecies());

        if (currAmount != null) {
            quantity += currAmount;
        }
        this.model.getWhaleCount().put(currObs.getSpecies(), quantity);

        this.notifyObservers();
    }

    /**
     * Prepares the next observation to be inserted.
     * The current observation is insertion when the user hits the submit button
     *
     * @param species - The species of the Whale
     */
    public void prepareObservation(String species) {
        if (model.getObservations().size() < 1) {
            //Create a new observation with default values that will get inserted when the user hits the submit button
            model.getObservations().add(new Observation(1, WhaleSpecies.GREY, 1));
        } else if (model.getObservations().get(model.getObservations().size() - 1).isInserted()) {
            //Add to the new observation with that will get inserted when the user hits the submit button
            Observation head = model.getObservations().get(model.getObservations().size() - 1);
            model.getObservations().add(new Observation(head.getWhaleQuantity(), head.getSpecies(), head.getDay()));
        }

        //get the current observation that is going to be inserted
        Observation currObs = model.getObservations().get(model.getObservations().size() - 1);
        WhaleSpecies species1;

        //Parse the species from String to ENUM
        switch (species) {
            case "Orca":
                species1 = WhaleSpecies.ORCA;
                break;
            case "Grey":
                species1 = WhaleSpecies.GREY;
                break;
            case "Humpback":
                species1 = WhaleSpecies.HUMPBACK;
                break;
            case "Porpoise":
                species1 = WhaleSpecies.PORPOISE;
                break;
            default:
                species1 = WhaleSpecies.UNKNOWN;
                break;
        }
        currObs.setSpecies(species1);
    }

    /**
     * Prepares the next observation to be inserted.
     * The current observation is insertion when the user hits the submit button
     *
     * @param whaleAmount - The amount of whales observed in the pod
     */
    public void prepareObservation(int whaleAmount) {
        if (model.getObservations().size() < 1) {
            //Create a new observation with default values that will get inserted when the user hits the submit button
            model.getObservations().add(new Observation(1, WhaleSpecies.GREY, 1));
        } else if (model.getObservations().get(model.getObservations().size() - 1).isInserted()) {
            //Add to the new observation with that will get inserted when the user hits the submit button
            Observation head = model.getObservations().get(model.getObservations().size() - 1);
            model.getObservations().add(new Observation(head.getWhaleQuantity(), head.getSpecies(), head.getDay()));
        }
        Observation currObs = model.getObservations().get(model.getObservations().size() - 1);
        currObs.setWhaleQuantity(whaleAmount);
    }
}
