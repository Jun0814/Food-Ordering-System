/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package method;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * @author TPY
 * Sample create a round button: 
 * private final JButton confirmButton = RoundedButton.createRoundedButton("Healthcare That's Personalized for You", 60, 2, new Color(217, 217, 217));
 * RoundedButton.createRoundedButton(Button Text, Radius Integer, Thickness Integer, Color RGB);
 */
public class createRoundButton {
        public static JButton createRoundedButton(String text, int radius, int thickness, Color borderColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));
                g2.setColor(borderColor); 
                g2.setStroke(new BasicStroke(thickness)); 
                g2.draw(new RoundRectangle2D.Double(thickness / 2, thickness / 2, getWidth() - thickness, getHeight() - thickness, radius, radius));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setPreferredSize(new Dimension(200, 40)); 
        return button;
    }
}
