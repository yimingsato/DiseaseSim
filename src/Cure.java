public abstract class Cure {
    String name;		// way to identify each vaccine type 
    int efficacyRate;	// 1-10 scale, 1 least effective, 10 most effective

    //accessor methods
    public String getName() {
        return name;
    }
    public int getEfficacyRate() {
        return efficacyRate;
    }
    //mutator methods
    public void setName(String name) {
        this.name = name;
    }
    public void setEfficacyRate(int efficacyRate) {
        this.efficacyRate = efficacyRate;
    }

    //constructor
    public Cure(String name, int efficacyRate) {
        this.name = name;
        this.efficacyRate = efficacyRate;
    }   

    public abstract boolean applyTo(Person person);

    public String toString(){
        return "Cure{" +
                "name='" + name + '\'' +
                ", efficacyRate=" + efficacyRate +
                '}';
    }
    
    public double compareToEfficacy(Cure other){
        if (this.efficacyRate > other.efficacyRate) {
            return this.efficacyRate - other.efficacyRate; // this cure is more effective
        } else if (this.efficacyRate < other.efficacyRate) {
            return this.efficacyRate - other.efficacyRate; // other cure is more effective
        } else {
            return 0; // both cures are equally effective
        }
    }

}
