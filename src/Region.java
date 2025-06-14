public abstract class Region {
    private char name;
    private double temp;
    private int population;
    private int populationInfected;

    // Constructor
    public Region(char name, double temp, int population, int populationInfected) {
        this.name = name;
        this.temp = temp;
        this.population = population;
        this.populationInfected = populationInfected;
    }
    // Accessor mutators
    public char getName() {
        return name;
    }
    public double getTemp() {
        return temp;
    }
    public int getPopulation() {
        return population;
    }
    public int getPopulationInfected() {
        return populationInfected;
    }
    public void setName(char name) {
        this.name = name;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public void setPopulationInfected(int populationInfected) {
        this.populationInfected = populationInfected;
    }

    // CompareTo methods
    public double compareToPopulation(Region other) {
        return this.population - other.population;
    }
    public double compareToTemp(Region other) {
        return this.temp - other.getTemp();
    }

    public double calcInfectedPercentage() {
        if (population == 0) {
            return 0.0; // Avoid division by zero
        }
        return (double) populationInfected / population * 100;
    }

    public abstract double calcInfectionRateInfluence(Disease d);

    public boolean fullyInfected() {
        return populationInfected == population;
    }

    public String toString() {
        return name + "\n" + temp + "\n" + population + "\n" + populationInfected;
    }
}
