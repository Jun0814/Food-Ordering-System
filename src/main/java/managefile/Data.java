/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TPY
 */
public class Data {
    protected HashMap<Integer, ArrayList<String>> data = new HashMap<>();
    protected int currentMaxId = 0;
    
    // Auto generate Id for each data
    private void assignID(String filepath){
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
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
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*** Get absolute path from relative path ***/
   private String getResolvedPath(String relativeFilePath){        
        String resolvedPath = "";
        
        if (relativeFilePath != null) {
            File file = Paths.get(relativeFilePath).toFile();
            if (!file.isAbsolute()) {
                file = new File(System.getProperty("user.dir"), relativeFilePath);
            }
            resolvedPath = file.getAbsolutePath();
        }
        return resolvedPath;
    }
    
    // Create/Append data
    public void appendData (String content, String filepath){
        String resolvedPath = Data.this.getResolvedPath(filepath);
        assignID(resolvedPath);
        int newId = ++currentMaxId; // Generate a new ID
        data.putIfAbsent(newId, new ArrayList<>());
        data.get(newId).add(content);
        saveToFile(resolvedPath);
    }
    
    // Update single data By Id and Index 
    public void updateData(int targetId, int indexId, String newest, String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
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
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
                    bw.write(updatedContent.toString());
                }
                System.out.println("Updated index '" + indexId + "' to '" + newest + "' for key " + targetId);
            } else {
                System.out.println("No updates were made. Key or value not found.");
            }
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing the file content. Ensure proper formatting.");
        }
    }
    
    
    //*** Retrieve lines containing vendor data by Id In 2D Array ***//
    public String[][] retrieveDataAsArray(int indexId, String referenceId, String filepath) {

        String resolvedPath = Data.this.getResolvedPath(filepath);

        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resolvedPath))) {

            // Skip the first line (header)
            String headerLine = br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");

                if (line.trim().length() > 0 && credentials[indexId].equals(referenceId)) {
                    rows.add(credentials); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows.toArray(new String[0][0]);
    }

    
    //*** Retrieve single data by username and password based on the index ***//
    public String retrieveData(String username, String password, int indexId, String filepath) {

        String resolvedPath = Data.this.getResolvedPath(filepath);
        String output = null;

        try (BufferedReader br = new BufferedReader(new FileReader(resolvedPath))) {

            // Skip the first line (header)
            String headerLine = br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(","); 

                if (credentials.length > 4) {
                    String fileUsername = credentials[2].trim(); 
                    String filePassword = credentials[4].trim();

                    // Use .equals for String comparison
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        ArrayList<String> list = new ArrayList<>(Arrays.asList(credentials));

                        System.out.println(list); // Debugging purpose

                        // Retrieve data at the given index
                        if (indexId >= 0 && indexId < list.size()) {
                            output = list.get(indexId);
                        }
                        break; 
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing the file content. Ensure proper formatting.");
        }
        return output;
    }
        
    
    //*** Retrieve single data by Primary Key By Index ***// 
    public String retrieveData(String targetId, int indexId, String filepath) {
        
        String resolvedPath = Data.this.getResolvedPath(filepath);
        String output = null;
        
        try (BufferedReader br = new BufferedReader(new FileReader(resolvedPath))) {
            
            // Skip the first line (header)
            String headerLine = br.readLine();
            String line;

            // Read file line by line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > indexId) {
                    String primaryKey = data[0].trim();
                    if (primaryKey.equals(targetId)) {
                        output = data[indexId].trim();
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing the file content. Ensure proper formatting.");
        }
        return output;
    }
    
    
    //Remove single record By Id
    public void removeRowById(int targetId, String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
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
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))) {
                    bw.write(updatedContent.toString());
                }
                System.out.println("Removed row for key " + targetId);
            } else {
                System.out.println("No row found for key " + targetId);
            }
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing the file content. Ensure proper formatting.");
        }
    }
            
    // Method to save the HashMap to a file
    private void saveToFile(String filepath){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true))) {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line;
            if ((line = br.readLine()) == null) {                
                for (var entry : data.entrySet()) {
                    // Format: key=value1,value2,...
                    bw.write(entry.getKey() + "=" + String.join(",", entry.getValue()));
                    bw.newLine();
                }   
            }
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to load the HashMap from a file
    private void loadFromFile(String filepath){
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
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
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
