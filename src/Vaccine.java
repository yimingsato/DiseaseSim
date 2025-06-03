public class Vaccine extends Cure{

    // Accessor mutator
    public String getName() {
        return name;
    }
    public int getEfficacyRate() {
        return efficacyRate;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEfficacyRate(int efficacyRate) {
        this.efficacyRate = efficacyRate;
    }

    // Constructor
    public Vaccine(String name, int efficacyRate) {
        super(name, efficacyRate);
    }

    public boolean applyTo(Person person) {
        if (!person.isVaccinated()) {
        person.receiveCure(this);
        person.setHealthStatus('H');
        return true; // Antibiotic applied successfully
        //UPDATE RISK FACTOR
    }else{
        return false; // Antibiotic not applied, person already has antibiotics
    }
    }

    public String toString() {
        return "Vaccine{" +
                "name='" + name + '\'' +
                ", efficacyRate=" + efficacyRate +
                '}';
    }
}
