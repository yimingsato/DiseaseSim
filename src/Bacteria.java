/*
Filename: Bacteria.java
Description: This class represents a type of disease caused by bacteria, extending the Disease class.
*/

public class Bacteria extends Disease{
    private double antibioticResistance;

    //Constructor
    public Bacteria(String name, int id, double transmissionRate, double mortalityRate, double antibioticResistance) {
        super(name, id, transmissionRate, mortalityRate);
        this.antibioticResistance = antibioticResistance;
    }

    // Accessor and Mutator methods
    public double getAntibioticResistance() {
        return antibioticResistance;
    }
    public void setAntibioticResistance(int antibioticResistance) {
        this.antibioticResistance = antibioticResistance;
    }

    /* 
    * Spreads the bacteria from a given origin point (x, y) in the grid.
    * @param grid The grid of persons where the bacteria will spread.
    * @param x The x-coordinate of the origin point.
    * @param y The y-coordinate of the origin point.
    * @param dayLimit The maximum number of days to spread the bacteria.
    * @param infectedPeople A 2D array to track the days when each person was infected.
    * @return The total number of people infected by the bacteria.
    */
    public int spread(Person[][] grid, int x, int y, int dayLimit, int[][] infectedPeople) {
        return spreadFromOrigin(grid, x, y, 0, dayLimit, infectedPeople);
    }

    /*
    * Helper method to recursively spread the bacteria from a given origin point.
    * @param grid The grid of persons where the bacteria will spread.
    * @param x The x-coordinate of the current point.
    * @param y The y-coordinate of the current point.
    * @param currentDay The current day of the spread.
    * @param dayLimit The maximum number of days to spread the bacteria.
    * @param infectedDays A 2D array to track the days when each person was infected.
    * @return The total number of people infected by the bacteria from this origin point.
    */
    private int spreadFromOrigin(Person[][] grid, int x, int y, int currentDay, int dayLimit, int[][] infectedDays) {
        if (currentDay > dayLimit) return 0;

        Person current = grid[x][y];
        if (current == null || current.isDead()) return 0;

        // Check if we've already reached this person earlier
        if (infectedDays[x][y] != -1 && infectedDays[x][y] <= currentDay) return 0;

        // Update day of infection arrival
        infectedDays[x][y] = currentDay;

        int infectedCount = 0;

        if (current.isHealthy()) {
            boolean infected = this.infect(current);
            if (!infected) return 0; // don't spread further if not infected
            infectedCount++; // count this new infection
        }

        // Spread to all 4 cardinal directions
        int[][] directions = {
            {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
                    {1, 0}, {1, -1}, {0, -1}, {-1, -1}
        };

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            infectedCount += spreadFromOrigin(grid, nx, ny, currentDay + 1, dayLimit, infectedDays);
        }

        return infectedCount;
    }

    /*
    * Returns a string representation of the bacteria disease, including its name, transmission rate, and mortality rate.
    * @return A string representation of the bacteria disease.
    */
    public String toString() {
        return getName() + "Bacteria\n" + getTransmissionRate() + "\n" + getMortalityRate();
    }

}
