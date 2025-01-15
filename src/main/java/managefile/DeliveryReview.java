/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class DeliveryReview {
    private String reviewID;
    private String customerID;
    private String runnerID;
    private String rating;
    private String comments;
    private String datetime;
    private String filepath = "src\\main\\java\\repository\\deliveryReview.txt";
    
    public DeliveryReview(){}

    public DeliveryReview(String reviewID, String customerID, String runnerID, String rating, String comments) {
        this.reviewID = reviewID;
        this.customerID = customerID;
        this.runnerID = runnerID;
        this.rating = rating;
        this.comments = comments;
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String deliveryID) {
        this.customerID = deliveryID;
    }

    public String getRunnerID() {
        return runnerID;
    }

    public void setRunnerID(String runnerID) {
        this.runnerID = runnerID;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = comments;
    }
    
    public String getFilepath(){
        return filepath;
    }
}
