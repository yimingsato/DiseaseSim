import java.util.*;
public class Child extends Person {
    private boolean inSchool;
    private int numFriends;

    // Constructor
    public Child(int id, int age, char healthStatus, Region location, double immunityLevel, Cure cure, boolean inSchool, int numFriends) {
        super(id, age, healthStatus, location, immunityLevel, cure);
        this.inSchool = inSchool;
        this.numFriends = numFriends;
    }

    public Child(int id, int age, char healthStatus, double immunityLevel, boolean inSchool, int numFriends) {
        super(id, age, healthStatus, immunityLevel);
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
    /*Takes in base immunity, disease transmission rate, cure or no cure and any cure influence (antibotic resistance/mutation rate of virus),
    region influence and numFriends and whether or not in school attended as an child to
      calculate risk factor, higher risk returns higher num */
    public double calcRiskFactor(Disease d) {
        double baseRiskFactor = calcBaseRiskFactor(d);
        double modifiedTransmissionRate = getLocation().calcInfectionRateInfluence(d);
        double socialRiskFactor = 1 + 0.05 * numFriends;
        double schoolFactor = inSchool ? 1.3 : 1.0; // Higher risk if in school

        double riskFactor = modifiedTransmissionRate * (1 - baseRiskFactor) * schoolFactor * socialRiskFactor;
        return Math.max(0, Math.min(1, riskFactor)); // Ensure risk factor is between 0 and 1
    }

    /*
     * Returns a string representation of the Child object, including its id, age, health status, vaccination status,
     * antibiotic status, location, immunity level, school status, and number of friends.
     * @return a string representation of the Child object
     */
    @Override
    public String toString() {
        return "Child"; //placeholder
    }
}