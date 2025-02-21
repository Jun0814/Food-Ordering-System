/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vendor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import managefile.Data;
import method.ImageHandler;
import method.RoundedButton;
import method.primaryKey;
import vendor.FoodBlock;

/**
 *
 * @author TPY
 */
public class VendorStore extends javax.swing.JPanel {

    Data data = new Data();
    ImageHandler imageHandler = new ImageHandler();
    primaryKey primaryKey = new primaryKey();
    private String[][] foodData;
    private String userId, currentFoodId, currentFoodCategory;
    
    /**
     * Creates new form VendorStore
     */
    
    public VendorStore(String userId) {
        initComponents();        
        this.userId = userId;
        foodData = data.retrieveDataAsArray(7, userId, "src\\main\\java\\repository\\food.txt");
        
        intiCategoryButton();
        intiDefaultFoodCategory();
        setHorizontalScrollPane();
        this.add(jLabel1);
        setVerticalScrollPane();
    }

    private String getCurrentFoodId() {
        return currentFoodId;
    }

    private void setCurrentFoodId(String currentFoodId) {
        this.currentFoodId = currentFoodId;
    }

    private String getCurrentFoodCategory() {
        return currentFoodCategory;
    }

    private void setCurrentFoodCategory(String currentFoodCategory) {
        this.currentFoodCategory = currentFoodCategory;
    }
    
    private void setSubCategoryWidth() {
        int dateBlockCount = 0;
        int row;
        int subCategoryWidth = 0;
        
        for (int i = 0; i < categoryPanel.getComponentCount(); i++) {
            if (categoryPanel.getComponent(i) instanceof RoundedButton) {
                dateBlockCount++;
            }
        }
        
        row = (int) dateBlockCount;
        
        if(row >= 6){
            subCategoryWidth = 1000 + ((row-6)*(150));
        }else{
            subCategoryWidth = 1000;
        }
        categoryPanel.setPreferredSize(new Dimension(subCategoryWidth,60));
    }
    
    private void setMenuPanelHeight(){
        int foodBlockCount = 0;
        int row;
        int menuPanelHeight = 600;
        
        for (int i = 0; i < menuPanel.getComponentCount(); i++) {
            if (menuPanel.getComponent(i) instanceof JPanel) {
                foodBlockCount++;
            }
        }
        
        row = (int) Math.ceil((double) foodBlockCount / 4);
        
        if(row >= 3){
            menuPanelHeight = 600 + ((row-3)*(250));
        }else{
            menuPanelHeight = 600;
        }
        menuPanel.setPreferredSize(new Dimension(1000,menuPanelHeight));
    }
    
    private void setHorizontalScrollPane(){
        categoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JScrollPane scrollPane2 = new JScrollPane(categoryPanel);
        scrollPane2.setPreferredSize(new Dimension(1000,60));
        scrollPane2.setBorder(BorderFactory.createEmptyBorder());
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane2.getHorizontalScrollBar().setUnitIncrement(10);
            
        JScrollBar hScrollBar = scrollPane2.getHorizontalScrollBar();
        hScrollBar.setPreferredSize(new Dimension(6,6));
        
        this.add(scrollPane2);
    }
    
    private void setVerticalScrollPane(){
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setPreferredSize(new Dimension(1000,610));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.add(scrollPane);
    }
    
