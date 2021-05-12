package ca.uvic.seng330.ex4;

import java.util.*;
import java.util.stream.Collectors;

public class WhaleList implements Iterable<Whale>{

    private final List<Whale> whales;

    /**
    * Default constructor
    */
    public WhaleList(){
        whales = new ArrayList<>();
    }

    /**
     * Copy Constructor. Copies the field variables
     * @param other WhaleRepository object.
     */
    public WhaleList(WhaleList other){
        if (other.whales != null) {
            this.whales = other.whales.stream().map(Whale::new).collect(Collectors.toList());
        } else {
            this.whales = new ArrayList<>();
        }
    }

    /**
     * Add a whale to the repository
     * @param whale A whale to add to the repository
     */
    public void add(Whale whale) {
        whales.add(new Whale(whale));
    }

    /**
     * @pre whales != null
     * @param whales Whales to add to list
     */
    public void addAll(Collection<? extends Whale> whales){
        assert whales != null;
        this.whales.addAll(whales.stream().map((Whale::new)).collect(Collectors.toList()));
    }


    public void remove(Whale whale) {
     whales.remove(whale);
    }

    public void clear() {
     whales.clear(); 
    }


    /**
     * @return Returns a copy of the Array List of whales in the repository
     */
    public List<Whale> getWhales() {
        return new ArrayList<>(whales);
    }

    /**
    * @return Returns an iterator to iterate through Arraylist of whales
    */
    public Iterator<Whale> iterator() {
        return whales.iterator();
    }

    /**
     * @pre comparator!=null
     * @see Whale.NameComparator
     * @see Whale.AgeComparator
     * @see Whale.SpeciesComparator
     * @param comparator
     */
    public void sortBy(Comparator<Whale> comparator) {
        if (whales != null && comparator != null)
            whales.sort(comparator);
    }

}
