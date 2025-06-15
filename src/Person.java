
public abstract class Person {
    public static final char HEALTHY = 'H';
    public static final char INFECTED = 'I';
    public static final char DEAD = 'D';
    public static final char RECOVERED = 'R';
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
    public Person(int personId, int age, char healthStatus, Region location, double immunityLevel, Cure cure) {
        this.personID = personId;
        this.age = age;
        this.healthStatus = healthStatus;
        this.location = location;
        this.baseImmunityLevel = immunityLevel;
        this.cure = cure;
    }

    public Person(int personId, int age, char healthStatus, double immunityLevel) {
        this.personID = personId;
        this.age = age;
        this.healthStatus = HEALTHY;
        this.location = null;
        this.baseImmunityLevel = immunityLevel;
        cure = null;
    }

    // Getters
    public int getPersonId() {
        return personID;
    }
    public int getAge() {
        return age;
    }
    public String getAgeCategory() {
        if (age > 0 && age < CHILD){
            return "Child";
        } else if (age >= CHILD && age <= SENIOR) {
            return "Adult";
        } else if (age >= SENIOR && age <= AGE_LIMIT) {
            return "Senior";
        } else {
            return "Invalid age";
        }
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

    //Setters
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
    public boolean isRecovered() {
        return healthStatus == RECOVERED;
    }


    public boolean hasCureForDisease(Disease disease) {
        if (disease == null) return false;
        int diseaseID = disease.getDiseaseID();

        if (cure.getCureID() == diseaseID) return true;

        return false;
    }

    public Cure getCureForDisease(Disease disease) {
        if (disease == null) return null;
        int diseaseID = disease.getDiseaseID();

        if (cure.getCureID() == diseaseID) return cure;

        return null;
    }

    public abstract double calcRiskFactor(Disease d);

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

    public double compareToImmunity(Person other) {
        return this.baseImmunityLevel - other.baseImmunityLevel;
    }

    @Override
    public String toString() {
        return "Person ID: " + personID +
                "\nAge: " + age +
                "\nHealth Status: " + healthStatus +
                "\nLocation: " + (location != null ? location.getName() : "Unknown") +
                "\nBase Immunity Level: " + baseImmunityLevel +
                "\nCure: " + (cure != null ? cure.getName() : "None");
    }
}
