/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author Asus
 */
public class Admin extends GeneralRole{
    private String filepath = "\\src\\main\\java\\repository\\admin.txt";
    
    public Admin() {}
    
    public Admin(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }
    
    public String getFilepath(){ return this.filepath; } 
}
