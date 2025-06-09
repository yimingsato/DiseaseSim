public abstract class Person {
    public static final char HEALTHY = 'H';
    public static final char INFECTED = 'I';
    public static final char DEAD = 'D';
    public static final char RECOVERED = 'R';

    private int id;
    private int age;
    private char healthStatus;
    private boolean vaccinated;
    private boolean hasAntibiotics;
    private Region location;
    private int baseImmunityLevel;

    // Constructor
    public Person(int id, int age, char healthStatus, boolean vaccinated, boolean hasAntibiotics, Region location, int immunityLevel) {
        this.id = id;
        this.age = age;
        this.healthStatus = healthStatus;
        this.vaccinated = vaccinated;
        this.hasAntibiotics = hasAntibiotics;
        this.location = location;
        this.baseImmunityLevel = immunityLevel;
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
    public int getBaseImmunityLevel() {
    
        return immunityLevel;
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
    public void setBaseImmunityLevel(int immunityLevel) {
        this.baseImmunityLevel = immunityLevel;
    }

    // Abstract methods
    /**
     * Calculates the risk factor of the person based on their age, health status, and other factors.
     * @return an integer representing the risk factor.
     */
    public abstract double calcRiskFactor(Disease D);

    public boolean isHealthy() {
        return healthStatus == 'H';
    }

    public boolean isInfected() {
        return healthStatus == 'I';
    }

    public boolean diseaseID() {
        return healthStatus == 'D';
    }
    

    /**
     * Compares this person with another person based on their immunity level.
     * @param other the other person to compare with.
     * @return a negative integer, zero, or a positive integer as this person's immunity level is less than, equal to, or greater than the specified person's immunity level.
     */
    public double compareToImmunity(Person other) {
        return this.baseImmunityLevel - other.baseImmunityLevel;
    }

}
