/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class Order {
    private String orderID;
    private String customerID;
    private String orderType;
    private String orderTypeDetails;
    private String datetime;
    private String totalAmount;
    private String status;

    public Order(String orderID, String customerID, String orderType, String orderTypeDetails, String datetime, String totalAmount, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderType = orderType;
        this.orderTypeDetails = orderTypeDetails;
        this.datetime = datetime;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeDetails() {
        return orderTypeDetails;
    }

    public void setOrderTypeDetails(String orderTypeDetails) {
        this.orderTypeDetails = orderTypeDetails;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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

    
    
    
    
}
