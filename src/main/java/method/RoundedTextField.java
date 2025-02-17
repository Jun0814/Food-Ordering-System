package method;

import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

public class RoundedTextField extends JTextField {
    private int radius = 15; // Default corner radius
    private int leftrightpadding = 10; // Default padding from the border
    private int topbottompadding = 5;
    
    public RoundedTextField() {
        setOpaque(false); // Ensure background is transparent for rounded corners
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public int getRadius() {
        return radius;
    }

    public void setTopBottomPadding(int padding) {
        this.topbottompadding = padding;
        repaint();
    }

    public int getTopBottomPadding() {
        return topbottompadding;
    }
    
    public void setLeftRightPadding(int padding) {
        this.leftrightpadding = padding;
        repaint();
    }

    public int getLeftRightPadding() {
        return leftrightpadding;
    }

    @Override
    public Insets getInsets() {
        // Add padding to the text field
        return new Insets(topbottompadding, leftrightpadding, topbottompadding, leftrightpadding);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Draw border
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        g2.dispose();

        // Let the parent class draw the text
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Avoid painting the default border
    }
}


