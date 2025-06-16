/*
Filename: Person.java
Description: This class represents a person in a disease simulation system. It includes attributes such as person ID, age, health status, location, base immunity level, and cure. It also provides methods to manage health status, calculate risk factors, and check for cures against diseases.
*/
public abstract class Person {
    public static final char HEALTHY = 'H';
    public static final char INFECTED = 'I';
    public static final char DEAD = 'D';
    public static final int CHILD = 18;
    public static final int SENIOR = 60;
    public static final int AGE_LIMIT = 120;

    private int personID;
    private int age;
    private char healthStatus;
    private Region location;
    private double baseImmunityLevel;
    private Cure cure;

    // Constructor
    // Initializes a Person object with the given parameters.
    public Person(int personId, int age, char healthStatus, Region location, double immunityLevel, Cure cure) {
        this.personID = personId;
        this.age = age;
        this.healthStatus = healthStatus;
        this.location = location;
        this.baseImmunityLevel = immunityLevel;
        this.cure = cure;
    }

    // Overloaded constructor for creating a healthy person without a cure
    public Person(int personId, int age, char healthStatus, double immunityLevel) {
        this.personID = personId;
        this.age = age;
        this.healthStatus = HEALTHY;
        this.location = null;
        this.baseImmunityLevel = immunityLevel;
        cure = null;
    }

    //Accessor and mutator methods
    public int getPersonId() {
        return personID;
    }
    public int getAge() {
        return age;
    }
    public char getHealthStatus() {
        return healthStatus;
    }
    public Region getLocation() {
        return location;
    }
    public double getBaseImmunityLevel() {
        return baseImmunityLevel;
    }
    public Cure getCure() {
        return cure;
    }

    public void setId(int id) {
        this.personID = id;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setHealthStatus(char healthStatus) {
        this.healthStatus = healthStatus;
    }
    public void setLocation(Region location) {
        this.location = location;
    }
    public void setBaseImmunityLevel(double immunityLevel) {
        this.baseImmunityLevel = immunityLevel;
    }
    public void setCure(Cure cure) {
        this.cure = cure;
    }
    public boolean isHealthy() {
        return healthStatus == HEALTHY;
    }
    public boolean isInfected() {
        return healthStatus == INFECTED;
    }
    public boolean isDead() {
        return healthStatus == DEAD;
    }

    /*
     * Sets the health status of the person to infected.
     * If the person is already infected, it does nothing.
     * @param disease The disease that is infecting the person.
     * @return true if the person was successfully infected, false if they were already infected.
     */
    public boolean killedByDisease(Disease disease) {
        if (disease == null || !isInfected()) return false;

        double effectiveMortalityRate = disease.getMortalityRate() * (1 - baseImmunityLevel);
        effectiveMortalityRate = Math.max(0, Math.min(1, effectiveMortalityRate));

        double roll = Math.random(); // random number between 0.0 and 1.0

        if (roll < effectiveMortalityRate) {
            setHealthStatus(DEAD);
            return true;
        }
        return false;
    }

    /*
     * Sets the health status of the person to infected.
     * If the person is already infected, it does nothing.
     * @param disease The disease that is infecting the person.
     * @return true if the person was successfully infected, false if they were already infected.
     */
    public boolean hasCureForDisease(Disease disease) {
        if (disease == null) return false;
        int diseaseID = disease.getDiseaseID();

        if (cure.getCureID() == diseaseID) return true;

        return false;
    }

    /*
     * Checks if the person has a cure for the specified disease.
     * @param disease The disease to check against.
     * @return true if the person has a cure for the disease, false otherwise.
     */
    public Cure getCureForDisease(Disease disease) {
        if (disease == null) return null;
        int diseaseID = disease.getDiseaseID();

        if (cure.getCureID() == diseaseID) return cure;

        return null;
    }
    /*
     * Abstract method to calculate the risk factor for a disease.
     * This method must be implemented by subclasses of Person.
     * @param d The disease for which the risk factor is calculated.
     * @return The calculated risk factor as a double.
     */
    public abstract double calcRiskFactor(Disease d);

    /*
     * Calculates the base risk factor for a disease based on the person's immunity level.
     * This method adjusts the base immunity level based on the type of disease (Bacteria or Virus).
     * @param d The disease for which the base risk factor is calculated.
     * @return The adjusted base immunity level as a double, clamped between 0 and 1.
     */
    public double calcBaseRiskFactor(Disease d) {
        if (d instanceof Bacteria) {
            double antibioticResistance = ((Bacteria)d).getAntibioticResistance();
            baseImmunityLevel -=  antibioticResistance ;
        } else if (d instanceof Virus) {
            double mutationRate = ((Virus)d).getMutationRate();
            baseImmunityLevel -= (mutationRate + 0.05);
        }

        return Math.max(0, Math.min(1, baseImmunityLevel));
    }

    /*
     * Compares the immunity level of this person with another person.
     * @param other The other person to compare against.
     * @return A negative value if this person's immunity is lower, zero if they are equal, and a positive value if this person's immunity is higher.
     */
    public double compareToImmunity(Person other) {
        return this.baseImmunityLevel - other.baseImmunityLevel;
    }

    /*
     * Returns a string representation of the Person object.
     * This includes the person's ID, age, health status, location, base immunity level, and cure.
     * @return A string representation of the Person object.
     */
    public String toString() {
        return "Person ID: " + personID +
                "\nAge: " + age +
                "\nHealth Status: " + healthStatus +
                "\nLocation: " + (location != null ? location.getName() : "Unknown") +
                "\nBase Immunity Level: " + baseImmunityLevel +
                "\nCure: " + (cure != null ? cure.getName() : "None");
    }
}
