/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class VendorReview {
    private String reviewID;
    private String vendorID;
    private String rating;
    private String comments;
    private String filepath ="src\\main\\java\\repository\\orderreview.txt";
    
    public VendorReview(){}

    public VendorReview(String reviewID, String vendorID, String rating, String comments) {
        this.reviewID = reviewID;
        this.vendorID = vendorID;
        this.rating = rating;
        this.comments = comments;
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
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
    
    public String getFilepath(){
        return filepath;
    }
    
}
