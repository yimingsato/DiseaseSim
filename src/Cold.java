public class Cold extends Region{
    private boolean snowCoverage;
    private boolean crowdedIndoors;
    
    //Accessor/Mutator
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
//Cold(String, double, int, double, int, int)
    public Cold(String name, double temp, int population, int populationInfected, boolean snowCoverage, boolean crowdedIndoors) {
        super(name, temp, population, populationInfected);
        this.snowCoverage = snowCoverage;
        this.crowdedIndoors = crowdedIndoors;
    }   

//calcInfectionRateInfluence(int baseInfectionRate): double
    public double calcInfectionRateInfluence(int baseInfectionRate) {
        double influence = baseInfectionRate;
        if (snowCoverage) {
            influence *= 0.9; // Snow coverage increases infection rate
        }
        if (crowdedIndoors) {
            influence *= 1.5; // Crowded indoors increases infection rate
        }
        return influence;
    }

    public String toString() {
        return "Cold{" +
                "name='" + getName() + '\'' +
                ", temp=" + getTemp() +
                ", population=" + getPopulation() +
                ", populationInfected=" + getPopulationInfected() +
                ", snowCoverage=" + snowCoverage +
                ", crowdedIndoors=" + crowdedIndoors +
                '}';
    }

}
