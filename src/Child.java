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
    /*Takes in base immunity, disease transmission rate, region influence and numFriends and whether or not in school attended as an child to 
      calculate risk factor, higher risk returns higher num */ 
    public double calcRiskFactor(Disease d) {
        double baseImmunity = getBaseImmunityLevel();
        double modifiedTransmissionRate = getLocation().calcInfectionRateInfluence(d);
        double socialRiskFactor = 1 + 0.05 * numFriends;
        double schoolFactor;
        if (inSchool) {
            schoolFactor = 1.3; // Higher risk if in school
        } else {
            schoolFactor = 1.0; // Normal risk if not in school
        }
        
        double riskFactor = modifiedTransmissionRate * (1 - baseImmunity) * schoolFactor * socialRiskFactor;
        return Math.max(0, Math.min(1, riskFactor)); // Ensure risk factor is between 0 and 1
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
                ", immunityLevel=" + getBaseImmunityLevel() +
                ", inSchool=" + inSchool +
                ", numFriends=" + numFriends +
                '}';
    }
}