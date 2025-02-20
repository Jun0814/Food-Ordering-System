/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import managefile.Delivery;
import managefile.Runner;
import method.scaleImage;

/**
 *
 * @author Asus
 */
public class runnerAccountManager {
    scaleImage scale = new scaleImage();
    Runner runner = new Runner();
    Delivery delivery = new Delivery();
    private final String runnerFilepath = runner.getFilepath();
    private final String deliveryFilepath = delivery.getFilepath();
    
    public Map<String, Double> getYearlyRevenue(String runnerId){
        Map<String, Double> yearlyTotalRevenue = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(deliveryFilepath))){
            String headerLine = br.readLine();
            String line;
            
            while ((line = br.readLine()) != null){
                String[] columns = line.split(",");
                
                String fileRunnerId = columns[3].trim();
                String dateTime = columns[6].trim();
                String yearlyRevenueStr = columns[4].trim();
                
                String year = dateTime.split("-")[0];
                
                double yearlyRevenue = Double.parseDouble(yearlyRevenueStr);
                
                if(runnerId.equals(fileRunnerId)){
                    yearlyTotalRevenue.put(year, yearlyTotalRevenue.getOrDefault(year, 0.0) + yearlyRevenue);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return yearlyTotalRevenue;
    }
    
    public Map<LocalDate, Double> getDailySalesForYear(String year, String runnerId){
        Map<LocalDate, Double> dailySalesForYear = new HashMap<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(deliveryFilepath))){
            String headerLine = br.readLine();
            String line;
            
            while((line = br.readLine()) != null){
                String [] columns = line.split(",");
                
                String dateTimeStr = columns[6].trim();
                String amountStr = columns[4].trim();
                String fileRunnerId = columns[3].trim();
                
                LocalDate date = LocalDate.parse(dateTimeStr.split("T")[0]);
                
                if(runnerId.equals(fileRunnerId)){
                    if(String.valueOf(date.getYear()).equals(year)){
                        double amount = Double.parseDouble(amountStr);
                        dailySalesForYear.put(date, dailySalesForYear.getOrDefault(date, 0.0) + amount);
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return dailySalesForYear;
    }
}
