public class Region {
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
        return Double.compare(this.population, other.population);
    }
    public double compareToTemp() {
        return this.temp;
    }
// Accessor/mutator
// Region(String, double, int, double)
// compareToPopulation(Region): double
// compareToTemp(): double

// updateInfectedPopulation(): int
// Purpose: calculate number of infected people in a population 
// Parameter: none 
// Return: int (num infected)
// Algorithm:
// Run through population
// 	If infected 
// 		Count++

// calcInfectedPercentage(): double
// Purpose: calculate percentage of infected people in a population 
// Parameter: none 
// Return: double (percentage)
// Algorithm:
// 	Return infected population/total * 100

// calcInfectionRateInfluence(int baseInfectionRate )
// Purpose: Abstract method to be implemented to calculate infection rate
// Parameter: int baseInfectionRate (from disease transmisison rate)
// Return: int (modified infection rate)
// Algorithm: implemented differently per subclass 

// calcSize()
// Purpose: Recursively backtracks through every cell and returns size of region
// Parameter: int int (current location)
// Return: double (size)
// Algorithm: go through people standing next to each other and see which ones are in the same region as the person at the beginning
// If currentLocation is out of bounds or already visited
// Return 0 
// Else
// Mark currentLocaiton as visited
// Return 1+ recursive call

// fullyInfected()
// Purpose: checks if population of this region is fully infected
// Parameter: N/A
// Return: boolean
// Algorithm: 
//  	If infectedPopulation == population
    
}
