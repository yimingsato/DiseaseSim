public class Virus extends Disease {
    private double mutationRate;

    public Virus(String name, int id, double transmissionRate, double mortalityRate, double mutationRate) {
        super(name, id, transmissionRate, mortalityRate);
        this.mutationRate = mutationRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(int mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int spread(Person[][] grid, int x, int y, int dayLimit, int[][] infectionDays) {
        return spreadFromOrigin(grid, x, y, 0, dayLimit, infectionDays);
    }

    private int spreadFromOrigin(Person[][] grid, int x, int y, int currentDay, int dayLimit, int[][] infectionDays) {
        if (currentDay > dayLimit) return 0;

        Person current = grid[x][y];
        if (current == null || current.isDead()) return 0;

        // Check if we've already reached this person earlier
        if (infectionDays[x][y] != -1 && infectionDays[x][y] <= currentDay) return 0;

        // Update day of infection arrival
        infectionDays[x][y] = currentDay;

        int infectedCount = 0;

        if (current.isHealthy()) {
            boolean infected = this.infect(current);
            if (!infected) return 0; // don't spread further if not infected
            infectedCount++; // count this new infection
        }

        // Spread to all 4 cardinal directions
        int[][] directions = {
            {-1, 0}, {0, 1},
            {1, 0}, {0, -1}
        };

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            infectedCount += spreadFromOrigin(grid, nx, ny, currentDay + 1, dayLimit, infectionDays);
        }

        return infectedCount;
    }


    public String toString() {
        return getName() + "Virus\n" + getTransmissionRate() + "\n" + getMortalityRate();
    }

}
