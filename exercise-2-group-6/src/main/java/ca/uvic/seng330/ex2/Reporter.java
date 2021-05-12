package ca.uvic.seng330.ex2;


/**
 * Extension of User class for Reporter associated details and methods
 */
public class Reporter extends User{

    /**
     * Copy constructor. Copies field variables
     * @param other - Reporter object to be copied
     */
    public Reporter(Reporter other){
        super(other);
    }

    public Reporter(){}

    public Reporter(String userId, String name, String email){
        super(userId, name, email, Type.REPORTER);
        System.out.println("Created Reporter \"" + name + "\"");

    }


}
