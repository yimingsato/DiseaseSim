/**
Filename: Vaccine.java
Description: This class represents a Vaccine that extends the Cure class. It applies
*/

public class Vaccine extends Cure{

    // Constructor
    public Vaccine(String name, int id, double efficacyRate) {
        super(name, id, efficacyRate);
    }

    /*
     * Applies the vaccine to a person, modifying their immunity level based on their age.
     * @param person The person to whom the vaccine is applied.
     */
    public void applyTo(Person person) {
        person.setCure(this);
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

    /*
     * Returns a string representation of the Vaccine object.
     * @return A string containing the name and efficacy rate of the vaccine.
     */
    public String toString() {
        return "Vaccine\n" + this.getName() + "\n" + getCureID() + "\n" + this.getEfficacyRate() + "\n\n";
    }
}
