/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package method;

/**
 *
 * @author TPY
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Base64;

public class ImageHandler {
    
    public ImageHandler() {}
    
    // Save image as base64 to a text file - UNFIHSIED - DONT USED
    public void saveImage(BufferedImage image, String filePath) {
        try {
            // Convert BufferedImage to byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Encode byte array to base64 string
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

            // Write base64 string to text file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(encodedImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Load image out by path
    public static BufferedImage loadImage(String imagepath) {
        BufferedImage loadedImage = null;
        try {
            File imageFile = new File(imagepath);
            loadedImage = ImageIO.read(imageFile);            
        } catch (IOException e) {
            System.err.println("Error loading image: " + imagepath);
            e.printStackTrace();
        }
        return loadedImage;
    }
    
    // Display an image on a JLabel
    public void displayImageOnLabel(BufferedImage image, JLabel label) {
        if (image != null) {
            int labelWidth = label.getWidth();
            int labelHeight = label.getHeight();

            Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            ImageIcon imageIcon = new ImageIcon(scaledImage);
            label.setIcon(imageIcon);
            label.repaint();
        }
    }
}