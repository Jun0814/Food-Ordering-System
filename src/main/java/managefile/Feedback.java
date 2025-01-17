/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class Feedback {
    private String feedbackID;
    private String customerID;
    private String managerID;
    private String description;
    private String datetime;
    private String filepath = "src\\main\\java\\repository\\feedback.txt";
    
    public Feedback(){}
  
    public Feedback(String feedbackID,String customerID, String managerID, String description,String datetime) {
        this.feedbackID = feedbackID;
        this.customerID = customerID;
        this.managerID = managerID;
        this.description = description;
        this.datetime =datetime;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFilepath(){
        return filepath;
    }
    
}
