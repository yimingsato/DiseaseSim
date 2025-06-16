import java.util.Scanner;

// public class SimulationRunner {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         CureManager cureManager = new CureManager();
//         DiseaseManager diseaseManager = new DiseaseManager();

//         System.out.println("Before beginning the simulation, you can choose from the following options:");
//         System.out.print("=======================================");

//         boolean running = true  ;
//         while (running) {
//             System.out.println("\n==== DISEASE DATABASE MENU ====");
//             System.out.println("1. Load Diseases from File");
//             System.out.println("2. Save Diseases to File");
//             System.out.println("3. Add Disease");
//             System.out.println("4. Remove Disease");
//             System.out.println("5. List All Diseases");
//             System.out.println("6. List Bacteria Only");
//             System.out.println("7. List Viruses Only");
//             System.out.println("8. Sort Diseases by Mortality");
//             System.out.println("9. Sort Diseases by ID");
//             System.out.println("10. Search Disease by ID (Binary Search)");
//             System.out.println("11. Find Most Deadly Disease");

//             System.out.println("\n==== CURE DATABASE MENU ====");
//             System.out.println("12. Load Cures from File");
//             System.out.println("13. Save Cures to File");
//             System.out.println("14. Add Cure");
//             System.out.println("15. Remove Cure");
//             System.out.println("16. List All Cures");
//             System.out.println("17. List Vaccines Only");
//             System.out.println("18. List Antibiotics Only");
//             System.out.println("19. Sort Cures by Efficacy");
//             System.out.println("20. Find Cure by Disease");
//             System.out.println("21. Find Cure by ID");

//             System.out.println("0. Exit");

//             System.out.print("\nEnter your choice: ");
//             int choice = Integer.parseInt(scanner.nextLine());

//             switch (choice) {
//                 case 1 -> {
//                     System.out.print("Enter filename to load diseases: ");
//                     String file = scanner.nextLine();
//                     diseaseManager.loadDiseases(file);
//                 }
//                 case 2 -> {
//                     System.out.print("Enter filename to save diseases: ");
//                     String file = scanner.nextLine();
//                     diseaseManager.saveDiseases(file);
//                 }
//                 case 3 -> {
//                     System.out.print("Enter type (Bacteria/Virus): ");
//                     String type = scanner.nextLine();
//                     System.out.print("Enter name: ");
//                     String name = scanner.nextLine();
//                     System.out.print("Enter ID: ");
//                     int id = Integer.parseInt(scanner.nextLine());
//                     System.out.print("Enter transmission rate: ");
//                     double tRate = Double.parseDouble(scanner.nextLine());
//                     System.out.print("Enter mortality rate: ");
//                     double mRate = Double.parseDouble(scanner.nextLine());

//                     if (type.equalsIgnoreCase("Bacteria")) {
//                         System.out.print("Enter resistance level: ");
//                         double resistance = Double.parseDouble(scanner.nextLine());
//                         diseaseManager.addDisease(new Bacteria(name, id, tRate, mRate, resistance));
//                     } else if (type.equalsIgnoreCase("Virus")) {
//                         System.out.print("Enter mutation rate: ");
//                         double mutation = Double.parseDouble(scanner.nextLine());
//                         diseaseManager.addDisease(new Virus(name, id, tRate, mRate, mutation));
//                     }
//                 }
//                 case 4 -> {
//                     System.out.print("Enter index to remove: ");
//                     int index = Integer.parseInt(scanner.nextLine());
//                     diseaseManager.removeDisease(index);
//                 }
//                 case 5 -> diseaseManager.listAllDisease();
//                 case 6 -> diseaseManager.listBacteria();
//                 case 7 -> diseaseManager.listVirus();
//                 case 8 -> diseaseManager.sortByMortality();
//                 case 9 -> diseaseManager.sortByID();
//                 case 10 -> {
//                     System.out.print("Enter disease ID: ");
//                     int id = Integer.parseInt(scanner.nextLine());
//                     Disease d = diseaseManager.binarySearchByID(id);
//                     System.out.println(d != null ? d : "Not found.");
//                 }
//                 case 11 -> {
//                     Disease d = diseaseManager.mostDeadlyDisease();
//                     System.out.println(d != null ? d : "No diseases.");
//                 }

//                 case 12 -> {
//                     System.out.print("Enter filename to load cures: ");
//                     String file = scanner.nextLine();
//                     cureManager.loadCures(file);
//                 }
//                 case 13 -> {
//                     System.out.print("Enter filename to save cures: ");
//                     String file = scanner.nextLine();
//                     cureManager.saveCures(file);
//                 }
//                 case 14 -> {
//                     System.out.print("Enter type (Vaccine/Antibiotic): ");
//                     String type = scanner.nextLine();
//                     System.out.print("Enter name: ");
//                     String name = scanner.nextLine();
//                     System.out.print("Enter ID: ");
//                     int id = Integer.parseInt(scanner.nextLine());
//                     System.out.print("Enter efficacy rate: ");
//                     double eff = Double.parseDouble(scanner.nextLine());

