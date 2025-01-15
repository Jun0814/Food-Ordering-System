/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author USER
 */
public class Runner extends GeneralRole{
    private String status;
    private String filepath = "src\\main\\java\\repository\\runner.txt";
    
    public Runner(){}

    public Runner(String id, String name, String email, String phone, String password,String status) {
        super(id, name, email, phone, password);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getFilepath(){ return this.filepath; } 
    
}
