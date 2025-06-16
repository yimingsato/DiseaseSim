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

    // Constructor
    public Disease(String name, int diseaseID, double transmissionRate, double mortalityRate) {
        this.name = name;
        this.diseaseID = diseaseID;
        this.transmissionRate = transmissionRate;
        this.mortalityRate = mortalityRate;
        numInfected = 0;
    }

    // Accessor and mutator methods
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

    /*
     * Abstract method to spread the disease in a grid of persons.
     * This method must be implemented by subclasses to define how the disease spreads.
     * @param grid The grid of Person objects representing the population.
     * @param x The x-coordinate in the grid where the spread starts.
     * @param y The y-coordinate in the grid where the spread starts.
     * @param dayLimit The maximum number of days for which the spread should be calculated.
     * @param infectionDays A 2D array to track the days on which each person gets infected.
     * @return The number of new infections caused by this spread operation.
     */
    public abstract int[] spread(Person[][] grid, int x, int y, int dayLimit, int[][] infectionDays);

    /*
     * Method to infect a person based on the disease's transmission rate and the person's health status.
     * If the person is healthy, it calculates the risk of infection and updates the person's health status if infected.
     * @param person The Person object to be potentially infected.
     * @return true if the person is successfully infected, false otherwise.
     */
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

    /*
     * Compares the mortality rate of this disease with another disease.
     * @param other The other Disease object to compare with.
     * @return A double representing the difference in mortality rates.
     */
    public double compareToMortalityRate(Disease other) {
        return this.mortalityRate - other.mortalityRate;
    }

    /*
     * Returns a string representation of the disease, including its name, disease ID, transmission rate, and mortality rate.
     * @return A string representation of the disease.
     */
    public String toString() {
        return "\nName: " + name + 
               "\n\t Disease ID: " + diseaseID + 
               "\n\t Transmission Rate: " + transmissionRate + 
               "\n\t Mortality Rate: " + mortalityRate + 
               "\n\t Number of Infected: " + numInfected;
    }

}


