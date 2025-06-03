public class Senior extends Person {
    private boolean inCareHome;
    private int mobilityLevel;

    // Constructor
    public Senior(int id, int age, char healthStatus, boolean vaccinated, boolean hasAntibiotics, Region location, int immunityLevel, boolean inCareHome, int mobilityLevel) {
        super(id, age, healthStatus, vaccinated, hasAntibiotics, location, immunityLevel);
        this.inCareHome = inCareHome;
        this.mobilityLevel = mobilityLevel;
    }

    // Accessor and Mutator methods
    public boolean isInCareHome() {
        return inCareHome;
    }
    public void setInCareHome(boolean inCareHome) {
        this.inCareHome = inCareHome;
    }
    public int getMobilityLevel() {
        return mobilityLevel;
    }
    public void setMobilityLevel(int mobilityLevel) {
        this.mobilityLevel = mobilityLevel;
    }

    @Override
    /*
     * Calculates the risk factor for a senior based on their age and mobility level.
     * If the senior is in a care home, the risk factor is age plus mobility level.
     * If they are not in a care home, the risk factor is simply their age.
     * @return the calculated risk factor as an integer
     */
    public int calcRiskFactor() {
        if (inCareHome) {
            return getAge()/2; // Higher risk factor for those in care homes
        } else {
            if (mobilityLevel > 5) {
                return getAge()/3; // Higher risk factor for those with lower mobility
            }
            return getAge(); // Moderate risk factor for those not in care homes
        }
    
}
