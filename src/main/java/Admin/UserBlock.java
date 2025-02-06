package Admin;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JPanel;
import method.RoundedButton;

public class UserBlock extends JPanel {

    public String username, userId, email, phone;
    public Color edgeColor, backgroundColor, themeColor;

    public UserBlock() {
        initComponents();
        setMinimumSize(new java.awt.Dimension(916, 200));
        setPreferredSize(new java.awt.Dimension(916, 200));
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (UserId != null) { 
            UserId.setText(userId);
        }
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
        if (UsernameLabel != null) { 
            UsernameLabel.setText(username != null ? username : "Unknown User");
        }
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
        if (Email != null) { 
            Email.setText(email);
        }
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
        if (Phone != null) { 
            Phone.setText(phone);
        }
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
        if (userPanel.getBackground() != null) {
            userPanel.setBackground(edgeColor != null ? edgeColor : Color.WHITE);
        }
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        if(userPanel.getBackgroundColor() != new Color(248,248,248)){
            userPanel.setBackgroundColor(backgroundColor != null ? backgroundColor : Color.WHITE);
        }
    }

    public Color getThemeColor() {
        return themeColor;
    }
    
    public RoundedButton getButton(){
        return roundedButton;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userPanel = new method.RoundedPanel();
        Phone = new javax.swing.JLabel();
        roundedButton = new method.RoundedButton();
        roundedButton1 = new method.RoundedButton();
        UsernameLabel = new javax.swing.JLabel();
        UserId = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(916, 200));
        setPreferredSize(new java.awt.Dimension(916, 200));

        userPanel.setBackground(new java.awt.Color(126, 127, 154));
        userPanel.setMinimumSize(new java.awt.Dimension(916, 200));
        userPanel.setPreferredSize(new java.awt.Dimension(916, 200));
        userPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Phone.setBackground(new java.awt.Color(40, 40, 56));
        Phone.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Phone.setForeground(new java.awt.Color(40, 40, 56));
        Phone.setText("018-7783486");
        userPanel.add(Phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 370, 30));

        roundedButton.setBackground(new java.awt.Color(140, 75, 242));
        roundedButton.setForeground(new java.awt.Color(248, 248, 248));
        roundedButton.setText("Edit");
        roundedButton.setBorderColor(new java.awt.Color(248, 248, 248));
        roundedButton.setColor(new java.awt.Color(140, 75, 242));
        roundedButton.setColorClick(new java.awt.Color(50, 255, 130));
        roundedButton.setColorOver(new java.awt.Color(50, 255, 100));
        roundedButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        roundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        roundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        roundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        roundedButton.setRadius(20);
        roundedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButtonActionPerformed(evt);
            }
        });
        userPanel.add(roundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 120, 50));

        roundedButton1.setBackground(new java.awt.Color(255, 51, 51));
        roundedButton1.setForeground(new java.awt.Color(248, 248, 248));
        roundedButton1.setText("Delete");
        roundedButton1.setBorderColor(new java.awt.Color(248, 248, 248));
        roundedButton1.setColor(new java.awt.Color(255, 51, 51));
        roundedButton1.setColorClick(new java.awt.Color(204, 0, 51));
        roundedButton1.setColorOver(new java.awt.Color(204, 0, 51));
        roundedButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        roundedButton1.setFontColor(new java.awt.Color(248, 248, 248));
        roundedButton1.setFontColorClick(new java.awt.Color(248, 248, 248));
        roundedButton1.setFontColorOver(new java.awt.Color(248, 248, 248));
        roundedButton1.setRadius(20);
        roundedButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton1ActionPerformed(evt);
            }
        });
        userPanel.add(roundedButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, 120, 50));

        UsernameLabel.setBackground(new java.awt.Color(40, 40, 56));
        UsernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        UsernameLabel.setForeground(new java.awt.Color(40, 40, 56));
        UsernameLabel.setText("Username");
        userPanel.add(UsernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 590, 50));

        UserId.setBackground(new java.awt.Color(40, 40, 56));
        UserId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UserId.setForeground(new java.awt.Color(40, 40, 56));
        UserId.setText("X1");
        userPanel.add(UserId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 370, 30));

        Email.setBackground(new java.awt.Color(40, 40, 56));
        Email.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Email.setForeground(new java.awt.Color(40, 40, 56));
        Email.setText("jiajunchong00@gmail.com");
        userPanel.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 370, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void roundedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButtonActionPerformed
        // actionPerformed(evt);
        String userId = UserId.getText();
        
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle("Edit Customer");
        dialog.setSize(450, 470);
        dialog.setLocationRelativeTo(this);

        EditCustomer editUserPanel = new EditCustomer();
        editUserPanel.loadUserDetails(userId); // Custom method to load vendor details

        // Set the content pane of the dialog to the EditVendor panel
        dialog.setContentPane(editUserPanel);

        // Make the dialog modal and visible
        dialog.setModal(true);
        dialog.setVisible(true);
    }//GEN-LAST:event_roundedButtonActionPerformed

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed
        // TODO add your handling code here:
        String filePath = "src\\main\\java\\repository\\customer.txt";
        String tempFilePath = "src\\main\\java\\repository\\customer_temp.txt";
        String userToDelete = UserId.getText();

        File inputFile = new File(filePath);
        File tempFile = new File(tempFilePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean isHeader = true;

            while ((currentLine = reader.readLine()) != null) {
                if (isHeader) { 
                    writer.write(currentLine);
                    writer.newLine();
                    isHeader = false;
                    continue;
                }

                String[] userData = currentLine.split(",");
                if (!userData[0].equals(userToDelete)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else {
            System.out.println("Error deleting file");
        }

        getParent().remove(this);
        getParent().revalidate();
        getParent().repaint();
    }//GEN-LAST:event_roundedButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Phone;
    private javax.swing.JLabel UserId;
    private javax.swing.JLabel UsernameLabel;
    private method.RoundedButton roundedButton;
    private method.RoundedButton roundedButton1;
    method.RoundedPanel userPanel;
    // End of variables declaration//GEN-END:variables
}
