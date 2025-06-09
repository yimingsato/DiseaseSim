public class Virus extends Disease {
    private int mutationRate;
    
    public Virus(String name, int transmissionRate, int mortalityRate, int mutationRate) {
        super(name, transmissionRate, mortalityRate);
        this.mutationRate = mutationRate;
    }

    public int getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(int mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void spread(Person[][] grid, int x, int y, int dayLimit, int[][] infectionDays) {
        spreadFromOrigin(grid, x, y, 0, dayLimit, infectionDays);
    }

    private void spreadFromOrigin(Person[][] grid, int x, int y, int currentDay, int dayLimit, int[][] infectionDays) {
        if (currentDay > dayLimit) return;

        Person current = grid[x][y];
        if (current == null || current.isDead()) return;

        // Check if we've already reached this person earlier
        if (infectionDays[x][y] != -1 && infectionDays[x][y] <= currentDay) return;

        // Update day of infection arrival
        infectionDays[x][y] = currentDay;

        if (current.isHealthy()) {
            boolean infected = this.infect(current);
            if (!infected) return; // don't spread further if not infected
        }

        // Spread to all 8 directions (bacteria-style)
        int[][] directions = {
            {-1, 0}, {0, 1},
            {1, 0}, {0, -1}
        };

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            spreadFromOrigin(grid, nx, ny, currentDay + 1, dayLimit, infectionDays);
        }
    }

    public String toString() {
        return getName() + "Virus\n" + getTransmissionRate() + "\n" + getMortalityRate();
    }
    
}
