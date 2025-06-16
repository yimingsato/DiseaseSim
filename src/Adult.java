/*
Filename: Adult.java
Description: This class represents an adult person in the simulation, extending the Person class.
*/

public class Adult extends Person {
    private int numEventsAttended;

    // Constructors
    // Default constructor
    public Adult(int id, int age, char healthStatus, Region location, double immunityLevel, int numEventsAttended, Cure cure) {
        super(id, age, healthStatus, location, immunityLevel, cure);
        this.numEventsAttended = numEventsAttended;
    }
    // Constructor without cure
    public Adult(int id, int age, char healthStatus, double immunityLevel, int numEventsAttended) {
        super(id, age, healthStatus, immunityLevel);
        this.numEventsAttended = numEventsAttended;
    }

    // Accessor and Mutator methods
    public int getNumEventsAttended() {
        return numEventsAttended;
    }
    public void setNumEventsAttended(int numEventsAttended) {
        this.numEventsAttended = numEventsAttended;
    }

    /*
     * Calculates the base risk factor for an adult based on their age, health status, and immunity level.
     * @param d the disease for which the risk factor is being calculated
     * @return the base risk factor as a double
     */
    public double calcRiskFactor(Disease d) {
        double baseRiskFactor = calcBaseRiskFactor(d);
        double modifiedTransmissionRate = getLocation().calcInfectionRateInfluence(d);

        double riskFactor = modifiedTransmissionRate * (1 - baseRiskFactor) * (1 + 0.1 * numEventsAttended);
        return Math.max(0, Math.min(1, riskFactor)); // Ensure risk factor is between 0 and 1
    }
    /*
     * Returns a string representation of the Adult object, including its id, age, health status, vaccination status,
     * antibiotic status, location, immunity level, and number of events attended.
     * @return a string representation of the Adult object
     */
    public String toString() {
        return ""; //placeholder
    }
}
