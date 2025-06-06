public class Antibiotic extends Cure {
    
    //Constructor
    public Antibiotic(String name, int efficacyRate) {
        super(name, efficacyRate);
    }

    public boolean applyTo(Person person) {
        if (!person.hasAntibiotics()) {
            person.setHasAntibiotics(true);
            person.updateImmunityLevel();
            person.setHealthStatus('H');
            return true; // Antibiotic applied successfully
            //UPDATE RISK FACTOR
        } else {
            return false; // Antibiotic not applied, person already has antibiotics
        }
    }

    public String toString() {
        return name + "\nAntibiotic\n" + efficacyRate;
    }

}