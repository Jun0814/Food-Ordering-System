/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author TPY
 */
public class Vendor2 extends User{
    protected String filepath = "\\src\\main\\java\\repository\\vendor.txt";
    protected String role = "vendor";
    
    public Vendor2(){ }
    
    public Vendor2(String name, String email, String phone, String password){
        super.name = name;
        super.email = email;
        super.phone = phone;
        super.password = password;
    }
    
    protected String getContent(){
        String cotent = name + "," + email + "," + phone + "," + password;
        return cotent;
    }
    
    protected String getFilepath(){ return this.filepath; } 
}
