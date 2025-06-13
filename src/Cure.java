public abstract class Cure {
    private String name;		// way to identify each vaccine type
    private int cureID; // unique identifier for each cure corresponding to disease id
    private double efficacyRate;	// 1-10 scale, 1 least effective, 10 most effective
    //efficacy rate will be determined by the type of cure and immunity level of the person


    //constructor
    public Cure(String name, int cureID, double efficacyRate) {
        this.name = name;
        this.cureID = cureID;
        this.efficacyRate = efficacyRate;
    }

    //accessor methods
    public String getName() {
        return name;
    }
    public int getCureID() {
        return cureID;
    }
    public double getEfficacyRate() {
        return efficacyRate;
    }

    //mutator methods
    public void setName(String name) {
        this.name = name;
    }
    public void setCureId(int id) {
        this.cureID = id;
    }
    public void setEfficacyRate(int efficacyRate) {
        this.efficacyRate = efficacyRate;
    }

    public boolean applyTo(Person person) {
        return person.addCure(this);
    }

    public double compareToEfficacy(Cure other){
        return this.efficacyRate - other.efficacyRate;
    }

    @Override
    public String toString(){
        String s = null;
        if (this instanceof Antibiotic) {
            s = "Antibiotic: " + name + 
                   "\n\tCure ID: " + cureID + 
                   "\n\tEfficacy Rate: " + efficacyRate;
        } else if (this instanceof Vaccine) {
            s =  "Vaccine: " + name + 
                   "\n\tCure ID: " + cureID + 
                   "\n\tEfficacy Rate: " + efficacyRate;
        }
        return s;
    }
}
