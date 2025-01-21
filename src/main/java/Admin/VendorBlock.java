package Admin;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import method.RoundedButton;

public class VendorBlock extends JPanel {

    public String vendorName, vendorId, stallName, email, phone;
    public Color edgeColor, backgroundColor, themeColor;

    public VendorBlock() {
        initComponents();
        setMinimumSize(new java.awt.Dimension(916, 200));
        setPreferredSize(new java.awt.Dimension(916, 200));
    }
    
    public String getUserId() {
        return vendorId;
    }

    public void setUserId(String vendorId) {
        this.vendorId = vendorId;
        if (VendorId != null) { 
            VendorId.setText(vendorId);
        }
    }
    
    public String getStallName() {
        return stallName;
    }
    
    public void setStallName(String stallName) {
        this.stallName = stallName;
        if (vendorStallName != null) { 
            vendorStallName.setText(stallName != null ? stallName : "Unknown Store");
        }
    }
    
    public String getUsername() {
        return vendorName;
    }
    
    public void setUsername(String vendorName) {
        this.vendorName = vendorName;
        if (VendorName != null) { 
            VendorName.setText(vendorName != null ? vendorName : "Unknown User");
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
        if (vendorPanel.getBackground() != null) {
            vendorPanel.setBackground(edgeColor != null ? edgeColor : Color.WHITE);
        }
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        if(vendorPanel.getBackgroundColor() != new Color(248,248,248)){
            vendorPanel.setBackgroundColor(backgroundColor != null ? backgroundColor : Color.WHITE);
        }
    }

    public Color getThemeColor() {
        return themeColor;
    }
    
    public RoundedButton getButton(){
        return roundedButton;
    }
    
    public JLabel getLabel(){
        return ImageLabel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vendorPanel = new method.RoundedPanel();
        Phone = new javax.swing.JLabel();
        roundedButton = new method.RoundedButton();
        roundedButton1 = new method.RoundedButton();
        vendorStallName = new javax.swing.JLabel();
        UserId = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        VendorId = new javax.swing.JLabel();
        VendorName = new javax.swing.JLabel();
        ImageLabel = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(916, 200));
        setPreferredSize(new java.awt.Dimension(916, 200));

        vendorPanel.setBackground(new java.awt.Color(126, 127, 154));
        vendorPanel.setMinimumSize(new java.awt.Dimension(916, 200));
        vendorPanel.setPreferredSize(new java.awt.Dimension(916, 200));
        vendorPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Phone.setBackground(new java.awt.Color(40, 40, 56));
        Phone.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Phone.setForeground(new java.awt.Color(40, 40, 56));
        Phone.setText("018-7783486");
        vendorPanel.add(Phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 370, 30));

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
        vendorPanel.add(roundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 120, 50));

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
        vendorPanel.add(roundedButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, 120, 50));

        vendorStallName.setBackground(new java.awt.Color(40, 40, 56));
        vendorStallName.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        vendorStallName.setForeground(new java.awt.Color(40, 40, 56));
        vendorStallName.setText("StallName");
        vendorPanel.add(vendorStallName, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 640, 50));

        UserId.setBackground(new java.awt.Color(40, 40, 56));
        UserId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UserId.setForeground(new java.awt.Color(40, 40, 56));
        UserId.setText("|");
        vendorPanel.add(UserId, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 10, 30));

        Email.setBackground(new java.awt.Color(40, 40, 56));
        Email.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Email.setForeground(new java.awt.Color(40, 40, 56));
        Email.setText("jiajunchong00@gmail.com");
        vendorPanel.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 370, 30));

        VendorId.setBackground(new java.awt.Color(40, 40, 56));
        VendorId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        VendorId.setForeground(new java.awt.Color(40, 40, 56));
        VendorId.setText("X1");
        vendorPanel.add(VendorId, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 30, 30));

        VendorName.setBackground(new java.awt.Color(40, 40, 56));
        VendorName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        VendorName.setForeground(new java.awt.Color(40, 40, 56));
        VendorName.setText("vendorName");
        vendorPanel.add(VendorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 180, 30));

        ImageLabel.setForeground(new java.awt.Color(40, 40, 56));
        ImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImageLabel.setMaximumSize(new java.awt.Dimension(1438, 1125));
        ImageLabel.setPreferredSize(new java.awt.Dimension(240, 200));
        vendorPanel.add(ImageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 18, 220, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vendorPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vendorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JLabel Phone;
    private javax.swing.JLabel UserId;
    private javax.swing.JLabel VendorId;
    private javax.swing.JLabel VendorName;
    private method.RoundedButton roundedButton;
    private method.RoundedButton roundedButton1;
    method.RoundedPanel vendorPanel;
    private javax.swing.JLabel vendorStallName;
    // End of variables declaration//GEN-END:variables
}
