import java.io.*;

public class CureManager {
    Cure[] cures; // Array to hold Cure objects
    int numCures; // Current number of cures
    int maxCures; // Maximum number of cures allowed

//CureManager() Constructor
    public CureManager(int maxCures) {
        this.maxCures = maxCures;
        this.cures = new Cure[maxCures];
        this.numCures = 0;
    }

    // Method to load cures from a file
    public void loadCures(String filename) {
        numCures = 0;
        
        try{
            BufferedReader in = new BufferedReader(new FileReader(filename));
            int arrayLength = Integer.parseInt(in.readLine());
            String name;
            String type;
            int efficacyRate;

            for(int i = 0; i < arrayLength; i++) {
                //need to implement the way to read the file
                name = in.readLine(); // Read the name of the cure
                type = in.readLine(); // Read the type of the cure
                if(type.equals("Vaccine")){
                    efficacyRate = Integer.parseInt(in.readLine()); // Read the efficacy rate for Vaccine
                    Cure cure = new Vaccine(name, efficacyRate); // Create a Vaccine object
                    addCure(cure); // Add the cure to the manager
                } else if(type.equals("Antibiotic")) {
                    efficacyRate = Integer.parseInt(in.readLine()); // Read the efficacy rate for Antidote
                    Cure cure = new Antibiotic(name, efficacyRate); // Create an Antidote object
                    addCure(cure); // Add the cure to the manager
                } else {
                    System.out.println("Unknown cure type: " + type);
                }

            }
            in.close();
        }catch(IOException iox){
            System.out.println("error reading file ");
        }
    }

    // Method to save cures to a file
    public boolean saveCures(String filename) {
        try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));
         out.write(Integer.toString(numCures));
         out.newLine();
         for (int i = 0; i < numCures; i++) {
            out.write(cures[i].toString());
            out.newLine();
         }
         out.close();
      } catch (IOException e) {
         System.out.println("Error saving to file.");
      }
        return true; // Placeholder return value
    }

    // Method to add a new cure
    public boolean addCure(Cure cure) {
        if (numCures < maxCures) {
            cures[numCures++] = cure;
            return true;
        }
        return false; // No space to add new cure
    }

    // Method to remove a cure by index
    public void removeCure(int index) {
        if (index >= 0 && index < numCures) {
            for (int i = index; i < numCures - 1; i++) {
                cures[i] = cures[i + 1];
            }
            cures[--numCures] = null; // Clear last element
        }
    }

    // Method to find a cure for a specific disease
    public Cure cureFor(Disease d) {
        for (int i = 0; i < numCures; i++) {
            if (cures[i] instanceof Vaccine && d instanceof Virus) {
                return cures[i];
            }else if (cures[i] instanceof Antibiotic && d instanceof Bacteria) {
                return cures[i];
            }
        }
        return null;
    }

    // Method to sort cures by efficacy rate
    public void sortByEfficacyRate() {
        for (int i = 0; i < numCures - 1; i++) {
            for (int j = i + 1; j < numCures; j++) {
                if (cures[i].getEfficacyRate() > cures[j].getEfficacyRate()) {
                    Cure temp = cures[i];
                    cures[i] = cures[j];
                    cures[j] = temp;
                }
            }
        }
    }

    // Method to list all cures
    public void listAllCures() {
        for (int i =
            0; i < numCures; i++) {
            System.out.println(cures[i]);
        }
    }
}
