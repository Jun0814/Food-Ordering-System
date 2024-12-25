/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final String foodFile = "src\\main\\java\\repository\\food.txt";
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
    public List<Vendor> getVendors(){
        return acc.readVendorAccount(vendorFile);
    }
    public Map<String, Object> getSpecificVendorDetail(String vendorid){
        List<Vendor> vendors = acc.readVendorAccount(vendorFile);
        List<managefile.Food> foods = acc.readFood(foodFile);
        List<Vendor> matchedVendors = new ArrayList<>();
        List<managefile.Food> matchedFoods = new ArrayList<>();
        
        for (Vendor vendor : vendors) {
            if (vendor.getId().equals(vendorid)){
                matchedVendors.add(vendor);
            }
        }
        for (managefile.Food food:foods){
            if (food.getVendorid().equals(vendorid)){
                matchedFoods.add(food);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("vendors", matchedVendors);
        result.put("foods", matchedFoods);
        return result;
    }
}
