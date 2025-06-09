import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class DiseaseManager {
    Disease[] diseases;
    int numDiseases;
    int maxDiseases;
    

//Constructor
public DiseaseManager(int maxDiseases) {
    this.maxDiseases = maxDiseases;
    this.diseases = new Disease[maxDiseases];
    this.numDiseases = 0;
}

public boolean loadDisease(String filename){
    numDiseases = 0;
        
        try{
            BufferedReader in = new BufferedReader(new FileReader(filename));
            int arrayLength = Integer.parseInt(in.readLine());
            String name;
            String type;
            int transmissionRate, mortalityRate, numInfected;

            for(int i = 0; i < arrayLength; i++) {
                //need to implement the way to read the file
                name = in.readLine(); // Read the name of the cure
                type = in.readLine(); // Read the type of the cure
                if(type.equals("Virus")){
                    transmissionRate = Integer.parseInt(in.readLine()); // Read the transmission rate
                    mortalityRate = Integer.parseInt(in.readLine()); // Read the mortality rate
                    numInfected = Integer.parseInt(in.readLine()); // Read the number of infected
                    Disease disease = new Virus(name, diseaseID, transmissionRate, mortalityRate); // Create a Virus object
                } else if(type.equals("Bacteria")) {
                    transmissionRate = Integer.parseInt(in.readLine()); // Read the transmission rate
                    mortalityRate = Integer.parseInt(in.readLine()); // Read the mortality rate
                    numInfected = Integer.parseInt(in.readLine()); // Read the number of infected
                    Disease disease = new Bacteria(name, diseaseID, transmissionRate, mortalityRate); // Create a Bacteria object
                } else {
                    System.out.println("Unknown disease type: " + type);
                }
            }
            in.close();
        }catch(IOException iox){
            System.out.println("error reading file ");
        }
}

    public boolean saveDiseases(String filename) {
        try {
         BufferedWriter out = new BufferedWriter(new FileWriter(filename));
         out.write(Integer.toString(numDiseases));
         out.newLine();
         for (int i = 0; i < numDiseases; i++) {
            out.write(diseases[i].toString());
            out.newLine();
         }
         out.close();
      } catch (IOException e) {
         System.out.println("Error saving to file.");
      }
        return true; // Placeholder return value
    }


public boolean addDisease(Disease disease) {
        if (numDiseases < maxDiseases) {
            diseases[numDiseases++] = disease;
            return true;
        }
        return false; // No space to add new cure
    }
    
    public void removeDisease(int index) {
        if (index >= 0 && index < numDiseases) {
            for (int i = index; i < numDiseases - 1; i++) {
                diseases[i] = diseases[i + 1];
            }
            diseases[--numDiseases] = null; // Clear last element
        }
    }  


public Disease mostDeadlyDisease() {
        if (numDiseases == 0) {
            return null; // No diseases available
        }
        Disease mostDeadly = diseases[0];
        for (int i = 1; i < numDiseases; i++) {
            if (diseases[i].compareToMortalityRate(mostDeadly) > 0) {
                mostDeadly = diseases[i];
            }
        }
        return mostDeadly;
    }

public void sortByMortality() {
        for (int i = 0; i < numDiseases - 1; i++) {
            for (int j = i + 1; j < numDiseases; j++) {
                if (diseases[i].compareToMortalityRate(diseases[j]) < 0) {
                    // Swap diseases[i] and diseases[j]
                    Disease temp = diseases[i];
                    diseases[i] = diseases[j];
                    diseases[j] = temp;
                }
            }
        }
    }

public void listAllDisease() {
        for (int i = 0; i < numDiseases; i++) {
            System.out.println(diseases[i]);
        }
    }
}

    public void listBacteria() {
        for (int i = 0; i < numDiseases; i++) {
            if (diseases[i] instanceof Bacteria) {
                System.out.println(diseases[i]);
            }
        }
    }

    public void listVirus() {
        for (int i = 0; i < numDiseases; i++) {
            if (diseases[i] instanceof Virus) {
                System.out.println(diseases[i]);
            }
        }

}
