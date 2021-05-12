package ca.uvic.seng330.ex2;

public class Pod {
    private String podName;
    private WhaleRepository podMembers;

    /**
     * Default constructor.
     */
    public Pod() {
        this.podName = "";
        this.podMembers = new WhaleRepository();
    }
    /**
     * Copy constructor. Copies field variables
     * @param other - Pod object to be copied
     */
    public Pod(Pod other) {
        if (other != null) {
            this.podName = other.podName;
            this.podMembers = other.podMembers;
        }
    }

    /**
     * Sets pod name
     *
     * @param podName A string representing pod's name
     */
    public void setPodName(String podName) {
        this.podName = podName;
    }

    /**
     * Gets pod name
     *
     * @return A string representing pod's name
     */
    public String getPodName() {
        return this.podName;
    }

    /**
     * Sets podMembers for the pod instance.
     *
     * @param podMembers
     */
    public void setPodMembers(WhaleRepository podMembers) {
        this.podMembers = new WhaleRepository(podMembers);
    }

    /**
     * Gets pod members
     *
     * @return An instance of the whale repository
     */
    public WhaleRepository getPodMembers() {
        return new WhaleRepository(this.podMembers);
    }
}
