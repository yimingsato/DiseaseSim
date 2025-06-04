public class Bacteria extends Disease{
    private int antibioticResistance;

    public Bacteria(String name, int diseaseID, int transmissionRate, int mortalityRate, int resistance) {
        super(name, diseaseID, transmissionRate, mortalityRate);
        int antibioticResistance = resistance;
    }

    public int getAntibioticResistance() {
        return antibioticResistance;
    }

    public void setAntibioticResistance(int antibioticResistance) {
        this.antibioticResistance = antibioticResistance;
    }

    public void spread() {
        
    }
    

}
