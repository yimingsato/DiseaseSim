public class Virus extends Disease {
    public Virus(String name, int transmissionRate, int mortalityRate) {
        super(name, transmissionRate, mortalityRate);
    }

    public void spread() {
        // Implementation of how the virus spreads
        System.out.println("The virus " + getName() + " is spreading.");
    }

    public String toString() {
        return getName() + "Virus\n" + getTransmissionRate() + "\n" + getMortalityRate();
    }
    
}
