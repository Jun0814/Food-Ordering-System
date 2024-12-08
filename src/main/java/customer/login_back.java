/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customer;
import java.util.List;
import managefile.*;

/**
 *
 * @author USER
 */
public class login_back {
    public Customer validateCredentials(String email, String password) {
        readAccount acc = new readAccount();
        List<Customer> customers = acc.readCustomer();

        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                System.out.println(customer);
                return customer;
            }
        }
        return null;
    }
}
