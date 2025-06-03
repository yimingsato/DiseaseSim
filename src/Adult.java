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
    /*
     * Calculates the risk factor for an adult based on their age and the number of events attended.
     * If the adult has attended more than 10 events, the risk factor is age plus half of age.
     * If they have attended 10 or fewer events, the risk factor is simply their age.
     * @return the calculated risk factor as an integer
     */
    public int calcRiskFactor() {
        if (numEventsAttended > 10) {
            return getAge() + getAge()/2; // Higher risk factor for attending many events
        } else {
            return getAge(); // Moderate risk factor for fewer events
        }
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
                ", immunityLevel=" + getImmunityLevel() +
                ", numEventsAttended=" + numEventsAttended +
                '}';
    }
}
