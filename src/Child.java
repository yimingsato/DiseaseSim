/*
Filename: Child.java
Description: This class represents a Child, which is a type of Person in the disease simulation. It includes properties such as school status and number of friends, and methods to calculate risk factors for disease transmission.
*/

import java.util.*;
public class Child extends Person {
    private boolean inSchool;
    private int numFriends;

    // Constructor
    // Initializes a Child object with the given parameters.
    public Child(int id, int age, char healthStatus, Region location, double immunityLevel, Cure cure, boolean inSchool, int numFriends) {
        super(id, age, healthStatus, location, immunityLevel, cure);
        this.inSchool = inSchool;
        this.numFriends = numFriends;
    }

    // Constructor without Cure
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

    /*
    * Calculates the risk factor for a child contracting a disease based on their location, social interactions, and school status.
    * The risk factor is influenced by the disease's transmission rate, the child's immunity level, and their social environment.
    * @param d The Disease object representing the disease to calculate the risk factor for.
    * @return A double representing the risk factor, which is a value between 0 and 1.
    */
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
    public String toString() {
        return "Child"; //placeholder
    }
}