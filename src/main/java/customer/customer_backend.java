/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import managefile.Customer;
import managefile.Data;
import managefile.Delivery;
import managefile.DeliveryReview;
import managefile.Feedback;
import managefile.Notification;
import managefile.OrderItems;
import managefile.Runner;
import managefile.RunnerNotification;
import managefile.Vendor;
import managefile.VendorReview;
import managefile.VendorReview1;
import managefile.readFile;
import managefile.writeFile;
import method.primaryKey;
import method.scaleImage;
import runner.NotificationService;

/**
 *
 * @author Asus
 */
public class customer_backend{
    private final String customerFile = "src\\main\\java\\repository\\customer.txt";
    private final String vendorFile = "src\\main\\java\\repository\\vendor.txt";
    private final String runnerFile = "src\\main\\java\\repository\\runner.txt";
    private final String foodFile = "src\\main\\java\\repository\\food.txt";
    private final String cartFile = "src\\main\\java\\repository\\cart.txt";
    private final String orderFile = "src\\main\\java\\repository\\order.txt";
    private final String orderItemFile = "src\\main\\java\\repository\\orderitems.txt";
    private final String transactionFile = "src\\main\\java\\repository\\transaction.txt";
    private final String deliveryFile = "src\\main\\java\\repository\\delivery.txt";
    private final String reviewFile = "src\\main\\java\\repository\\review.txt";
    private final String notificationFile = "src\\main\\java\\repository\\notifications.txt";
    private final String deliveryReviewFile = "src\\main\\java\\repository\\deliveryreview.txt";
    private final String orderReviewFile = "src\\main\\java\\repository\\orderreview.txt";
    private final String feedbackFile = "src\\main\\java\\repository\\feedback.txt";
    RunnerNotification runnerNotification = new RunnerNotification();
    private final String runnerNotificationFile = runnerNotification.getFilepath();
    private readFile read = new readFile();
    private writeFile write = new writeFile();
    scaleImage scale = new scaleImage();
    method.primaryKey pri = new primaryKey();
    NotificationService notificationService = new NotificationService();
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    String datetime = currentDate.toString()+"T" + currentTime.toString().split("\\.")[0];
    Data data = new Data();
    
    public Customer getSpecificCustomerDetail(String customerid){
        List<Customer> customers = read.readCustomerAccount(customerFile);
        for (Customer customer : customers) {
            if (customer.getId().equals(customerid)){
                return customer;
            }
        }
        return null;
    }
    
    public String returnOrderItemDescription(String status){
        String des = "";
        switch (status.toLowerCase()) {
            case "cancel":
                des = "The order item has been cancelled!";
                break;
            case "done":
                des = "The order item is prepared!";
                break;
            case "pending":
                des = "The order item is waiting for vendor!";
                break;
            case "completed":
                des = "The order item has been received!";
                break;
            default:
                break;
        }
        return des;
    }
    
    public String returnOrderDescription(String status){
        String des = "";
        switch (status.toLowerCase()) {
            case "reject":
                des = "The order is rejected by vendor!";
                break;
            case "cancel":
                des = "The order has been cancelled!";
                break;
            case "done":
                des = "The order is prepared!";
                break;
            case "accept":
                des = "The order is being preparing!";
                break;
            case "pending":
                des = "The order is waiting for vendor!";
                break;
            case "completed":
                des = "The order has been received!";
                break;
            default:
                break;
        }
        return des;
    }
    
    public String returnDeliveryDescription(String status, String orderStatus) {
        String des = "";

        switch (status.toLowerCase()) {
            case "accept":
                if (orderStatus.equalsIgnoreCase("done")) {
                    des = "The delivery task is accepted by the runner! The order will be arriving soon!";
                } else {
                    des = "The delivery task is accepted by the runner!";
                }
                break;

            case "pending":
                des = "Waiting for a delivery runner to accept the task.";
                break;

            case "reject":
                des = "The delivery task was rejected by the runner. Searching for the next runner.";
                break;

            case "completed":
                des = "The order has been successfully delivered to the customer.";
                break;

            case "cancel":
                if (orderStatus.equalsIgnoreCase("cancel")) {
                    des = "The delivery task was cancelled due to order cancellation.";
                } else {
                    des = "The delivery task was cancelled.";
                }
                break;

            default:
                break;
        }

        return des;
    }
    
