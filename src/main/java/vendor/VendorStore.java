/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vendor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import managefile.Data;
import method.ImageHandler;
import method.RoundedButton;
import vendor.FoodBlock;

/**
 *
 * @author TPY
 */
public class VendorStore extends javax.swing.JPanel {

    Data data = new Data();
    String[][] foodData;
    ImageHandler imageHandler = new ImageHandler();
    String userId;
    
    /**
     * Creates new form VendorStore
     */
    
    public VendorStore(String userId) {
        initComponents();        
        this.userId = userId;
        foodData = data.retrieveDataAsArray(7, userId, "src\\main\\java\\repository\\food.txt");
        addCategoryButton();
    }

    public void addCategoryButton() {        
        Map<String, RoundedButton> foodTypeButtons = new HashMap<>();

        for (String[] data : foodData) {
            try {
                String foodType = data.length > 6 ? data[6].trim() : "General";
                
                // Create a button for each food type
                RoundedButton foodTypeButton = foodTypeButtons.get(foodType);
                if (foodTypeButton == null) {
                    
                    foodTypeButton = new RoundedButton();
                    foodTypeButton.setPreferredSize(new Dimension(150, 50)); 
                    foodTypeButton.setText(foodType);
                    foodTypeButton.setRadius(20);
                    foodTypeButton.setBorderColor(new Color(200,200,255));
                    
                    foodTypeButton.setColor(new Color(255,255,255));
                    foodTypeButton.setColorClick(new Color(243,222,138));
                    foodTypeButton.setColorOver(new Color(140,75,242));
                    
                    foodTypeButton.setFontColor(Color.black);
                    foodTypeButton.setFontColorClick(Color.black);
                    foodTypeButton.setFontColorOver(Color.white);
                    
                    foodTypeButton.addActionListener(e -> {
                        addMenuPanel(foodType);
                    });

                    foodTypeButtons.put(foodType, foodTypeButton);
                    categoryPanel.add(foodTypeButton);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void addMenuPanel(String foodCategory){
        Component[] componentList = menuPanel.getComponents();
        for(Component c : componentList){
            if(c instanceof FoodBlock){
                menuPanel.remove(c);
            }
        }
        
        for (String[] data : foodData) {
            try {
                String foodType = data.length > 6 ? data[6].trim() : "";
                if (foodCategory.equalsIgnoreCase(foodType)) {
                    String foodId = data.length > 0 ? data[0].trim() : "";
                    String foodName = data.length > 1 ? data[1].trim() : "";
                    String quantity = data.length > 2 ? data[2].trim() : "";
                    String foodDescription = data.length > 3 ? data[3].trim() : "";
                    double price = data.length > 4 ? Math.round(Double.parseDouble(data[4].trim()) * 100.0) / 100.0 : 0.0;
                    String imagePath = data.length > 5 ? data[5].trim() : "";
                    price = Double.parseDouble(String.format("%.2f", price));
                    String formattedPrice = String.format("%.2f", price);
                    
                    FoodBlock foodPanel = new FoodBlock();
                    foodPanel.setFoodId(foodId);
                    foodPanel.setFoodName(foodName);
                    foodPanel.setPriceTag("RM" + formattedPrice);
                    foodPanel.setButtonText("Edit");
                    foodPanel.setThemeColor(new Color(140,75,242));
                    foodPanel.setEdgeColor(new Color(200,200,255));
                    foodPanel.setBackgroundColor(new Color(255,255,255));       
                    foodPanel.setPreferredSize(new Dimension(240,200));
                    
                    BufferedImage loadedImage = imageHandler.loadImage(imagePath);
                    JLabel label = foodPanel.getLabel();
                    label.setBounds(0, 0, 220, 110);
                    imageHandler.displayImageOnLabel(loadedImage, label);
                    
                    RoundedButton editButton = foodPanel.getButton();
                    VendorFoodPopUp popUp = new VendorFoodPopUp();
                    popUp.setFoodId(foodId);
                    popUp.setFoodName(foodName);
                    popUp.setPriceTag(formattedPrice);
                    popUp.setQuantity(quantity);
                    popUp.setDescription(foodDescription);
                    RoundedButton backButton  = popUp.getBackButton();
                    
                    JLabel label2 = popUp.getLabel();
                    label2.setBounds(0, 0, 400, 200);
                    imageHandler.displayImageOnLabel(loadedImage, label2);
                    
                    backButton.addActionListener(e -> {
                        JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(popUp);
                        if (dialog != null) {
                            dialog.getContentPane().removeAll();
                            dialog.getContentPane().revalidate();
                            dialog.getContentPane().repaint();
                            dialog.dispose();
                        }
                    });
                            
                    editButton.addActionListener(e -> {
                        for (Window window : Window.getWindows()) {
                            if (window instanceof JDialog) {
                                JDialog dialog = (JDialog) window;
                                dialog.dispose();
                            }
                        }
                        showPopUp(popUp);
                    });
                    
                    foodPanel.repaint();
                    foodPanel.revalidate();
                    menuPanel.add(foodPanel);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        menuPanel.repaint();
        menuPanel.revalidate();
    }
    
    public static void showPopUp(JPanel panel) {        
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        
        addDragFunctionality(dialog);

        dialog.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(
            (screenSize.width - dialog.getWidth()) / 2,
            (screenSize.height - dialog.getHeight()) / 2
        );
        dialog.setVisible(true);
    }
        
    private static void addDragFunctionality(JDialog dialog) {
        final int[] mouseX = {0};
        final int[] mouseY = {0};

        // Mouse Pressed: Record the mouse starting point
        dialog.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX[0] = e.getX();
                mouseY[0] = e.getY();
            }
        });

        // Mouse Dragged: Update the dialog's location
        dialog.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = dialog.getLocation().x + e.getX() - mouseX[0];
                int y = dialog.getLocation().y + e.getY() - mouseY[0];
                dialog.setLocation(x, y);
            }
        });
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        categoryPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(200, 200, 255));
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        jLabel2.setBackground(new java.awt.Color(39, 40, 56));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(140, 75, 242));
        jLabel2.setText("Category");
        add(jLabel2);

        categoryPanel.setBackground(new java.awt.Color(200, 200, 255));
        categoryPanel.setPreferredSize(new java.awt.Dimension(1000, 60));
        add(categoryPanel);

        jLabel1.setBackground(new java.awt.Color(39, 40, 56));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 75, 242));
        jLabel1.setText("Food & Beverage");
        add(jLabel1);

        menuPanel.setBackground(new java.awt.Color(200, 200, 255));
        menuPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
        add(menuPanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel menuPanel;
    // End of variables declaration//GEN-END:variables
}
