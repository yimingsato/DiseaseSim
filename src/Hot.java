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
    public Hot(String name, double temp, int population, int populationInfected, boolean healthySunExposure, boolean dryClimate) {
        super(name, temp, population, populationInfected);
        this.healthySunExposure = healthySunExposure;
        this.dryClimate = dryClimate;
    }

//calcInfectionRateInfluence(int baseInfectionRate ): double
    public double calcInfectionRateInfluence(int baseInfectionRate) {
        double influence = baseInfectionRate;
        if (healthySunExposure) {
            influence *= 0.9;
        }
        if (dryClimate) {
            influence *= 1.1; 
        }
        return influence;
    }

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
