import java.util.InputMismatchException;
import java.util.Scanner;

public class SimulationRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String diseaseFile = null, cureFile = null, peopleFile, regionFile;
        System.out.println("Welcome to the Disease Simulation Setup");
        System.out.println("==========================================");

        DiseaseManager diseaseManager = null;
        CureManager cureManager = null;
        DiseaseSim diseaseSim = null;

        boolean setupComplete = false;
        while (!setupComplete) {
            System.out.println("\n==== DATABASE SETUP MENU ==== (1 and 2 must be done initally)");
            System.out.println("1. Load Diseases from File");
            System.out.println("2. Load Cures from File");
            System.out.println("3. Add Disease");
            System.out.println("4. Add Cure");
            System.out.println("5. List Diseases");
            System.out.println("6. List Cures");
            System.out.println("0. Proceed to Simulation");

            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("\nEnter your choice: ");
                try {
                    int choice = input.nextInt();
                    input.nextLine(); // consume newline
                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter disease file name: ");
                            diseaseFile = input.nextLine();
                            diseaseManager = new DiseaseManager(diseaseFile);
                        }
                        case 2 -> {
                            System.out.print("Enter cure file name: ");
                            cureFile = input.nextLine();
                            cureManager = new CureManager(cureFile);
                        }
                        case 3 -> {
                            System.out.print("Enter type (Bacteria/Virus): ");
                            String type = input.nextLine();
                            System.out.print("Enter name: ");
                            String name = input.nextLine();
                            System.out.print("Enter ID: ");
                            int id = Integer.parseInt(input.nextLine());
                            System.out.print("Enter transmission rate: ");
                            double tRate = Double.parseDouble(input.nextLine());
                            System.out.print("Enter mortality rate: ");
                            double mRate = Double.parseDouble(input.nextLine());

                            if (type.equalsIgnoreCase("Bacteria")) {
                                System.out.print("Enter resistance level: ");
                                double resistance = Double.parseDouble(input.nextLine());
                                diseaseManager.addDisease(new Bacteria(name, id, tRate, mRate, resistance));
                            } else if (type.equalsIgnoreCase("Virus")) {
                                System.out.print("Enter mutation rate: ");
                                double mutation = Double.parseDouble(input.nextLine());
                                diseaseManager.addDisease(new Virus(name, id, tRate, mRate, mutation));
                            }
                        }
                        case 4 -> {
                            System.out.print("Enter type (Vaccine/Antibiotic): ");
                            String type = input.nextLine();
                            System.out.print("Enter name: ");
                            String name = input.nextLine();
                            System.out.print("Enter ID: ");
                            int id = Integer.parseInt(input.nextLine());
                            System.out.print("Enter efficacy rate: ");
                            double eff = Double.parseDouble(input.nextLine());

                            if (type.equalsIgnoreCase("Vaccine")) {
                                cureManager.addCure(new Vaccine(name, id, eff));
                            } else if (type.equalsIgnoreCase("Antibiotic")) {
                                cureManager.addCure(new Antibiotic(name, id, eff));
                            }
                        }
                        case 5 -> diseaseManager.listAllDisease();
                        case 6 -> cureManager.listAllCures();
                        case 0 -> setupComplete = true;
                        default -> System.out.println("Invalid choice. Try again.");
                    }

                    validChoice = true; // only mark as valid if no exception and valid int input

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer choice.");
                }
            }
        }


        System.out.println("\n============== SIMULATION SETUP ==============");

        Disease chosenDisease = null;
        while (chosenDisease == null) {
            System.out.print("Enter disease name: ");
            String diseaseName = input.nextLine();
            chosenDisease = diseaseManager.searchByName(diseaseName);
            if (chosenDisease == null) {
                System.out.println("Disease not found in the database. Please try again.");
            }
        }

        System.out.print("Enter the people file name: (people.txt) ");
        peopleFile = input.nextLine();

        System.out.print("Enter the region file name: (regions.txt) ");
        regionFile = input.nextLine(); 

        int x = -1, y = -1;
        // Get valid x coordinate
        boolean validX = false;
        while (!validX) {
            try {
                System.out.print("Enter starting x coordinate (0-9): ");
                x = input.nextInt();
                if (x >= 0 && x <= 9) {
                    validX = true;
                } else {
                    System.out.println("X must be between 0 and 9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                input.next(); // Clear the invalid token
            }
        }

        // Get valid y coordinate
        boolean validY = false;
        while (!validY) {
            try {
                System.out.print("Enter starting y coordinate (0-9): ");
                y = input.nextInt();
                if (y >= 0 && y <= 9) {
                    validY = true;
                } else {
                    System.out.println("Y must be between 0 and 9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                input.next(); // Clear the invalid token
            }
        }
        
        System.out.print("Do you want to apply cures during the simulation? (yes/no): ");
        input.nextLine(); // consume newline
        String applyCureResponse = input.nextLine();
        boolean applyCure = applyCureResponse.equalsIgnoreCase("yes");

        System.out.println();
        DiseaseSim simulation = new DiseaseSim(chosenDisease, cureFile, diseaseFile, peopleFile, regionFile, x, y); 
        System.out.println();

        System.out.println("Simulation initialized with the following parameters:");
        System.out.println("Disease: \n" + chosenDisease);
        System.out.printf("Cure File: %s\n", cureFile);
        System.out.printf("Starting Coordinates: (%d, %d)\n", x, y);
        if (applyCure) {
            System.out.print("Enter number of people to cure per day: ");
            int numToCure = input.nextInt();
            simulation.applyCuresOverDays(numToCure, 1);
        }
        
        boolean finished = false;
        while (!finished) {
            System.out.print("Enter number of days to simulate (0 to stop): ");
            int days = input.nextInt();
            if (days <= 0) {
                System.out.println("Simulation stopped by user.");
                finished = true;
            } else {
                System.out.printf("Total Days to Simulate: %d\n", days);
            
                simulation.simulateSpread(days); 

                System.out.printf("Infection Rate: %.2f%%\n", simulation.getInfectionRate() * 100);
                System.out.printf("Mortality Rate: %.2f%%\n", simulation.getTotalMortalityRate() * 100);

                System.out.println("\nSimulation Complete.");
                System.out.printf("Total Infected: %d out of %d\n", simulation.getPopulationInfected(), simulation.getPopulation());

                simulation.printMap();
                if (simulation.isEveryoneInfectedOrDead(simulation.getPopulationMap())) {
                    System.out.println("All individuals are either infected or dead.");
                    finished = true;
                }
            }
        }
        input.close();
    } 
}
