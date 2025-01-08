/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author Asus
 */
public class Manager extends GeneralRole{
    private String filepath = "\\src\\main\\java\\repository\\manager.txt";
    
    public Manager() {}
    
    public Manager(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }
    
    public String getFilepath(){ return this.filepath; } 
}
