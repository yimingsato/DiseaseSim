public class Adult extends Person {
    private int numEventsAttended;

    // Constructor
    public Adult(int id, int age, char healthStatus, boolean vaccinated, boolean hasAntibiotics, Region location, int immunityLevel, int numEventsAttended) {
        super(id, age, healthStatus, vaccinated, hasAntibiotics, location, immunityLevel);
        this.numEventsAttended = numEventsAttended;
    }

    // Accessor and Mutator methods
    public int getNumEventsAttended() {
        return numEventsAttended;
    }
    public void setNumEventsAttended(int numEventsAttended) {
        this.numEventsAttended = numEventsAttended;
    }   

    @Override
    /*Takes in base immunity, cure or no cure, disease transmission rate and any antibiotic resistance/mutation rate of virus, region influence and num events attended as an adult to 
      calculate risk factor, higher risk returns higher num */ 
    public double calcRiskFactor(Disease d, Cure c) {
        double baseRiskFactor = calcBaseRiskFactor(d, c);
        double modifiedTransmissionRate = getLocation().calcInfectionRateInfluence(d);
        
        double riskFactor = modifiedTransmissionRate * (1 - baseRiskFactor) * (1 + 0.1 * numEventsAttended); 
        return Math.max(0, Math.min(1, riskFactor)); // Ensure risk factor is between 0 and 1
    }

    /*
     * Returns a string representation of the Adult object, including its id, age, health status, vaccination status,
     * antibiotic status, location, immunity level, and number of events attended.
     * @return a string representation of the Adult object
     */
    @Override 
    public String toString() {
        return "Adult" + "\n" + getId() + "\n" + getAge() + "\n" + getHealthStatus() + "\n" +
                isVaccinated() + "\n" + hasAntibiotics() + "\n" + getLocation().getName() + "\n" + 
                getBaseImmunityLevel() + "\n" + numEventsAttended;
    }
}
