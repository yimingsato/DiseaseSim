/*
Filename: Antibiotic.java
Description: This class represents an antibiotic cure that can be applied to a person to enhance their immunity based on various factors.
*/

public class Antibiotic extends Cure {

    //Constructor
    public Antibiotic(String name, int ID, double efficacyRate) {
        super(name, ID, efficacyRate);
    }

    /*
    * Applies the antibiotic cure to a person, enhancing their immunity level based on their age.
    * The efficacy of the antibiotic is adjusted based on the person's age group
    * @param person The person to whom the antibiotic is applied.
    */
    public void applyTo(Person person) {
        person.setCure(this);
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

    /*
    * Returns a string representation of the antibiotic cure, including its name and efficacy rate.
    * @return A string representation of the antibiotic cure.
    */
    public String toString() {
        return this.getName() + "\nAntibiotic\n" + this.getEfficacyRate();
    }

}