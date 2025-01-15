/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import managefile.Order;
import java.util.List;
import java.util.Map;
import managefile.Manager;
import managefile.readFile;
/**
 *
 * @author Asus
 */
public class managerAccountManager {
    Manager manager = new Manager();
    Order order = new Order();
    private final String manager_file_path = manager.getFilepath();
    private readFile acc = new readFile();
    
    public Manager getManagerDetails(String managerId){
        List<Manager> managers = acc.readManagerAccount(manager_file_path);
        for (Manager manager : managers ){
            if(manager.getId().equals(managerId)){
                return manager;
            }
        }
        return null;
    }
    
//    public Map<String, Double> getYearlyRevenue(){
//        String filepath = order.getFilepath();
//        double totalAmount = 0;
//        
//        Map<String, Double> yearlyTotalRevenue = new HashMap<>();
//        
//        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
//            String headerLine = br.readLine();
//            String line;
//            
//            while((line = br.readLine()) != null){
//                String [] columns = line.split(",");
//                
//                String dateTime = columns[7].trim();
//                String yearlyRevenueStr = columns[8].trim();
//                
//                String year = dateTime.split("-")[0];
//                
//                double yearlyRevenue = Double.parseDouble(yearlyRevenueStr);
//                yearlyTotalRevenue.put(year, yearlyTotalRevenue.getOrDefault(year, 0.0) + yearlyRevenue);
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        return yearlyTotalRevenue;
//    }    
    
    public Map<String, Double> getYearlyRevenue(String vendorId) {
        String filepath = order.getFilepath();
        Map<String, Double> yearlyTotalRevenue = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String headerLine = br.readLine(); // Skip the header
            String line;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                String fileVendorId = columns[3].trim(); // Assuming the vendorId is in the first column
                String dateTime = columns[7].trim();
                String yearlyRevenueStr = columns[8].trim();

                // Parse the year from the date
                String year = dateTime.split("-")[0];

                // Parse revenue amount
                double yearlyRevenue = Double.parseDouble(yearlyRevenueStr);

                // Check if vendorId matches or vendorId is not provided
                if (vendorId == null || vendorId.isEmpty() || vendorId.equals(fileVendorId)) {
                    yearlyTotalRevenue.put(year, yearlyTotalRevenue.getOrDefault(year, 0.0) + yearlyRevenue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return yearlyTotalRevenue;
    }
    
    public Map<LocalDate, Double> getDailySalesForYear(String year, String vendorId){
        String filepath = order.getFilepath();
        
        Map<LocalDate, Double> dailySalesForYear = new HashMap<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String headerLine = br.readLine();
            String line;
            
            while((line = br.readLine()) != null){
                String [] columns = line.split(",");
                
                String dateTimeStr = columns[7].trim();
                String amountStr = columns[8].trim();
                String fileVendorId = columns[3].trim();
                
                LocalDate date = LocalDate.parse(dateTimeStr.split("T")[0]);
                
                if(vendorId == null || vendorId.isEmpty() || vendorId.equals(fileVendorId)){
                    if(String.valueOf(date.getYear()).equals(year)){
                        double amount = Double.parseDouble(amountStr);
                        dailySalesForYear.put(date,dailySalesForYear.getOrDefault(date, 0.0)+ amount);
                    }   
                }  
               
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return dailySalesForYear;
    }
    
    
}
