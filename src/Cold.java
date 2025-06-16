/*
Filename: Cold.java
Description: This class represents a cold region in the disease simulation. It extends the Region class and includes properties such as snow coverage and crowded indoors, along with methods to calculate the influence of these factors on disease transmission rates.
*/

public class Cold extends Region{
    private boolean snowCoverage;
    private boolean crowdedIndoors;

    //Constructor
    public Cold(char name, double temp, boolean snowCoverage, boolean crowdedIndoors) {
        super(name, temp);
        this.snowCoverage = snowCoverage;
        this.crowdedIndoors = crowdedIndoors;
    }

    //Accessor and Mutator methods
    public boolean isSnowCoverage() {
        return snowCoverage;
    }
    public void setSnowCoverage(boolean snowCoverage) {
        this.snowCoverage = snowCoverage;
    }
    public boolean isCrowdedIndoors() {
        return crowdedIndoors;
    }
    public void setCrowdedIndoors(boolean crowdedIndoors) {
        this.crowdedIndoors = crowdedIndoors;
    }

    /*
     * Calculates the influence of the cold region's conditions on the transmission rate of a given disease.
     * @param d The Disease object for which to calculate the influence.
     * @return A double representing the modified transmission rate, constrained between 0 and 1.
     */
    public double calcInfectionRateInfluence(Disease d) {
        double transmissionRate = d.getTransmissionRate();
        if (d instanceof Virus) {
            if (snowCoverage) {
                transmissionRate += 0.1;
            }
            if (crowdedIndoors) {
                transmissionRate += 0.15;
            }
        } else if (d instanceof Bacteria) {
            if (snowCoverage) {
                transmissionRate -= 0.05;
            }
            if (crowdedIndoors) {
                transmissionRate += 0.1;
            }
        }
        return Math.max(0, Math.min(1, transmissionRate)); //max 1
    }

    /*
     * Returns a string representation of the cold region, including its name, temperature, snow coverage, and crowded indoors status.
     * @return A string representation of the cold region.
     */
    public String toString() {
        return "Cold\n" + getName() + "\n" + getTemp() + "\n" + snowCoverage +
                "\n" + crowdedIndoors + "\n";
    }
}
