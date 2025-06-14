public class SimulationMap {

    private Person[][] grid;
    private int rows;
    private int cols;

    public SimulationMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Person[rows][cols];
    }

    // Hardcode or load initial Person grid setup here
    public void initializeMap() {
        // Example initialization (replace with your own logic)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = null; // You could add default Person objects if desired
            }
        }
        // Example: place some actual people
        // grid[1][1] = new Adult();
        // grid[1][2] = new Senior();
        // etc.
    }

    public Person getPersonAt(int x, int y) {
        if (!inBounds(x, y)) return null;
        return grid[x][y];
    }

    public void setPersonAt(int x, int y, Person person) {
        if (inBounds(x, y)) {
            grid[x][y] = person;
        }
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public void printMapStatus() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Person p = grid[i][j];
                if (p == null) {
                    System.out.print(" . ");
                } else {
                    char status = p.getHealthStatus(); // e.g., 'H', 'I', 'D'
                    System.out.print(" " + status + " ");
                }
            }
            System.out.println();
        }
    }

    public Person[][] getGrid() {
        return grid;
    }
}
