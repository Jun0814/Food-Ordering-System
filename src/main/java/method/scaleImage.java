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
}
