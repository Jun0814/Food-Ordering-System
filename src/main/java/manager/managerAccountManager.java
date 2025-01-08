/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.util.List;
import managefile.Manager;
import managefile.readFile;
/**
 *
 * @author Asus
 */
public class managerAccountManager {
    private final String manager_file_path = "src\\main\\java\\repository\\manager.txt";
    private readFile acc = new readFile();
    
    public String validateCredentials(String email, String password) {
        List<Manager> managers = acc.readManagerAccount(manager_file_path);
        for (Manager manager : managers) {
            if (manager.getEmail().equals(email) && manager.getPassword().equals(password)) {
                return manager.getId();
            }
        }
        return null;
    }
    
    public Manager getManagerDetails(String managerId){
        List<Manager> managers = acc.readManagerAccount(manager_file_path);
        for (Manager manager : managers ){
            if(manager.getId().equals(managerId)){
                return manager;
            }
        }
        return null;
    }
}
