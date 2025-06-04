public abstract class Disease {
    private String name;
    private int diseaseID;
    private int transmissionRate;
    private int mortalityRate;
    private int numInfected;

    public Disease(String name, int diseaseID, int transmissionRate, int mortalityRate) {
        this.name = name;
        this.diseaseID = diseaseID;
        this.transmissionRate = transmissionRate;
        this.mortalityRate = mortalityRate;
        numInfected = 0;
    }

    public String getName() {
        return name;
    }
    public int getDiseaseID() {
        return diseaseID;
    }
    public int getTransmissionRate() {
        return transmissionRate;
    }
    public int getMortalityRate() {
        return mortalityRate;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }
    public void setTransmissionRate(int transmissionRate) {
        this.transmissionRate = transmissionRate;
    }
    public void setMortalityRate(int mortalityRate) {
        this.mortalityRate = mortalityRate;
    }

    public abstract void spread();

    public void infect(Person person) {
        if (!person.isHealthy()) {
            return;
        }
        double risk = person.calcRiskFactor(this);
        double roll = Math.random();
        if (risk > roll) {
            person.setHealthStatus('I'); // 'I' for infected
            numInfected++;
        }
    }

    public void displaySymptoms() {
        System.out.println("Symptoms of " + name + ":");
        System.out.println("Fever, Cough, Fatigue");
    }

    public double compareToMortalityRate(Disease other) {
        return this.mortalityRate - other.mortalityRate;
    }

    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                ", diseaseID=" + diseaseID +
                ", transmissionRate=" + transmissionRate +
                ", mortalityRate=" + mortalityRate +
                '}';
    }

}
