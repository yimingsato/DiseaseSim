/*
Filename: Disease.java
Description: This class represents a disease with properties such as name, disease ID, transmission rate, mortality rate, and the number of infected individuals. It includes methods for infection, symptom display, and comparison of mortality rates. The class is abstract, requiring subclasses to implement the spread method.
*/

public abstract class Disease {
    private String name;
    private int diseaseID;
    private double transmissionRate;
    private double mortalityRate;
    private int numInfected;

    public Disease(String name, int diseaseID, double transmissionRate, double mortalityRate) {
        this.name = name;
        this.diseaseID = diseaseID;
        this.transmissionRate = transmissionRate;
        this.mortalityRate = mortalityRate;
        numInfected = 0;
    }

    public String getName() {
        return name;
    }
    public double getTransmissionRate() {
        return transmissionRate;
    }
    public double getMortalityRate() {
        return mortalityRate;
    }
    public int getNumInfected() {
        return numInfected;
    }
    public int getDiseaseID() {
        return diseaseID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTransmissionRate(double transmissionRate) {
        this.transmissionRate = transmissionRate;
    }
    public void setMortalityRate(double mortalityRate) {
        this.mortalityRate = mortalityRate;
    }
    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public abstract int spread(Person[][] grid, int x, int y, int dayLimit, int[][] infectionDays);

    public boolean infect(Person person) {
        if (!(person.getHealthStatus() == Person.HEALTHY)) {
            return false;
        }
        double risk = person.calcRiskFactor(this );
        double roll = Math.random();
        if (risk > roll) {
            person.setHealthStatus('I'); // 'I' for infected
            numInfected++;
            return true;
        }
        return false;
    }

    public void displaySymptoms() {
        System.out.println("Symptoms of " + name + ":");
        System.out.println("Fever, Cough, Fatigue");
    }

    public double compareToMortalityRate(Disease other) {
        return this.mortalityRate - other.mortalityRate;
    }

    @Override
    public String toString() {
        return name +"\n" + "Disease\n" + transmissionRate + "\n" + mortalityRate + "\n";
    }

}


