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


/**
 *
 * @author USER
 */
public class readAccount {
    public List readFile(String filePath){
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
//                double credit = Double.parseDouble(fields[5]);
//                System.out.println(credit);
//                users.add(new Customer(id,name,email,phone,password,credit));
                if (fields.length > 5){
                    if(fields[5].matches("\\d+\\.\\d+")){
                        double credit = Double.parseDouble(fields[5]);
                        users.add(new Customer(id,name,email,phone,password,credit));
                    }
                } else {
                    users.add(new GeneralRole(id, name, email, phone, password));
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
    
}
