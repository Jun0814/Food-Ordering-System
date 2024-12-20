/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customer;

import java.util.List;
import managefile.Customer;
import managefile.Vendor;
import managefile.readFile;
import method.scaleImage;

/**
 *
 * @author Asus
 */
public class customer_backend{
    private final String customerFile = "src\\main\\java\\repository\\customer.txt";
    private final String vendorFile = "src\\main\\java\\repository\\vendor.txt";
    private readFile acc = new readFile();
    scaleImage scale = new scaleImage();
    
    public Customer getSpecificCustomerDetail(String customerid){
        List<Customer> customers = acc.readCustomerAccount(customerFile);
        for (Customer customer : customers) {
            if (customer.getId().equals(customerid)){
                return customer;
            }
        }
        return null;
    }
    public String validateCredentials(String email, String password) {
        List<Customer> customers = acc.readCustomerAccount(customerFile);
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer.getId();
            }
        }
        return null;
    }
    public Vendor getVendor(){
        return null;
    }
}
