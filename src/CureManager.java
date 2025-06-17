/*
Filename: CureManager.java
Description: This class manages a collection of Cure objects, allowing for loading, saving, adding, removing, and searching cures.
*/

import java.io.*;
import java.util.*;

public class CureManager {
    private ArrayList<Cure> cures;

    //Constructor
    // Initializes an empty list of cures
    public CureManager() {
        cures = new ArrayList<>();
    }
    //Constructor that loads cures from a file
    public CureManager(String filename) {
        cures = new ArrayList<>();
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
                    System.out.println("Cure #" + (i+1) + " added successfully.");
                } else if (type.equalsIgnoreCase("Antibiotic")) {
                    cures.add(new Antibiotic(name, cureID, efficacyRate));
                    System.out.println("Cure #" + (i+1) + " added successfully.");
                } else {
                    System.out.println("Unknown cure type: " + type);
                }
                in.readLine();
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /* 
     * Method to load cures from a file.
     * The file should contain the number of cures, followed by each cure's type, name, ID, and efficacy rate.
     * @param filename The name of the file to load cures from.
     * @return true if the cures were loaded successfully, false otherwise.
     */
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
                    System.out.println("Cure #" + (i+1) + " added successfully.");
                } else if (type.equalsIgnoreCase("Antibiotic")) {
                    cures.add(new Antibiotic(name, cureID, efficacyRate));
                    System.out.println("Cure #" + (i+1) + " added successfully.");
                } else {
                    System.out.println("Unknown cure type: " + type);
                }
                in.readLine();
            }
            in.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
    }

    /* 
     * Method to save the current list of cures to a file.
     * The file will contain the number of cures, followed by each cure's type, name, ID, and efficacy rate.
     * @param filename The name of the file to save cures to.
     * @return true if the cures were saved successfully, false otherwise.
     */
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

    /* 
     * Method to add a cure to the list.
     * @param cure The Cure object to be added.
     * @return true if the cure was added successfully, false if it was null or already exists.
     */
    public boolean addCure(Cure cure) {
        if (cure != null && !cures.contains(cure)) {
            cures.add(cure);
            return true;
        }
        return false;
    }

    /* 
     * Method to remove a cure from the list by its index.
     * @param index The index of the cure to be removed.
     * @return true if the cure was removed successfully, false if the index is out of bounds.
     */
    public boolean removeCure(int index) {
        if (index >= 0 && index < cures.size()) {
            cures.remove(index);
            return true;
        }
        return false;
    }

    /* 
     * Method to search for a cure by its associated disease.
     * @param d The Disease object to search for.
     * @return the Cure object if found, null otherwise.
     */
    public Cure searchByDisease(Disease d) {
        for (Cure cure : cures) {
            if (cure.getCureID() == d.getDiseaseID()) {
                return cure;
            }
        }
        return null;
    }

    public int indexOfCure(int id) {
        for (int i = 0; i < cures.size(); i++) {
            if (cures.get(i).getCureID() == id) {
                return i;
            }
        }
        return -1; // not found
    }

    /* 
     * Method to search for a cure by its ID, linear search.
     * @param id The ID of the cure to search for.
     * @return the Cure object if found, null otherwise.
     */
    public Cure searchByID(int id) {
        for (Cure cure : cures) {
            if (cure.getCureID() == id) {
                return cure;
            }
        }
        return null;
    }

    /* 
     * Method to sort the list of cures by their efficacy rate in ascending order using bubble sort.
     */
    public void sortByEfficacyRate() {
        for (int i = 0; i < cures.size() - 1; i++) {
            for (int j = 0; j < cures.size() - i - 1; j++) {
                if (cures.get(j).compareToEfficacy(cures.get(j + 1)) > 0) {
                    Cure temp = cures.get(j);
                    cures.set(j, cures.get(j + 1));
                    cures.set(j + 1, temp);
                }
            }
        }
    }

    /* 
     * method to list all cures in the collection.
     */
    public void listAllCures() {
        for (Cure cure : cures) {
            System.out.println(cure);
            System.out.println();
        }
    }

    /* 
     * Method to list all vaccines in the collection.
     * It iterates through the list of cures and prints only those that are instances of Vaccine.
     */
    public void listAllVaccines() {
        for (Cure cure : cures) {
            if (cure instanceof Vaccine) {
                System.out.println(cure);
                System.out.println();
            }
        }
    }

    /* 
     * Method to list all antibiotics in the collection.
     * It iterates through the list of cures and prints only those that are instances of Antibiotic.
     */
    public void listAllAntibiotics() {
        for (Cure cure : cures) {
            if (cure instanceof Antibiotic) {
                System.out.println(cure);
                System.out.println();

            }
        }
    }

    /* 
     * Method to get the list of all cures.
     * @return the ArrayList of Cure objects.
     */
    public ArrayList<Cure> getCures() {
        return cures;
    }
}
