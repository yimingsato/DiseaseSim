public class Vaccine extends Cure{

    // Constructor
    public Vaccine(String name, int id, double efficacyRate) {
        super(name, id, efficacyRate);
    }

    @Override
    public boolean applyTo(Person person) {
        if (!person.isVaccinated()) {
        person.setVaccinated(true);
        person.setHealthStatus('H');
        return true; // Antibiotic applied successfully
        //UPDATE RISK FACTOR
    }else{
        return false; // Antibiotic not applied, person already has antibiotics
    }
    }

    @Override
    public String toString() {
        return this.getName() + "\nVaccine\n" + this.getEfficacyRate();
    }
}
