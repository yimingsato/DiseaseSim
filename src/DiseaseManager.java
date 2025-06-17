/*
Filename: DiseaseManager.java
Description: This class manages a collection of diseases, allowing for loading, saving, adding, removing, and listing diseases. It also provides functionality to find the most deadly disease and sort diseases by mortality rate.
*/

import java.io.*;
import java.util.*;

public class DiseaseManager {
    private ArrayList<Disease> diseases;

    //Constructor
    // Initializes an empty list of diseases
    public DiseaseManager() {
        diseases = new ArrayList<>();
    }

    // Constructor that loads diseases from a file
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
                in.readLine();
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /* 
    * Method to load diseases from a file.
     * @param filename The name of the file to read diseases from.
     * @return true if diseases were loaded successfully, false otherwise.
     */
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

    /* 
     * Method to save diseases to a file.
     * @param filename The name of the file to write diseases to.
     * @return true if diseases were saved successfully, false otherwise.
     */
    public boolean saveDiseases(String filename) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            out.write(Integer.toString(diseases.size()));
            out.newLine();
            for (Disease d : diseases) {
                out.write(d.toString());
                out.newLine();;
            }
            out.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving to file.");
            return false;
        }
    }

    /* 
     * Method to add a disease to the collection.
     * @param disease The Disease object to be added.
     * @return true if the disease was added successfully, false if it is null or already exists.
     */
    public boolean addDisease(Disease disease) {
        if (disease != null && !diseases.contains(disease)) {
            diseases.add(disease);
            return true;
        }
        return false;
    }

    /* 
     * Method to remove a disease by its index.
     * @param index The index of the disease to be removed.
     * @return true if the disease was removed successfully, false if the index is out of bounds.
     */
    public boolean removeDisease(int index) {
        if (index >= 0 && index < diseases.size()) {
            diseases.remove(index);
            return true;
        }
        return false;
    }

    /* Method to find the index of a disease by its id.
     * @param name The name of the disease to search for.
     * @return the index of the disease if found, -1 otherwise.
     */

    public int indexOfDisease(int id) {
        for (int i = 0; i < diseases.size(); i++) {
            if (diseases.get(i).getDiseaseID() == id) {
                return i;
            }
        }
        return -1; // not found
    }


    /* 
     * Method to find the most deadly disease in the collection.
     * @return the Disease object with the highest mortality rate, or null if the list is empty.
     */
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

    //Sort by mortality rate first, then by transmission rate
    public void sortByMortalityThenTransmission() {
        for (int i = 0; i < diseases.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < diseases.size(); j++) {
                Disease current = diseases.get(j);
                Disease max = diseases.get(maxIndex);

                // Compare by mortality rate first
                if (current.getMortalityRate() > max.getMortalityRate()) {
                    maxIndex = j;
                }
                // If equal, break tie with transmission rate
                else if (current.getMortalityRate() == max.getMortalityRate()) {
                    if (current.getTransmissionRate() > max.getTransmissionRate()) {
                        maxIndex = j;
                    }
                }
            }

            // Swap if needed
            if (i != maxIndex) {
                Disease temp = diseases.get(i);
                diseases.set(i, diseases.get(maxIndex));
                diseases.set(maxIndex, temp);
            }
        }
    }
    
    public Disease searchByMortalityAndTransmission(double mortalityRate, double transmissionRate) {
        for (Disease d : diseases) {
            if (d.getMortalityRate() == mortalityRate && d.getTransmissionRate() == transmissionRate) {
                return d;
            }
        }
        return null; // not found
    }

    public Disease searchByName(String name) {
        for (Disease d : diseases) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null; // not found
    }

    /* 
     * Method to sort the diseases by their ID rate in descending order.
     * It uses a selection sort algorithm.
     */
    public void sortByID() {
        for (int i = 0; i < diseases.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < diseases.size(); j++) {
                if (diseases.get(j).getDiseaseID() < diseases.get(minIndex).getDiseaseID()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Disease temp = diseases.get(i);
                diseases.set(i, diseases.get(minIndex));
                diseases.set(minIndex, temp);
            }
        }
    }

    /* 
     * Method to find the disease with a specific ID using binary search.
     * It assumes that the diseases list is sorted by ID.
     * @return the Disease object with the targetID.
     */
    public Disease searchByID(int targetID) {
        int bottom = 0;
        int top = diseases.size() - 1;

        while (bottom <= top) {
            int mid = (bottom + top) / 2;
            int midID = diseases.get(mid).getDiseaseID();

            if (midID == targetID) {
                return diseases.get(mid);
            } else if (midID < targetID) {
                bottom = mid + 1;
            } else {
                top = mid - 1;
            }
        }
        return null; // not found
    }

    /* 
     * Method to list all diseases in the collection.
     * It iterates through the list of diseases and prints each one.
     */
    public void listAllDisease() {
        for (Disease d : diseases) {
            System.out.println(d);
            System.out.println();
        }
    }

    /* 
     * Method to list all bacteria in the collection.
     * It iterates through the list of diseases and prints only those that are instances of Bacteria.
     */
    public void listBacteria() {
        for (Disease d : diseases) {
            if (d instanceof Bacteria) {
                System.out.println(d);
                System.out.println();
            }
        }
    }
    /* 
     * Method to list all viruses in the collection.
     * It iterates through the list of diseases and prints only those that are instances of Virus.
     */
    public void listVirus() {
        for (Disease d : diseases) {
            if (d instanceof Virus) {
                System.out.println(d);
                System.out.println();
            }
        }
    }

    /* 
     * Method to get the list of all diseases.
     * @return the ArrayList of Disease objects.
     */
    public ArrayList<Disease> getDiseases() {
        return diseases;
    }
}
