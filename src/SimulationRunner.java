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

        System.out.print("Enter the people file name: (people.txt) ");
        String peopleFile = input.nextLine();

        System.out.print("Enter the region file name: (regions.txt) ");
        String regionFile = input.nextLine(); 

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

        DiseaseSim simulation = new DiseaseSim(chosenDisease, cureFile, diseaseFile, peopleFile, regionFile, x, y); 

        System.out.println("Simulation initialized with the following parameters:");
        System.out.printf("Disease: %s\n", chosenDisease.getName());
        System.out.printf("Cure File: %s\n", cureFile);
        System.out.printf("Disease File: %s\n", diseaseFile);
        System.out.printf("People File: %s\n", peopleFile);
        System.out.printf("Region File: %s\n", regionFile);
        System.out.printf("Starting Coordinates: (%d, %d)\n", x, y);
        System.out.printf("Total Days to Simulate: %d\n", totalDays);
    
        simulation.simulateSpread(totalDays); 

        if (applyCure) {
            System.out.print("Enter number of people to cure per day: ");
            int numToCure = input.nextInt();
            simulation.applyCuresOverDays(numToCure, 1);
        }

        System.out.printf("Infection Rate: %.2f%%\n", simulation.getInfectionRate() * 100);
        System.out.printf("Mortality Rate: %.2f%%\n", simulation.getTotalMortalityRate() * 100);

        System.out.println("\nSimulation Complete.");
        System.out.printf("Total Infected: %d out of %d\n", simulation.getPopulationInfected(), simulation.getPopulation());
        System.out.printf("Final Infection Rate: %.2f%%\n", simulation.getInfectionRate() * 100);
        System.out.printf("Final Mortality Rate: %.2f%%\n", simulation.getTotalMortalityRate() * 100);

        input.close();
    }
}
