/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class Vendor extends GeneralRole {
    private String stallName;
    private String stallType;
    private String imagePath;
    private String status;
    private String filepath = "src\\main\\java\\repository\\vendor.txt";
    
    public Vendor(){}
    
    public Vendor(String id, String name, String email, String phone, String password, String stallName,String stallType,String imagePath,String status) {
        super(id, name, email, phone, password);
        this.stallName = stallName;
        this.stallType = stallType;
        this.imagePath = imagePath;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getStallType() {
        return stallType;
    }

    public void setStallType(String stallType) {
        this.stallType = stallType;
    }
    
    public String getContent(){
        String cotent = super.getName() + "," + super.getEmail() + "," + super.getPhone()+ "," + super.getPassword();
        return cotent;
    }
    
    public String getFilepath(){ return this.filepath; } 
}
