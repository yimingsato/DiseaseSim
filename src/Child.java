public class Child extends Person {
    private boolean inSchool;
    private int numFriends;
    
    // Constructor
    public Child(int id, int age, char healthStatus, boolean vaccinated, boolean hasAntibiotics, Region location, int immunityLevel, boolean inSchool, int numFriends) {
        super(id, age, healthStatus, vaccinated, hasAntibiotics, location, immunityLevel);
        this.inSchool = inSchool;
        this.numFriends = numFriends;
    }

    //Accessor and Mutator methods
    public boolean isInSchool() {
        return inSchool;
    }
    public void setInSchool(boolean inSchool) {
        this.inSchool = inSchool;
    }
    public int getNumFriends() {
        return numFriends;
    }
    public void setNumFriends(int numFriends) {
        this.numFriends = numFriends;
    }

    
    @Override
    /*  
     * calculates the risk factor for a child based on their age, school status, and number of friends.
     * If the child is in school and has more than 5 friends, the risk factor is age squared.
     * If the child is in school but has 5 or fewer friends, the risk factor is age multiplied by 2.
     * If the child is not in school, the risk factor is simply their age.
     * @return the calculated risk factor as an integer
     */
    public int calcRiskFactor() {
        if (inSchool) {
            if (numFriends > 5) {
                return getAge() * getAge();
            } else {
                return getAge() * 2;
            }
        } else {
            return this.getAge();
        }
    }

    /*
     * Returns a string representation of the Child object, including its id, age, health status, vaccination status,
     * antibiotic status, location, immunity level, school status, and number of friends.
     * @return a string representation of the Child object
     */
    @Override
    public String toString() {
        return "Child{" +
                "id=" + getId() +
                ", age=" + getAge() +
                ", healthStatus=" + getHealthStatus() +
                ", vaccinated=" + isVaccinated() +
                ", hasAntibiotics=" + hasAntibiotics() +
                ", location=" + getLocation() +
                ", immunityLevel=" + getImmunityLevel() +
                ", inSchool=" + inSchool +
                ", numFriends=" + numFriends +
                '}';
    }
}