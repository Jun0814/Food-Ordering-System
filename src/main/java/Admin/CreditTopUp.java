package Admin;

import customer.customer_backend;
import customer.topUpPanel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import method.scaleImage;

public class CreditTopUp extends javax.swing.JPanel {
    
    customer_backend backend = new customer_backend();

    public CreditTopUp() {
        initComponents();
    }
    
    private boolean isCustomerExists(String customerId) {
    File file = new File("src\\main\\java\\repository\\customer.txt");
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        boolean isFirstLine = true;

        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String[] data = line.split(",");
            if (data.length > 0 && data[0].trim().equals(customerId)) {
                return true;
            }
        }
    } catch (IOException e) {
        Logger.getLogger(topUpPanel.class.getName()).log(Level.SEVERE, null, e);
    }
    return false;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CreditLabel = new javax.swing.JLabel();
        roundedPanel1 = new method.RoundedPanel();
        TopUpAmountLabel = new javax.swing.JLabel();
        customerId = new method.RoundedTextField();
        TopUpAmount = new method.RoundedTextField();
        customerIdLabel = new javax.swing.JLabel();
        TopUpButton = new method.RoundedButton();

        setBackground(new java.awt.Color(126, 127, 154));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        CreditLabel.setBackground(new java.awt.Color(255, 255, 51));
        CreditLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        CreditLabel.setForeground(new java.awt.Color(255, 255, 51));
        CreditLabel.setText("Credit Top-up");

        roundedPanel1.setBackground(new java.awt.Color(126, 127, 154));
        roundedPanel1.setBackgroundColor(new java.awt.Color(40, 40, 56));

        TopUpAmountLabel.setBackground(new java.awt.Color(255, 255, 51));
        TopUpAmountLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TopUpAmountLabel.setForeground(new java.awt.Color(255, 255, 51));
        TopUpAmountLabel.setText("Amount");

        customerId.setBackground(new java.awt.Color(255, 255, 255));
        customerId.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        customerId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerIdActionPerformed(evt);
            }
        });

        TopUpAmount.setBackground(new java.awt.Color(255, 255, 255));
        TopUpAmount.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        TopUpAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TopUpAmountActionPerformed(evt);
            }
        });

        customerIdLabel.setBackground(new java.awt.Color(255, 255, 51));
        customerIdLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        customerIdLabel.setForeground(new java.awt.Color(255, 255, 51));
        customerIdLabel.setText("Customer ID");

        TopUpButton.setBackground(new java.awt.Color(126, 127, 154));
        TopUpButton.setText("TOP-UP");
        TopUpButton.setBorderColor(new java.awt.Color(40, 40, 56));
        TopUpButton.setColor(new java.awt.Color(126, 127, 154));
        TopUpButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TopUpButton.setFontColor(new java.awt.Color(255, 255, 51));
        TopUpButton.setRadius(15);
        TopUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TopUpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customerIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TopUpAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TopUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(customerId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TopUpAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(customerId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TopUpAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TopUpAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(customerIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(TopUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(CreditLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(301, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(CreditLabel)
                .addGap(169, 169, 169)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(250, 250, 250))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void customerIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerIdActionPerformed

    private void TopUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TopUpButtonActionPerformed
        // TODO add your handling code here:
        method.scaleImage sc = new scaleImage();
        boolean validInput = true;
        boolean validAmount = true;
        String CustomerId = customerId.getText();
        String Amount = TopUpAmount.getText();

        if (CustomerId.isEmpty() || Amount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }
        if (Amount.equals("") || !sc.isNumeric(Amount)){
            JOptionPane.showMessageDialog(null, "Please enter valid amount!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            validAmount = false;
        }else if (Double.parseDouble(Amount) < 20){
            JOptionPane.showMessageDialog(null, "The minimum amount is RM 20!", "Minimum amount required", JOptionPane.WARNING_MESSAGE);
            validAmount = false;
        }else if (!isCustomerExists(CustomerId)) {
            JOptionPane.showMessageDialog(null, "Customer ID not found!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (validInput && validAmount){
            int confirm = JOptionPane.showConfirmDialog(
                null, 
                "Are you sure you want to proceed with the top-up of RM " + Amount + " ?", 
                "Confirm", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Top-up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                try {
                    backend.addTransaction(CustomerId,null,Amount,"Credit","Cash");
                    backend.sendNotifications(CustomerId, "You have top up RM "+Amount+" to your e-wallet successfully!");
                    backend.updateCredit(CustomerId, Double.parseDouble(Amount), "Credit");
                    
                } catch (IOException ex) {
                    Logger.getLogger(topUpPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Top-up cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_TopUpButtonActionPerformed

    private void TopUpAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TopUpAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TopUpAmountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CreditLabel;
    private method.RoundedTextField TopUpAmount;
    private javax.swing.JLabel TopUpAmountLabel;
    private method.RoundedButton TopUpButton;
    private method.RoundedTextField customerId;
    private javax.swing.JLabel customerIdLabel;
    private method.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
