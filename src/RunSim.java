import java.util.*;

public class RunSim {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DiseaseManager diseaseManager = new DiseaseManager();
        CureManager cureManager = new CureManager();
        SimulationMap simulationMap = new SimulationMap(0,0);

        System.out.println("Welcome to the Disease Simulation!");
        System.out.println("Enter file for Disease data:");
        String filename = sc.nextLine();
        diseaseManager.loadDiseases(filename);
        System.out.println("Enter file for Cure data:");
        filename = sc.nextLine();
        cureManager.loadCures(filename);
        System.out.println("Enter file for Simulation Map data:");
        filename = sc.nextLine();
        simulationMap.loadMap(filename);
        System.out.println("Loaded successfully!");

        System.out.println("Enter the number of days to run the simulation:");
        int days = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        System.out.println("Enter the starting coordinates (x y):");
        String[] coords = sc.nextLine().split(" ");
        int startX = Integer.parseInt(coords[0]);
        int startY = Integer.parseInt(coords[1]);
        System.out.println("Enter the disease name to simulate:");
        String diseaseName = sc.nextLine();
        Disease disease = diseaseManager.getDisease(diseaseName);
        if (disease == null) {
            System.out.println("Disease not found. Exiting simulation.");
            return;
        }
        System.out.println("Starting simulation with disease: " + diseaseName);
        DiseaseSim diseaseSim = new DiseaseSim(simulationMap, disease);
        for (int day = 0; day < days; day++) {
            System.out.println("Day " + (day + 1));
            diseaseSim.simulateStep();
            diseaseSim.runSimulation(startX, startY);
            // Optionally, print the state of the simulation map or population
        }
        


    }
}
