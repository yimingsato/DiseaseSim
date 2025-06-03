public abstract class Disease {
    private String name;
    private int diseaseID;
    private int transmissionRate;
    private int mortalityRate;

    public Disease(String name, int diseaseID, int transmissionRate, int mortalityRate) {
        this.name = name;
        this.diseaseID = diseaseID;
        this.transmissionRate = transmissionRate;
        this.mortalityRate = mortalityRate;
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

    public void displaySymptoms() {
        System.out.println("Symptoms of " + name + ":");
        System.out.println("Fever, Cough, Fatigue");
    }

    public double compareToMortalityRate(Disease other) {
        return this.mortalityRate - other.mortalityRate;
    }

    

}
