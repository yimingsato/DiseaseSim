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
        DiseaseSim simulation = null;

        boolean diseasesLoaded = false;
        boolean curesLoaded = false;
        boolean setupComplete = false;

        while (!setupComplete) {
            System.out.println("\n==== DISEASE & CURE DATABASE SETUP MENU (1 & 12 must be done first) ====");
            System.out.println("1. Load Diseases from File");
            System.out.println("2. Save Diseases to a File");
            System.out.println("3. Add Disease");
            System.out.println("4. Remove Disease (Will also remove associated cure)");
            System.out.println("5. Search Disease by Name (Linear)");
            System.out.println("6. Search Disease by ID (Binary, assumes sorted by ID)");
            System.out.println("7. Sort by ID");
            System.out.println("8. Sort by Mortality Rate");
            System.out.println("9. List Diseases");
            System.out.println("10. List Bacteria");
            System.out.println("11. List Viruses");

            System.out.println("12. Load Cures from File");
            System.out.println("13. Save Cures to a File");
            System.out.println("14. Add Cure");
            System.out.println("15. Search Cure by Disease");
            System.out.println("16. Search Cure by ID");
            System.out.println("17. Sort Cures by Efficacy Rate");
            System.out.println("18. List Cures");
            System.out.println("19. List Antibiotics");
            System.out.println("20. List Vaccines");

            System.out.println("\n0. BEGIN SIMULATION");

            boolean validChoice = false;
            while (!validChoice) {
                System.out.print("\nEnter your choice: ");
                try {
                    int choice = input.nextInt();
                    input.nextLine(); // consume newline
                    validChoice = true;

                    switch (choice) {
                        case 1 -> {
                            System.out.print("Enter disease file name: ");
                            diseaseFile = input.nextLine();
                            diseaseManager = new DiseaseManager(diseaseFile);
                            diseasesLoaded = true;
                        }
                        case 2 -> {
                            if (diseasesLoaded) {
                                System.out.println("Enter disease file name to save: ");
                                String saveFile = input.nextLine();
                                diseaseManager.saveDiseases(saveFile);
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }
                        case 3 -> {
                            if (diseasesLoaded) {
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
                                } else {
                                    System.out.println("Invalid disease type.");
                                }
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }
                        case 4 -> {
                            if (diseasesLoaded && curesLoaded) {
                                System.out.print("Enter disease ID to remove: ");
                                int id = Integer.parseInt(input.nextLine());
                                diseaseManager.indexOfDisease(id); 
                                if (diseaseManager.indexOfDisease(id) == -1) {
                                    System.out.println("Disease with ID " + id + " not found.");
                                    
                                } else {
                                    diseaseManager.removeDisease(id);
                                    cureManager.removeCure(id); 
                                    System.out.println("Disease with ID " + id + " and associated cure removed successfully.");     
                                }
                                
                            } else {
                                System.out.println("Please load both diseases and cures first.");
                            }
                        }
                        case 5 -> {
                            if (diseasesLoaded) {
                                System.out.print("Enter disease name: ");
                                String name = input.nextLine();
                                diseaseManager.searchByName(name);
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }
                        case 6 -> {
                            if (diseasesLoaded) {
                                System.out.print("Enter disease ID: ");
                                int id = Integer.parseInt(input.nextLine());
                                diseaseManager.searchByID(id);
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }
                        case 7 -> {
                            if (diseasesLoaded) {
                                diseaseManager.sortByID();;
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }
                        case 8 -> {
                            if (diseasesLoaded) {
                                diseaseManager.sortByMortality();
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }
                        case 9 -> {
                            if (diseasesLoaded) {
                                diseaseManager.listAllDisease();
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }
                        case 10 -> {
                            if (diseasesLoaded) {
                                diseaseManager.listBacteria();
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }
                        case 11 -> {
                            if (diseasesLoaded) {
                                diseaseManager.listVirus();
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }
                        case 12 -> {
                            System.out.print("Enter cure file name: ");
                            cureFile = input.nextLine();
                            cureManager = new CureManager(cureFile);
                            curesLoaded = true;
                        }
                        case 13 -> {
                            if (curesLoaded) {
                                System.out.println("Enter cure file name to save: ");
                                String saveFile = input.nextLine();
                                cureManager.saveCures(saveFile);
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }
                        case 14 -> {
                            if (curesLoaded) {
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
                                } else {
                                    System.out.println("Invalid cure type.");
                                }
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }
                        case 15 -> {
                            if (curesLoaded) {
                                System.out.print("Enter disease ID: ");
                                int id = Integer.parseInt(input.nextLine());
                                Disease disease = diseaseManager.searchByID(id);
                                cureManager.searchByDisease(disease);
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }
                        case 16 -> {
                            if (curesLoaded) {
                                System.out.print("Enter cure ID: ");
                                int id = Integer.parseInt(input.nextLine());
                                cureManager.searchByID(id);
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }
                        case 17 -> {
                            if (curesLoaded) {
                                cureManager.sortByEfficacyRate();
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }
                        case 18 -> {
                            if (curesLoaded) {
                                cureManager.listAllCures();
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }
                        case 19 -> {
                            if (curesLoaded) {
                                cureManager.listAllAntibiotics();
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }
                        case 20 -> {
                            if (curesLoaded) {
                                cureManager.listAllVaccines();
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }
                        case 0 -> {
                            if (diseasesLoaded && curesLoaded) {
                                setupComplete = true;
                            } else {
                                System.out.println("You must load both diseases (1) and cures (12) first.");
                            }
                        }
                        default -> System.out.println("Invalid choice. Try again.");
                    }

                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer choice.");
                    input.nextLine(); 
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
        simulation = new DiseaseSim(chosenDisease, cureFile, diseaseFile, peopleFile, regionFile, x, y); 
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
