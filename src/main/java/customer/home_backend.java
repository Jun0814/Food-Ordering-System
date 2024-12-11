/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customer;

import java.util.List;
import managefile.Customer;
import managefile.readAccount;

/**
 *
 * @author USER
 */
public class home_backend {
    public Customer getSpecificCustomerDetail(String customerid){
        readAccount acc = new readAccount();
        List<Customer> customers = acc.readCustomer();
        for (Customer customer : customers) {
            if (customer.getId().equals(customerid)){
                return customer;
            }
        }
        return null;
    }
}
