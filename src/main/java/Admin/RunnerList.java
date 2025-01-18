package Admin;

public class RunnerList extends javax.swing.JPanel {

    public RunnerList() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RunnerLabel = new javax.swing.JLabel();
        addVendorButton = new method.RoundedButton();

        setBackground(new java.awt.Color(126, 127, 154));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        RunnerLabel.setBackground(new java.awt.Color(255, 255, 51));
        RunnerLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        RunnerLabel.setForeground(new java.awt.Color(255, 255, 51));
        RunnerLabel.setText("Runner");

        addVendorButton.setBackground(new java.awt.Color(40, 40, 56));
        addVendorButton.setForeground(new java.awt.Color(255, 255, 51));
        addVendorButton.setText("Add Runner");
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
                .addComponent(RunnerLabel)
                .addContainerGap(797, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addVendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(RunnerLabel)
                .addGap(18, 18, 18)
                .addComponent(addVendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(646, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVendorButtonActionPerformed
        // Create and configure the "Add Runner" dialog
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle("Add Runner");
        dialog.setSize(435, 513);
        dialog.setLocationRelativeTo(this);

        dialog.setContentPane(new AddRunner());

        dialog.setModal(true);
        dialog.setVisible(true);
    }//GEN-LAST:event_addVendorButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RunnerLabel;
    private method.RoundedButton addVendorButton;
    // End of variables declaration//GEN-END:variables
}
