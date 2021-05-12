package ca.uvic.seng330.ex2;

import java.util.List;

/**
 * Collection of objects with a species and and id
 * @param <T> Any object with a species and id field variable
 */
public interface Repository<T>{
    /**
     * Get a collection of objects that matches the species parameter
     * @param species - species of the desired object
     * @return collection of objects that match the species parameter
     */
    List<T> getBySpecies(Species species);

    /**
     * Get a specific object that matches id
     * @param id - id of the specific desired object
     * @return specific instance of an object that matches id parameter
     */
    T getById(int id);

    /**
     * Add an object into the collection
     * @param t desired object to be added to collection
     */
    void add(T t);
}
