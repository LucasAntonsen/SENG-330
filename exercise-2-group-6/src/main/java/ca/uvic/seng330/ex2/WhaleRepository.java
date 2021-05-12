package ca.uvic.seng330.ex2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A collection of whales and associated search methods on the
 * collection
 */
public class WhaleRepository implements Repository<Whale> {

    private List<Whale> whales;

    public WhaleRepository(){
        whales = new LinkedList<>();
    }

    public WhaleRepository(List<Whale> initialWhales){
        if (initialWhales != null) {
            this.whales = new LinkedList<>(initialWhales);
        }
    }

    /**
     * Copy Constructor. Copies the field variables
     * @param other WhaleRepository object.
     */
    public WhaleRepository(WhaleRepository other){
        if (other.whales != null) {
            this.whales = new LinkedList<>(other.whales);
        }
    }

    /**
     * Find the whale by its name inside the repository
     * @param name - name of a whale
     * @return Whale object whose name field variable matches the name parameter
     */
    public Whale getByName(String name){
        System.out.println("Getting Whale with name");
        return null;
    }

    /**
     * Finds all the whales by gender
     * @param gender (Gender) - MALE or FEMALE
     * @return List of whales with field variable gender <code>gender</code>
     */
    public List<Whale> getByGender(Gender gender){
        System.out.println("Getting Whales with gender");
        return null;
    }

    /**
     * Finds all the whales by species
     * @param species - species of whale declared by enum
     * @return List of whales with field variable species <code>species</code>
     */
    @Override
    public List<Whale> getBySpecies(Species species) {
        System.out.println("Getting Whales with gender");
        return null;
    }

    /**
     * Finds a specific whale with a specific id
     * @param id - id value of the desired whale
     * @return Specific Whale Object with id <code>id</code>
     */
    @Override
    public Whale getById(int id) {
        System.out.println("Getting Whales with id" );
        return null;
    }

    /**
     * Add a whale to the repository
     * @param whale A whale to add to the repository
     */
    @Override
    public void add(Whale whale) {
        System.out.println("Added Whale!");
    }

    /**
     * @return Returns a Linked List of the whales in the repository
     */
    public List<Whale> getWhales() {
        return new LinkedList<>(whales);
    }
}
