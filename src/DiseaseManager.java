import java.io.*;
import java.util.*;

public class DiseaseManager {
    private ArrayList<Disease> diseases;

    public DiseaseManager() {
        diseases = new ArrayList<>();
    }

    public DiseaseManager(String filename) {
        diseases = new ArrayList<>();
        diseases.clear();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            int count = Integer.parseInt(in.readLine());
            for (int i = 0; i < count; i++) {
                String type = in.readLine();
                String name = in.readLine();
                int diseaseID = Integer.parseInt(in.readLine());
                double transmissionRate = Double.parseDouble(in.readLine());
                double mortalityRate = Double.parseDouble(in.readLine());

                if (type.equalsIgnoreCase("Bacteria")) {
                    double resistanceLevel = Double.parseDouble(in.readLine());
                    diseases.add(new Bacteria(name, diseaseID, transmissionRate, mortalityRate, resistanceLevel));
                    System.out.println("Disease #" + (i+1) + " added successfully.");
                } else if (type.equalsIgnoreCase("Virus")) {
                    double mutationRate = Double.parseDouble(in.readLine());
                    diseases.add(new Virus(name, diseaseID, transmissionRate, mortalityRate, mutationRate));
                    System.out.println("Disease #" + (i+1) + " added successfully.");
                } else {
                    System.out.println("Unknown disease type: " + type);
                }
            }

            in.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public boolean loadDiseases(String filename) {
        diseases.clear();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            int count = Integer.parseInt(in.readLine());
            for (int i = 0; i < count; i++) {
                String type = in.readLine();
                String name = in.readLine();
                int diseaseID = Integer.parseInt(in.readLine());
                double transmissionRate = Double.parseDouble(in.readLine());
                double mortalityRate = Double.parseDouble(in.readLine());

                if (type.equalsIgnoreCase("Bacteria")) {
                    double resistanceLevel = Double.parseDouble(in.readLine());
                    diseases.add(new Bacteria(name, diseaseID, transmissionRate, mortalityRate, resistanceLevel));
                    System.out.println("Disease #" + (i+1) + " added successfully.");
                } else if (type.equalsIgnoreCase("Virus")) {
                    double mutationRate = Double.parseDouble(in.readLine());
                    diseases.add(new Virus(name, diseaseID, transmissionRate, mortalityRate, mutationRate));
                    System.out.println("Disease #" + (i+1) + " added successfully.");
                } else {
                    System.out.println("Unknown disease type: " + type);
                }
                in.readLine(); //each will be separated by a space
            }

            in.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
    }

    public boolean saveDiseases(String filename) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            out.write(Integer.toString(diseases.size()));
            out.newLine();
            for (Disease d : diseases) {
                out.write(d.toString());
                out.newLine();
            }
            out.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving to file.");
            return false;
        }
    }

    public boolean addDisease(Disease disease) {
        if (disease != null && !diseases.contains(disease)) {
            diseases.add(disease);
            return true;
        }
        return false;
    }

    public boolean removeDisease(int index) {
        if (index >= 0 && index < diseases.size()) {
            diseases.remove(index);
            return true;
        }
        return false;
    }

    public Disease mostDeadlyDisease() {
        if (diseases.isEmpty()) return null;
        Disease mostDeadly = diseases.get(0);
        for (Disease d : diseases) {
            if (d.getMortalityRate() > mostDeadly.getMortalityRate()) {
                mostDeadly = d;
            }
        }
        return mostDeadly;
    }

    // Selection Sort by mortality rate (descending)
    public void sortByMortality() {
        for (int i = 0; i < diseases.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < diseases.size(); j++) {
                if (diseases.get(j).getMortalityRate() > diseases.get(maxIndex).getMortalityRate()) {
                    maxIndex = j;
                }
            }
            if (i != maxIndex) {
                Disease temp = diseases.get(i);
                diseases.set(i, diseases.get(maxIndex));
                diseases.set(maxIndex, temp);
            }
        }
    }

    public void listAllDisease() {
        for (Disease d : diseases) {
            System.out.println(d);
        }
    }

    public void listBacteria() {
        for (Disease d : diseases) {
            if (d instanceof Bacteria) {
                System.out.println(d);
            }
        }
    }

    public void listVirus() {
        for (Disease d : diseases) {
            if (d instanceof Virus) {
                System.out.println(d);
            }
        }
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }

    public Disease getDiseaseByID(int id) {
        for (Disease d : diseases) {
            if (d.getDiseaseID() == id) return d;
        }
        return null;
    }
}
