/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class Delivery {
    private String deliveryID;
    private String deliveryReviewID;
    private String orderID;
    private String runnerID;
    private double deliveryFees;
    private String description;
    private String datetime;
    private String status;
    private String filepath = "src\\main\\java\\repository\\delivery.txt";
    
    public Delivery(){};

    public Delivery(String deliveryID, String deliveryReviewID, String orderID, String runnerID, double totalAmount, String description, String datetime, String status) {
        this.deliveryID = deliveryID;
        this.deliveryReviewID = deliveryReviewID;
        this.orderID = orderID;
        this.runnerID = runnerID;
        this.deliveryFees = totalAmount;
        this.description = description;
        this.datetime = datetime;
        this.status = status;
    }

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double totalAmount) {
        this.deliveryFees = totalAmount;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getDeliveryReviewID() {
        return deliveryReviewID;
    }

    public void setDeliveryReviewID(String deliveryReviewID) {
        this.deliveryReviewID = deliveryReviewID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getRunnerID() {
        return runnerID;
    }

    public void setRunnerID(String runnerID) {
        this.runnerID = runnerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getFilepath(){
        return filepath;
    }
    
}
