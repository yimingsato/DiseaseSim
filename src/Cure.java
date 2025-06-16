/*
Filename: Cure.java
Description: This abstract class represents a Cure in the disease simulation. It includes properties such as name, cure ID, and efficacy rate, along with methods to apply the cure to a person and compare efficacy with another cure.
*/

public abstract class Cure {
    private String name;
    private int cureID;
    private double efficacyRate;
    //efficacy rate will be determined by the type of cure and immunity level of the person

    //Constructor
    public Cure(String name, int cureID, double efficacyRate) {
        this.name = name;
        this.cureID = cureID;
        this.efficacyRate = efficacyRate;
    }

    //Accessor and mutator methods
    public String getName() {
        return name;
    }
    public int getCureID() {
        return cureID;
    }
    public double getEfficacyRate() {
        return efficacyRate;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCureId(int id) {
        this.cureID = id;
    }
    public void setEfficacyRate(int efficacyRate) {
        this.efficacyRate = efficacyRate;
    }

    /*
     * Abstract method to apply the cure to a person.
     * This method must be implemented by subclasses to define how the cure affects a Person.
     * @param person The Person object to which the cure will be applied.
     */
    public abstract void applyTo(Person person);
    
    /*
     * Compares the efficacy of this cure with another cure.
     * @param other The other Cure object to compare with.
     * @return A double representing the difference in efficacy rates.
     */
    public double compareToEfficacy(Cure other){
        return this.efficacyRate - other.efficacyRate;
    }

    /*
     * Returns a string representation of the cure, including its name, cure ID, and efficacy rate.
     * This method is overridden in subclasses to provide specific details for Antibiotic and Vaccine.
     * @return A string representation of the cure.
     */
    public String toString(){
        String s = null;
        if (this instanceof Antibiotic) {
            s = "Antibiotic: " + name + "\n" + cureID + "\n" + efficacyRate;
        } else if (this instanceof Vaccine) {
            s =  "Vaccine: " + name + "\n" + cureID + "\n" + efficacyRate;
        }
        return s;
    }
}