    public String returnTransactionDescription(String status,String amount){
        String des = "";
        
        switch (status.toLowerCase()) {
            case "debit":
                des = "Your wallet balance has been deducted by RM "+String.format("%.2f",Double.parseDouble(amount))+".";
                break;
            case "credit":
                des = "Your wallet balance has been top up by RM "+String.format("%.2f",Double.parseDouble(amount))+".";
                break;
            case "refund":
                des = "Your wallet balance has been added by RM "+String.format("%.2f",Double.parseDouble(amount))+".";
                break;
        }
        return des;
    }
    
    public void updateCredit(String customerid,double totalPrice,String option) throws IOException{
        List<Customer> customers = read.readCustomerAccount(customerFile);
        for (Customer customer : customers) {
            if (customer.getId().equals(customerid)){
                if (option.equalsIgnoreCase("debit")){
                    data.updateData(customerid,5, String.valueOf(customer.getCredit()-totalPrice), customerFile);
                    break;
                }else if (option.equalsIgnoreCase("credit")){
                    data.updateData(customerid,5, String.valueOf(customer.getCredit()+totalPrice), customerFile);
                    break;
                }
            }
        }
    }
    
    public String validateCredentials(String email, String password) {
        List<Customer> customers = read.readCustomerAccount(customerFile);
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer.getId();
            }
        }
        return null;
    }
    public List<Vendor> getVendors(){
        return read.readVendorAccount(vendorFile);
    }
    
    public Map<Object, Object> getVendorsReviews(){
        List<Vendor> vendors = read.readVendorAccount(vendorFile);
        List<managefile.VendorReview> vendorReviews = read.readVendorReview(orderReviewFile);
        
        Map<Object, Object> result = new HashMap<>();
        result.put("vendors", vendors);
        result.put("reviews", vendorReviews);
        return result;
    }
    public Map<Object, Object> getVendorsReviews(String vendorID){
        List<Vendor> vendors = read.readVendorAccount(vendorFile);
        List<managefile.VendorReview> vendorReviews = read.readVendorReview(orderReviewFile);
        
        List<Vendor> matchedVendors = new ArrayList<>();
        List<managefile.VendorReview> matchedReviews = new ArrayList<>();
        for (Vendor vendor : vendors) {
            for (VendorReview vendorReview : vendorReviews) {
                if (vendor.getId().equals(vendorID) && vendor.getId().equals(vendorReview.getVendorID())){
                    matchedVendors.add(vendor);
                    matchedReviews.add(vendorReview);
                }
            }
        }
        
        Map<Object, Object> result = new HashMap<>();
        result.put("vendors", matchedVendors);
        result.put("reviews", matchedReviews);
        return result;
    }
    
    public Map<Object, Object> getSpecificVendorDetail(String vendorid){
        List<Vendor> vendors = read.readVendorAccount(vendorFile);
        List<managefile.Food> foods = read.readFood(foodFile);
        List<Vendor> matchedVendors = new ArrayList<>();
        List<managefile.Food> matchedFoods = new ArrayList<>();
        
        for (Vendor vendor : vendors) {
            if (vendor.getId().equals(vendorid)){
                matchedVendors.add(vendor);
            }
        }
        for (managefile.Food food:foods){
            if (food.getVendorid().equals(vendorid)){
                matchedFoods.add(food);
            }
        }
        Map<Object, Object> result = new HashMap<>();
        result.put("vendors", matchedVendors);
        result.put("foods", matchedFoods);
        return result;
    }
    
    
    public Map<Object, Object> getCart(String customerID) throws IOException{
        List<managefile.Cart> carts = read.readCart(cartFile);
        List<managefile.Food> foods = read.readFood(foodFile);
        List<managefile.Cart> validCarts = new ArrayList<>();
        List<managefile.Food> matchedFoods = new ArrayList<>();
        for (managefile.Cart cart:carts){
            for (managefile.Food food:foods){
                if (customerID.equals(cart.getCustomerID())){
                    if (cart.getFoodID().equals(food.getId()) && food.getStatus().equalsIgnoreCase("available")){
                        validCarts.add(cart);
                        matchedFoods.add(food);
                        break;
                    }
                }
            }
        }
        Map<Object, Object> result = new HashMap<>();
        result.put("carts", validCarts);
        result.put("foods", matchedFoods);
        return result;
    }
    
    public void addCartItems(String customerID,String foodID,String quantitySelection,String vendorid, String remark) throws IOException{
        Map<Object, Object> carts = getCart(customerID);
        List<managefile.Cart> cart = (List<managefile.Cart>) carts.get("carts");
        List cartIDList = new ArrayList();
        
        for (managefile.Cart cart1 : cart) {
            cartIDList.add(cart1.getCartID());
        }
        String latestCartID;
        if (!cartIDList.isEmpty()){
            latestCartID = pri.incrementPrimaryKey(cartIDList);
        }else{
            latestCartID = "CR1";
        }
        boolean itemUpdated = false;
        
        for (managefile.Cart cart1 : cart) {
            if (customerID.equals(cart1.getCustomerID()) && foodID.equals(cart1.getFoodID())){
                int newQuantity = Integer.parseInt(cart1.getQuantity())+Integer.parseInt(quantitySelection);
                data.updateData(cart1.getCartID(),4, String.valueOf(newQuantity), cartFile);
                itemUpdated = true;
                break;
            }
        }
        if (!itemUpdated) {
            data.updateData(latestCartID, vendorid, remark);
            List<String> cartItems = new ArrayList<>();
            cartItems.add(latestCartID);
            cartItems.add(customerID);
            cartItems.add(foodID);
            cartItems.add(vendorid);
            cartItems.add(quantitySelection);
            cartItems.add(remark);
            cartItems.add(datetime);
            
            data.addGeneralFile(cartItems, cartFile);
        }
    }
    
    public void updateCartItems(String cartID,String customerID,String quantity) throws IOException{
        Map<Object, Object> carts = getCart(customerID);
        List<managefile.Cart> cart = (List<managefile.Cart>) carts.get("carts");
        for (managefile.Cart cart1 : cart) {
            if (cartID.equals(cart1.getCartID())){
                data.updateData(cart1.getCartID(),4, quantity, cartFile);
                break;
            }
        }
    }
    
    public void removeCartItems(String cartID, String customerID,String foodID) throws IOException{
        Map<Object, Object> carts = getCart(customerID);
        List<managefile.Cart> cart = (List<managefile.Cart>) carts.get("carts");
        List<String> cartItems = new ArrayList<>();
        data.removeRowById(cartID, cartFile);
    }
    
    public Map<Object, Object> getOrder(String customerID){
        List<managefile.Order> orders = read.readOrder(orderFile);
        List<managefile.OrderItems> orderItems = read.readOrderItems(orderItemFile);
        
        List<managefile.Order> allOrders = new ArrayList<>();
        List<managefile.OrderItems> allOrderItems = new ArrayList<>();
        for (managefile.Order order :orders){
            for (managefile.OrderItems orderitem:orderItems){
                if(customerID.equals(order.getCustomerID())){
                    if (order.getOrderID().equals(orderitem.getOrderID())){
                        allOrders.add(order);
                        allOrderItems.add(orderitem);
                    }
                }
            }
        }
        Map<Object, Object> result = new HashMap<>();
        result.put("orders", allOrders);
        result.put("ordersItems", allOrderItems);
        return result;
    }
    public Map<Object, Object> getOrderByOrderID(String orderID){
        List<managefile.Order> orders = read.readOrder(orderFile);
        List<managefile.OrderItems> orderItems = read.readOrderItems(orderItemFile);
        List<managefile.Food> foodItems = read.readFood(foodFile);
        
        List<managefile.Order> allOrders = new ArrayList<>();
        List<managefile.OrderItems> allOrderItems = new ArrayList<>();
        List<managefile.Food> matchingFoodItems = new ArrayList<>();
        for (managefile.Order order :orders){
            for (managefile.OrderItems orderitem:orderItems){
                for (managefile.Food foodItem : foodItems) {
                    if(orderID.equals(order.getOrderID())){
                        if (order.getOrderID().equals(orderitem.getOrderID())){
                            if (orderitem.getFoodID().equals(foodItem.getId())){
                                allOrders.add(order);
                                allOrderItems.add(orderitem);
                                matchingFoodItems.add(foodItem);
                            }
                        }
                    }
                }
            }
        }
        Map<Object, Object> result = new HashMap<>();
        result.put("orders", allOrders);
        result.put("ordersItems", allOrderItems);
        result.put("foodItems", matchingFoodItems);
        return result;
    }
    
    public Map<Object, Object> getOrderByOrderReviewID(String reviewID){
        List<managefile.Order> orders = read.readOrder(orderFile);
        List<managefile.Customer> customers = read.readCustomerAccount(customerFile);
        List<managefile.OrderItems> orderItems = read.readOrderItems(orderItemFile);
        List<managefile.Food> foodItems = read.readFood(foodFile);
        
        List<managefile.Order> allOrders = new ArrayList<>();
        List<managefile.OrderItems> allOrderItems = new ArrayList<>();
        List<managefile.Food> matchingFoodItems = new ArrayList<>();
        List<managefile.Customer> matchingCustomer = new ArrayList<>();
        
        for (managefile.Order order :orders){
            for (Customer customer : customers) {
                for (managefile.OrderItems orderitem:orderItems){
                    for (managefile.Food foodItem : foodItems) {
                        if (order.getCustomerID().equals(customer.getId())){
                            if(reviewID.equals(order.getOrderReviewID())){
                                if (order.getOrderID().equals(orderitem.getOrderID())){
                                    if (orderitem.getFoodID().equals(foodItem.getId())){
                                        allOrders.add(order);
                                        allOrderItems.add(orderitem);
                                        matchingFoodItems.add(foodItem);
                                        matchingCustomer.add(customer);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Map<Object, Object> result = new HashMap<>();
        result.put("orders", allOrders);
        result.put("ordersItems", allOrderItems);
        result.put("foodItems", matchingFoodItems);
        result.put("customers", matchingCustomer);
        return result;
    }
    public List<managefile.Transaction> getTransaction(){
        List<managefile.Transaction> transactions = read.readTransaction(transactionFile);
        
        return transactions;
    }
    
    public List<managefile.Transaction> getTransaction(String customerID){
        List<managefile.Transaction> transactions = read.readTransaction(transactionFile);
        List<managefile.Transaction> matchedTransactions = new ArrayList<>();
        
        for (managefile.Transaction transaction :transactions){
            if (transaction.getCustomerID().equals(customerID)){
                matchedTransactions.add(transaction);
            }
        }
        return matchedTransactions;
    }
    
    public List<managefile.Transaction> getTransactionByID(String transactionID){
        List<managefile.Transaction> transactions = read.readTransaction(transactionFile);
        List<managefile.Transaction> matchedTransactions = new ArrayList<>();
        
        for (managefile.Transaction transaction :transactions){
            if (transaction.getTransactionID().equals(transactionID)){
                matchedTransactions.add(transaction);
            }
        }
        return matchedTransactions;
    }
    
    public List<managefile.Delivery> getDelivery(String orderID){
        List<managefile.Delivery> deliverys = read.readDelivery(deliveryFile);
        List<managefile.Delivery> matchedDelivery = new ArrayList<>();
        
        for (managefile.Delivery delivery :deliverys){
            if (delivery.getOrderID().equals(orderID)){
                matchedDelivery.add(delivery);
            }
        }
        return matchedDelivery;
    }
    public List<managefile.Runner> getRunner(){
        List<managefile.Runner> runners = read.readRunner(runnerFile);
        return runners;
    }
    
    public String addTransaction(String customerID,String generalID,String totalPrice,String type,String paymentMethod) throws IOException{
        List<managefile.Transaction> transactions = getTransaction();
        List transactionIDList = new ArrayList();
        for (managefile.Transaction transaction : transactions) {
            transactionIDList.add(transaction.getTransactionID());
        }
        String latestTransactionID = transactionIDList.isEmpty() ? "TR1" : pri.incrementPrimaryKey(transactionIDList);
        List<String> transactionStore = new ArrayList<>();
        transactionStore.add(latestTransactionID);
        transactionStore.add(customerID);
        transactionStore.add(generalID);
        transactionStore.add(datetime);
        transactionStore.add(String.valueOf(totalPrice));
        transactionStore.add(type);
        transactionStore.add(paymentMethod);
        data.addGeneralFile(transactionStore, transactionFile);
        
        return latestTransactionID;
    }
    public List<managefile.VendorReview> getAllVendorReview(){
        List<managefile.VendorReview> vendorReviews = read.readVendorReview(orderReviewFile);
        return vendorReviews;
    }
    
    public void addOrder(String customerID,List<managefile.Cart> cartList,List<managefile.Food> foodList,String orderType,String orderTypeDetails,double totalPrice,double deliveryfees, String runnerId) throws IOException{
        System.out.println(deliveryfees);
        Map<Object, Object> allOrders = getOrder(customerID);
        List<managefile.Order> orders = (List<managefile.Order>) allOrders.get("orders");
        List<managefile.Runner> runners = read.readRunner(runnerFile);
        List<managefile.Delivery> deliverys = read.readDelivery(deliveryFile);
        List<managefile.VendorReview> vendorReviews = read.readVendorReview(orderReviewFile);
        List <managefile.RunnerNotification> runnerNotifications = read.readRunnerNotification(runnerNotificationFile);
        
        List orderIDList = new ArrayList();
        for (managefile.Order order : orders) {
            orderIDList.add(order.getOrderID());
        }
        String latestOrderID = orderIDList.isEmpty() ? "O1": pri.incrementPrimaryKey(orderIDList);
        
        List deliveryIDList = new ArrayList();
        for (managefile.Delivery delivery : deliverys) {
            deliveryIDList.add(delivery.getDeliveryID());
        }
        String latestDeliveryID = deliveryIDList.isEmpty() ? "D1" : pri.incrementPrimaryKey(deliveryIDList);
        
        //send notification to runner
        List runnerNotificationIdList = new ArrayList();
        for (managefile.RunnerNotification runnerNotification : runnerNotifications){
            runnerNotificationIdList.add(runnerNotification.getNotificationId());
        }
        String latestRunnerNotificationId = runnerNotificationIdList.isEmpty() ? "RN1" : pri.incrementPrimaryKey(runnerNotificationIdList);
        List<RunnerNotification> notifications = new ArrayList<>();
        notifications.add(new RunnerNotification(latestRunnerNotificationId, runnerId, latestOrderID, "Pending"));
        write.writeRunnerNotification(notifications, runnerNotificationFile, true);
        
        List vendorReviewIDList = new ArrayList();
        for (managefile.VendorReview vendorReview : vendorReviews) {
            vendorReviewIDList.add(vendorReview.getReviewID());
        }
        String latestVendorReviewID = vendorReviewIDList.isEmpty() ? "VR1" : pri.incrementPrimaryKey(vendorReviewIDList);

        
        //main order store
        List<String> orderStore = new ArrayList<>();
        orderStore.add(latestOrderID);
        orderStore.add(customerID);
        if (orderType.equals("delivery")){
            orderStore.add(latestDeliveryID);
        }else{
            orderStore.add(null);
        }
        orderStore.add(cartList.getFirst().getVendorID());
        orderStore.add(latestVendorReviewID);
        orderStore.add(orderType);
        orderStore.add(orderTypeDetails);
        orderStore.add(datetime);
        orderStore.add(String.valueOf(totalPrice-deliveryfees));
        orderStore.add("Pending");
        data.addGeneralFile(orderStore, orderFile);
        
        //transaction store
        addTransaction(customerID,latestOrderID,String.valueOf(totalPrice),"Debit","E-Wallet");
        
        //delivery and delivery review store
        List<managefile.DeliveryReview> deliReviews = read.readDeliveryReview(deliveryReviewFile);
        List deliReviewIDList = new ArrayList();
        for (managefile.DeliveryReview deliReview : deliReviews) {
            deliReviewIDList.add(deliReview.getReviewID());
        }
        String latestDeliveryReviewID = deliReviewIDList.isEmpty() ? "DR1" : pri.incrementPrimaryKey(deliReviewIDList);
        if (orderType.equals("delivery")){
            List<String> deliveryStore = new ArrayList<>();
            deliveryStore.add(latestDeliveryID);
            deliveryStore.add(latestDeliveryReviewID);
            deliveryStore.add(latestOrderID);
            deliveryStore.add(runnerId);
            deliveryStore.add(String.valueOf(deliveryfees));
            deliveryStore.add(null);
            deliveryStore.add(datetime);
            deliveryStore.add("Pending");
            data.addGeneralFile(deliveryStore, deliveryFile);
            
            List<String> deliveryReviewStore = new ArrayList<>();
            deliveryReviewStore.add(latestDeliveryReviewID);
            deliveryReviewStore.add(latestDeliveryID);
            deliveryReviewStore.add(runners.getFirst().getId());
            deliveryReviewStore.add(null);
            deliveryReviewStore.add(null);
            data.addGeneralFile(deliveryReviewStore, deliveryReviewFile);
        }
        
        List<String> vendorReviewStore = new ArrayList<>();
        vendorReviewStore.add(latestVendorReviewID);
        vendorReviewStore.add(cartList.getFirst().getVendorID());
        vendorReviewStore.add(null);
        vendorReviewStore.add(null);
        data.addGeneralFile(vendorReviewStore, orderReviewFile);
        
        String orderItems = "";
        for (managefile.Cart cart : cartList) {
            Map<Object, Object> allOrders2 = getOrder(customerID);
            List<managefile.OrderItems> orderItems1 = (List<managefile.OrderItems>) allOrders2.get("ordersItems");
            String foodid = cart.getFoodID();
            String quantity = cart.getQuantity();
            String remarks = cart.getRemarks();
            
            List orderItemIDList = new ArrayList();
            for (managefile.OrderItems item : orderItems1) {
                orderItemIDList.add(item.getOrderItemID());
            }
            String latestOrderItemID = orderItemIDList.isEmpty() ? "OI1" : pri.incrementPrimaryKey(orderItemIDList);
            
            List<String> orderItemStore = new ArrayList<>();
            orderItemStore.add(latestOrderItemID);
            orderItemStore.add(latestOrderID);
            orderItemStore.add(foodid);
            orderItemStore.add(quantity);
            for (managefile.Food food : foodList) {
                if (food.getId().equals(foodid)){
                    orderItems += "x"+quantity +" "+food.getName()+" is placed order!|";
                    double totalAmount = Double.parseDouble(food.getPrice())*Integer.parseInt(quantity);
                    orderItemStore.add(String.valueOf(totalAmount));
                    break;
                }
            }
            orderItemStore.add("Pending");
            if (!remarks.equals("")){
                orderItemStore.add(remarks);
            }else{
                orderItemStore.add(null);
            }
            data.addGeneralFile(orderItemStore, orderItemFile);
        }
        String description1 = "Your Order #"+latestOrderID+" is placed order!";
        String description2 = "You received Order #"+latestOrderID+"|"+orderItems;
        sendNotifications(customerID,description1);
        sendNotifications(customerID,description2);
    }
    
    public void completeOrder(String customerID,String orderID) throws IOException{
        Map<Object, Object> allOrders = getOrderByOrderID(orderID);
        List<managefile.Order> orders = (List<managefile.Order>) allOrders.get("orders");
        List<managefile.OrderItems> orderItems = (List<managefile.OrderItems>) allOrders.get("ordersItems");
        List<managefile.Food> foodItems = (List<managefile.Food>) allOrders.get("foodItems");
        
        for (OrderItems orderItem : orderItems) {
            data.updateData(orderItem.getOrderItemID(), 5, "Completed", orderItemFile);
        }
        
        if (orders.getFirst().getOrderType().equalsIgnoreCase("delivery")){
            data.updateData(orders.getFirst().getDeliveryID(), 7, "Completed", deliveryFile);
            sendNotifications(customerID, "Your delivery order is received!");
        }else{
            sendNotifications(customerID, "Your order is received!");
        }
        data.updateData(orderID, 9, "Completed", orderFile);
    }
    
    public String sendNotifications(String userID,String message) throws IOException{
        List<managefile.Notification> notifications =  read.readNotification(notificationFile);
        List notificationIDList = new ArrayList();
        for (managefile.Notification notification : notifications) {
            notificationIDList.add(notification.getNotificationID());
        }
        String latestNotificationID = notificationIDList.isEmpty() ? "NT1": pri.incrementPrimaryKey(notificationIDList);
        
        List<String> notificationStore = new ArrayList<>();
        notificationStore.add(latestNotificationID);
        notificationStore.add(message);
        notificationStore.add(datetime);
        notificationStore.add(userID);
        data.addGeneralFile(notificationStore, notificationFile);
        return latestNotificationID;
    }
    
    public void removeCart(String customerID) throws IOException{
        Map<Object, Object> carts = getCart(customerID);
        List<managefile.Cart> cart = (List<managefile.Cart>) carts.get("carts");
        List<String> cartItems = new ArrayList<>();
        for (managefile.Cart cartItem : cart) {
            if (cartItem.getCustomerID().equals(customerID)) {
                data.removeRowById(cartItem.getCartID(),cartFile);
            }
        }
    }
    
    public Map<Object, Object> getDeliveryDetails(String orderID){
        List<managefile.Delivery> deliverys = read.readDelivery(deliveryFile);
        List<managefile.Runner> runners = read.readRunner(runnerFile);
        List<managefile.Delivery> matchedDelivery = new ArrayList<>();
        List<managefile.Runner> matchedRunner = new ArrayList<>();
        for (Delivery delivery : deliverys) {
            for (Runner runner : runners) {
                if (delivery.getOrderID().equals(orderID) && delivery.getRunnerID().equals(runner.getId())){
                    matchedDelivery.add(delivery);
                    matchedRunner.add(runner);
                }
            }
        }
        Map<Object, Object> result = new HashMap<>();
        result.put("deliverys", matchedDelivery);
        result.put("runners", matchedRunner);
        return result;
    }
    
    public void updateOrderDetails(String customerID,String orderID) throws IOException{
        Map<Object, Object> allOrders = getOrderByOrderID(orderID);
        List<managefile.Order> orders = (List<managefile.Order>) allOrders.get("orders");
        List<managefile.OrderItems> orderItems = (List<managefile.OrderItems>) allOrders.get("ordersItems");
        List<managefile.Food> foodItems = (List<managefile.Food>) allOrders.get("foodItems");
        
        for (OrderItems orderItem : orderItems) {
            data.updateData(orderItem.getOrderItemID(), 5, "Cancel", orderItemFile);
        }
        double totalAmount = Double.parseDouble(orders.getFirst().getTotalAmount());
        double totalRefund = totalAmount * 0.8;
        addTransaction(customerID, orderID, String.valueOf(totalRefund), "Refund", "E-Wallet");
        if (orders.getFirst().getOrderType().equalsIgnoreCase("delivery")){
            data.updateData(orders.getFirst().getDeliveryID(), 6, "Cancel", deliveryFile);
            sendNotifications(customerID, "Your delivery order is cancel.|The total of RM "+String.format("%.2f",totalRefund)+" is refunded.");
        }else{
            sendNotifications(customerID, "Your order is cancel.|The total of RM "+String.format("%.2f",totalRefund)+" is refunded.");
        }
        data.updateData(orderID, 9, "Cancel", orderFile);
        updateCredit(customerID, totalRefund, "Credit");
    }
    
    public List<managefile.Notification> getUserNotifications(String userid){
        List<managefile.Notification> notifications = read.readNotification(notificationFile);
        List<managefile.Notification> matchedNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getUserID().equals(userid)){
                matchedNotifications.add(notification);
            }
        }
        return matchedNotifications;
    }
    
    public void addDeliveryReview(String orderID,String ratings,String reviews){
        Map<Object, Object> allOrders = getOrderByOrderID(orderID);
        List<managefile.Order> orders = (List<managefile.Order>) allOrders.get("orders");
        List<managefile.Delivery> deliverys = getDelivery(orderID);
        String deliveryReviewID = deliverys.getFirst().getDeliveryReviewID();
        
        List<managefile.DeliveryReview> deliveryReviews = read.readDeliveryReview(deliveryReviewFile);
        for (DeliveryReview deliveryReview : deliveryReviews) {
            if (deliveryReview.getReviewID().equals(deliveryReviewID)){
                String updateData = deliveryReviewID+","+
                                    deliveryReview.getCustomerID()+","+
                                    deliverys.getFirst().getRunnerID()+","+
                                    ratings+","+
                                    reviews+","+
                                    datetime;
                data.updateData(deliveryReviewID, updateData, deliveryReviewFile);
            }
        }
    }
    
    public List<managefile.VendorReview> getOrderReviewByID(String reviewID){
        List<managefile.VendorReview> vendorReviews = read.readVendorReview(orderReviewFile);
                
        List<managefile.VendorReview> matchedVendorReview = new ArrayList<>();
        
        for (managefile.VendorReview vendorReview :vendorReviews){
            if (vendorReview.getReviewID().equals(reviewID)){
                matchedVendorReview.add(vendorReview);
            }
        }
        return matchedVendorReview;
    }
    
    public void addOrderReview(String orderID,String ratings,String reviews){
        Map<Object, Object> allOrders = getOrderByOrderID(orderID);
        List<managefile.Order> orders = (List<managefile.Order>) allOrders.get("orders");
        String orderReviewID = orders.getFirst().getOrderReviewID();
        List<managefile.VendorReview> vendorReviews = getOrderReviewByID(orderReviewID);
        
        for (managefile.VendorReview vendorReview :vendorReviews){
            if (vendorReview.getReviewID().equals(orderReviewID)){
                String updateData = orderReviewID+","+
                                    vendorReview.getVendorID()+","+
                                    ratings+","+
                                    reviews;
                data.updateData(orderReviewID, updateData, orderReviewFile);
            }
        }
    }
    public Map<Object, Object> getAllFeedback(){
        List<Feedback> feedbacks = read.readFeedback(feedbackFile);
        List<Customer> customers = read.readCustomerAccount(customerFile);
        
        List<Feedback> matchedFeedbacks = new ArrayList<>();
        List<Customer> matchedCustomers = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            for (Customer customer : customers) {
                if (feedback.getCustomerID().equals(customer.getId())){
                    matchedFeedbacks.add(feedback);
                    matchedCustomers.add(customer);
                }
            }
        }
        
        Map<Object, Object> result = new HashMap<>();
        result.put("feedbacks", matchedFeedbacks);
        result.put("customers", matchedCustomers);
        return result;
    }
    public void addFeedback(String customerID,String description) throws IOException{
        List<Feedback> feedbacks = read.readFeedback(feedbackFile);
        List feedbackList = new ArrayList();
        for (managefile.Feedback feedback : feedbacks) {
            feedbackList.add(feedback.getFeedbackID());
        }
        String latestFeedbackID = feedbackList.isEmpty() ? "F1" : pri.incrementPrimaryKey(feedbackList);
        
        List<String> feedbackStore = new ArrayList<>();
        feedbackStore.add(latestFeedbackID);
        feedbackStore.add(customerID);
        feedbackStore.add(null);
        feedbackStore.add(description);
        feedbackStore.add(datetime);
        data.addGeneralFile(feedbackStore, feedbackFile);
    }
    public List<VendorReview1> setVendorReviews() {
        List<String> reviewVendor = read.readVendorReview1(orderReviewFile);
        List<String> vendor = read.readVendorAccount1(vendorFile);
        Map<String, Vendor> vendorMap = new HashMap<>();

        for (String vendorLine : vendor) {
            String[] parts = vendorLine.split(",");
            String id = parts[0];
            String name = parts[1];
            String email = parts[2];
            String phone = parts[3];
            String password = parts[4];
            String stallName = parts[5];
            String stallType = parts[6];
            String imagePath = parts[7];
            String status = parts[8];
            
            Vendor vendors = new Vendor(id, name, email, phone, password, stallName, stallType, imagePath, status);
            vendorMap.put(id, vendors);
        }

        List<VendorReview1> reviews1 = new ArrayList<>();
        for (String reviewLine : reviewVendor) {
            String[] parts = reviewLine.split(",");
            String reviewId = parts[0];
            String vendorId = parts[1];
            String rating = parts[2];
            String comments = parts[3];

            Vendor vendors = vendorMap.get(vendorId);
            if (vendors != null) {
                VendorReview1 review = new VendorReview1(reviewId, vendors, vendorId, rating, comments);
                reviews1.add(review);
            }
        }
        return reviews1;
    }
    public List<VendorReview1> setVendorReviews(String targetVendor) {
        List<String> reviewVendor = read.readVendorReview1(orderReviewFile);
        List<String> vendor = read.readVendorAccount1(vendorFile);
        Map<String, Vendor> vendorMap = new HashMap<>();

        for (String vendorLine : vendor) {
            String[] parts = vendorLine.split(",");
            String id = parts[0];
            String name = parts[1];
            String email = parts[2];
            String phone = parts[3];
            String password = parts[4];
            String stallName = parts[5];
            String stallType = parts[6];
            String imagePath = parts[7];
            String status = parts[8];
            
            Vendor vendors = new Vendor(id, name, email, phone, password, stallName, stallType, imagePath, status);
            vendorMap.put(id, vendors);
        }

        List<VendorReview1> reviews1 = new ArrayList<>();
        for (String reviewLine : reviewVendor) {
            String[] parts = reviewLine.split(",");
            String reviewId = parts[0];
            String vendorId = parts[1];
            String rating = parts[2];
            String comments = parts[3];

            if (vendorId.equals(targetVendor)) {
                Vendor vendors = vendorMap.get(vendorId);
                if (vendors != null) {
                    VendorReview1 review = new VendorReview1(reviewId, vendors, vendorId, rating, comments);
                    reviews1.add(review);
                }
            }
        }
        return reviews1;
    }
}
