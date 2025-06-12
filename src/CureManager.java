import java.io.*;

public class CureManager {
    private Cure[] cures;
    private int numCures;
    private int maxCures;

    public CureManager(int maxCures) {
        this.maxCures = maxCures;
        this.cures = new Cure[maxCures];
        this.numCures = 0;
    }

    public boolean loadCures(String filename) {
        numCures = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            int count = Integer.parseInt(in.readLine());
            for (int i = 0; i < count; i++) {
                String type = in.readLine();
                String name = in.readLine();
                double efficacyRate = Double.parseDouble(in.readLine());
                int cureID = Integer.parseInt(in.readLine());
                if (type.equalsIgnoreCase("Vaccine")) {
                    cures[numCures++] = new Vaccine(name, cureID, efficacyRate );
                } else if (type.equalsIgnoreCase("Antibiotic")) {
                    cures[numCures++] = new Antibiotic(name, cureID, efficacyRate);
                } else {
                    System.out.println("Unknown cure type: " + type);
                }
            }
            in.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
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
