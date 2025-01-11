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
    
    public Map<String, Double> getYearlyRevenue(){
        String filepath = order.getFilepath();
        double totalAmount = 0;
        
        Map<String, Double> yearlyTotalRevenue = new HashMap<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String headerLine = br.readLine();
            String line;
            
            while((line = br.readLine()) != null){
                String [] columns = line.split(",");
                
                String dateTime = columns[5].trim();
                String yearlyRevenueStr = columns[6].trim();
                
                String year = dateTime.split("-")[0];
                
                double yearlyRevenue = Double.parseDouble(yearlyRevenueStr);
                yearlyTotalRevenue.put(year, yearlyTotalRevenue.getOrDefault(year, 0.0) + yearlyRevenue);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return yearlyTotalRevenue;
    }    
    
    public Map<LocalDate, Double> getDailySalesForYear(String year){
        String filepath = order.getFilepath();
        
        Map<LocalDate, Double> dailySalesForYear = new HashMap<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String headerLine = br.readLine();
            String line;
            
            while((line = br.readLine()) != null){
                String [] columns = line.split(",");
                
                String dateTimeStr = columns[5].trim();
                String amountStr = columns[6].trim();
                
                LocalDate date = LocalDate.parse(dateTimeStr.split(" ")[0]);
                
                if(String.valueOf(date.getYear()).equals(year)){
                    double amount = Double.parseDouble(amountStr);
                    dailySalesForYear.put(date,dailySalesForYear.getOrDefault(date, 0.0)+ amount);
                }       
               
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return dailySalesForYear;
    }
    
    
}
