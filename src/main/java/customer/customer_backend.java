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
import managefile.Customer;
import managefile.Vendor;
import managefile.readFile;
import managefile.writeFile;
import method.primaryKey;
import method.scaleImage;

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
    private readFile read = new readFile();
    private writeFile write = new writeFile();
    scaleImage scale = new scaleImage();
    method.primaryKey pri = new primaryKey();
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    String datetime = currentDate.toString()+"T" + currentTime.toString().split("\\.")[0];
    
    public Customer getSpecificCustomerDetail(String customerid){
        List<Customer> customers = read.readCustomerAccount(customerFile);
        for (Customer customer : customers) {
            if (customer.getId().equals(customerid)){
                return customer;
            }
        }
        return null;
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
    public Map<String, Object> getSpecificVendorDetail(String vendorid){
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
        Map<String, Object> result = new HashMap<>();
        result.put("vendors", matchedVendors);
        result.put("foods", matchedFoods);
        return result;
    }
    
    public Map<Object, Object> getCart(String customerID){
        List<managefile.Cart> carts = read.readCart(cartFile);
        List<managefile.Food> foods = read.readFood(foodFile);
        List<managefile.Cart> validCarts = new ArrayList<>();
        List<managefile.Food> matchedFoods = new ArrayList<>();
        for (managefile.Cart cart:carts){
            for (managefile.Food food:foods){
                if (customerID.equals(cart.getCustomerID())){
                    if (cart.getFoodID().equals(food.getId())){
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
    
    public void addCartItems(String customerID,String foodID,String quantitySelection,String remark) throws IOException{
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
                cart1.setQuantity(String.valueOf(newQuantity));
                itemUpdated = true;
                break;
            }
        }
        if (!itemUpdated) {
            List<String> cartItems = new ArrayList<>();
            cartItems.add(latestCartID);
            cartItems.add(customerID);
            cartItems.add(foodID);
            cartItems.add(quantitySelection);
            cartItems.add(remark);
            cartItems.add(datetime);
            write.addGeneralFile(cartItems, cartFile);
        }else{
            List<String> cartItems = new ArrayList<>();
            for (managefile.Cart cart1 : cart) {
                cartItems.add(cart1.getCartID());
                cartItems.add(cart1.getCustomerID());
                cartItems.add(cart1.getFoodID());
                cartItems.add(cart1.getQuantity());
                cartItems.add(cart1.getRemarks());
                cartItems.add(cart1.getDatetime());
            }
            write.updateCart(cartItems, cartFile);
        }
    }
    
    public void updateCartItems(String cartID,String customerID,String quantity) throws IOException{
        Map<Object, Object> carts = getCart(customerID);
        List<managefile.Cart> cart = (List<managefile.Cart>) carts.get("carts");
        for (managefile.Cart cart1 : cart) {
            if (cartID.equals(cart1.getCartID())){
                cart1.setQuantity(quantity);
                break;
            }
        }
        List<String> cartItems = new ArrayList<>();
        for (managefile.Cart cart1 : cart) {
            cartItems.add(cart1.getCartID());
            cartItems.add(cart1.getCustomerID());
            cartItems.add(cart1.getFoodID());
            cartItems.add(cart1.getQuantity());
            cartItems.add(cart1.getRemarks());
            cartItems.add(cart1.getDatetime());
        }
        write.updateCart(cartItems, cartFile);
    }
    
    public void removeCartItems(String cartID, String customerID,String foodID) throws IOException{
        Map<Object, Object> carts = getCart(customerID);
        List<managefile.Cart> cart = (List<managefile.Cart>) carts.get("carts");
        List<String> cartItems = new ArrayList<>();
        if (cartID != null && customerID != null  && foodID != null ){
            for (managefile.Cart cartItem : cart) {
                if (!(cartItem.getFoodID().equals(foodID) && cartItem.getCustomerID().equals(customerID))) {
                    cartItems.add(cartItem.getCartID());
                    cartItems.add(cartItem.getCustomerID());
                    cartItems.add(cartItem.getFoodID());
                    cartItems.add(cartItem.getQuantity());
                    cartItems.add(cartItem.getRemarks());
                    cartItems.add(cartItem.getDatetime());
                }
            }
            write.updateCart(cartItems, cartFile);
        }
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
        
        List<managefile.Order> allOrders = new ArrayList<>();
        List<managefile.OrderItems> allOrderItems = new ArrayList<>();
        for (managefile.Order order :orders){
            for (managefile.OrderItems orderitem:orderItems){
                if(orderID.equals(order.getOrderID())){
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
        result.put("ordersItems", allOrderItems);
        return result;
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
    
    public void addOrder(String customerID,List<managefile.Cart> cartList,String orderType,String orderTypeDetails,double totalPrice) throws IOException{
        Map<Object, Object> allOrders = getOrder(customerID);
        List<managefile.Order> orders = (List<managefile.Order>) allOrders.get("orders");
        List<managefile.Transaction> transactions = getTransaction(customerID);
        List<managefile.Notification> notifications =  read.readNotification(notificationFile);
        
        List orderIDList = new ArrayList();
        for (managefile.Order order : orders) {
            orderIDList.add(order.getOrderID());
        }
        String latestOrderID = orderIDList.isEmpty() ? "O1": pri.incrementPrimaryKey(orderIDList);
        
        List<String> orderStore = new ArrayList<>();
        orderStore.add(latestOrderID);
        orderStore.add(customerID);
        orderStore.add(orderType);
        orderStore.add(orderTypeDetails);
        orderStore.add(datetime);
        orderStore.add(String.valueOf(totalPrice));
        orderStore.add("Pending");
        write.addGeneralFile(orderStore, orderFile);
        
        List transactionIDList = new ArrayList();
        for (managefile.Transaction transaction : transactions) {
            transactionIDList.add(transaction.getTransactionID());
        }
        String latestTransactionID = transactionIDList.isEmpty() ? "TR1" : pri.incrementPrimaryKey(transactionIDList);
        List<String> transactionStore = new ArrayList<>();
        transactionStore.add(latestTransactionID);
        transactionStore.add(customerID);
        transactionStore.add(latestOrderID);
        transactionStore.add(null);
        transactionStore.add(datetime);
        transactionStore.add(String.valueOf(totalPrice));
        transactionStore.add("Debit");
        write.addGeneralFile(transactionStore, transactionFile);
        
        List<managefile.Runner> runners = read.readRunner(runnerFile);
        for (managefile.Cart cart : cartList) {
            Map<Object, Object> allOrders2 = getOrder(customerID);
            List<managefile.OrderItems> orderItems1 = (List<managefile.OrderItems>) allOrders2.get("ordersItems");
            String foodid = cart.getFoodID();
            String quantity = cart.getQuantity();
            String remarks = cart.getRemarks();
            List<managefile.Delivery> deliverys = read.readDelivery(deliveryFile);
            
            
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
            orderItemStore.add("Accept");
            if (!remarks.equals("")){
                orderItemStore.add(remarks);
            }else{
                orderItemStore.add(null);
            }
            write.addGeneralFile(orderItemStore, orderItemFile);
            
            
            List deliveryIDList = new ArrayList();
            for (managefile.Delivery delivery : deliverys) {
                deliveryIDList.add(delivery.getDeliveryID());
            }
            String latestDeliveryID = deliveryIDList.isEmpty() ? "D1" : pri.incrementPrimaryKey(deliveryIDList);
            if (orderType.equals("delivery")){
                List<String> deliveryStore = new ArrayList<>();
                deliveryStore.add(latestDeliveryID);
                deliveryStore.add(latestOrderID);
                deliveryStore.add(runners.getFirst().getId());
                deliveryStore.add(null);
                deliveryStore.add(datetime);
                deliveryStore.add("Pending");
                write.addGeneralFile(deliveryStore, deliveryFile);
            }
        }
    }
    public void removeCart(){
        
    }
    
    public void sendNotifications(){
        
    }
}
