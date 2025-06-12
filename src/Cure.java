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
    public double getEfficacyRate() {
        return efficacyRate;
    }
    public int getCureId() {
        return cureID;
    }
    //mutator methods
    public void setName(String name) {
        this.name = name;
    }
    public void setEfficacyRate(int efficacyRate) {
        this.efficacyRate = efficacyRate;
    } 
    public void setCureId(int id) {
        this.cureID = id;
    }

    public abstract boolean applyTo(Person person);

    @Override
    public String toString(){
        return name + "\nCure\n" + efficacyRate;
    }
    
    public double compareToEfficacy(Cure other){
            return this.efficacyRate - other.efficacyRate;
    }

}
