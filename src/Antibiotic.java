public class Antibiotic extends Cure {

    //Constructor
    public Antibiotic(String name, int ID, double efficacyRate) {
        super(name, ID, efficacyRate);
    }

    public String toString() {
        return this.getName() + "\nAntibiotic\n" + this.getEfficacyRate();
    }

}