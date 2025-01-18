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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

// If want to use this file, the txt file first line must have header

public class Data {
    
    
    //*** Get absolute path from relative path ***//
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
    
    
    /*** Update lines/new data to the file ***/
    public void insertData(String newLineContent, String filepath) {
        String resolvedPath = Data.this.getResolvedPath(filepath);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(resolvedPath, true))) {
            bw.write(newLineContent);
            bw.newLine(); 
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, "File I/O error", ex);
        }
    }
    
    
    //*** Update a single data element by the first ID and target index in a file ***/
    public void updateData(String targetId, int targetIndex, String newContent, String filepath) {
        String resolvedPath = Data.this.getResolvedPath(filepath);

        try (BufferedReader br = new BufferedReader(new FileReader(resolvedPath))) {
            StringBuilder updatedContent = new StringBuilder();

            // Read and store the header line
            String headerLine = br.readLine();
            if (headerLine != null) { updatedContent.append(headerLine).append(System.lineSeparator()); }

            String line;
            boolean isUpdated = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                // Check if the line's ID matches the target ID
                if (parts[0].trim().equals(targetId)) {
                    if (targetIndex >= 0 && targetIndex < parts.length) {
                        parts[targetIndex] = newContent;
                        isUpdated = true;
                    } else {
                        System.out.println("Invalid index. No updates were made for ID: " + targetId);
                    }
                }
                updatedContent.append(String.join(",", parts)).append(System.lineSeparator());
            }

            if (isUpdated) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(resolvedPath))) {
                    bw.write(updatedContent.toString());
                }
            } else {
                System.out.println("No updates were made. Key '" + targetId + "' not found.");
            }
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, "File I/O error", ex);
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing the file content. Ensure proper formatting.");
        }
    }
    
    
    //*** Update lines by the firstID ***
    public void updateData(String targetId, String newContent, String filepath) {
        String resolvedPath = Data.this.getResolvedPath(filepath);

        try (BufferedReader br = new BufferedReader(new FileReader(resolvedPath))) {
            StringBuilder updatedContent = new StringBuilder();

            // Read and store the header line
            String headerLine = br.readLine();
            if (headerLine != null) { updatedContent.append(headerLine).append(System.lineSeparator()); }

            String line;
            boolean isUpdated = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts[0].trim().equals(targetId)) {
                    updatedContent.append(newContent).append(System.lineSeparator());
                    isUpdated = true;
                } else {
                    updatedContent.append(line).append(System.lineSeparator());
                }
            }

            if (isUpdated) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(resolvedPath))) {
                    bw.write(updatedContent.toString());
                }
                System.out.println("Updated line with ID: " + targetId); // Debug Line
            } else {
                System.out.println("No updates were made. ID '" + targetId + "' not found.");
            }
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, "File I/O error", ex);
        }
    }

        
    //*** Retrieve lines containing vendor data by first id in 2d array ***//
    public String[][] retrieveDataAsArray(int indexId, String referenceId, String filepath) {

        String resolvedPath = Data.this.getResolvedPath(filepath);
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(resolvedPath))) {
            // Skip the header line
            String headerLine = br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > indexId && data[indexId].equals(referenceId)) {
                    rows.add(data);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, "Error reading file: " + filepath, e);
        }
        return rows.toArray(new String[rows.size()][]);
    }
    
    
    //*** Retrieve All Ids from certain file ***//
    public String[] retrieveIdsFromFile(String filepath) {
        String resolvedPath = Data.this.getResolvedPath(filepath);
        List<String> ids = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(resolvedPath))) {
            // Skip the header line
            String headerLine = br.readLine();
            String line;

            // Read through all rows in the file
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    // Add the first column (ID) to the list
                    ids.add(data[0]);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, "Error reading file: " + filepath, e);
        }

        // Return the list of IDs as an array
        return ids.toArray(new String[0]);
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
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        ArrayList<String> list = new ArrayList<>(Arrays.asList(credentials));
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
        
    
    //*** Retrieve single data by first ID and index ***// 
    public String retrieveData(String targetId, int indexId, String filepath) {
        
        String resolvedPath = Data.this.getResolvedPath(filepath);
        String output = null;
        
        try (BufferedReader br = new BufferedReader(new FileReader(resolvedPath))) {
            
            // Skip the first line (header)
            String headerLine = br.readLine();
            String line;

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
    
    
    //*** Remove a single record by the first ID in a file ***/
    public void removeRowById(String targetId, String filepath) {
        String resolvedPath = Data.this.getResolvedPath(filepath);

        try (BufferedReader br = new BufferedReader(new FileReader(resolvedPath))) {
            StringBuilder updatedContent = new StringBuilder();
            String line;
            boolean isRemoved = false;

            String headerLine = br.readLine();
            if (headerLine != null) {
                updatedContent.append(headerLine).append(System.lineSeparator());
            }

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0].trim();

                if (id.equals(targetId)) {
                    isRemoved = true; 
                    continue;         
                }
                updatedContent.append(line).append(System.lineSeparator());
            }

            if (isRemoved) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(resolvedPath))) {
                    bw.write(updatedContent.toString());
                }
                System.out.println("Removed row for key: " + targetId);
            } else {
                System.out.println("No row found for key: " + targetId);
            }
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, "File I/O error", ex);
        } catch (NumberFormatException ex) {
            System.out.println("Error parsing the file content. Ensure proper formatting.");
        }
    }
    
//  Read all data from txt fill and store as lst
    public static List<String[]> readRolesFromFile(String filePath){
        List<String[]> roles = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                roles.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return roles;
    }
    
    public void addGeneralFile(List<String> generalList,String filepath) throws IOException{
        FileWriter fw = new FileWriter(filepath,true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            String cartRow = "";
            for (int i = 0; i < generalList.size(); i++) {
                if (generalList.size()-1 == i){
                    cartRow += generalList.get(i)+"\n";
                }else{
                    cartRow += generalList.get(i)+",";
                }
            }
            bw.write(cartRow);
            bw.flush();
            bw.close();
        }
    }
}
