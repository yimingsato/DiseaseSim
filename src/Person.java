public abstract class Person {
    private int id;
    private int age;
    private char healthStatus;
    private boolean vaccinated;
    private boolean hasAntibiotics;
    private Region location;
    private int immunityLevel;

    // Constructor
    public Person(int id, int age, char healthStatus, boolean vaccinated, boolean hasAntibiotics, Region location, int immunityLevel) {
        this.id = id;
        this.age = age;
        this.healthStatus = healthStatus;
        this.vaccinated = vaccinated;
        this.hasAntibiotics = hasAntibiotics;
        this.location = location;
        this.immunityLevel = immunityLevel;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public char getHealthStatus() {
        return healthStatus;
    }   
    public boolean isVaccinated() {
        return vaccinated;
    }
    public boolean hasAntibiotics() {
        return hasAntibiotics;
    }
    public Region getLocation() {
        return location;
    }
    public int getImmunityLevel() {
        return ImmunityLevel;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setHealthStatus(char healthStatus) {
        this.healthStatus = healthStatus;
    }
    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }
    public void setHasAntibiotics(boolean hasAntibiotics) {
        this.hasAntibiotics = hasAntibiotics;
    }
    public void setLocation(Region location) {
        this.location = location;
    }
    public void setImmunityLevel(int immunityLevel) {
        this.ImmunityLevel = immunityLevel;
    }
    
    // Abstract methods
    /**
     * Calculates the risk factor of the person based on their age, health status, and other factors.
     * @return an integer representing the risk factor.
     */
    public abstract int calcRiskFactor();

    /**
     * Determines if the person can recover from a disease based on their immunity level and whether they have antibiotics.
     * @return true if the person recovers, false otherwise.
     */
    public boolean recoverFromDisease(Disease d) {
        if (immunityLevel > d.getMortalityRate()){
            healthStatus = 'H'; // Healthy
            return true;
        } else if (hasAntibiotics && immunityLevel + getEfficacy > Disease.MORTALITY_RATE) {
            healthStatus = 'H'; // Healthy
            return true;
        }
        return false;
    }

    /**
     * Determines if the person dies from a disease based on their immunity level and whether they have antibiotics.
     * @return true if the person dies, false otherwise.
     */
    public boolean dieFromDisease() {
        if (immunityLevel < Disease.mortalityRate && !hasAntibiotics || !vaccinated) { 
            healthStatus = 'D'; // Dead
            return true;
        }
        return false;
    }
    
    /**
     * Receives a cure (vaccine or antibiotic) and updates the person's health status accordingly.
     * @param cure the cure to be administered.
     */
    public void receiveCure(Cure cure) {
        if (cure instanceof Vaccine) {
            this.vaccinated = true;
            this.immunityLevel += cure.getEfficacy();
        } else if (cure instanceof Antibiotic) {
            this.hasAntibiotics = true;
            this.immunityLevel += cure.getEfficacy();
        }
    }

    /**
     * Checks if the person is healthy.
     * @return true if the person is healthy, false otherwise.
     */
    public boolean isHealthy() {
        return healthStatus == 'H';
    }
    /**
     * Checks if the person is infected.
     * @return true if the person is infected, false otherwise.
     */
    public boolean isInfected() {
        return healthStatus == 'I';
    }
    /**
     * Checks if the person is dead.
     * @return true if the person is dead, false otherwise.
     */
    public boolean isDead() {
        return healthStatus == 'D';
    }
    /**
     * Returns a string representation of the person.
     * @return a string containing the person's details.
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", healthStatus=" + healthStatus +
                ", vaccinated=" + vaccinated +
                ", hasAntibiotics=" + hasAntibiotics +
                ", location=" + location +
                ", immunityLevel=" + immunityLevel +
                '}';
    }

    /**
     * Compares this person with another person based on their immunity level.
     * @param other the other person to compare with.
     * @return a negative integer, zero, or a positive integer as this person's immunity level is less than, equal to, or greater than the specified person's immunity level.
     */
    public double compareToImmunity(Person other) {
        return Double.compare(this.immunityLevel, other.immunityLevel);
    }

}
