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
    public Cold(char name, double temp, boolean snowCoverage, boolean crowdedIndoors) {
        super(name, temp);
        this.snowCoverage = snowCoverage;
        this.crowdedIndoors = crowdedIndoors;
    }

    @Override
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

    @Override
    public String toString() {
        return "Cold{" +
                "name=" + getName() +
                ", temp=" + getTemp() +
                ", snowCoverage=" + snowCoverage +
                ", crowdedIndoors=" + crowdedIndoors +
                '}';
    }

}
