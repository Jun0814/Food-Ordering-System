/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import managefile.Vendor;


/**
 *
 * @author USER
 */
public class readFile {
    public List readUserAccount(String filePath){
        List<GeneralRole> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String email = fields[2];
                String phone = fields[3];
                String password = fields[4];
                users.add(new GeneralRole(id, name, email, phone, password));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
    public List readCustomerAccount(String filePath){
        List<Customer> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String email = fields[2];
                String phone = fields[3];
                String password = fields[4];
                double credit = Double.parseDouble(fields[5]);
                users.add(new Customer(id,name,email,phone,password,credit));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
    public List readVendorAccount(String filePath){
        List<Vendor> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String id = fields[0];
                String name = fields[1];
                String email = fields[2];
                String phone = fields[3];
                String password = fields[4];
                String stallname = fields[5];
                String stalltype = fields[6];
                String imagepath = fields[7];
                users.add(new Vendor(id,name,email,phone,password,stallname,stalltype,imagepath));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
}
