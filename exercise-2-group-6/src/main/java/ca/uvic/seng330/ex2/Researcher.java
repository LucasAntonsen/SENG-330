package ca.uvic.seng330.ex2;

/**
 * Extension of User class for Researcher associated details and methods
 */
public class Researcher extends User{

    /**
     * Copy constructor. Copies field variables
     * @param other - Researcher object to be copied
     */
    public Researcher(Researcher other){
        super(other);
    }

    public Researcher(){}

    public Researcher(String userId, String name, String email){
        super(userId, name, email, Type.RESEARCHER);
    }


}
