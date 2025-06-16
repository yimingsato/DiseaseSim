/*
Filename: Hot.java
Description: This class represents a hot region with properties such as healthy sun exposure and dry climate. It extends the Region class and overrides methods to calculate the influence of these properties on disease transmission rates.
*/

public class Hot extends Region{
    private boolean healthySunExposure;
    private boolean dryClimate;

    //Constructor
    public Hot(char name, int temp, boolean healthySunExposure, boolean dryClimate) {
        super(name, temp);
        this.healthySunExposure = healthySunExposure;
        this.dryClimate = dryClimate;
    }

    //Accessor and Mutator methods
    public boolean isHealthySunExposure() {
        return healthySunExposure;
    }
    public void setHealthySunExposure(boolean healthySunExposure) {
        this.healthySunExposure = healthySunExposure;
    }
    public boolean isDryClimate() {
        return dryClimate;
    }
    public void setDryClimate(boolean dryClimate) {
        this.dryClimate = dryClimate;
    }

    /*
     * Calculates the influence of the region's properties on the infection rate of a disease.
     * @param d The Disease object for which the infection rate influence is calculated.
     * @return The adjusted transmission rate based on the region's properties.
     */
    public double calcInfectionRateInfluence(Disease d) {
        double transmissionRate = d.getTransmissionRate();
        if (d instanceof Virus) {
            if (healthySunExposure) {
                transmissionRate -= 0.1; // Healthy sun exposure reduces transmission
            }
            if (dryClimate) {
                transmissionRate += 0.05; // Dry climate can increase transmission
            }
        } else if (d instanceof Bacteria) {
            if (healthySunExposure) {
                transmissionRate += 0.05; // Healthy sun exposure can increase transmission
            }
            if (dryClimate) {
                transmissionRate -= 0.1; // Dry climate reduces transmission
            }
        }
        return Math.max(0, Math.min(1, transmissionRate)); // Ensure transmission rate is between 0 and 1
    }

    
    public String toString() {
        return "Hot{" +
                "name=" + getName() +
                ", temp=" + getTemp() +
                ", healthySunExposure=" + healthySunExposure +
                ", dryClimate=" + dryClimate +
                '}';
    }

}
