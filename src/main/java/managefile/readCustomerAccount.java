/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

import java.util.List;

/**
 *
 * @author Asus
 */
public class readCustomerAccount {
    
    public Customer getSpecificCustomerDetail(String customerid){
        readAccount acc = new readAccount();
        List<Customer> customers = acc.readFile("src\\main\\java\\repository\\customer.txt");
        for (Customer customer : customers) {
            if (customer.getId().equals(customerid)){
                return customer;
            }
        }
        return null;
    }
    public String validateCredentials(String email, String password, String filePath) {
        readAccount acc = new readAccount();
        List<Customer> customers = acc.readFile(filePath);

        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                System.out.println(customer.getId());
                return customer.getId();
            }
        }
        return null;
    }
}
