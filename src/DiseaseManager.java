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

public boolean loadDisease(String){
    numDiseases = 0;
        
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
saveDisease(): boolean
addDisease(): boolean
removeDisease(): boolean
mostDeadlyDisease(): Disease
sortByMortality()
listAllDisease()
listBacteria()
listVirus()
Accessor/mutators
toString()

}