    private void intiDefaultFoodCategory(){
        for (String[] data : foodData) {
            try{
                String foodType = data.length > 6 ? data[6].trim() : "";
                if(foodType != null){
                    intiMenuPanel(foodType);
                    break;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void intiCategoryButton() {
        Map<String, RoundedButton> foodTypeButtons = new HashMap<>();

        for (String[] data : foodData) {
            try {
                String foodType = data.length > 6 ? data[6].trim() : "General";

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
                        intiMenuPanel(foodType);
                    });
                    
                    foodTypeButtons.put(foodType, foodTypeButton);
                    categoryPanel.add(foodTypeButton);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
                RoundedButton addRoundedButton = new RoundedButton();
        addRoundedButton.setRadius(15);
        addRoundedButton.setPreferredSize(new Dimension(50, 50));
        addRoundedButton.setBorderColor(new Color(200,200,255));
        addRoundedButton.setColor(new Color(255,255,255));
        addRoundedButton.setColorClick(new Color(243,222,138));
        addRoundedButton.setColorOver(new Color(140,75,242));
        addRoundedButton.setFontColor(Color.black);
        addRoundedButton.setFontColorClick(Color.black);
        addRoundedButton.setFontColorOver(Color.white);
        addRoundedButton.setFont(new Font("FontName", Font.BOLD, 20));
        addRoundedButton.setText("+");
        addRoundedButton.addActionListener(e -> {
            closeAllJDialog();
            intiPopUp();
        });
        categoryPanel.add(addRoundedButton);
        setSubCategoryWidth();
    }
    
    private void intiMenuPanel(String foodCategory){
        closeMenuPanel();
        
        for (String[] data : foodData) {
            try {
                String foodType = data.length > 6 ? data[6].trim() : "";
                if (foodCategory.equalsIgnoreCase(foodType)) {
                    String foodId = data.length > 0 ? data[0].trim() : "";
                    String foodName = data.length > 1 ? data[1].trim() : "";
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
                    editButton.addActionListener(e -> {
                        closeAllJDialog();
                        intiPopUp(foodId);
                    });
                    
                    foodPanel.repaint();
                    foodPanel.revalidate();
                    menuPanel.add(foodPanel);
                    setMenuPanelHeight();
                    
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        menuPanel.repaint();
        menuPanel.revalidate();
    }
    
    //** Create PopUp **//
    private void intiPopUp(){

        String[] ids = data.retrieveIdsFromFile("src\\main\\java\\repository\\food.txt");
        List<String> idList = Arrays.asList(ids); 
        String foodId = primaryKey.incrementPrimaryKey(idList);

        String foodName = "-";
        String status = "-";
        String foodDescription = "-";
        double price = 0;
        String imagePath = "src\\main\\java\\image_repository\\default-food.png";
        String foodType = "-";
        price = Double.parseDouble(String.format("%.2f", price));
        String formattedPrice = String.format("%.2f", price);

        FoodPopUp popUp = new FoodPopUp();
        popUp.setFoodId(foodId);
        popUp.setFoodName(foodName);
        popUp.setPriceTag(formattedPrice);
        popUp.setStatus(status);
        popUp.setFoodType(foodType);
        popUp.setImagePath(imagePath);
        popUp.setDescription(foodDescription);
        popUp.setVendorId(userId);
        popUp.setFilepath("src\\main\\java\\repository\\food.txt");

        BufferedImage loadedImage = imageHandler.loadImage(imagePath);
        JLabel label2 = popUp.getLabel();
        label2.setBounds(0, 0, 400, 200);
        imageHandler.displayImageOnLabel(loadedImage, label2);

        RoundedButton backButton  = popUp.getBackButton();
        backButton.setVisible(true);
        RoundedButton deleteButton  = popUp.getDeleteButton();
        deleteButton.setVisible(false);
        RoundedButton saveButton  = popUp.getSaveButton();
        saveButton.setVisible(false);
        RoundedButton uploadButton = popUp.getUploadButton();
        uploadButton.setVisible(false);
        RoundedButton uploadButton2 = popUp.getUploadButton2();
        uploadButton2.setVisible(true);
        RoundedButton addButton = popUp.getAddButton();
        addButton.setVisible(true);

        addButton.addActionListener(e -> {
            boolean confimation = popUp.popUpButtonAction(4,"add this food to the store");
            if(confimation == true){
                closeAllJDialog();
                reinitializeToMenuPanel();
            }
        });
        uploadButton2.addActionListener(e -> {
            boolean confimation = popUp.popUpButtonAction(5,"upload new image to this food");
            if(confimation == true){
                closeAllJDialog();
                reinitializeToPopUp();
            }
        });
        backButton.addActionListener(e -> {
            closeAllJDialog();
        });

        showPopUp(popUp);
        setCurrentFoodCategory(foodType);
        setCurrentFoodId(foodId);
        popUp.repaint();
        popUp.revalidate();

        menuPanel.repaint();
        menuPanel.revalidate();
    }
    
    //** Update Pop Up **//
    private void intiPopUp(String foodID){
        for (String[] data : foodData) {
            try {
                String firstId = data.length > 0 ? data[0].trim() : "";
                if (foodID.equalsIgnoreCase(firstId)) {
                    String foodId = data.length > 0 ? data[0].trim() : "";
                    String foodName = data.length > 1 ? data[1].trim() : "";
                    String status = data.length > 2 ? data[2].trim() : "Available";
                    String foodDescription = data.length > 3 ? data[3].trim() : "";
                    double price = data.length > 4 ? Math.round(Double.parseDouble(data[4].trim()) * 100.0) / 100.0 : 0.0;
                    String imagePath = data.length > 5 ? data[5].trim() : "";
                    String foodType = data.length > 6 ? data[6].trim() : "";
                    price = Double.parseDouble(String.format("%.2f", price));
                    String formattedPrice = String.format("%.2f", price);
                    
                    FoodPopUp popUp = new FoodPopUp();
                    popUp.setFoodId(foodId);
                    popUp.setFoodName(foodName);
                    popUp.setPriceTag(formattedPrice);
                    popUp.setStatus(status);
                    popUp.setFoodType(foodType);
                    popUp.setImagePath(imagePath);
                    popUp.setDescription(foodDescription);
                    popUp.setVendorId(userId);
                    popUp.setFilepath("src\\main\\java\\repository\\food.txt");
                    
                    BufferedImage loadedImage = imageHandler.loadImage(imagePath);
                    JLabel label2 = popUp.getLabel();
                    label2.setBounds(0, 0, 400, 200);
                    imageHandler.displayImageOnLabel(loadedImage, label2);
                    
                    RoundedButton backButton  = popUp.getBackButton();
                    backButton.setVisible(true);
                    RoundedButton deleteButton  = popUp.getDeleteButton();
                    deleteButton.setVisible(true);
                    RoundedButton saveButton  = popUp.getSaveButton();
                    saveButton.setVisible(true);
                    RoundedButton uploadButton = popUp.getUploadButton();
                    uploadButton.setVisible(true);
                    RoundedButton uploadButton2 = popUp.getUploadButton2();
                    uploadButton2.setVisible(false);
                    RoundedButton addButton = popUp.getAddButton();
                    addButton.setVisible(false);
                    
                    deleteButton.addActionListener(e -> {
                        boolean confimation = popUp.popUpButtonAction(2,"delete this food from the store");
                        if(confimation == true){
                            closeAllJDialog();
                            reinitializeToMenuPanel();
                        }
                    });
                    saveButton.addActionListener(e -> {
                        boolean confimation = popUp.popUpButtonAction(3,"change this food details");
                        if(confimation == true){
                            closeAllJDialog();
                            reinitializeToPopUp();
                        }
                    });
                    backButton.addActionListener(e -> {
                        closeAllJDialog();
                    });
                    uploadButton.addActionListener(e -> {
                        boolean confimation = popUp.popUpButtonAction(1,"upload new image to this food");
                        if(confimation == true){
                            closeAllJDialog();
                            reinitializeToPopUp();
                        }
                    });
                    
                    showPopUp(popUp);
                    setCurrentFoodCategory(foodType);
                    setCurrentFoodId(foodId);
                    popUp.repaint();
                    popUp.revalidate();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        menuPanel.repaint();
        menuPanel.revalidate();
    }
    
    private void closeMenuPanel(){
        Component[] componentList = menuPanel.getComponents();
        for(Component c : componentList){
            if(c instanceof FoodBlock){
                menuPanel.remove(c);
            }
        }
    }
    
    private void closeAllJDialog(){
        for (Window window : Window.getWindows()) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                dialog.dispose();
            }
        }
    }
    
    private void reinitializeToPopUp() {
        foodData = data.retrieveDataAsArray(7, userId, "src\\main\\java\\repository\\food.txt");
        removeAll();
        revalidate();
        repaint();
        initComponents();
        setVerticalScrollPane();
        intiCategoryButton();
        intiDefaultFoodCategory();
        intiMenuPanel(getCurrentFoodCategory());
        intiPopUp(getCurrentFoodId());
    }
    
    private void reinitializeToMenuPanel(){
        foodData = data.retrieveDataAsArray(7, userId, "src\\main\\java\\repository\\food.txt");
        removeAll();
        revalidate();
        repaint();
        initComponents();
        setVerticalScrollPane();
        intiCategoryButton();
        intiDefaultFoodCategory();
        intiMenuPanel(getCurrentFoodCategory());
    }
        
    private static void showPopUp(JPanel panel) {        
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
        menuPanel.setAutoscrolls(true);
        menuPanel.setMinimumSize(new java.awt.Dimension(1000, 600));
        menuPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        add(menuPanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel menuPanel;
    // End of variables declaration//GEN-END:variables
}
