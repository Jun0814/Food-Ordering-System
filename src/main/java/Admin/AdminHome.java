package Admin;

import java.awt.Dimension;

public class AdminHome extends javax.swing.JPanel {

    private String userId;
    
    /**
     * Creates new form AdminHome
     * @param userId
     */

    public AdminHome(String userId) {
        this.userId = userId;
        initComponents();
        setPreferredSize(new Dimension(1000, 800));
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WelcomeLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(126, 127, 154));

        WelcomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        WelcomeLabel.setForeground(new java.awt.Color(255, 255, 51));
        WelcomeLabel.setText("Welcome back, ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(WelcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(513, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(WelcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(714, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void run(){
        new AdminHome(userId).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel WelcomeLabel;
    // End of variables declaration//GEN-END:variables
}
