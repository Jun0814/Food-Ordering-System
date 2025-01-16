package Admin;

public class VendorList extends javax.swing.JPanel {

    public VendorList() {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VendorLabel = new javax.swing.JLabel();
        addVendorButton = new method.RoundedButton();

        setBackground(new java.awt.Color(126, 127, 154));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        VendorLabel.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        VendorLabel.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel.setText("Vendor");

        addVendorButton.setBackground(new java.awt.Color(40, 40, 56));
        addVendorButton.setForeground(new java.awt.Color(255, 255, 51));
        addVendorButton.setText("Add Vendor");
        addVendorButton.setAlignmentX(0.5F);
        addVendorButton.setBorderColor(new java.awt.Color(40, 40, 56));
        addVendorButton.setColor(new java.awt.Color(40, 40, 56));
        addVendorButton.setColorClick(new java.awt.Color(255, 255, 51));
        addVendorButton.setColorOver(new java.awt.Color(140, 75, 242));
        addVendorButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addVendorButton.setFontColor(new java.awt.Color(255, 255, 51));
        addVendorButton.setFontColorClick(new java.awt.Color(40, 40, 56));
        addVendorButton.setFontColorOver(new java.awt.Color(255, 255, 51));
        addVendorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addVendorButton.setRadius(30);
        addVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVendorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(VendorLabel)
                .addContainerGap(794, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addVendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(VendorLabel)
                .addGap(18, 18, 18)
                .addComponent(addVendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(646, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVendorButtonActionPerformed
    }//GEN-LAST:event_addVendorButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VendorLabel;
    private method.RoundedButton addVendorButton;
    // End of variables declaration//GEN-END:variables
}
