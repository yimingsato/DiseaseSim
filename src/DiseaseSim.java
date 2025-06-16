import java.io.*;
import java.util.*;

public class DiseaseSim {
    public static final int MAP_LENGTH = 10;
    public static final int MAP_WIDTH = 10;

    private int population;
    private int populationInfected;
    private CureManager cureDatabase; //database of cures
    private DiseaseManager diseaseDatabase;
    private Person[][] populationMap;
    private Region[][] regionMap; //parallel array with populationMap
    private Disease chosenDisease;
    private int x, y;
    private int numDead;
    private int numHealthy;
    private int totalDaysToInfect;
    private int[][] infectedPeople;

    public DiseaseSim(Disease disease, String cureFileName, String diseaseFileName, String peopleFileName, String regionFile, int x, int y, int days) {
        this.chosenDisease = disease;
        this.cureDatabase = new CureManager(cureFileName); //initialize cureDatabase
        this.diseaseDatabase = new DiseaseManager(diseaseFileName);
        this.population = MAP_LENGTH * MAP_WIDTH; //total population is the size of the map
        this.populationInfected = 0; //initialize infected population to 0
        this.x = x;
        this.y = y;
        this.totalDaysToInfect = days;
        populationInfected = 0;
        numHealthy = population;
        numDead = 0;
        
        //initialize populationMap and regionMap
        populationMap = new Person[MAP_LENGTH][MAP_WIDTH]; //initialize people by reading in people file
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        
        //initialize regionMap by reading in region file
        regionMap = new Region[MAP_LENGTH][MAP_WIDTH];
        try (BufferedReader in = new BufferedReader(new FileReader(regionFile))) {
            for (int i = 0; i < MAP_LENGTH; i++) {
                for (int j = 0; j < MAP_WIDTH; j++) {
                    char regionType = in.readLine().charAt(0);
                    int temp = Integer.parseInt(in.readLine());
                    if (regionType == 'c' || regionType == 'C') { //cold region
                        boolean snowCoverage = in.readLine().charAt(0) == 'Y';
                        boolean crowdedIndoors = in.readLine().charAt(0) == 'Y';
                        regionMap[i][j] = new Cold(regionType, temp, snowCoverage, crowdedIndoors);
                        populationMap[i][j].setLocation(regionMap[i][j]); //set region for each person
                    } else if (regionType == 'h' || regionType == 'H') { //hot region
                        boolean healthySunExposure = in.readLine().charAt(0) == 'Y';
                        boolean dryClimate = in.readLine().charAt(0) == 'Y';
                        regionMap[i][j] = new Hot(regionType, temp, healthySunExposure, dryClimate);
                        populationMap[i][j].setLocation(regionMap[i][j]); //set region for each person
                    }
                }
            }
        
        } catch (FileNotFoundException e) {
            System.out.println("File not fouund");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        // Initialize infectionDays with -1 (uninfected)
        infectedPeople = new int[MAP_LENGTH][MAP_WIDTH];
        for (int[] row : infectedPeople) {
            Arrays.fill(row, -1);
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

    public int getPopulation() {
        return population;
    }
    public int getPopulationInfected() {
        return populationInfected;
    }
    public CureManager getCureDatabase() {
        return cureDatabase;
    }
    public DiseaseManager getDiseaseDatabase() {
        return diseaseDatabase;
    }
    public Person[][] getPopulationMap() {
        return populationMap;
    }
    public Region[][] getRegionMap() {
        return regionMap;
    }
    public Disease getChosenDisease() {
        return chosenDisease;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getTotalDaysToInfect() {
        return totalDaysToInfect;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public void setPopulationInfected(int populationInfected) {
        this.populationInfected = populationInfected;
    }
    public void setCureDatabase(CureManager cureDatabase) {
        this.cureDatabase = cureDatabase;
    }
    public void setDiseaseDatabase(DiseaseManager diseaseDatabase) {
        this.diseaseDatabase = diseaseDatabase;
    }
    public void setPopulationMap(Person[][] populationMap) {
        this.populationMap = populationMap;
    }
    public void setRegionMap(Region[][] regionMap) {
        this.regionMap = regionMap;
    }
    public void setChosenDisease(Disease chosenDisease) {
        this.chosenDisease = chosenDisease;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void settDays(int days) {
        this.totalDaysToInfect = days;
    }

    //Save the current state of the simulation to files
    public boolean saveToFile(String peopleFileName, String cureFileName, String diseaseFileName, String regionFile, String simulationData) {
        try {
            // Save people data
            try (BufferedWriter out = new BufferedWriter(new FileWriter(peopleFileName))) {
                for (int i = 0; i < MAP_LENGTH; i++) {
                    for (int j = 0; j < MAP_WIDTH; j++) {
                        Person person = populationMap[i][j];
                        if (person != null) {
                            out.write(person.getPersonId() + "\n");
                            out.write(person.getAge() + "\n");
                            out.write(person.getBaseImmunityLevel() + "\n");
                            if (person instanceof Child) {
                                Child child = (Child) person;
                                out.write((child.isInSchool() ? "Y" : "N") + "\n");
                                out.write(child.getNumFriends() + "\n");
                            } else if (person instanceof Adult) {
                                Adult adult = (Adult) person;
                                out.write(adult.getNumEventsAttended() + "\n");
                            } else if (person instanceof Senior) {
                                Senior senior = (Senior) person;
                                out.write((senior.isInCareHome() ? "Y" : "N") + "\n");
                                out.write(senior.getMobilityLevel() + "\n");
                            }
                        }
                    }
                }
            }

            // Save cure data
            cureDatabase.saveCures(cureFileName);

            // Save disease data
            diseaseDatabase.saveDiseases(diseaseFileName);

            // Save region data
            try (BufferedWriter out = new BufferedWriter(new FileWriter(regionFile))) {
                for (int i = 0; i < MAP_LENGTH; i++) {
                    for (int j = 0; j < MAP_WIDTH; j++) {
                        Region region = regionMap[i][j];
                        if (region != null) {
                            out.write(region.getName() + "\n");
                            out.write(region.getTemp() + "\n");
                            if (region instanceof Cold) {
                                Cold coldRegion = (Cold) region;
                                out.write((coldRegion.isSnowCoverage() ? "Y" : "N") + "\n");
                                out.write((coldRegion.isCrowdedIndoors() ? "Y" : "N") + "\n");
                            } else if (region instanceof Hot) {
                                Hot hotRegion = (Hot) region;
                                out.write((hotRegion.isHealthySunExposure() ? "Y" : "N") + "\n");
                                out.write((hotRegion.isDryClimate() ? "Y" : "N") + "\n");
                            }
                        }
                    }
                }
                out.close();
            }
            
            // Save simulation data
            try (BufferedWriter out = new BufferedWriter(new FileWriter(simulationData))) {
                out.write("Population: " + population + "\n");
                out.write("Population Infected: " + populationInfected + "\n");
                out.write("Chosen Disease: " + chosenDisease.getName() + "\n");
                out.write("Starting X: " + x + "\n");
                out.write("Starting Y: " + y + "\n");
            }
            
            return true; // Save successful
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
            return false; // Save failed
        }
    }

    // Simulate one step of disease spread
    public void simulateSpread(int days) {
        int[] counts = new int[2];
        counts = chosenDisease.spread(populationMap, x, y, days, infectedPeople);
        populationInfected += counts[0];
        numDead += counts[1];
        numHealthy = countHealthy();
        
        // Update map or other simulation state as needed
    }

    public void applyCuresOverDays(int numToCurePerDay, int numDays) {
        Cure cure = cureDatabase.searchByDisease(chosenDisease);
        if (cure == null) {
            System.out.println("No cure found for the selected disease.");
            return;
        }

        for (int day = 1; day <= numDays; day++) {
            System.out.println("Day " + day + ": Applying cure to " + numToCurePerDay + " people...");
            for (int i = 0; i < numToCurePerDay; i++) {
                int row = (int) (Math.random() * MAP_LENGTH);
                int col = (int) (Math.random() * MAP_WIDTH);

                Person person = populationMap[row][col];
                if (person != null) {
                    person.setCure(cure);
                }
            }
        }

        System.out.println("Cure applied over " + numDays + " days.");
    }

    public int countHealthy() {
        int count = 0;
        for (int i = 0; i < MAP_LENGTH; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                Person person = populationMap[i][j];
                if (person != null && person.isHealthy()) {
                    count++;
                }
            }
        }
        return count;
    }

    public double getInfectionRate() {
        return (double) populationInfected / population;
    }

    public double getTotalMortalityRate() {
        if (numDead == 0) return 0.0; // Avoid division by zero
        return (double) numDead / population;
    }

    //pritn health status map
    public void printMap() {
        for (int i = 0; i < MAP_LENGTH; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                Person person = populationMap[i][j];
                if (person != null) {
                    char healthStatus = person.getHealthStatus();
                    System.out.print(healthStatus + " ");
                } else {
                    System.out.print("N "); // N for no person
                }
            }
            System.out.println();
        }
    }

    //print region map
    public void printRegionMap() {
        for (int i = 0; i < MAP_LENGTH; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                Region region = regionMap[i][j];
                if (region != null) {
                    System.out.print(region.getName() + " ");
                } else {
                    System.out.print("N "); // N for no region
                }
            }
            System.out.println();
        }
    }

    public static void displayMap(Person[][] people) {
        char[][] map = new char[people.length][people[0].length];

        for (int i = 0; i < people.length; i++) {
            for (int j = 0; j < people[i].length; j++) {
                Person p = people[i][j];
                if (p == null) {
                    map[i][j] = '.';
                } else {
                    switch (p.getHealthStatus()) {
                        case 'H' -> map[i][j] = 'H';
                        case 'I' -> map[i][j] = 'I';
                        case 'D' -> map[i][j] = 'D';
                        default -> map[i][j] = '?';
                    }
                }
            }
        }

        for (char[] row : map) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    
    
}




