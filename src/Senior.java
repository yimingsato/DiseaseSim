/*Filename: Senior.java
Description: This file defines the Senior class, which extends the Person class to represent senior citizens in a disease simulation model.
*/

public class Senior extends Person {
    private boolean inCareHome;
    private int mobilityLevel;

    // Constructor
    // This constructor initializes a Senior object with the given parameters.
    public Senior(int id, int age, char healthStatus, Region location, double immunityLevel, Cure cure, boolean inCareHome, int mobilityLevel) {
        super(id, age, healthStatus, location, immunityLevel, cure);
        this.inCareHome = inCareHome;
        this.mobilityLevel = mobilityLevel;
    }

    // Overloaded constructor for cases where cure is not applicable
    public Senior(int id, int age, char healthStatus, double immunityLevel, boolean inCareHome, int mobilityLevel) {
        super(id, age, healthStatus, immunityLevel);
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

    /*
    Takes in base immunity, disease transmission rate, cure or no cure and any cure influence
    (antibotic resistance/mutation rate of virus),
    region influence and mobility level and whether or not in care home attended as a senior to
    calculate risk factor, higher risk returns higher num 
    * @param d The disease for which the risk factor is calculated.
    * @return The calculated risk factor as a double, between 0 and 1.
    */
    public double calcRiskFactor(Disease d) {
        double baseRiskFactor = calcBaseRiskFactor(d);
        double modifiedTransmissionRate = getLocation().calcInfectionRateInfluence(d);
        double careHomeFactor = inCareHome ? 1.4 : 1.0; // Higher risk if in care home
        double mobilityFactor = 1 + 0.05 * mobilityLevel;

        double risk = modifiedTransmissionRate * (1 - baseRiskFactor) * careHomeFactor * mobilityFactor;
        return Math.max(0, Math.min(1, risk)); // Ensure risk factor is between 0 and 1
    }

}
