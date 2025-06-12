public class Vaccine extends Cure{

    // Constructor
    public Vaccine(String name, int id, double efficacyRate) {
        super(name, id, efficacyRate);
    }

    @Override
    public String toString() {
        return this.getName() + "\nVaccine\n" + this.getEfficacyRate();
    }
}
