/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vendor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import managefile.Data;
import method.RoundedButton;

/**
 *
 * @author TPY
 */
public class VendorFoodPopUp extends javax.swing.JPanel {

    Data data = new Data();
    public String foodId, foodName, priceTag, quantity, imagePath, filepath, description, vendorId, foodType;
    public Color edgeColor, backgroundColor, themeColor;
    
    /**
     * Creates new form VendorFoodPopUp
     */
    public VendorFoodPopUp() {
        initComponents();
        titleLabel.setText("<html><u>Current Data</u></html>");
        roundedPanel.setOpaque(false);
        this.setOpaque(false);
    }
    
    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;        
        if(foodIdLabel != null){
            foodIdLabel.setText(foodId != null ? "Food ID: " + foodId : "Food ID: NULL");
        }
    }
    
    public String getFoodName() {
        return foodName;
    }
    
    public void setFoodName(String foodName) {
        this.foodName = foodName; 
        if(getFoodName() != null){ 
            foodNameLabel.setText(foodName != null ? "Food Name: " + foodName : "Food Name: NULL");
        }
        if(nameTextField != null){
            nameTextField.setText(foodName);
        }
    }
    
    public String getPriceTag() {
        return priceTag;
    }
    
    public void setPriceTag(String priceTag) {
        this.priceTag = priceTag;
        if (priceTag != null) { 
            priceLabel.setText(priceTag != null ? "Price: " + priceTag : "RM 0.00");
        }
        
        if(priceTextField != null){
            priceTextField.setText(priceTag);
        }
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
        if (quantity != null) { 
            quantityLabel.setText(quantity != null ? "Quantity: " + quantity : "Quantity: NULL");
        }
        
        if(quantityTextField != null){
            quantityTextField.setText(quantity);
        }
    }
        
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;        
        if(descriptionTextField != null){
            descriptionTextField.setText(description);
        }
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
        if (foodType != null) { 
            foodTypeLabel.setText(foodType != null ? "Food Type: " + foodType : "Food Type: NULL");
        }
        
        if(foodTypeTextField != null){
            foodTypeTextField.setText(foodType);
        }
    }
    
    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
        if (roundedPanel.getBackground() != null) {
            roundedPanel.setBackground(edgeColor != null ? edgeColor : Color.WHITE);
        }
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        if(roundedPanel.getBackgroundColor() != new Color(248,248,248)){
            roundedPanel.setBackgroundColor(backgroundColor != null ? backgroundColor : Color.WHITE);
        }
    }

    public Color getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(Color themeColor) {
        this.themeColor = themeColor;
        if (priceLabel2 != null && uploadRoundedButton != null) {
            priceLabel2.setForeground(themeColor != null ? themeColor : Color.BLACK);
            uploadRoundedButton.setColor(themeColor != null ? themeColor : Color.BLUE);
            uploadRoundedButton.setBorderColor(backgroundColor != null ? backgroundColor : Color.WHITE);
        }
    }
    
    public RoundedButton getBackButton(){
        return backRoundedButton;
    }
    
    public RoundedButton getUploadButton(){
        return uploadRoundedButton;
    }
    
    public RoundedButton getDeleteButton(){
        return deleteRoundedButton;
    }
    
    public RoundedButton getSaveButton(){
        return saveRoundedButton;
    }
    
    public JLabel getLabel(){
        return imageLabel2;
    }
    
    public String getNewContent(){
        foodId = getFoodId();
        foodName = nameTextField.getText();
        quantity = quantityTextField.getText();
        description = descriptionTextField.getText();
        priceTag = priceTextField.getText();
        foodType = foodTypeTextField.getText();
        imagePath = getImagePath();
        String newContent = foodId+ "," + foodName + "," + quantity + "," + description + "," + priceTag + "," + imagePath + "," + foodType + "," + vendorId;
        return newContent;
    }
    
    // Method to choose an image file and update the path in the text file
    public void chooseAndUpdateImage(String filepath, String foodId) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String originalImagePath = selectedFile.getAbsolutePath();

            String targetDir = "src/main/java/image_repository";
            String targetFileName = foodId + ".png";
            File targetFile = new File(targetDir, targetFileName);

            try {
                File directory = new File(targetDir);
                if (!directory.exists()) {
                    directory.mkdirs(); // Create the directory if it doesn't exist
                }

                // Copy the selected image to the target directory
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                String relativeImagePath = targetDir + "/" + targetFileName;
                setImagePath(relativeImagePath);
                
                data.updateData(getFoodId(), 5, getImagePath(), getFilepath());
                
                JOptionPane.showMessageDialog(null, "Image successfully updated and stored!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving the image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean popUpButtonAction(int option, String dialog){
        boolean confirmation = false;
        int response = JOptionPane.showConfirmDialog(
            null, 
            "Are you sure you want to " + dialog +  "?", 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            
            if(option == 1){
                chooseAndUpdateImage(filepath, foodId);
            }else if(option ==2){
                data.removeRowById(foodId, filepath);
                JOptionPane.showMessageDialog(null,  "The item has been successfully removed!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }else if(option ==3){
                data.updateData(foodId, getNewContent(), filepath);
                JOptionPane.showMessageDialog(null, "The item has been successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            confirmation = true;
        } else if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Exit To The Previous Page...", "Exit", JOptionPane.ERROR_MESSAGE);
            confirmation =  false;
        }
        return confirmation;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel = new method.RoundedPanel();
        imageLabel2 = new javax.swing.JLabel();
        priceLabel2 = new javax.swing.JLabel();
        uploadRoundedButton = new method.RoundedButton();
        saveRoundedButton = new method.RoundedButton();
        priceTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        quantityLabel2 = new javax.swing.JLabel();
        quantityTextField = new javax.swing.JTextField();
        descriptionTextField = new javax.swing.JTextField();
        imageLabel = new javax.swing.JLabel();
        foodNameLabel2 = new javax.swing.JLabel();
        foodNameLabel = new javax.swing.JLabel();
        backRoundedButton = new method.RoundedButton();
        deleteRoundedButton = new method.RoundedButton();
        descriptionLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        foodIdLabel = new javax.swing.JLabel();
        descriptionLabel1 = new javax.swing.JLabel();
        foodTypeTextField = new javax.swing.JTextField();
        foodTypeLabel = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 500));

        roundedPanel.setBackground(new java.awt.Color(100, 100, 100));
        roundedPanel.setBackgroundColor(new java.awt.Color(220, 220, 255));
        roundedPanel.setPreferredSize(new java.awt.Dimension(240, 200));
        roundedPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageLabel2.setForeground(new java.awt.Color(40, 40, 56));
        imageLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel2.setMaximumSize(new java.awt.Dimension(1438, 1125));
        imageLabel2.setPreferredSize(new java.awt.Dimension(240, 200));
        roundedPanel.add(imageLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 200));

        priceLabel2.setBackground(new java.awt.Color(40, 40, 56));
        priceLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        priceLabel2.setForeground(new java.awt.Color(40, 40, 56));
        priceLabel2.setText("Price:");
        roundedPanel.add(priceLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 110, 30));

        uploadRoundedButton.setBackground(new java.awt.Color(70, 73, 75));
        uploadRoundedButton.setForeground(new java.awt.Color(248, 248, 248));
        uploadRoundedButton.setText("Upload Image");
        uploadRoundedButton.setBorderColor(new java.awt.Color(220, 220, 255));
        uploadRoundedButton.setColor(new java.awt.Color(140, 75, 242));
        uploadRoundedButton.setColorClick(new java.awt.Color(50, 255, 100));
        uploadRoundedButton.setColorOver(new java.awt.Color(50, 255, 100));
        uploadRoundedButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uploadRoundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        uploadRoundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        uploadRoundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        uploadRoundedButton.setRadius(15);
        roundedPanel.add(uploadRoundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 190, 30));

        saveRoundedButton.setBackground(new java.awt.Color(140, 75, 242));
        saveRoundedButton.setForeground(new java.awt.Color(248, 248, 248));
        saveRoundedButton.setText("Save");
        saveRoundedButton.setBorderColor(new java.awt.Color(220, 220, 255));
        saveRoundedButton.setColor(new java.awt.Color(140, 75, 242));
        saveRoundedButton.setColorClick(new java.awt.Color(50, 255, 100));
        saveRoundedButton.setColorOver(new java.awt.Color(50, 255, 100));
        saveRoundedButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        saveRoundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        saveRoundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        saveRoundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        saveRoundedButton.setRadius(20);
        roundedPanel.add(saveRoundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 440, 90, 50));
        roundedPanel.add(priceTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 660, 30));
        roundedPanel.add(nameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 660, 30));

        quantityLabel2.setBackground(new java.awt.Color(40, 40, 56));
        quantityLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        quantityLabel2.setForeground(new java.awt.Color(40, 40, 56));
        quantityLabel2.setText("Quantity:");
        roundedPanel.add(quantityLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 110, 30));
        roundedPanel.add(quantityTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 660, 30));
        roundedPanel.add(descriptionTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 660, 30));

        imageLabel.setBackground(new java.awt.Color(40, 40, 56));
        imageLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        imageLabel.setForeground(new java.awt.Color(40, 40, 56));
        imageLabel.setText("Image:");
        roundedPanel.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, 60, 30));

        foodNameLabel2.setBackground(new java.awt.Color(40, 40, 56));
        foodNameLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        foodNameLabel2.setForeground(new java.awt.Color(40, 40, 56));
        foodNameLabel2.setText("Food Name:");
        roundedPanel.add(foodNameLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 110, 30));

        foodNameLabel.setBackground(new java.awt.Color(40, 40, 56));
        foodNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        foodNameLabel.setForeground(new java.awt.Color(40, 40, 56));
        foodNameLabel.setText("Food Name: Fried Rice");
        roundedPanel.add(foodNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 360, 30));

        backRoundedButton.setBackground(new java.awt.Color(140, 75, 242));
        backRoundedButton.setForeground(new java.awt.Color(248, 248, 248));
        backRoundedButton.setText("Back");
        backRoundedButton.setBorderColor(new java.awt.Color(220, 220, 255));
        backRoundedButton.setColor(new java.awt.Color(140, 75, 242));
        backRoundedButton.setColorClick(new java.awt.Color(200, 50, 120));
        backRoundedButton.setColorOver(new java.awt.Color(200, 50, 120));
        backRoundedButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        backRoundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        backRoundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        backRoundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        backRoundedButton.setRadius(20);
        roundedPanel.add(backRoundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 90, 50));

        deleteRoundedButton.setBackground(new java.awt.Color(140, 75, 242));
        deleteRoundedButton.setForeground(new java.awt.Color(248, 248, 248));
        deleteRoundedButton.setText("Delete");
        deleteRoundedButton.setBorderColor(new java.awt.Color(220, 220, 255));
        deleteRoundedButton.setColor(new java.awt.Color(140, 75, 242));
        deleteRoundedButton.setColorClick(new java.awt.Color(200, 50, 120));
        deleteRoundedButton.setColorOver(new java.awt.Color(200, 50, 120));
        deleteRoundedButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteRoundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        deleteRoundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        deleteRoundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        deleteRoundedButton.setRadius(20);
        roundedPanel.add(deleteRoundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 90, 50));

        descriptionLabel.setBackground(new java.awt.Color(40, 40, 56));
        descriptionLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        descriptionLabel.setForeground(new java.awt.Color(40, 40, 56));
        descriptionLabel.setText("Description:");
        roundedPanel.add(descriptionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 110, 30));

        titleLabel.setBackground(new java.awt.Color(140, 75, 242));
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(140, 75, 242));
        titleLabel.setText("Current Data");
        roundedPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 360, 20));

        priceLabel.setBackground(new java.awt.Color(40, 40, 56));
        priceLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(40, 40, 56));
        priceLabel.setText("Price: RM20.00");
        roundedPanel.add(priceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 360, 30));

        quantityLabel.setBackground(new java.awt.Color(40, 40, 56));
        quantityLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        quantityLabel.setForeground(new java.awt.Color(40, 40, 56));
        quantityLabel.setText("Quantity: 5");
        roundedPanel.add(quantityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 360, 30));

        foodIdLabel.setBackground(new java.awt.Color(40, 40, 56));
        foodIdLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        foodIdLabel.setForeground(new java.awt.Color(40, 40, 56));
        foodIdLabel.setText("Food ID: F1");
        roundedPanel.add(foodIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 360, 30));

        descriptionLabel1.setBackground(new java.awt.Color(40, 40, 56));
        descriptionLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        descriptionLabel1.setForeground(new java.awt.Color(40, 40, 56));
        descriptionLabel1.setText("Food Type:");
        roundedPanel.add(descriptionLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 110, 30));
        roundedPanel.add(foodTypeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 280, 30));

        foodTypeLabel.setBackground(new java.awt.Color(40, 40, 56));
        foodTypeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        foodTypeLabel.setForeground(new java.awt.Color(40, 40, 56));
        foodTypeLabel.setText("Food Type: Rice");
        roundedPanel.add(foodTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 360, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private method.RoundedButton backRoundedButton;
    private method.RoundedButton deleteRoundedButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel descriptionLabel1;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JLabel foodIdLabel;
    private javax.swing.JLabel foodNameLabel;
    private javax.swing.JLabel foodNameLabel2;
    private javax.swing.JLabel foodTypeLabel;
    private javax.swing.JTextField foodTypeTextField;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel imageLabel2;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel priceLabel2;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JLabel quantityLabel2;
    private javax.swing.JTextField quantityTextField;
    private method.RoundedPanel roundedPanel;
    private method.RoundedButton saveRoundedButton;
    private javax.swing.JLabel titleLabel;
    private method.RoundedButton uploadRoundedButton;
    // End of variables declaration//GEN-END:variables
}
