package Admin;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        return editVendorButton;
    }
    
    public JLabel getLabel(){
        return ImageLabel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vendorPanel = new method.RoundedPanel();
        Phone = new javax.swing.JLabel();
        editVendorButton = new method.RoundedButton();
        deleteVendorButton = new method.RoundedButton();
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

        editVendorButton.setBackground(new java.awt.Color(140, 75, 242));
        editVendorButton.setForeground(new java.awt.Color(248, 248, 248));
        editVendorButton.setText("Edit");
        editVendorButton.setBorderColor(new java.awt.Color(248, 248, 248));
        editVendorButton.setColor(new java.awt.Color(140, 75, 242));
        editVendorButton.setColorClick(new java.awt.Color(50, 255, 130));
        editVendorButton.setColorOver(new java.awt.Color(50, 255, 100));
        editVendorButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        editVendorButton.setFontColor(new java.awt.Color(248, 248, 248));
        editVendorButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        editVendorButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        editVendorButton.setRadius(20);
        editVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVendorButtonActionPerformed(evt);
            }
        });
        vendorPanel.add(editVendorButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 120, 50));

        deleteVendorButton.setBackground(new java.awt.Color(255, 51, 51));
        deleteVendorButton.setForeground(new java.awt.Color(248, 248, 248));
        deleteVendorButton.setText("Delete");
        deleteVendorButton.setBorderColor(new java.awt.Color(248, 248, 248));
        deleteVendorButton.setColor(new java.awt.Color(255, 51, 51));
        deleteVendorButton.setColorClick(new java.awt.Color(204, 0, 51));
        deleteVendorButton.setColorOver(new java.awt.Color(204, 0, 51));
        deleteVendorButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteVendorButton.setFontColor(new java.awt.Color(248, 248, 248));
        deleteVendorButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        deleteVendorButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        deleteVendorButton.setRadius(20);
        deleteVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVendorButtonActionPerformed(evt);
            }
        });
        vendorPanel.add(deleteVendorButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, 120, 50));

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
    
    private void editVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVendorButtonActionPerformed
        // actionPerformed(evt);
        String vendorId = VendorId.getText();
        
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle("Edit Vendor");
        dialog.setSize(450, 600);
        dialog.setLocationRelativeTo(this);

        EditVendor editVendorPanel = new EditVendor();
        editVendorPanel.loadVendorDetails(vendorId); // Custom method to load vendor details

        // Set the content pane of the dialog to the EditVendor panel
        dialog.setContentPane(editVendorPanel);

        // Make the dialog modal and visible
        dialog.setModal(true);
        dialog.setVisible(true);
        
    }//GEN-LAST:event_editVendorButtonActionPerformed

    private void deleteVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVendorButtonActionPerformed
        // TODO add your handling code here:
        String filePath = "src\\main\\java\\repository\\vendor.txt";
        String tempFilePath = "src\\main\\java\\repository\\vendor_temp.txt";
        String vendorToDelete = VendorId.getText();

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

                String[] vendorData = currentLine.split(",");
                if (!vendorData[0].equals(vendorToDelete)) {
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
    }//GEN-LAST:event_deleteVendorButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JLabel Phone;
    private javax.swing.JLabel UserId;
    private javax.swing.JLabel VendorId;
    private javax.swing.JLabel VendorName;
    private method.RoundedButton deleteVendorButton;
    private method.RoundedButton editVendorButton;
    method.RoundedPanel vendorPanel;
    private javax.swing.JLabel vendorStallName;
    // End of variables declaration//GEN-END:variables
}
