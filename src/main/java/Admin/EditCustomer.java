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

public class EditCustomer extends javax.swing.JPanel {
    
    private String userId;
    private String credit;
    private String feedback;

    public EditCustomer() {
        initComponents();
    }
    
    public void loadUserDetails(String userId) {
        this.userId = userId;
        String filePath = "src\\main\\java\\repository\\customer.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            // Skip header
                if (line.startsWith("ID,")) continue;

                // Parse vendor data
                String[] userData = line.split(",", -1);
                if (userData[0].equals(userId)) {
                // Populate fields
                customerName.setText(userData[1]);
                customerEmail.setText(userData[2]);
                customerPhone.setText(userData[3]);
                customerPassword.setText(userData[4]);
                credit = (userData[5]);
                feedback = (userData[6]);
                break;
                }
            }
        } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error loading user details: " + e.getMessage());
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
        VendorLabel = new javax.swing.JLabel();
        VendorLabel2 = new javax.swing.JLabel();
        VendorLabel3 = new javax.swing.JLabel();
        customerPassword = new method.RoundedTextField();
        customerName = new method.RoundedTextField();
        customerPhone = new method.RoundedTextField();
        customerEmail = new method.RoundedTextField();
        VendorLabel4 = new javax.swing.JLabel();
        VendorLabel1 = new javax.swing.JLabel();
        updateButton = new method.RoundedButton();

        setBackground(new java.awt.Color(40, 40, 56));

        VendorLabel.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        VendorLabel.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel.setText("Edit Customer");

        VendorLabel2.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel2.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel2.setText("Password");

        VendorLabel3.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel3.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel3.setText("Email");

        customerPassword.setBackground(new java.awt.Color(255, 255, 255));
        customerPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        customerName.setBackground(new java.awt.Color(255, 255, 255));
        customerName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        customerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerNameActionPerformed(evt);
            }
        });

        customerPhone.setBackground(new java.awt.Color(255, 255, 255));
        customerPhone.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        customerEmail.setBackground(new java.awt.Color(255, 255, 255));
        customerEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        VendorLabel4.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel4.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel4.setText("Phone");

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
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(VendorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(VendorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(customerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(customerEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(VendorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(customerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(VendorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(customerPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(VendorLabel)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(VendorLabel)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(VendorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(VendorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void customerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerNameActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        String updatedUserName = customerName.getText();
        String updatedUserEmail = customerEmail.getText();
        String updatedUserPhone = customerPhone.getText();
        String updatedUserPassword = customerPassword.getText();

        String filePath = "src\\main\\java\\repository\\customer.txt";
        String tempFilePath = "src\\main\\java\\repository\\customer_temp.txt";
        
        if (this.userId == null || this.userId.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
            "User ID is missing. Unable to update.",
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

                String[] userData = line.split(",", -1);
                if (userData[0].equals(this.userId)) {
                    // Write updated data
                    writer.write(String.join(",", new String[] {
                    this.userId,
                    updatedUserName,
                    updatedUserEmail,
                    updatedUserPhone,
                    updatedUserPassword,
                    credit,
                    feedback
                    }));
                } else {
                    // Write existing data unchanged
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error updating user data: " + e.getMessage());
        }

        // Replace the original file with the updated file
        File originalFile = new File(filePath);
        File tempFile = new File(tempFilePath);

        if (originalFile.delete() && tempFile.renameTo(originalFile)) {
            javax.swing.JOptionPane.showMessageDialog(this,
            "User details updated successfully.",
            "Success", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            // Close dialog window if update is successful
            javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
            "Error replacing the updated file. User data might be corrupted.",
            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VendorLabel;
    private javax.swing.JLabel VendorLabel1;
    private javax.swing.JLabel VendorLabel2;
    private javax.swing.JLabel VendorLabel3;
    private javax.swing.JLabel VendorLabel4;
    private method.RoundedTextField customerEmail;
    private method.RoundedTextField customerName;
    private method.RoundedTextField customerPassword;
    private method.RoundedTextField customerPhone;
    private method.ImageHandler imageHandler1;
    private method.RoundedButton updateButton;
    // End of variables declaration//GEN-END:variables
}
