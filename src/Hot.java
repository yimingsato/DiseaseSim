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
    @Override
    public double calcInfectionRateInfluence(int baseInfectionRate) {
        double influence = baseInfectionRate;
        if (healthySunExposure) {
            influence *= 0.9; // Healthy sun exposure reduces infection rate by 10%
        }
        if (dryClimate) {
            influence *= 0.8; // Dry climate reduces infection rate by 20%
        }
        return influence;
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
