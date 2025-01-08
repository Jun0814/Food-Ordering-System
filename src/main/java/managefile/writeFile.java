/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author USER
 */
public class writeFile {
    public void addGeneralFile(List<String> cartList,String filepath) throws IOException{
        FileWriter fw = new FileWriter(filepath,true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            String cartRow = "";
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.size()-1 == i){
                    cartRow += cartList.get(i)+"\n";
                }else{
                    cartRow += cartList.get(i)+",";
                }
            }
            bw.write(cartRow);
            bw.flush();
            bw.close();
        }
    }
    public void updateCart(List<String> cartList,String filepath) throws IOException{
        FileWriter fw = new FileWriter(filepath);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            for (int i=0;i< cartList.size();i+=6){
                bw.write(cartList.get(i)+",");
                bw.write(cartList.get(i+1)+",");
                bw.write(cartList.get(i+2)+",");
                bw.write(cartList.get(i+3)+",");
                bw.write(cartList.get(i+4)+",");
                bw.write(cartList.get(i+5)+"\n");
            }
            bw.flush();
            bw.close();
        }
    }
}   
