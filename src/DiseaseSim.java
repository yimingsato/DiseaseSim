import java.util.Arrays;
import java.util.Random;

public class DiseaseSim {
    private Person[] population;
    private Disease disease;
    private int mapSize;
t
    public DiseaseSim(int populationSize, int mapSize, Disease disease) {
        this.mapSize = mapSize;
        this.disease = disease;
        this.population = new Person[populationSize];
        // Initialize population with random positions and health status
        for (int i = 0; i < populationSize; i++) {
            population[i] = new Person(/* random position, initial health status, etc. */);
        }
    }

    // Simulate one step of disease spread
    public void simulateStep() {
        for (Person p : population) {
            if (p.isInfected()) {
                // Try to infect nearby people
                for (Person other : population) {
                    if (!other.isInfected() && isNearby(p, other)) {
                        if (new Random().nextDouble() < disease.getInfectionRate()) {
                            other.infect();
                        }
                    }
                }
            }
        }
        // Update map or other simulation state as needed
    }

    // Example helper to check if two people are near each other
    private boolean isNearby(Person a, Person b) {
        // Implement your logic based on positions
        return false;
    }

    public void runSimulation(int startX, int startY) {
        Person[][] grid = map.getGrid();
        int rows = grid.length;
        int cols = grid[0].length;

        // Initialize infection day tracking
        int[][] infectionDays = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(infectionDays[i], -1); // -1 means not infected yet
        }

        // Start the infection from a given coordinate
        if (map.inBounds(startX, startY) && grid[startX][startY] != null) {
            System.out.println("Starting simulation at (" + startX + ", " + startY + ")");
            disease.spread(grid, startX, startY, dayLimit, infectionDays);
        } else {
            System.out.println("Invalid starting position.");
            return;
        }

        // Print simulation result
        System.out.println("\nFinal map status:");
        map.printMapStatus();
    }

    // Add more methods as needed (apply cures, print map, etc.)



}
