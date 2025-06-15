public abstract class Region {
    private char type;
    private double temp;

    // Constructor
    public Region(char name, double temp) {
        this.name = name;
        this.temp = temp;
    }

    // Accessor mutators
    public char getName() {
        return name;
    }
    public double getTemp() {
        return temp;
    }
    public void setName(char name) {
        this.name = name;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double compareToTemp(Region other) {
        return this.temp - other.getTemp();
    }

    public abstract double calcInfectionRateInfluence(Disease d);

    public String toString() {
        return "Region{" +
                "name=" + name +
                ", temp=" + temp +
                '}';
    }
}
