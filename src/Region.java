public abstract class Region {
    private char type;
    private double temp;

    // Constructor
    public Region(char type, double temp) {
        this.type = type;
        this.temp = temp;
    }

    // Accessor mutators
    public char getName() {
        return type;
    }
    public double getTemp() {
        return temp;
    }
    public void setType(char name) {
        this.type = name;
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
                "Type=" + type +
                ", temp=" + temp +
                '}';
    }
}
