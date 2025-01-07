/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package method;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author TPY
 */
public class RoundedButton extends JButton {

    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private Color fontColor;
    private Color fontColorOver;
    private Color fontColorClick;
    private int radius = 0;

    public RoundedButton() {
        setColor(Color.WHITE);
        colorOver = new Color(179, 250, 160);
        colorClick = new Color(152, 184, 144);
        borderColor = new Color(30, 136, 56);
        fontColor = Color.BLACK;
        fontColorOver = Color.BLACK;
        fontColorClick = Color.BLACK;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(colorOver);
                setForeground(fontColorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(color);
                setForeground(fontColor);
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
                setForeground(fontColorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(colorOver);
                    setForeground(fontColorOver);
                } else {
                    setBackground(color);
                    setForeground(fontColor);
                }
            }
        });
    }

    public boolean isOver() {
        return over;
    }

    public Color getColor() {
        return color;
    }

    public Color getColorOver() {
        return colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Color getFontColorOver() {
        return fontColorOver;
    }

    public Color getFontColorClick() {
        return fontColorClick;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
        setForeground(fontColor); // Update the default foreground color
    }

    public void setFontColorOver(Color fontColorOver) {
        this.fontColorOver = fontColorOver;
    }

    public void setFontColorClick(Color fontColorClick) {
        this.fontColorClick = fontColorClick;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        // Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}
