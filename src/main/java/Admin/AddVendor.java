package Admin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import managefile.Data;
import method.primaryKey;

public class AddVendor extends javax.swing.JPanel {

    public AddVendor() {
        initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Enable anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set background color
        g2.setColor(new Color(40, 40, 56));

        // Draw rounded rectangle
        int arcWidth = 50;
        int arcHeight = 50;
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageHandler1 = new method.ImageHandler();
        VendorLabel = new javax.swing.JLabel();
        VendorLabel2 = new javax.swing.JLabel();
        VendorLabel7 = new javax.swing.JLabel();
        VendorLabel3 = new javax.swing.JLabel();
        vendorPassword = new method.RoundedTextField();
        vendorName = new method.RoundedTextField();
        vendorPhone = new method.RoundedTextField();
        stallStatus = new javax.swing.JComboBox<>();
        vendorEmail = new method.RoundedTextField();
        VendorLabel5 = new javax.swing.JLabel();
        stallName = new method.RoundedTextField();
        VendorLabel6 = new javax.swing.JLabel();
        VendorLabel4 = new javax.swing.JLabel();
        stallType = new method.RoundedTextField();
        VendorLabel1 = new javax.swing.JLabel();
        addButton = new method.RoundedButton();

        setBackground(new java.awt.Color(40, 40, 56));

        VendorLabel.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        VendorLabel.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel.setText("Add Vendor");

        VendorLabel2.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel2.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel2.setText("Password");

        VendorLabel7.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel7.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel7.setText("Stall Status");

        VendorLabel3.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel3.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel3.setText("Email");

        vendorPassword.setBackground(new java.awt.Color(255, 255, 255));
        vendorPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        vendorName.setBackground(new java.awt.Color(255, 255, 255));
        vendorName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        vendorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendorNameActionPerformed(evt);
            }
        });

        vendorPhone.setBackground(new java.awt.Color(255, 255, 255));
        vendorPhone.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        stallStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Unavailable" }));

        vendorEmail.setBackground(new java.awt.Color(255, 255, 255));
        vendorEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        VendorLabel5.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel5.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel5.setText("Stall Type");

        stallName.setBackground(new java.awt.Color(255, 255, 255));
        stallName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        VendorLabel6.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel6.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel6.setText("Stall Name");

        VendorLabel4.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel4.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel4.setText("Phone");

        stallType.setBackground(new java.awt.Color(255, 255, 255));
        stallType.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        VendorLabel1.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel1.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel1.setText("Name");

        addButton.setBackground(new java.awt.Color(126, 127, 154));
        addButton.setText("ADD");
        addButton.setBorderColor(new java.awt.Color(40, 40, 56));
        addButton.setColor(new java.awt.Color(126, 127, 154));
        addButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addButton.setFontColor(new java.awt.Color(255, 255, 51));
        addButton.setRadius(15);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(VendorLabel)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(VendorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(VendorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vendorName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vendorEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(VendorLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(stallType, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(VendorLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(stallName, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(VendorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(vendorPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(VendorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(vendorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(VendorLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(stallStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(VendorLabel)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(vendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vendorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(VendorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(VendorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vendorPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vendorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stallName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stallType, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stallStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel7))
                .addGap(29, 29, 29)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void vendorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vendorNameActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        String VendorName = vendorName.getText();
        String VendorEmail = vendorEmail.getText();
        String VendorPhone = vendorPhone.getText();
        String VendorPassword = vendorPassword.getText();
        String StallName = stallName.getText();
        String StallType = stallType.getText();
        String Avatar = "null";
        String Status = (String) stallStatus.getSelectedItem();
        
        if (VendorName.isEmpty() || VendorEmail.isEmpty() || VendorPassword.isEmpty() || StallName.isEmpty() || 
            StallType.isEmpty() || Status.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }
        
        String filePath = "src\\main\\java\\repository\\vendor.txt";

        try {
            // Read existing IDs from the file
            List<String> existingIDs = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(","); 
                    if (parts.length > 0) {
                        existingIDs.add(parts[0]); // Add the first column (userID) to the list
                    }
                }
            }

            // Generate the next unique user ID
            primaryKey pk = new primaryKey();
            String newUserID = pk.incrementPrimaryKey(existingIDs);

            String newLineContent = String.join(",",
                newUserID, VendorName, VendorEmail, VendorPhone, VendorPassword, StallName, StallType, Avatar, Status
            );

            Data dataManager = new Data();
            dataManager.insertData(newLineContent, filePath);

            JOptionPane.showMessageDialog(null, "Vendor added successfully!");

            vendorName.setText("");
            vendorEmail.setText("");
            vendorPhone.setText("");
            vendorPassword.setText("");
            stallName.setText("");
            stallType.setText("");
            stallStatus.setSelectedIndex(0);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while saving vendor data.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VendorLabel;
    private javax.swing.JLabel VendorLabel1;
    private javax.swing.JLabel VendorLabel2;
    private javax.swing.JLabel VendorLabel3;
    private javax.swing.JLabel VendorLabel4;
    private javax.swing.JLabel VendorLabel5;
    private javax.swing.JLabel VendorLabel6;
    private javax.swing.JLabel VendorLabel7;
    private method.RoundedButton addButton;
    private method.ImageHandler imageHandler1;
    private method.RoundedTextField stallName;
    private javax.swing.JComboBox<String> stallStatus;
    private method.RoundedTextField stallType;
    private method.RoundedTextField vendorEmail;
    private method.RoundedTextField vendorName;
    private method.RoundedTextField vendorPassword;
    private method.RoundedTextField vendorPhone;
    // End of variables declaration//GEN-END:variables
}
