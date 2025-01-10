/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package method;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author USER
 */
public class scaleImage {
    public ImageIcon processImage(String path, int wid, int hei){
        ImageIcon imageIcon = new ImageIcon(path);
        Image resizedImage = imageIcon.getImage().getScaledInstance(wid,hei,Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    
    public boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public int maxMonth(int[] arrs){
        int max = arrs[0];
        for (int arr : arrs) {
            if (arr > max) {
                max = arr;
            }
        }
        return max;
    }
}
