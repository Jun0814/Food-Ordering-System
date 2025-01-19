package Admin;

import java.awt.Color;
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
    
    public RoundedButton getButton(){
        return roundedButton;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel = new method.RoundedPanel();
        Phone = new javax.swing.JLabel();
        roundedButton = new method.RoundedButton();
        roundedButton1 = new method.RoundedButton();
        UsernameLabel = new javax.swing.JLabel();
        UserId = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(916, 200));
        setPreferredSize(new java.awt.Dimension(916, 200));

        roundedPanel.setBackground(new java.awt.Color(126, 127, 154));
        roundedPanel.setMinimumSize(new java.awt.Dimension(916, 200));
        roundedPanel.setPreferredSize(new java.awt.Dimension(916, 200));
        roundedPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Phone.setBackground(new java.awt.Color(40, 40, 56));
        Phone.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Phone.setForeground(new java.awt.Color(40, 40, 56));
        Phone.setText("018-7783486");
        roundedPanel.add(Phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 370, 30));

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
        roundedPanel.add(roundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 120, 50));

        roundedButton1.setBackground(new java.awt.Color(255, 51, 51));
        roundedButton1.setForeground(new java.awt.Color(248, 248, 248));
        roundedButton1.setText("Delete");
        roundedButton1.setBorderColor(new java.awt.Color(248, 248, 248));
        roundedButton1.setColor(new java.awt.Color(140, 75, 242));
        roundedButton1.setColorClick(new java.awt.Color(50, 255, 130));
        roundedButton1.setColorOver(new java.awt.Color(50, 255, 100));
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
        roundedPanel.add(roundedButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, 120, 50));

        UsernameLabel.setBackground(new java.awt.Color(40, 40, 56));
        UsernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        UsernameLabel.setForeground(new java.awt.Color(40, 40, 56));
        UsernameLabel.setText("Username");
        roundedPanel.add(UsernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 590, 50));

        UserId.setBackground(new java.awt.Color(40, 40, 56));
        UserId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UserId.setForeground(new java.awt.Color(40, 40, 56));
        UserId.setText("X1");
        roundedPanel.add(UserId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 370, 30));

        Email.setBackground(new java.awt.Color(40, 40, 56));
        Email.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Email.setForeground(new java.awt.Color(40, 40, 56));
        Email.setText("jiajunchong00@gmail.com");
        roundedPanel.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 370, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void roundedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButtonActionPerformed
        // actionPerformed(evt);
    }//GEN-LAST:event_roundedButtonActionPerformed

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundedButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JLabel Phone;
    private javax.swing.JLabel UserId;
    private javax.swing.JLabel UsernameLabel;
    private method.RoundedButton roundedButton;
    private method.RoundedButton roundedButton1;
    method.RoundedPanel roundedPanel;
    // End of variables declaration//GEN-END:variables
}
