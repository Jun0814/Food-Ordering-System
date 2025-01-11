/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author Asus
 */
public class Order {
    public String filepath = "src\\main\\java\\repository\\order.txt";
    public String orderId, customerId, reviewId, orderType, orderTypeDetails, dateTime, status;
    private double totalAmount;
    
    public Order(){}
    
    public Order(String orderId, String customerId, String reviewId, String orderType, String orderTypeDetails, String dateTime, double totalAmount, String status){
        this.orderId = orderId;
        this.customerId = customerId;
        this.reviewId = reviewId;
        this.orderType = orderType;
        this.orderTypeDetails = orderTypeDetails;
        this.dateTime = dateTime;
        this.totalAmount = totalAmount;
        this.status = status;
    }
    
    public double getTotalAmount(){
        return totalAmount;
    }
    
    public void setTotalAmount(){
        this.totalAmount = totalAmount;
    }
    
    public String getFilepath(){
        return filepath;
    }
}
