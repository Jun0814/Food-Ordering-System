/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author USER
 */
public class readFile {
    public List readCustomerAccount(String filePath){
        List<Customer> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0].trim();
                String name = fields[1].trim();
                String email = fields[2].trim();
                String phone = fields[3].trim();
                String password = fields[4].trim();
                double credit = Double.parseDouble(fields[5].trim());
                String feedbackid = fields[6].trim();
                users.add(new Customer(id,name,email,phone,password,credit,feedbackid));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
    public List readFeedback(String filePath){
        List<Feedback> feedbacks = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String feedbackID = fields[0].trim();
                String customerID = fields[1].trim();
                String managerID = fields[2].trim();
                String description = fields[3].trim();
                String datetime = fields[4].trim();
                feedbacks.add(new Feedback(feedbackID,customerID,managerID,description,datetime));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return feedbacks;
    } 
    public List readVendorAccount(String filePath){
        List<Vendor> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0].trim();
                String name = fields[1].trim();
                String email = fields[2].trim();
                String phone = fields[3].trim();
                String password = fields[4].trim();
                String stallname = fields[5].trim();
                String stalltype = fields[6].trim();
                String imagepath = fields[7].trim();
                String status = fields[8].trim();
                users.add(new Vendor(id,name,email,phone,password,stallname,stalltype,imagepath,status));
            }
            fr.close();
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
    public List readFood(String filePath){
        List<Food> foods = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("FoodID")) continue;
                
                String[] fields = line.split(",");
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    String status = fields[2].trim();
                    String description = fields[3].trim();
                    String price = fields[4].trim();
                    String imagepath = fields[5].trim();
                    String cate = fields[6].trim();
                    String vendorid = fields[7].trim();
                    foods.add(new Food(id,name,status,description,price,imagepath,cate,vendorid));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return foods;
    }
    
    public List readCart(String filePath){
        List<managefile.Cart> carts = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String cartid = fields[0].trim();
                String customerid = fields[1].trim();
                String foodid = fields[2].trim();
                String vendorid = fields[3].trim();
                String quantity = fields[4].trim();
                String remarks = fields[5].trim();
                String datetime = fields[6].trim();
                carts.add(new managefile.Cart(cartid,customerid,foodid,vendorid,quantity,remarks,datetime));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return carts;
    }
    
    public List readManagerAccount(String filepath){
        List<Manager> managers = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while((line = br.readLine()) != null) {
                String [] fields = line.split(",");
                String id = fields[0].trim();
                String name = fields[1].trim();
                String email = fields[2].trim();
                String phone = fields[3].trim();
                String password = fields[4].trim();
                managers.add(new Manager(id, name, email, phone, password));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return managers;
    }
        
    public List readOrder(String filePath){
        List<Order> orders = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String orderID= fields[0].trim();
                String customerID= fields[1].trim();
                String deliveryID= fields[2].trim();
                String vendorID= fields[3].trim();
                String orderReviewID= fields[4].trim();
                String orderType= fields[5].trim();
                String orderTypeDetails= fields[6].trim();
                String datetime= fields[7].trim();
                String totalAmount= fields[8].trim();
                String status= fields[9].trim();
                orders.add(new Order(orderID,customerID,deliveryID,vendorID,orderReviewID,orderType,orderTypeDetails,datetime,totalAmount,status));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return orders;
    }
    public List readOrderItems(String filePath){
        List<OrderItems> orders = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String orderitemid = fields[0].trim();
                String orderid = fields[1].trim();
                String foodid = fields[2].trim();
                String totalAmount = fields[3].trim();
                String quantity = fields[4].trim();
                String status = fields[5].trim();
                String remarks = fields[6].trim();
                orders.add(new OrderItems(orderitemid,orderid,foodid,totalAmount,quantity,status,remarks));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return orders;
    }
    public List readTransaction(String filePath){
        List<Transaction> transactions = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String transactionID = fields[0].trim();
                String customerID = fields[1].trim();
                String generalID = fields[2].trim();
                String datetime = fields[3].trim();
                String amount = fields[4].trim();
                String transactionType = fields[5].trim();
                String topupType = fields[6].trim();
                transactions.add(new Transaction(transactionID, customerID, generalID, datetime, amount, transactionType,topupType));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return transactions;
    }
    public List readDelivery(String filePath){
        List<Delivery> deliverys = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String deliveryid = fields[0].trim();
                String deliveryreviewid = fields[1].trim();
                String orderid = fields[2].trim();
                String runnerid = fields[3].trim();
                String total = fields[4].trim();
                String description = fields[5].trim();
                String datetime = fields[6].trim();
                String status = fields[7].trim();
                deliverys.add(new Delivery(deliveryid,deliveryreviewid, orderid, runnerid, Double.parseDouble(total), description, datetime, status));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return deliverys;
    }
    public List readRunner(String filePath){
        List<Runner> runners = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String runnerid = fields[0].trim();
                String name = fields[1].trim();
                String emailaddress = fields[2].trim();
                String phonenum = fields[3].trim();
                String password = fields[4].trim();
                String status = fields[5].trim();
                runners.add(new Runner(runnerid, name, emailaddress, phonenum, password, status));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return runners;
    }
    public List readVendorReview(String filePath){
        List<VendorReview> reviews = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String reviewID = fields[0].trim();
                String vendorID = fields[1].trim();
                String rating = fields[2].trim();
                String comments = fields[3].trim();
                reviews.add(new VendorReview(reviewID,vendorID, rating, comments));
            }
            fr.close();
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return reviews;
    }
    public List readVendorAccount1(String filePath){
        List<String> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0].trim();
                String name = fields[1].trim();
                String email = fields[2].trim();
                String phone = fields[3].trim();
                String password = fields[4].trim();
                String stallname = fields[5].trim();
                String stalltype = fields[6].trim();
                String imagepath = fields[7].trim();
                String status = fields[8].trim();
                users.add(id+","+name+","+email+","+phone+","+password+","+stallname+","+stalltype+","+imagepath+","+status);
            }
            fr.close();
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
    public List readVendorReview1(String filePath){
        List<String> reviews = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String reviewID = fields[0].trim();
                String vendorID = fields[1].trim();
                String rating = fields[2].trim();
                String comments = fields[3].trim();
                reviews.add(reviewID+","+vendorID+","+rating+","+comments);
            }
            fr.close();
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return reviews;
    }
    public List readDeliveryReview(String filePath){
        List<DeliveryReview> reviews = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String reviewID = fields[0].trim();
                String deliveryID = fields[1].trim();
                String runnerID = fields[2].trim();
                String rating = fields[3].trim();
                String comments = fields[4].trim();
                reviews.add(new DeliveryReview(reviewID,deliveryID,runnerID, rating, comments));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return reviews;
    }
    public List readNotification(String filePath){
        List<Notification> notifications = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String notificationID = fields[0].trim();
                    String description = fields[1].trim();
                    String datetime = fields[2].trim();
                    String userID = fields[3].trim();
                    notifications.add(new Notification(notificationID, description, datetime,userID));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return notifications;
    }

    public List<RunnerNotification> readRunnerNotification(String filepath) {
        List<RunnerNotification> notifications = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String runnerNotificationId = fields[0].trim();
                String runnerId = fields[1].trim();
                String orderId = fields[2].trim();
                String status = fields[3].trim();
                notifications.add(new RunnerNotification(runnerNotificationId,runnerId,orderId, status));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return notifications;
    }
}
