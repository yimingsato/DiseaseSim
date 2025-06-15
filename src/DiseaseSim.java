import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DiseaseSim {
    public static final int MAP_LENGTH = 100;
    public static final int MAP_WIDTH = 100;
    public static final int POPULATION = MAP_LENGTH * MAP_WIDTH;

    private CureManager cureDatabase;
    private DiseaseManager diseaseDatabase;
    private Person[][] populationMap;
    private Region[][] regionMap; //parallel array with populationMap
    private Disease chosenDisease;

    public DiseaseSim(Disease disease, String cureFileName, String diseaseFileName, String peopleFileName, String mapFile) {
        this.chosenDisease = disease;
        this.cureDatabase = new CureManager(cureFileName); //initialize cureDatabase
        this.diseaseDatabase = new DiseaseManager(diseaseFileName);

        populationMap = new Person[MAP_LENGTH][MAP_WIDTH]; //initialize people by reading in people file
        regionMap = new Region[MAP_LENGTH][MAP_WIDTH];
        try (BufferedReader in = new BufferedReader(new FileReader(peopleFileName))) {
            for (int i = 0; i < MAP_LENGTH; i++) {
                for (int j = 0; j < MAP_WIDTH; j++) {
                    int personID = Integer.parseInt(in.readLine());
                    int age = Integer.parseInt(in.readLine());
                    char healthStatus = Person.HEALTHY; //everyone starts out healthy
                    double baseImmunityLevel = Double.parseDouble(in.readLine());

                    //take in secondary conditions for adult, child, senior
                    if (ageGroup(age).equals("Child")) {
                        boolean inSchool = in.readLine().charAt(0) == 'Y';
                        int numFriends = Integer.parseInt(in.readLine());
                        populationMap[i][j] = new Child(personID, age, healthStatus, baseImmunityLevel, inSchool, numFriends);
                    } else if (ageGroup(age).equals("Adult")) {
                        int numEventsAttended = Integer.parseInt(in.readLine());
                        populationMap[i][j] = new Adult(personID, age, healthStatus, baseImmunityLevel, numEventsAttended);
                    } else if (ageGroup(age).equals("Senior")) {
                        boolean inCareHome = in.readLine().charAt(0) == 'Y';
                        int mobilityLevel = Integer.parseInt(in.readLine());
                        populationMap[i][j] = new Senior(personID, age, healthStatus, baseImmunityLevel, inCareHome, mobilityLevel);
                    } else {
                        System.out.println(ageGroup(age));
                    }
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        try (BufferedReader in = new BufferedReader(new FileReader(mapFile))) {
            for (int i = 0; i < MAP_LENGTH; i++) {
                for (int j = 0; j < MAP_WIDTH; j++) {
                    char regionChar = in.readLine().charAt(0);
                    int temp = Integer.parseInt(in.readLine());
                    int population = Integer.parseInt(in.readLine());

                }
            }

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not fouund");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    private String ageGroup(int age) {
        if (age > 0 && age < Person.CHILD) {
            return "Child";
        } else if (age >= Person.CHILD && age <= Person.SENIOR) {
            return "Adult";
        } else if (age >= Person.SENIOR && age <= Person.AGE_LIMIT) {
            return "Senior";
        }
        return "Invalid age";
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
