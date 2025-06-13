import java.io.*;
import java.util.*;

public class CureManager {
    private ArrayList<Cure> cures;

    public CureManager() {
        cures = new ArrayList<>();
    }

    public boolean loadCures(String filename) {
        cures.clear();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            int count = Integer.parseInt(in.readLine());
            for (int i = 0; i < count; i++) {
                String type = in.readLine();
                String name = in.readLine();
                int cureID = Integer.parseInt(in.readLine());
                double efficacyRate = Double.parseDouble(in.readLine());

                if (type.equalsIgnoreCase("Vaccine")) {
                    cures.add(new Vaccine(name, cureID, efficacyRate));
                } else if (type.equalsIgnoreCase("Antibiotic")) {
                    cures.add(new Antibiotic(name, cureID, efficacyRate));
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

    public boolean saveCures(String filename) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            out.write(Integer.toString(cures.size()));
            out.newLine();
            for (Cure cure : cures) {
                out.write(cure.toString());
                out.newLine();
            }
            out.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving to file.");
            return false;
        }
    }

    public boolean addCure(Cure cure) {
        if (cure != null && !cures.contains(cure)) {
            cures.add(cure);
            return true;
        }
        return false;
    }

    public boolean removeCure(int index) {
        if (index >= 0 && index < cures.size()) {
            cures.remove(index);
            return true;
        }
        return false;
    }

    public Cure searchByDisease(Disease d) {
        for (Cure cure : cures) {
            if (cure.getCureID() == d.getDiseaseID()) {
                return cure;
            }
        }
        return null;
    }

    public Cure searchByID(int id) {
        for (Cure cure : cures) {
            if (cure.getCureID() == id) {
                return cure;
            }
        }
        return null;
    }

    public void sortByEfficacyRate() {
        cures.sort((a, b) -> Double.compare(a.getEfficacyRate(), b.getEfficacyRate()));
    }

    public void listAllCures() {
        for (Cure cure : cures) {
            System.out.println(cure);
        }
    }

    public void listAllVaccines() {
        for (Cure cure : cures) {
            if (cure instanceof Vaccine) {
                System.out.println(cure);
            }
        }
    }

    public void listAllAntibiotics() {
        for (Cure cure : cures) {
            if (cure instanceof Antibiotic) {
                System.out.println(cure);
            }
        }
    }


    public ArrayList<Cure> getCures() {
        return cures;
    }
}
