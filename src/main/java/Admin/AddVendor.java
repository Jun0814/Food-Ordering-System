package Admin;

public class AddVendor extends javax.swing.JPanel {

    public AddVendor() {
        initComponents();
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
        roundedButton1 = new method.RoundedButton();

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

        roundedButton1.setBackground(new java.awt.Color(126, 127, 154));
        roundedButton1.setText("ADD");
        roundedButton1.setBorderColor(new java.awt.Color(40, 40, 56));
        roundedButton1.setColor(new java.awt.Color(126, 127, 154));
        roundedButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        roundedButton1.setFontColor(new java.awt.Color(255, 255, 51));
        roundedButton1.setRadius(15);
        roundedButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(stallStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(roundedButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(VendorLabel)
                .addGap(85, 85, 85))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(roundedButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void vendorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vendorNameActionPerformed

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roundedButton1ActionPerformed


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
    private method.RoundedButton roundedButton1;
    private method.RoundedTextField stallName;
    private javax.swing.JComboBox<String> stallStatus;
    private method.RoundedTextField stallType;
    private method.RoundedTextField vendorEmail;
    private method.RoundedTextField vendorName;
    private method.RoundedTextField vendorPassword;
    private method.RoundedTextField vendorPhone;
    // End of variables declaration//GEN-END:variables
}
