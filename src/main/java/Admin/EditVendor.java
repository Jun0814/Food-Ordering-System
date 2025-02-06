package Admin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class EditVendor extends javax.swing.JPanel {

    String imagePath;
    private String vendorId;
    
    public EditVendor() {
        initComponents();
    }
    
    public void loadVendorDetails(String vendorId) {
        this.vendorId = vendorId;
        String filePath = "src\\main\\java\\repository\\vendor.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            // Skip header
                if (line.startsWith("ID,")) continue;

                // Parse vendor data
                String[] vendorData = line.split(",", -1);
                if (vendorData[0].equals(vendorId)) {
                // Populate fields
                vendorName.setText(vendorData[1]);
                vendorEmail.setText(vendorData[2]);
                vendorPhone.setText(vendorData[3]);
                vendorPassword.setText(vendorData[4]);
                stallName.setText(vendorData[5]);
                stallType.setText(vendorData[6]);
                imagePath = vendorData[7];
                stallStatus.setSelectedItem(vendorData[8]);
                break;
                }
            }
        } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error loading vendor details: " + e.getMessage());
        }
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
        stallType1 = new method.RoundedTextField();
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
        updateButton = new method.RoundedButton();

        stallType1.setBackground(new java.awt.Color(255, 255, 255));
        stallType1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setBackground(new java.awt.Color(40, 40, 56));

        VendorLabel.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        VendorLabel.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel.setText("Edit Vendor");

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

        updateButton.setBackground(new java.awt.Color(126, 127, 154));
        updateButton.setText("UPDATE");
        updateButton.setBorderColor(new java.awt.Color(40, 40, 56));
        updateButton.setColor(new java.awt.Color(126, 127, 154));
        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        updateButton.setFontColor(new java.awt.Color(255, 255, 51));
        updateButton.setRadius(15);
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
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
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void vendorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vendorNameActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        String updatedVendorName = vendorName.getText();
        String updatedVendorEmail = vendorEmail.getText();
        String updatedVendorPhone = vendorPhone.getText();
        String updatedVendorPassword = vendorPassword.getText();
        String updatedStallName = stallName.getText();
        String updatedStallType = stallType.getText();
        String updatedStatus = (String) stallStatus.getSelectedItem();

        String filePath = "src\\main\\java\\repository\\vendor.txt";
        String tempFilePath = "src\\main\\java\\repository\\vendor_temp.txt";
        
        if (this.vendorId == null || this.vendorId.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
            "Vendor ID is missing. Unable to update.",
            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {

            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    writer.write(line); // Write header
                    writer.newLine();
                    isHeader = false;
                    continue;
                }

                String[] vendorData = line.split(",", -1);
                if (vendorData[0].equals(this.vendorId)) {
                    // Write updated data
                    writer.write(String.join(",", new String[] {
                    this.vendorId,
                    updatedVendorName,
                    updatedVendorEmail,
                    updatedVendorPhone,
                    updatedVendorPassword,
                    updatedStallName,
                    updatedStallType,
                    imagePath, // Update with the appropriate image path or placeholder
                    updatedStatus
                    }));
                } else {
                    // Write existing data unchanged
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error updating vendor data: " + e.getMessage());
        }

        // Replace the original file with the updated file
        File originalFile = new File(filePath);
        File tempFile = new File(tempFilePath);

        if (originalFile.delete() && tempFile.renameTo(originalFile)) {
            javax.swing.JOptionPane.showMessageDialog(this,
            "Vendor details updated successfully.",
            "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            // Close dialog window if update is successful
            javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
            "Error replacing the updated file. Vendor data might be corrupted.",
            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VendorLabel;
    private javax.swing.JLabel VendorLabel1;
    private javax.swing.JLabel VendorLabel2;
    private javax.swing.JLabel VendorLabel3;
    private javax.swing.JLabel VendorLabel4;
    private javax.swing.JLabel VendorLabel5;
    private javax.swing.JLabel VendorLabel6;
    private javax.swing.JLabel VendorLabel7;
    private method.ImageHandler imageHandler1;
    private method.RoundedTextField stallName;
    private javax.swing.JComboBox<String> stallStatus;
    private method.RoundedTextField stallType;
    private method.RoundedTextField stallType1;
    private method.RoundedButton updateButton;
    private method.RoundedTextField vendorEmail;
    private method.RoundedTextField vendorName;
    private method.RoundedTextField vendorPassword;
    private method.RoundedTextField vendorPhone;
    // End of variables declaration//GEN-END:variables
}
