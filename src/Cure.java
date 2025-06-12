public abstract class Cure {
    private String name;		// way to identify each vaccine type 
    private int cureID; // unique identifier for each cure corresponding to disease id
    private double efficacyRate;	// 1-10 scale, 1 least effective, 10 most effective
//efficacy rate will be determined by the type of cure and immunity level of the person
    

    //constructor
    public Cure(String name, double efficacyRate, int cureID) {
        this.name = name;
        this.efficacyRate = efficacyRate;
        this.cureID = cureID;
    }
    
    //accessor methods
    public String getName() {
        return name;
    }
    public double getEfficacyRate() {
        return efficacyRate;
    }
    public int getId() {
        return cureID;
    }
    //mutator methods
    public void setName(String name) {
        this.name = name;
    }
    public void setEfficacyRate(int efficacyRate) {
        this.efficacyRate = efficacyRate;
    } 
    public void setId(int id) {
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
