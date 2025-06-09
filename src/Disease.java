public abstract class Disease {
    private String name;
    private int transmissionRate;
    private int mortalityRate;
    private int numInfected;

    public Disease(String name, int transmissionRate, int mortalityRate) {
        this.name = name;
        this.transmissionRate = transmissionRate;
        this.mortalityRate = mortalityRate;
        numInfected = 0;
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
    public void setName(String name) {
        this.name = name;
    }
    public void setTransmissionRate(int transmissionRate) {
        this.transmissionRate = transmissionRate;
    }
    public void setMortalityRate(int mortalityRate) {
        this.mortalityRate = mortalityRate;
    }

    public abstract void spread();

    public boolean infect(Person person) {
        if (!person.isHealthy()) {
            return false;
        }
        double risk = person.calcRiskFactor(this);
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
