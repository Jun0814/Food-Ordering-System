/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class Customer extends GeneralRole {
    private String feedbackID;
    private double credit;
    
    private String filepath = "\\src\\main\\java\\repository\\customer.txt";
    
    public Customer(){}
    
    public Customer(String id, String name, String email, String phone, String password, double credit,String feedbackID) {
        super(id, name, email, phone, password);
        this.credit = credit;
        this.feedbackID = feedbackID;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
    
    public String getFilepath(){
        return filepath;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }
    
}
