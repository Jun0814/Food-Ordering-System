/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TPY
 */
public class TextStorageSystem {
    
    public HashMap<Integer, ArrayList<String>> data = new HashMap<>();
    private int currentMaxId = 0;
    
    // Auto generate Id for each data
    private void assignID(String filename){
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim());
                    String[] values = parts[1].split(",");
                    data.put(id, new ArrayList<>(Arrays.asList(values)));
                    currentMaxId = Math.max(currentMaxId, id);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TextStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    // Append single data
    public void appendData(String value, String filename){
        assignID(filename);
        int newId = ++currentMaxId; // Generate a new ID
        data.putIfAbsent(newId, new ArrayList<>()); // If the key doesn't exist, create a new ArrayList
        data.get(newId).add(value);
        saveToFile(filename);
    }
    
    // Update single data By Id and Index
    public void updateData(int targetId, int indexId, String newest, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder updatedContent = new StringBuilder();
            String line;
            boolean isUpdated = false;

            // Read file line by line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim());

                    // Update only the targetId line
                    if (id == targetId) {
                        String[] values = parts[1].split(",");
                        ArrayList<String> list = new ArrayList<>(Arrays.asList(values));

                        if (indexId >= 0 && indexId < list.size()) {
                            list.set(indexId, newest);
                            isUpdated = true;
                            String updatedValues = String.join(",", list);
                            line = id + "=" + updatedValues;
                        } else {
                            System.out.println("Invalid indexId. No updates were made.");
                        }
                    }
                }
                // Append line to the updated content
                updatedContent.append(line).append(System.lineSeparator());
            }

            if (isUpdated) {
                // Write updated content back to the file
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                    bw.write(updatedContent.toString());
                }
                System.out.println("Updated index '" + indexId + "' to '" + newest + "' for key " + targetId);
            } else {
                System.out.println("No updates were made. Key or value not found.");
            }
        } catch (IOException ex) {
            Logger.getLogger(TextStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing the file content. Ensure proper formatting.");
        }
    }
    
    // Retrieve single data by Id and Index
    public String retrieveData(int targetId, int indexId, String filename) {
        String output = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            // Read file line by line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim());

                    // Retrieve data for the targetId
                    if (id == targetId) {
                        String[] values = parts[1].split(",");
                        ArrayList<String> list = new ArrayList<>(Arrays.asList(values));

                        if (indexId >= 0 && indexId < list.size()) {
                            output = list.get(indexId);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TextStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing the file content. Ensure proper formatting.");
        }
        return output;
    }
    
    // Remove single record By Id
    public void removeRowById(int targetId, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder updatedContent = new StringBuilder();
            String line;
            boolean isRemoved = false;

            // Read file line by line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim());

                    // Skip adding the line if it matches the targetId
                    if (id == targetId) {
                        isRemoved = true;
                        continue;
                    }
                }
                // Append non-matching lines to the updated content
                updatedContent.append(line).append(System.lineSeparator());
            }

            if (isRemoved) {
                // Write updated content back to the file
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                    bw.write(updatedContent.toString());
                }
                System.out.println("Removed row for key " + targetId);
            } else {
                System.out.println("No row found for key " + targetId);
            }
        } catch (IOException ex) {
            Logger.getLogger(TextStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing the file content. Ensure proper formatting.");
        }
    }
        
    // Method to save the HashMap to a file
    public void saveToFile(String filename){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            if ((line = br.readLine()) == null) {                
                for (var entry : data.entrySet()) {
                    // Format: key=value1,value2,...
                    bw.write(entry.getKey() + "=" + String.join(",", entry.getValue()));
                    bw.newLine();
                }   
            }
        } catch (IOException ex) {
            Logger.getLogger(TextStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to load the HashMap from a file
    public void loadFromFile(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse the line: key=value1,value2,...
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    int key = Integer.parseInt(parts[0]);
                    String[] values = parts[1].split(",");
                    ArrayList<String> valueList = new ArrayList<>();
                    for (String value : values) {
                        valueList.add(value.trim());
                    }
                    data.put(key, valueList);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TextStorageSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
