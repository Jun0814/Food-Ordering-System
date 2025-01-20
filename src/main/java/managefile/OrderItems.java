/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class OrderItems {
    private String orderItemID;
    private String orderID;
    private String foodID;
    private String quantity;
    private String totalAmount;
    private String status;
    private String remarks;
    private String filepath = "src\\main\\java\\repository\\orderitems.txt";
    
    public OrderItems(){}

    public OrderItems(String orderItemID, String orderID, String foodID, String quantity, String totalAmount, String status, String remarks) {
        this.orderItemID = orderItemID;
        this.orderID = orderID;
        this.foodID = foodID;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
        this.remarks = remarks;
    }

    public String getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(String orderItemID) {
        this.orderItemID = orderItemID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getFilepath(){
        return filepath;
    }
    
}
