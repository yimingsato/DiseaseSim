public class Vaccine extends Cure{

    // Constructor
    public Vaccine(String name, int id, double efficacyRate) {
        super(name, id, efficacyRate);
    }

    public void applyTo(Person person) {
        double efficacy = this.getEfficacyRate();
        double newImmunity = person.getBaseImmunityLevel();

        if (person.getAge() > 60) {
            newImmunity += 0.05 * efficacy;
        } else if (person.getAge() < 20) {
            newImmunity += 0.25 * efficacy;
        } else {
            newImmunity += 0.15 * efficacy;
        }

        // Clamp between 0 and 1
        newImmunity = Math.max(0, Math.min(1, newImmunity));
        person.setBaseImmunityLevel(newImmunity);
    }

    @Override
    public String toString() {
        return this.getName() + "\nVaccine\n" + this.getEfficacyRate();
    }
}
