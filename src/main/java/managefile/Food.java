/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class Food {
    private String id;
    private String name;
    private String status;
    private String description;
    private String price;
    private String imagepath;
    private String category;
    private String vendorid;
    private String filepath = "src\\main\\java\\repository\\food.txt";
    
    public Food(){}

    public Food(String id, String name, String status, String description, String price, String imagepath, String category, String vendorid) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.price = price;
        this.imagepath = imagepath;
        this.category = category;
        this.vendorid = vendorid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVendorid() {
        return vendorid;
    }

    public void setVendorid(String vendorid) {
        this.vendorid = vendorid;
    }
    
    public String getFilepath(){
        return this.filepath;
    }
    
}
