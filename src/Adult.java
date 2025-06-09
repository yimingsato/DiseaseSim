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
    /*Takes in base immunity, disease transmission rate, region influence and num events attended as an adult to 
      calculate risk factor, higher risk returns higher num */ 
    public double calcRiskFactor(Disease d) {
        double baseImmunity = getBaseImmunityLevel();
        double modifiedTransmissionRate = getLocation().calcInfectionRateInfluence(d);
        
        return modifiedTransmissionRate * (1 - baseImmunity) * (1 + 0.1 * numEventsAttended);
    }

    /*
     * Returns a string representation of the Adult object, including its id, age, health status, vaccination status,
     * antibiotic status, location, immunity level, and number of events attended.
     * @return a string representation of the Adult object
     */
    @Override 
    public String toString() {
        return "Adult{" +
                "id=" + getId() +
                ", age=" + getAge() +
                ", healthStatus=" + getHealthStatus() +
                ", vaccinated=" + isVaccinated() +
                ", hasAntibiotics=" + hasAntibiotics() +
                ", location=" + getLocation() +
                ", immunityLevel=" + getBaseImmunityLevel() +
                ", numEventsAttended=" + numEventsAttended +
                '}';
    }
}
