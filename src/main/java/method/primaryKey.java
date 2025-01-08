/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package method;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class primaryKey {
    public String incrementPrimaryKey(List<String> primaryArr){
        List<String> mainStr = new ArrayList<>();
        List<Integer> mainInt = new ArrayList<>();
        
        for (String primaryKey : primaryArr) {
            StringBuilder stringPart = new StringBuilder();
            StringBuilder intPart = new StringBuilder();
            
            for (char key : primaryKey.toCharArray()) {
                if (Character.isDigit(key)) {
                    intPart.append(key);
                } else {
                    stringPart.append(key);
                }
            }
            
            mainStr.add(stringPart.toString());
            mainInt.add(Integer.parseInt(intPart.toString()));
        }
        
        int maxValue;
        maxValue = mainInt.get(0);
        for (int i = 1; i < mainInt.size(); i++) {
            int num = mainInt.get(i);
            if (num > maxValue) {
                maxValue = num;
            }
        }
        
        String stringPart = mainStr.get(mainStr.size() - 1);
        
        return stringPart + (maxValue + 1);
    }
}
