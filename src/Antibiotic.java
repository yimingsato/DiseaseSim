public class Antibiotic extends Cure {
    
    //Accessor mutator
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
    //Constructor
    public Antibiotic(String name, int efficacyRate) {
        super(name, efficacyRate);
    }

public boolean applyTo(Person person) {
    if (person.isInfected()) {
        person.setHealthStatus("Recovered");
        return true;
    }
    return false;
}

}