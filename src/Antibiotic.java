public class Antibiotic extends Cure {

    //Constructor
    public Antibiotic(String name, int ID, double efficacyRate) {
        super(name, ID, efficacyRate);
    }

    public void applyTo(Person person) {
        double efficacy = this.getEfficacyRate();
        double newImmunity = person.getBaseImmunityLevel();

        // Adjust immunity based on age
        int age = person.getAge();
        if (age > 60) {
            newImmunity += 0.25 * efficacy;
        } else if (age < 20) {
            newImmunity += 0.05 * efficacy;
        } else {
            newImmunity += 0.15 * efficacy;
        }

        // Clamp between 0 and 1
        newImmunity = Math.max(0, Math.min(1, newImmunity));
        person.setBaseImmunityLevel(newImmunity);
    }

    @Override
    public String toString() {
        return this.getName() + "\nAntibiotic\n" + this.getEfficacyRate();
    }

}