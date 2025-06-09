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
    /*Takes in base immunity, disease transmission rate, region influence and mobility level and whether or not in care home attended as a senior to
      calculate risk factor, higher risk returns higher num */ 
    public double calcRiskFactor(Disease d) {
        double baseImmunity = getBaseImmunityLevel();
        double modifiedTransmissionRate = getLocation().calcInfectionRateInfluence(d);
        double careHomeFactor = inCareHome ? 1.4 : 1.0; // Higher risk if in care home
        double mobilityFactor = 1 + 0.05 * mobilityLevel;

        double risk = modifiedTransmissionRate * (1 - baseImmunity) * careHomeFactor * mobilityFactor;
        return Math.max(0, Math.min(1, risk)); // Ensure risk factor is between 0 and 1
    }
    
}
