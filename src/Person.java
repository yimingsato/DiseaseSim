import java.util.Set;

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
    
    public abstract int calcRiskFactor();
    public boolean recoverFromDisease() {
        return true;
    }

//     Person Class:
// Accessor/mutators
// Person() constructor
// toString(): String 
// 	Print all details 
// compareTo(Person): double
// 	Compare immunity level

// calcRiskFactor(): int
// Purpose: abstract method to calculate immunity level 
// Parameters: none
// Return value: int
// Algorithm: different per subclass

// recover(): boolean
// Purpose: checks if person can recover from the disease 
// Parameter: none
// Return value: boolean
// Algorithm:
// If person immunity level > mortality rate 
// 	Set status to healthy 
// If person person has cure
// 	If person immunity level and cure efficacy > mortality rate of disease
// Set status to healthy

// die(): boolean
// Purpose: simulate death by disease
// Parameter: none
// Return value: boolean
// Algorithm:
// 	If person immunity level < mortality rate
// 		Set status to dead

// recieveCure(Cure c)
// Purpose: simulate death by disease
// Parameter: none
// Return value: boolean
// Algorithm: 
// If cure is Vaccine 
// Set vaccine boolean to true
// immunityLevel == higher
// If cure is Antibiotic
// Set vaccine boolean to true
// immunityLevel == high
}
