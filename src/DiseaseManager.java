import java.io.*;
import java.util.*;

// Note when running the spread method, keep track of the starting point. It spreads out like a ripple moving farther
// each time from the starting point. This means each Region has to have a starting point for the spread method.
// This can be hard coded since it doesnt matter.
// An infection days array is also used:
    /*
     *
int[][] infectionDays = new int[grid.length][grid[0].length];
for (int i = 0; i < grid.length; i++) {
    Arrays.fill(infectionDays[i], -1); // -1 means not reached
}

     */

public class DiseaseManager {
     private Disease[] diseases;
     private int numDiseases;
     private int maxDiseases;

     public DiseaseManager(int max) {
         diseases = new Disease[max];
         maxDiseases = max;
         numDiseases = 0;
     }

     public
    // public void assignCures(Collection<Cure> cures) {
    //     for (Cure cure : cures) {
    //         String diseaseName = cure.getTargetDisease();
    //         if (diseases.containsKey(diseaseName)) {
    //             diseases.get(diseaseName).setCure(cure);
    //         }
    //     }
    // }

    // //Constructor
    // public DiseaseManager(int maxDiseases) {
    //     this.maxDiseases = maxDiseases;
    //     this.diseases = new Disease[maxDiseases];
    //     this.numDiseases = 0;
    // }


    // public boolean saveDiseases(String filename) {
    //     try {
    //      BufferedWriter out = new BufferedWriter(new FileWriter(filename));
    //      out.write(Integer.toString(numDiseases));
    //      out.newLine();
    //      for (int i = 0; i < numDiseases; i++) {
    //         out.write(diseases[i].toString());
    //         out.newLine();
    //      }
    //      out.close();
    //   } catch (IOException e) {
    //      System.out.println("Error saving to file.");
    //   }
    //     return true; // Placeholder return value
    // }


    // public boolean addDisease(Disease disease) {
    //     if (numDiseases < maxDiseases) {
    //         diseases[numDiseases++] = disease;
    //         return true;
    //     }
    //     return false; // No space to add new cure
    // }

    // public void removeDisease(int index) {
    //     if (index >= 0 && index < numDiseases) {
    //         for (int i = index; i < numDiseases - 1; i++) {
    //             diseases[i] = diseases[i + 1];
    //         }
    //         diseases[--numDiseases] = null; // Clear last element
    //     }
    // }


    // public Disease mostDeadlyDisease() {
    //     if (numDiseases == 0) {
    //         return null; // No diseases available
    //     }
    //     Disease mostDeadly = diseases[0];
    //     for (int i = 1; i < numDiseases; i++) {
    //         if (diseases[i].compareToMortalityRate(mostDeadly) > 0) {
    //             mostDeadly = diseases[i];
    //         }
    //     }
    //     return mostDeadly;
    // }

    // public void sortByMortality() {
    //     for (int i = 0; i < numDiseases - 1; i++) {
    //         for (int j = i + 1; j < numDiseases; j++) {
    //             if (diseases[i].compareToMortalityRate(diseases[j]) < 0) {
    //                 // Swap diseases[i] and diseases[j]
    //                 Disease temp = diseases[i];
    //                 diseases[i] = diseases[j];
    //                 diseases[j] = temp;
    //             }
    //         }
    //     }
    // }

    // public void listAllDisease() {
    //     for (int i = 0; i < numDiseases; i++) {
    //         System.out.println(diseases[i]);
    //     }
    // }

    // public void listBacteria() {
    //     for (int i = 0; i < numDiseases; i++) {
    //         if (diseases[i] instanceof Bacteria) {
    //             System.out.println(diseases[i]);
    //         }
    //     }
    // }

    // public void listVirus() {
    //     for (int i = 0; i < numDiseases; i++) {
    //         if (diseases[i] instanceof Virus) {
    //             System.out.println(diseases[i]);
    //         }
    //     }

    // }
}
