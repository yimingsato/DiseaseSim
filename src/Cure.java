public abstract class Cure {
    private String name;		// way to identify each vaccine type 
    private double efficacyRate;	// 1-10 scale, 1 least effective, 10 most effective
//efficacy rate will be determined by the type of cure and immunity level of the person
    private String targetDisease;

    //constructor
    public Cure(String DiseaseName, double efficacyRate, String targetDiseaseName) {
        this.name = DiseaseName + " Cure";
        this.efficacyRate = efficacyRate;
        this.targetDisease = targetDiseaseName;
    }  
    //accessor methods
    public String getName() {
        return name;
    }
    public double getEfficacyRate() {
        return efficacyRate;
    }
    public String getTargetDisease() {
        return targetDisease;
    }
    //mutator methods
    public void setName(String name) {
        this.name = name;
    }
    public void setEfficacyRate(int efficacyRate) {
        this.efficacyRate = efficacyRate;
    } 
    public void setTargetDisease(String targetDisease) {
        this.targetDisease = targetDisease;
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
