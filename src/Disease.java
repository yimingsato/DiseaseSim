public abstract class Disease {
    private String name;
    private int transmissionRate;
    private int mortalityRate;
    private int numInfected;
    private Cure cure;

    public Disease(String name, int transmissionRate, int mortalityRate, Cure cure) {
        this.name = name;
        this.transmissionRate = transmissionRate;
        this.mortalityRate = mortalityRate;
        numInfected = 0;
        this.cure = cure;
    }

    public String getName() {
        return name;
    }
    public int getTransmissionRate() {
        return transmissionRate;
    }
    public int getMortalityRate() {
        return mortalityRate;
    }
    public int getNumInfected() {
        return numInfected;
    }
    public Cure getCure() {
        return cure;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTransmissionRate(int transmissionRate) {
        this.transmissionRate = transmissionRate;
    }
    public void setMortalityRate(int mortalityRate) {
        this.mortalityRate = mortalityRate;
    }
    public void setCure(Cure cure) {
        this.cure = cure;
    }

    public abstract void spread(Person[][] grid, int x, int y, int dayLimit, int[][] infectionDays);

    public boolean infect(Person person) {
        if (!person.isHealthy()) {
            return false;
        }
        double risk = person.calcRiskFactor(this, cure);
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

    public String toString() {
        return name +"\n" + "Disease\n" + transmissionRate + "\n" + mortalityRate + "\n";
    }

}
