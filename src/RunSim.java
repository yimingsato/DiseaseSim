public class RunSim {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DiseaseManager diseaseManager = new DiseaseManager();
        CureManager cureManager = new CureManager();
        SimulationMap simulationMap = new SimulationMap(0,0);

        System.out.println("Welcome to the Disease Simulation!");
        System.out.println("Enter file for");
        String filename = sc.nextLine();

        //diseaseManager.loadDisease(filename);

    }
}
