import java.util.InputMismatchException;
import java.util.Scanner;

public class SimulationRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String diseaseFile = null, cureFile = null, peopleFile, regionFile;
        System.out.println("\nWelcome to LAA's simple Disease Simulation project");

        DiseaseManager diseaseManager = new DiseaseManager();
        CureManager cureManager = new CureManager();
        DiseaseSim simulation = null;

        boolean diseasesLoaded = false;
        boolean curesLoaded = false;
        boolean setupComplete = false;

        while (!setupComplete) {
            System.out.println("\n\n\n==== DISEASE & CURE DATABASE SETUP MENU (1 & 12 must be done first, -1 to exit) ====");
            System.out.println("1. Load Diseases from File");
            System.out.println("2. Save Diseases to a File");
            System.out.println("3. Add Disease");
            System.out.println("4. Remove Disease (Will also remove associated cure)");
            System.out.println("5. Search Disease by Name (Linear)");
            System.out.println("6. Search Disease by ID (Binary, assumes sorted by ID)");
            System.out.println("7. Search Disease by Mortality Rate and Transmission Rate");
            System.out.println("8. Sort by ID");
            System.out.println("9. Sort by Mortality Rate (then Transmission Rate if equal)");
            System.out.println("10. List Diseases");
            System.out.println("11. List Bacteria");
            System.out.println("12. List Viruses");
            System.out.println("==========================================");
            System.out.println("13. Load Cures from File");
            System.out.println("14. Save Cures to a File");
            System.out.println("15. Add Cure");
            System.out.println("16. Search Cure by Disease ID, (Assumes diseases are sorted by ID)");
            System.out.println("17. Search Cure by ID");
            System.out.println("18. Sort Cures by Efficacy Rate");
            System.out.println("19. List Cures");
            System.out.println("20. List Antibiotics");
            System.out.println("21. List Vaccines");

            System.out.println("-1. Exit");
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
                            diseaseManager = new DiseaseManager();
                            if (diseaseManager.loadDiseases(diseaseFile)) {
                                System.out.println("Diseases loaded successfully from " + diseaseFile);
                                diseasesLoaded = true;

                            } else {
                                System.out.println("Failed to load diseases. Please check the file path.");
                            }
                        }

                        case 2 -> {
                            if (diseasesLoaded) {
                                System.out.print("Enter disease file name to save: ");
                                String saveFile = input.nextLine();
                                if (diseaseManager.saveDiseases(saveFile)) {
                                    System.out.println("Diseases saved successfully to " + saveFile);
                                } else {
                                    System.out.println("Failed to save diseases. Please check the file path.");
                                }
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
                                    if (diseaseManager.addDisease(new Bacteria(name, id, tRate, mRate, resistance))) {
                                        System.out.println("Bacteria added successfully.");
                                    } else {
                                        System.out.println("Failed to add bacteria. May already be in the system.");
                                    }
                                } else if (type.equalsIgnoreCase("Virus")) {
                                    System.out.print("Enter mutation rate: ");
                                    double mutation = Double.parseDouble(input.nextLine());
                                    if (diseaseManager.addDisease(new Virus(name, id, tRate, mRate, mutation))) {
                                        System.out.println("Virus added successfully.");
                                    } else {
                                        System.out.println("Failed to add virus. May already be in the system.");
                                    }
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
                                if (diseaseManager.indexOfDisease(id) == -1) {
                                    System.out.println("Disease with ID " + id + " not found.");
                                    
                                } else {
                                    if (diseaseManager.removeDisease(id) && cureManager.removeCure(id)) {
                                        System.out.println("Disease with ID " + id + " and associated cure removed successfully.");
                                    } else {
                                        System.out.println("Failed to remove disease or associated cure. Please check the ID.");
                                    }
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
                                Disease disease = diseaseManager.searchByName(name);
                                if (disease == null) {
                                    System.out.println("Disease with name '" + name + "' not found.");
                                } else {
                                    System.out.println("Disease information: " + disease);
                                }
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }

                        case 6 -> {
                            if (diseasesLoaded) {
                                System.out.print("Enter disease ID: ");
                                int id = Integer.parseInt(input.nextLine());
                                Disease disease = diseaseManager.searchByID(id);
                                if (disease == null) {
                                    System.out.println("Disease with ID " + id + " not found.");
                                } else {
                                    System.out.println("Disease information: " + disease);
                                }
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }

                        case 7 -> {
                            if (diseasesLoaded) {
                                System.out.print("Enter mortality rate: ");
                                double mRate = Double.parseDouble(input.nextLine());
                                System.out.print("Enter transmission rate: ");
                                double tRate = Double.parseDouble(input.nextLine());
                                Disease disease = diseaseManager.searchByMortalityAndTransmission(mRate, tRate);
                                if (disease == null) {
                                    System.out.println("No disease found with the specified mortality and transmission rates.");
                                } else {
                                    System.out.println("Disease information: " + disease);}
                                System.out.println("Disease information: " + diseaseManager.searchByMortalityAndTransmission(mRate, tRate));
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }

                        case 8 -> {
                            if (diseasesLoaded) {
                                diseaseManager.sortByID();
                                System.out.println("Diseases sorted by ID.");
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }

                        case 9 -> {
                            if (diseasesLoaded) {
                                diseaseManager.sortByMortalityThenTransmission();
                                System.out.println("Diseases sorted by mortality rate (then transmission rate if equal).");
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }

                        case 10 -> {
                            if (diseasesLoaded) {
                                System.out.println("Listing all diseases:");
                                diseaseManager.listAllDisease();
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }

                        case 11 -> {
                            if (diseasesLoaded) {
                                System.out.println("Listing all bacteria:");
                                diseaseManager.listBacteria();
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }

                        case 12 -> {
                            if (diseasesLoaded) {
                                System.out.println("Listing all viruses:");
                                diseaseManager.listVirus();
                            } else {
                                System.out.println("Please load diseases first (option 1).");
                            }
                        }

                        case 13 -> {
                            System.out.print("Enter cure file name: ");
                            cureFile = input.nextLine();
                            if (cureManager.loadCures(cureFile)) {
                                System.out.println("Cures loaded successfully from " + cureFile);
                                curesLoaded = true;
                            } else {
                                System.out.println("Failed to load cures. Please check the file path.");
                            }
                        }

                        case 14 -> {
                            if (curesLoaded) {
                                System.out.print("Enter cure file name to save: ");
                                String saveFile = input.nextLine();
                                if (cureManager.saveCures(saveFile)) { 
                                    System.out.println("Cures saved successfully to " + saveFile);
                                } else {
                                    System.out.println("Failed to save cures. Please check the file path.");
                                }
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }

                        case 15 -> {
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
                                    if (cureManager.addCure(new Vaccine(name, id, eff))) {
                                        System.out.println("Vaccine added successfully.");
                                    } else {
                                        System.out.println("Failed to add vaccine. May already be in the system.");
                                    }
                                } else if (type.equalsIgnoreCase("Antibiotic")) {
                                    if (cureManager.addCure(new Antibiotic(name, id, eff))){ 
                                        System.out.println("Antibiotic added successfully.");
                                    } else {
                                        System.out.println("Failed to add antibiotic. May already be in the system.");
                                    }
                                } else {
                                    System.out.println("Invalid cure type.");
                                }
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }

                        case 16 -> {
                            if (curesLoaded) {
                                System.out.print("Enter disease ID: ");
                                int id = Integer.parseInt(input.nextLine());
                                Disease disease = diseaseManager.searchByID(id);
                                if (disease == null) {
                                    System.out.println("Disease with ID " + id + " not found.");
                                } else {
                                    Cure cure = cureManager.searchByDisease(disease);
                                    if (cure == null) {
                                        System.out.println("No cure found for disease with ID " + id);
                                    } else {
                                        System.out.println("Cure by disease: \n" + cure);
                                    }
                                }
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }

                        case 17 -> {
                            if (curesLoaded) {
                                System.out.print("Enter cure ID: ");
                                int id = Integer.parseInt(input.nextLine());
                                Cure cure = cureManager.searchByID(id);
                                if (cure == null) {
                                    System.out.println("Cure with ID " + id + " not found.");
                                } else {
                                    System.out.println("Cure information: " + cure);
                                }
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }

                        case 18 -> {
                            if (curesLoaded) {
                                System.out.println("Sorting cures by efficacy rate...");
                                cureManager.sortByEfficacyRate();
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }

                        case 19 -> {
                            if (curesLoaded) {
                                System.out.println("Listing all cures:");
                                cureManager.listAllCures();
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }

                        case 20 -> {
                            if (curesLoaded) {
                                System.out.println("Listing all antibiotics:");
                                cureManager.listAllAntibiotics();
                            } else {
                                System.out.println("Please load cures first (option 12).");
                            }
                        }

                        case 21 -> {
                            if (curesLoaded) {
                                System.out.println("Listing all vaccines:");
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

                        case -1 -> {
                            System.out.println("Exiting setup menu. Goodbye.");
                            input.close();
                            return; // Exit the program
                        }

                        default -> System.out.println("Invalid choice. Try again.");
                    }

                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    input.nextLine(); 
                }
            }
        }


        boolean runAgain = true;

        while (runAgain) {
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
            boolean validX = false;
            while (!validX) {
                try {
                    System.out.print("Enter starting x coordinate (1-10): ");
                    x = input.nextInt();
                    if (x >= 1 && x <= 10) {
                        validX = true;
                    } else {
                        System.out.println("X must be between 1 and 10.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    input.next(); // clear
                }
            }

            boolean validY = false;
            while (!validY) {
                try {
                    System.out.print("Enter starting y coordinate (1-10): ");
                    y = input.nextInt();
                    if (y >= 1 && y <= 10) {
                        validY = true;
                    } else {
                        System.out.println("Y must be between 1 and 10.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    input.next(); // clear
                }
            }

            input.nextLine(); // consume leftover newline
            System.out.print("Do you want to apply cures during the simulation? (yes/no): ");
            String applyCureResponse = input.nextLine();
            boolean applyCure = applyCureResponse.equalsIgnoreCase("yes");

            simulation = new DiseaseSim(chosenDisease, cureFile, diseaseFile, peopleFile, regionFile, x, y); 
            System.out.println("\nSimulation initialized with the following parameters:");
            System.out.println("Disease: \n" + chosenDisease);
            System.out.printf("Cure File: %s%n", cureFile);
            System.out.printf("Starting Coordinates: (%d, %d)%n", x, y);
        
            if (applyCure && cureManager.searchByDisease(chosenDisease) != null) {
                System.out.println("Cure will be applied before the simulation.");
                System.out.println(simulation.applyCureRandomly(cureManager.searchByDisease(chosenDisease)) + " individuals have the cure applied: " + cureManager.searchByDisease(chosenDisease)); 
            } else {
                System.out.println("No cure will be applied during the simulation.");
            }

            boolean finished = false;
            while (!finished) {
                System.out.print("Enter number of days to simulate (0 to stop): ");
                int days = input.nextInt();
                
                // if(applyCure) {
                //     simulation.applyCuresOverDays(numToCure, days);
                // } 

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

                    simulation.displayMap(simulation.getPopulationMap());
                    if (simulation.isEveryoneInfectedOrDead(simulation.getPopulationMap())) {
                        System.out.println("All individuals are either infected or dead.");
                        finished = true;
                    }
                }
            }

            input.nextLine(); // clear buffer
            System.out.print("\nDo you want to run another simulation? (yes/no): ");
            String response = input.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                runAgain = false;
                System.out.println("Exiting simulation program. Goodbye.");
            }
        }
        input.close();
    }
}
