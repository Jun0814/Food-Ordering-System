/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import managefile.Order;
import java.util.List;
import java.util.Map;
import managefile.Feedback;
import managefile.Food;
import managefile.Manager;
import managefile.Vendor;
import managefile.readFile;
import managefile.writeFile;
import method.scaleImage;
/**
 *
 * @author Asus
 */
public class managerAccountManager {
    scaleImage scale = new scaleImage();
    Manager manager = new Manager();
    Order order = new Order();
    Vendor vendor = new Vendor();
    Feedback feedback = new Feedback();
    writeFile writer = new writeFile();
    Food food = new Food();
    private final String manager_file_path = manager.getFilepath();
    private final String feedbackFilepath = feedback.getFilepath();
    private final String vendorFilepath = vendor.getFilepath();
    private final String foodFilepath = food.getFilepath();
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
    
    public Vendor getVendorDetails(String vendorId){
        List<Vendor> vendors = acc.readVendorAccount(vendorFilepath);
        for (Vendor vendor : vendors ){
            if(vendor.getId().equals(vendorId)){
                return vendor;
            }
        }
        return null;
    }
    
    public Feedback getFeedbackDetails(String feedbackId){
        List <Feedback> feedbacks = acc.readFeedback(feedbackFilepath);
        for (Feedback feedback : feedbacks){
            if(feedback.getFeedbackID().equals(feedbackId)){
                return feedback;
            }
        }
        return null;
    }
    
    public List<Food> getVendorFoodList(String vendorId){
        System.out.println(vendorId);
        List <Food> foods = acc.readFood(foodFilepath);
        List<Food> vendorFoods = new ArrayList<>();
        
        for (Food food : foods) {
            if (food.getVendorid().equals(vendorId)) {
                vendorFoods.add(food);
            }
        }
        return vendorFoods;
    }
    
    public Food getFoodDetails(String foodId){
        List <Food> foods = acc.readFood(foodFilepath);
        for (Food food : foods){
            if(food.getId().equals(foodId)){
                return food;
            }
        }
        return null;
    }
    
    public void approveFeedback(String feedbackId, String managerId){
        List <Feedback> feedbacks = acc.readFeedback(feedbackFilepath);
        for (Feedback feedback : feedbacks){
            if(feedback.getFeedbackID().equals(feedbackId)){
                feedback.setManagerID(managerId);
            }
        }
        writer.writeFeedback(feedbacks, feedbackFilepath);
    }
    
    public void removeFood(String foodId){
        List <Food> foods = acc.readFood(foodFilepath);
        boolean removed = false;

        // Use an iterator to avoid concurrent modification issues
        Iterator<Food> iterator = foods.iterator();
        while (iterator.hasNext()) {
            Food food = iterator.next();
            if (food.getId().equals(foodId)) {
                iterator.remove(); // Remove the food item from the list
                removed = true;
                break; // Exit the loop once the food is found and removed
            }
        }

        if (removed) {
            // Save the updated list back to the file
            writer.writeFood(foods, foodFilepath);
            System.out.println("Food with ID " + foodId + " has been removed.");
        } else {
            System.out.println("Food with ID " + foodId + " not found.");
        }
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
    
    public static double calculateTotalRatings(List<String> ratings){
        double totalRatings = 0.0;
        int validRatingsCount = 0;
        
        if(ratings == null || ratings.isEmpty()){
            return totalRatings;
        }
        
        for (String rating : ratings){
            if(rating != null && !rating.isEmpty()){
                try{
                    totalRatings += Double.parseDouble(rating);
                    validRatingsCount++;
                }catch (NumberFormatException e){
                    // Ignore invalid numbers (e.g., "null")
                }
            }
        }
        return totalRatings/validRatingsCount;
    }
    
}
