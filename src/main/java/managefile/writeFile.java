/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author USER
 */
public class writeFile {
    public void addGeneralFile(List<String> cartList,String filepath) throws IOException{
        FileWriter fw = new FileWriter(filepath,true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            String cartRow = "";
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.size()-1 == i){
                    cartRow += cartList.get(i)+"\n";
                }else{
                    cartRow += cartList.get(i)+",";
                }
            }
            bw.write(cartRow);
            bw.flush();
            bw.close();
        }
    }
    public void updateCart(List<String> cartList,String filepath) throws IOException{
        FileWriter fw = new FileWriter(filepath);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            for (int i=0;i< cartList.size();i+=7){
                bw.write(cartList.get(i)+",");
                bw.write(cartList.get(i+1)+",");
                bw.write(cartList.get(i+2)+",");
                bw.write(cartList.get(i+3)+",");    
                bw.write(cartList.get(i+4)+","); 
                bw.write(cartList.get(i+5)+",");
                bw.write(cartList.get(i+6)+"\n");
            }
            bw.flush();
            bw.close();
        }
    }
    public void updateCustomer(List<String> customerList,String filepath) throws IOException{
        FileWriter fw = new FileWriter(filepath);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            for (int i=0;i< customerList.size();i+=6){
                bw.write(customerList.get(i)+",");
                bw.write(customerList.get(i+1)+",");
                bw.write(customerList.get(i+2)+",");
                bw.write(customerList.get(i+3)+",");
                bw.write(customerList.get(i+4)+",");
                bw.write(customerList.get(i+5)+"\n");
            }
            bw.flush();
            bw.close();
        }
    }
    
    public void writeFeedback(List<Feedback> feedbacks, String filepath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // Write the header
            writer.write("FeedbackId,CustomerId,ManagerId,Description,Datetime");
            writer.newLine();

            // Write each feedback as a line in the CSV format
            for (Feedback feedback : feedbacks) {
                String line = String.format("%s,%s,%s,%s,%s",
                        feedback.getFeedbackID(),
                        feedback.getCustomerID(),
                        feedback.getManagerID(),
                        feedback.getDescription(),
                        feedback.getDatetime());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public void writeFood(List<Food> foods, String filepath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
            // Write the header
            writer.write("FoodID,Name,Status,Description,Price,Imagepath,Category,VendorId");
            writer.newLine();
            
            for (Food food : foods){
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                        food.getId(),
                        food.getName(),
                        food.getStatus(),
                        food.getDescription(),
                        food.getPrice(),
                        food.getImagepath(),
                        food.getCategory(),
                        food.getVendorid());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e){
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public void writeRunnerNotification(List<RunnerNotification> notifications, String filepath,Boolean method) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, method))) {
            for (RunnerNotification notification : notifications) {
                // Create a line for each notification in the format: notificationId,userId,orderId,status
                String line = notification.getNotificationId() + "," +
                              notification.getRunnerId() + "," +
                              notification.getOrderId() + "," +
                              notification.getStatus();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the notification file: " + e.getMessage());
        }
    }
    
    public void writeRunner(List<Runner> runners, String filepath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
            // Write the header
            writer.write("RunnerId,Name,Email,Phone,Password,Status");
            writer.newLine();
            
            for (Runner runner : runners){
                String line = String.format("%s,%s,%s,%s,%s,%s",
                        runner.getId(),
                        runner.getName(),
                        runner.getEmail(),
                        runner.getPhone(),
                        runner.getPassword(),
                        runner.getStatus());
                writer.write(line);
                writer.newLine();
            }
 
        } catch (IOException e) {
            System.err.println("Error writing to the notification file: " + e.getMessage());
        }
    }
    
    public void writeDeliveryReview(List<DeliveryReview> deliveryReviews, String filepath){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
            //write headerline
            writer.write("ReviewID,CustomerId,RunnerId,Rating,Comments,Datetime");
            writer.newLine();
            
            for(DeliveryReview deliveryReview : deliveryReviews){
                String line = String.format("%s,%s,%s,%s,%s,%s",
                        deliveryReview.getReviewID(),
                        deliveryReview.getCustomerID(),
                        deliveryReview.getRunnerID(),
                        deliveryReview.getRating(),
                        deliveryReview.getComments(),
                        deliveryReview.getDatetime());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e){
            System.err.println("Error writing to the notification file: " + e.getMessage());
        }
        
    }

}   
