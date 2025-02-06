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

public class EditRunner extends javax.swing.JPanel {
    
    private String userId;

    public EditRunner() {
        initComponents();
    }
    
    public void loadUserDetails(String userId) {
        this.userId = userId;
        String filePath = "src\\main\\java\\repository\\runner.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            // Skip header
                if (line.startsWith("ID,")) continue;

                // Parse vendor data
                String[] userData = line.split(",", -1);
                if (userData[0].equals(userId)) {
                // Populate fields
                runnerName.setText(userData[1]);
                runnerEmail.setText(userData[2]);
                runnerPhone.setText(userData[3]);
                runnerPassword.setText(userData[4]);
                runnerStatus.setSelectedItem(userData[5]);
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
        VendorLabel7 = new javax.swing.JLabel();
        VendorLabel3 = new javax.swing.JLabel();
        runnerPassword = new method.RoundedTextField();
        runnerName = new method.RoundedTextField();
        runnerPhone = new method.RoundedTextField();
        runnerStatus = new javax.swing.JComboBox<>();
        runnerEmail = new method.RoundedTextField();
        VendorLabel4 = new javax.swing.JLabel();
        VendorLabel1 = new javax.swing.JLabel();
        updateButton = new method.RoundedButton();

        setBackground(new java.awt.Color(40, 40, 56));

        VendorLabel.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        VendorLabel.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel.setText("Edit Runner");

        VendorLabel2.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel2.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel2.setText("Password");

        VendorLabel7.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel7.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel7.setText("Status");

        VendorLabel3.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VendorLabel3.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel3.setText("Email");

        runnerPassword.setBackground(new java.awt.Color(255, 255, 255));
        runnerPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        runnerName.setBackground(new java.awt.Color(255, 255, 255));
        runnerName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        runnerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runnerNameActionPerformed(evt);
            }
        });

        runnerPhone.setBackground(new java.awt.Color(255, 255, 255));
        runnerPhone.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        runnerStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Unavailable" }));

        runnerEmail.setBackground(new java.awt.Color(255, 255, 255));
        runnerEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(VendorLabel)
                        .addGap(50, 50, 50))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(VendorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(VendorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(runnerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(runnerEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(VendorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(runnerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(VendorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(runnerPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(VendorLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(runnerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(VendorLabel)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(runnerName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(runnerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(VendorLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(VendorLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runnerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runnerPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(runnerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VendorLabel7))
                .addGap(39, 39, 39)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void runnerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runnerNameActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_runnerNameActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        String updatedUserName = runnerName.getText();
        String updatedUserEmail = runnerEmail.getText();
        String updatedUserPhone = runnerPhone.getText();
        String updatedUserPassword = runnerPassword.getText();
        String updatedRunnerStatus = (String) runnerStatus.getSelectedItem();

        String filePath = "src\\main\\java\\repository\\runner.txt";
        String tempFilePath = "src\\main\\java\\repository\\runner_temp.txt";
        
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
                    updatedRunnerStatus
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
    private javax.swing.JLabel VendorLabel7;
    private method.ImageHandler imageHandler1;
    private method.RoundedTextField runnerEmail;
    private method.RoundedTextField runnerName;
    private method.RoundedTextField runnerPassword;
    private method.RoundedTextField runnerPhone;
    private javax.swing.JComboBox<String> runnerStatus;
    private method.RoundedButton updateButton;
    // End of variables declaration//GEN-END:variables
}
