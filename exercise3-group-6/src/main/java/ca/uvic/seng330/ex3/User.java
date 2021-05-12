package ca.uvic.seng330.ex3;

/**
 * User details and associated methods
 */
public class User {

    private String userId;
    private String name;
    private String email;
    private Roles role;

    /**
     * Default constructor
     */
    public User(){
        userId = "";
        name = "";
        email = "";
        role = null;
    }

    /**
     * Constructor with basic userId
     * @param id - User Identification number
     */
    public User(String id){
        this.userId = id;
        name = "";
        email = "";
        role = null;
    }

    /**
     * Get userId of User
     * @return userId - userId number of User
     */
    public String getUserId() {
        System.out.println("Getting User Id");
        return "";
    }

    /**
     * Get name of User
     * @return String - name of User
     */
    public String getName() {
        System.out.println("Getting Name");
        return "";
    }

    /**
     * Set name of User
     * @param name - name of User
     */
    public void setName(String name) {
        System.out.println("Setting Name");
    }

    /**
     * Get email of User
     * @return String - email of User
     */
    public String getEmail() {
        System.out.println("Getting Email");
        return "";
    }

    /**
     * Set email of User
     * @param email - email of User
     */
    public void setEmail(String email) {
        System.out.println("Setting Email");
    }

    /**
     * Get Role of User
     * @return role - Role of User
     */
    public Roles getRole() {
        System.out.println("Getting Role");
        return null;
    }

    /**
     * Set Role of User
     * @param role - Role of User
     */
    public void setType(Roles role) {
        System.out.println("Setting Role");
    }

    /**
     * Copy constructor. Copies field variables
     * @param other - User object to be copied
     */
    public User(User other){
        if(other != null){
            this.userId = other.userId;
            this.name = other.name;
            this.email = other.email;
            this.role = other.role;
        }
    }

    public User(String userId, String name, String email, Roles role){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

}
