public abstract class Region {
    private String name;
    private double temp;
    private int population;
    private int populationInfected;

    // Constructor
    public Region(String name, double temp, int population, int populationInfected) {
        this.name = name;
        this.temp = temp;
        this.population = population;
        this.populationInfected = populationInfected;
    }
    // Getters and Setters
    public String getName() {
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
    public void setName(String name) {
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
        return this.temp - other temp;
    }

    //in order for this to work we need to have more methods in the Person class
    // public int updateInfectedPopulation() {
    //     int count = 0;
    //     for (int i = 0; i < population; i++) {
    //         if (person[i].getHealthStatus() == 'I') { //need to have an array of person objects
    //             count++;
    //         }
    //     }
    //     this.populationInfected = count;
    //     return count;
    // }

    public double calcInfectedPercentage() {
        if (population == 0) {
            return 0.0; // Avoid division by zero
        }
        return (double) populationInfected / population * 100;
    }

    public abstract double calcInfectionRateInfluence(int baseInfectionRate);

    public static boolean fullyInfected() {
        return populationInfected == population;
    }

}
