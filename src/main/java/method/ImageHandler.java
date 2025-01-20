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

public class ImageHandler {
    
    public ImageHandler() {}
        
    // Load image out by path
    public static BufferedImage loadImage(String imagepath) {
        BufferedImage loadedImage = null;
        try {
            if (imagepath == null || imagepath.isEmpty()) {
                imagepath = "src/main/java/image_repository/default-food.png";
            }

            File imageFile = new File(imagepath);

            // Check if the file exists
            if (!imageFile.exists()) {
                System.err.println("File not found: " + imagepath + ". Using default image.");
                imageFile = new File("src/main/java/image_repository/food-stall.png");
            }

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