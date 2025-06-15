import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class DiseaseSim {
    public static final int MAP_LENGTH = 100;
    public static final int MAP_WIDTH = 100;

    private int population;
    private int populationInfected;
    private CureManager cureDatabase; //database of cures
    private DiseaseManager diseaseDatabase;
    private Person[][] populationMap;
    private Region[][] regionMap; //parallel array with populationMap
    private Disease chosenDisease;
    private int x, y;
    private int numInfected;

    public DiseaseSim(Disease disease, String cureFileName, String diseaseFileName, String peopleFileName, String regionFile, int x, int y) {
        this.chosenDisease = disease;
        this.cureDatabase = new CureManager(cureFileName); //initialize cureDatabase
        this.diseaseDatabase = new DiseaseManager(diseaseFileName);
        this.population = MAP_LENGTH * MAP_WIDTH; //total population is the size of the map
        this.populationInfected = 0; //initialize infected population to 0
        this.x = x;
        this.y = y;
        numInfected = 0;
        
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
            in.close();
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
                    if (regionType == 'c' || regionType == 'c') { //cold region
                        boolean snowCoverage = in.readLine().charAt(0) == 'Y';
                        boolean crowdedIndoors = in.readLine().charAt(0) == 'Y';
                        regionMap[i][j] = new Cold(regionType, temp, snowCoverage, crowdedIndoors);
                    } else if (regionType == 'h' || regionType == 'H') { //hot region
                        boolean healthySunExposure = in.readLine().charAt(0) == 'Y';
                        boolean dryClimate = in.readLine().charAt(0) == 'Y';
                        regionMap[i][j] = new Hot(regionType, temp, healthySunExposure, dryClimate);
                    }
                }
            }
        
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not fouund");
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
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
    public int getNumInfected() {
        return numInfected;
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
    public void setNumInfected(int numInfected) {
        this.numInfected = numInfected;
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
    public void simulateStep(int x, int y, int days) {
        numInfected += chosenDisease.spread(populationMap, x, y, days);
        // Update map or other simulation state as needed
    }

}
