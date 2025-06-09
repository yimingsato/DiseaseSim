public class Virus extends Disease {
    public Virus(String name, int diseaseID, int transmissionRate, int mortalityRate) {
        super(name, diseaseID, transmissionRate, mortalityRate);
    }

    public void spread() {
        // Implementation of how the virus spreads
        System.out.println("The virus " + getName() + " is spreading.");
    }
    
}
