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
        diseaseManager.loadDisease(filename);
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
        
        

    }
}