//                     if (type.equalsIgnoreCase("Vaccine")) {
//                         cureManager.addCure(new Vaccine(name, id, eff));
//                     } else if (type.equalsIgnoreCase("Antibiotic")) {
//                         cureManager.addCure(new Antibiotic(name, id, eff));
//                     }
//                 }
//                 case 15 -> {
//                     System.out.print("Enter index to remove: ");
//                     int index = Integer.parseInt(scanner.nextLine());
//                     cureManager.removeCure(index);
//                 }
//                 case 16 -> cureManager.listAllCures();
//                 case 17 -> cureManager.listAllVaccines();
//                 case 18 -> cureManager.listAllAntibiotics();
//                 case 19 -> cureManager.sortByEfficacyRate();
//                 case 20 -> {
//                     System.out.print("Enter disease ID to find cure: ");
//                     int id = Integer.parseInt(scanner.nextLine());
//                     Disease d = diseaseManager.getDiseaseByID(id);
//                     if (d != null) {
//                         Cure c = cureManager.searchByDisease(d);
//                         System.out.println(c != null ? c : "No matching cure found.");
//                     } else {
//                         System.out.println("Disease not found.");
//                     }
//                 }
//                 case 21 -> {
//                     System.out.print("Enter cure ID: ");
//                     int id = Integer.parseInt(scanner.nextLine());
//                     Cure c = cureManager.searchByID(id);
//                     System.out.println(c != null ? c : "Cure not found.");
//                 }

//                 case 0 -> {
//                     running = false;
//                     System.out.println("Exiting Database. Moving on to simulation.");
//                 }
//                 default -> System.out.println("Invalid choice. Try again.");
//             }
//         }
        
//         System.out.println("==============SIMULATION===============");
//         System.out.println("Grid of people and region will be intialized with default game files.");
//         System.out.println("Choose a disease to simulate:");
//         diseaseManager.listAllDisease();
//         System.out.print("Enter the name of the disease wished to simulate: ");
//         String diseaseName = Integer.parseInt(scanner.nextLine());
//         Disease disease = diseaseManager.searchByName(diseaseName);
//         if (disease == null) {
//             System.out.println("Disease not found. Exiting simulation.");
//             break;
//         }

//         scanner.close();
//     }
// }

import java.util.Scanner;

public class SimulationRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Disease Simulation Setup");
        System.out.println("==========================================");

        DiseaseManager diseaseManager = new DiseaseManager();
        CureManager cureManager = new CureManager();

        boolean setupComplete = false;
        while (!setupComplete) {
            System.out.println("\n==== DATABASE SETUP MENU ====");
            System.out.println("1. Load Diseases from File");
            System.out.println("2. Load Cures from File");
            System.out.println("3. Add Disease");
            System.out.println("4. Add Cure");
            System.out.println("5. List Diseases");
            System.out.println("6. List Cures");
            System.out.println("0. Proceed to Simulation");
            System.out.print("\nEnter your choice: ");

            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter disease file name: ");
                    diseaseManager.loadDiseases(input.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter cure file name: ");
                    cureManager.loadCures(input.nextLine());
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

        System.out.print("Enter the cure file name: ");
        String cureFile = input.nextLine();

        System.out.print("Enter the disease file name: ");
        String diseaseFile = input.nextLine();

        // System.out.print("Enter the people file name: ");
        // String peopleFile = input.nextLine();

        // System.out.print("Enter the region file name: ");
        // String regionFile = input.nextLine(); 

        System.out.print("Enter starting x coordinate (0-9): ");
        int x = input.nextInt();

        System.out.print("Enter starting y coordinate (0-9): ");
        int y = input.nextInt();

        System.out.print("Enter total number of days to simulate: ");
        int totalDays = input.nextInt();

        System.out.print("Do you want to apply cures during the simulation? (yes/no): ");
        input.nextLine(); // consume newline
        String applyCureResponse = input.nextLine();
        boolean applyCure = applyCureResponse.equalsIgnoreCase("yes");

        DiseaseSim simulation = new DiseaseSim(
            chosenDisease, cureFile, diseaseFile, peopleFile, regionFile, x, y, totalDays
        );

        System.out.println("\nInitial Health Map:");
        simulation.printMap();

        for (int day = 1; day <= totalDays; day++) {
            System.out.println("\nDay " + day + ":");
            simulation.simulateSpread(1);
            simulation.printMap();

            if (applyCure) {
                System.out.print("Enter number of people to cure on day " + day + ": ");
                int numToCure = input.nextInt();
                simulation.applyCuresOverDays(numToCure, 1);
            }

            System.out.printf("Infection Rate: %.2f%%\n", simulation.getInfectionRate() * 100);
            System.out.printf("Mortality Rate: %.2f%%\n", simulation.getTotalMortalityRate() * 100);
        }

        System.out.println("\nSimulation Complete.");
        System.out.printf("Total Infected: %d out of %d\n", simulation.getPopulationInfected(), simulation.getPopulation());
        System.out.printf("Final Infection Rate: %.2f%%\n", simulation.getInfectionRate() * 100);
        System.out.printf("Final Mortality Rate: %.2f%%\n", simulation.getTotalMortalityRate() * 100);

        input.close();
    }
}
