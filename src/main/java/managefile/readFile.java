/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

import customer.Cart;
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
    public List readUserAccount(String filePath){
        List<GeneralRole> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String email = fields[2];
                String phone = fields[3];
                String password = fields[4];
                users.add(new GeneralRole(id, name, email, phone, password));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
    public List readCustomerAccount(String filePath){
        List<Customer> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String email = fields[2];
                String phone = fields[3];
                String password = fields[4];
                double credit = Double.parseDouble(fields[5]);
                users.add(new Customer(id,name,email,phone,password,credit));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
    public List readVendorAccount(String filePath){
        List<Vendor> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String id = fields[0];
                    String name = fields[1];
                    String email = fields[2];
                    String phone = fields[3];
                    String password = fields[4];
                    String stallname = fields[5];
                    String stalltype = fields[6];
                    String imagepath = fields[7];
                    users.add(new Vendor(id,name,email,phone,password,stallname,stalltype,imagepath));
            }
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String id = fields[0];
                    String name = fields[1];
                    String quantity = fields[2];
                    String description = fields[3];
                    String price = fields[4];
                    String imagepath = fields[5];
                    String vendorid = fields[6];
                    foods.add(new Food(id,name,quantity,description,price,imagepath,vendorid));
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String cartid = fields[0];
                    String customerid = fields[1];
                    String foodid = fields[2];
                    String quantity = fields[3];
                    String remarks = fields[4];
                    String datetime = fields[5];
                    carts.add(new managefile.Cart(cartid,customerid,foodid,quantity,remarks,datetime));
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
            String line;
            while((line = br.readLine()) != null) {
                String [] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String email = fields[2];
                String phone = fields[3];
                String password = fields[4];
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String cartid = fields[0];
                    String customerid = fields[1];
                    String ordertype = fields[2];
                    String ordertypedetail = fields[3];
                    String datetime = fields[4];
                    String amount = fields[5];
                    String status = fields[6];
                    orders.add(new Order(cartid,customerid,ordertype,ordertypedetail,datetime,amount,status));
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String orderitemid = fields[0];
                    String orderid = fields[1];
                    String foodid = fields[2];
                    String quantity = fields[3];
                    String status = fields[4];
                    String remarks = fields[5];
                    orders.add(new OrderItems(orderitemid,orderid,foodid,quantity,status,remarks));
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String transactionID = fields[0];
                    String customerID = fields[1];
                    String orderID = fields[2];
                    String adminID = fields[3];
                    String datetime = fields[4];
                    String amount = fields[5];
                    String transactionType = fields[6];
                    transactions.add(new Transaction(transactionID, customerID, orderID,adminID, datetime, amount, transactionType));
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String deliveryid = fields[0];
                    String orderid = fields[1];
                    String runnerid = fields[2];
                    String description = fields[3];
                    String datetime = fields[4];
                    String status = fields[5];
                    deliverys.add(new Delivery(deliveryid, orderid, runnerid, description, datetime, status));
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String runnerid = fields[0];
                    String name = fields[1];
                    String emailaddress = fields[2];
                    String phonenum = fields[3];
                    String passwor = fields[4];
                    String status = fields[5];
                    runners.add(new Runner(runnerid, name, emailaddress, phonenum, passwor, status));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return runners;
    }
    public List readReview(String filePath){
        List<Review> reviews = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String reviewID = fields[0];
                    String rating = fields[1];
                    String comments = fields[2];
                    reviews.add(new Review(reviewID, rating, comments));
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
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                    String notificationID = fields[0];
                    String description = fields[1];
                    String datetime = fields[2];
                    String recipientID = fields[3];
                    String senderID = fields[4];
                    notifications.add(new Notification(notificationID, description, datetime,recipientID,senderID));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return notifications;
    }
}
