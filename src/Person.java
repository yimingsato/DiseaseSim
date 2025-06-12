import java.util.*;

public abstract class Person {
    public static final char HEALTHY = 'H';
    public static final char INFECTED = 'I';
    public static final char DEAD = 'D';
    public static final char RECOVERED = 'R';

    private int id;
    private int age;
    private char healthStatus;
    private Region location;
    private double baseImmunityLevel;
    private ArrayList<Cure> cures;

    // Constructor
    public Person(int id, int age, char healthStatus, Region location, double immunityLevel, ArrayList<Cure> cures) {
        this.id = id;
        this.age = age;
        this.healthStatus = healthStatus;
        this.location = location;
        this.baseImmunityLevel = immunityLevel;
        this.cures = cures;
    }
    // Getters
    public int getId() {
        return id;
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
    public ArrayList<Cure> getCures() {
        return cures;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
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
    public void setBaseImmunityLevel(int immunityLevel) {
        this.baseImmunityLevel = immunityLevel;
    }
    public void setCures(ArrayList<Cure> cures) {
        this.cures = cures;
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

    public void setHealthy() {
        this.healthStatus = HEALTHY;
    }

    public boolean addCure(Cure cure) {
        if (cure != null && !cures.contains(cure)) {
            cures.add(cure);
            return true;  // Successfully added
        }
        return false;  // Null or already present
    }


    public boolean hasCureForDisease(Disease disease) {
        if (disease == null) return false;
        int diseaseID = disease.getDiseaseID();

        for (Cure cure : cures) {
            if (cure.getCureID() == diseaseID) {
                return true;
            }
        }
        return false;
    }

    public Cure getCureForDisease(Disease disease) {
        if (disease == null) return null;
        int diseaseID = disease.getDiseaseID();

        for (Cure cure : cures) {
            if (cure.getCureID() == diseaseID) {
                return cure;
            }
        }
        return null;
    }

    public abstract double calcRiskFactor(Disease d);

    public double calcBaseRiskFactor(Disease d) {
        if (d instanceof Bacteria) {
            if (hasCureForDisease(d)) {
                baseImmunityLevel += getCureForDisease(d).getEfficacyRate();
            }
            double antibioticResistance = ((Bacteria)d).getAntibioticResistance();
            baseImmunityLevel -=  antibioticResistance ;
        } else if (d instanceof Virus) {
            double mutationRate = ((Virus)d).getMutationRate();
            if (hasCureForDisease(d)) {
                baseImmunityLevel += getCureForDisease(d).getEfficacyRate();
            }
            baseImmunityLevel -= (mutationRate + 0.05);
        }

        return Math.max(0, Math.min(1, baseImmunityLevel));
    }

    public double compareToImmunity(Person other) {
        return this.baseImmunityLevel - other.baseImmunityLevel;
    }

    @Override
    public String toString() {
        return ""; //placehholder for now
    }
}
