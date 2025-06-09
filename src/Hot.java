public class Hot extends Region{
    private boolean healthySunExposure;
    private boolean dryClimate;

    //Accessor/Mutator
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
//Hot(String, double, int, double, int, int)
    public Hot(char name, double temp, int population, int populationInfected, boolean healthySunExposure, boolean dryClimate) {
        super(name, temp, population, populationInfected);
        this.healthySunExposure = healthySunExposure;
        this.dryClimate = dryClimate;
    }

//calcInfectionRateInfluence(int baseInfectionRate ): double
    @Override
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

    @Override
    public String toString() {
        return "Hot{" +
                "name='" + getName() + '\'' +
                ", temp=" + getTemp() +
                ", population=" + getPopulation() +
                ", populationInfected=" + getPopulationInfected() +
                ", healthySunExposure=" + healthySunExposure +
                ", dryClimate=" + dryClimate +
                '}';
    }

}
