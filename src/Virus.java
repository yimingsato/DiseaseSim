/*
Filename: Virus.java
Description: This class represents a Virus that extends the Disease class. It includes properties such as mutation rate and methods to spread the virus among a grid of people.
*/

public class Virus extends Disease {
    private double mutationRate;

    // Constructor
    public Virus(String name, int id, double transmissionRate, double mortalityRate, double mutationRate) {
        super(name, id, transmissionRate, mortalityRate);
        this.mutationRate = mutationRate;
    }

    //Accessor and Mutator methods
    public double getMutationRate() {
        return mutationRate;
    }
    public void setMutationRate(int mutationRate) {
        this.mutationRate = mutationRate;
    }

    /*
     * Method to infect a person based on the disease's transmission rate and the person's health status.
     * @param person The person to be infected.
     * @return true if the person is successfully infected, false otherwise.
     */
    public int[] spread(Person[][] grid, int x, int y, int dayLimit, int[][] infectedPeople) {
        return spreadFromOrigin(grid, x, y, 0, dayLimit, infectedPeople);
    }

    /*
     * Helper method to recursively spread the virus from a given origin point in the grid.
     * @param grid The grid of people.
     * @param x The x-coordinate of the origin.
     * @param y The y-coordinate of the origin.
     * @param currentDay The current day in the simulation.
     * @param dayLimit The maximum number of days to spread the virus.
     * @param infectedDays A 2D array to track the days on which each person gets infected.
     * @return An array containing the count of infected and dead people.
     */
    private int[] spreadFromOrigin(Person[][] grid, int x, int y, int currentDay, int dayLimit, int[][] infectedDays) {
        if (currentDay > dayLimit) return new int[]{0, 0};

        Person current = grid[x][y];
        if (current == null || current.isDead()) return new int[]{0, 0};

        if (infectedDays[x][y] != -1 && infectedDays[x][y] <= currentDay) return new int[]{0, 0};

        int infectedCount = 0;
        int deathCount = 0;

        if (current.isHealthy()) {
            boolean infected = this.infect(current);
            if (!infected) return new int[]{0, 0}; // stop if can't infect

            infectedDays[x][y] = currentDay; // only mark as infected if infection succeeds
            infectedCount++;

            if (current.killedByDisease(this)) {
                deathCount++;
            }
        } else {
            infectedDays[x][y] = currentDay; // aready infected or immune but mark visit to avoid revisiting
        }

        int[][] directions = {
            {-1, 0}, {0, 1},
            {1, 0}, {0, -1}
        };

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) { // check bounds
                
                int[] result = spreadFromOrigin(grid, nx, ny, currentDay + 1, dayLimit, infectedDays);
                infectedCount += result[0];
                deathCount += result[1];
            }
        }

        return new int[]{infectedCount, deathCount};
    }


    /*
     * Method to infect a person based on the disease's transmission rate and the person's health status.
     * @param person The person to be infected.
     * @return true if the person is successfully infected, false otherwise.
     */
    public String toString() {
        return getName() + "Virus\n" + getTransmissionRate() + "\n" + getMortalityRate();
    }

}
