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
}
