package ca.uvic.seng330.ex2;
public class Whale {
    private long whaleId;
    private String name;
    private Gender gender;
    private String color;
    private Species species;


    public Whale(long id, String name, Gender gender, String color, Species species){
        this.whaleId = id;
        this.color = color;
        this.name = name;
        this.color = color;
        this.species = species;

        System.out.println("Created Whale \"" + name + "\"");
    }

    /**
     * Default constructor.
     */
    public Whale() {
        this.whaleId = 0;
        this.name = "";
        this.gender = Gender.UNKNOWN;
        this.color = "";
        this.species = Species.UNKNOWN;
    }

    /**
     * Copy constructor. Copies field variables
     *
     * @param other Whale object to be copied
     */
    public Whale(Whale other) {
        if (other != null) {
            this.whaleId = other.whaleId;
            this.name = other.name;
            this.gender = other.gender;
            this.color = other.color;
            this.species = other.species;
        }
    }

    /**
     * Sets whale id.
     *
     * @param whaleId A long id that uniquely identifies a whale
     */
    public void setWhaleId(long whaleId) {
        this.whaleId = whaleId;
    }

    /**
     * Gets whale id
     *
     * @return A long id that uniquely identifies a whale
     */
    public long getWhaleId() {
        return this.whaleId;
    }

    /**
     * Sets whale name
     *
     * @param name A string containing the whale's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets whale name.
     *
     * @return A string containing the whale's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets whale's gender
     *
     * @param gender An Enum representing the whale's gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets the whale's gender
     *
     * @return An Enum representing the whale's gender
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Sets the whale's color
     *
     * @param color A string representing whale's color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets the whale's color
     *
     * @return A string representing whale's color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Sets the whale's species.
     *
     * @param species An enum representing whale's species
     */
    public void setSpecies(Species species) {
        this.species = species;
    }

    /**
     * Gets the whale's species.
     *
     * @return An enum representing whale's species
     */
    public Species getSpecies() {
        return this.species;
    }
}
