package Admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JPanel;
import method.scaleImage;

public class HomePage extends javax.swing.JFrame {

    private String userId;
    scaleImage scaleImage = new scaleImage();
    
    public HomePage() {}
    
    public HomePage(String userId) {
        initComponents();
        this.userId = userId;
        switchToPanel(new AdminHome(userId));
        this.setTitle("Admin");
        this.setSize(new Dimension(1280, 800));
        this.setResizable(false);
        this.setLocationRelativeTo(null); 
        logoLabel.setIcon(scaleImage.processImage("src\\main\\java\\image_repository\\logo.png", 230, 184));
    }

    protected void switchToPanel(JPanel targetPanel) {        
        targetPanel.setSize(contentPanel.getWidth(), contentPanel.getHeight());
        targetPanel.setLocation(0,0);
        contentPanel.removeAll();
        contentPanel.add(targetPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();

        
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { switchToPanel(new AdminHome(userId)); }
        });

    }
    
    public void run( ) {
        new HomePage(userId).setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vendorButton){
            switchToPanel(new VendorList());
        }else if(e.getSource() == customerButton){
            switchToPanel(new CustomerList());
        }else if(e.getSource() == runnerButton){
            switchToPanel(new RunnerList());
        }else if(e.getSource() == transactionButton){
            switchToPanel(new Transaction());
        }else if(e.getSource() == creditButton){
            switchToPanel(new CreditTopUp());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        vendorButton = new method.RoundedButton();
        customerButton = new method.RoundedButton();
        transactionButton = new method.RoundedButton();
        runnerButton = new method.RoundedButton();
        creditButton = new method.RoundedButton();
        contentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuPanel.setBackground(new java.awt.Color(40, 40, 56));
        menuPanel.setPreferredSize(new java.awt.Dimension(280, 800));
        menuPanel.setRequestFocusEnabled(false);

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        vendorButton.setBackground(new java.awt.Color(40, 40, 56));
        vendorButton.setText("Vendor");
        vendorButton.setAlignmentX(0.5F);
        vendorButton.setBorderColor(new java.awt.Color(40, 40, 56));
        vendorButton.setColor(new java.awt.Color(40, 40, 56));
        vendorButton.setColorClick(new java.awt.Color(243, 222, 138));
        vendorButton.setColorOver(new java.awt.Color(140, 75, 242));
        vendorButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        vendorButton.setFontColor(new java.awt.Color(227, 216, 255));
        vendorButton.setFontColorClick(new java.awt.Color(40, 40, 56));
        vendorButton.setFontColorOver(new java.awt.Color(227, 216, 255));
        vendorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        vendorButton.setRadius(30);
        vendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendorButtonActionPerformed(evt);
            }
        });

        customerButton.setBackground(new java.awt.Color(40, 40, 56));
        customerButton.setText("Customer");
        customerButton.setAlignmentX(0.5F);
        customerButton.setBorderColor(new java.awt.Color(40, 40, 56));
        customerButton.setColor(new java.awt.Color(40, 40, 56));
        customerButton.setColorClick(new java.awt.Color(243, 222, 138));
        customerButton.setColorOver(new java.awt.Color(140, 75, 242));
        customerButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        customerButton.setFontColor(new java.awt.Color(227, 216, 255));
        customerButton.setFontColorClick(new java.awt.Color(40, 40, 56));
        customerButton.setFontColorOver(new java.awt.Color(227, 216, 255));
        customerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        customerButton.setRadius(30);
        customerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerButtonActionPerformed(evt);
            }
        });

        transactionButton.setBackground(new java.awt.Color(40, 40, 56));
        transactionButton.setText("Transaction");
        transactionButton.setAlignmentX(0.5F);
        transactionButton.setBorderColor(new java.awt.Color(40, 40, 56));
        transactionButton.setColor(new java.awt.Color(40, 40, 56));
        transactionButton.setColorClick(new java.awt.Color(243, 222, 138));
        transactionButton.setColorOver(new java.awt.Color(140, 75, 242));
        transactionButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        transactionButton.setFontColor(new java.awt.Color(227, 216, 255));
        transactionButton.setFontColorClick(new java.awt.Color(40, 40, 56));
        transactionButton.setFontColorOver(new java.awt.Color(227, 216, 255));
        transactionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        transactionButton.setRadius(30);
        transactionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionButtonActionPerformed(evt);
            }
        });

        runnerButton.setBackground(new java.awt.Color(40, 40, 56));
        runnerButton.setText("Runner");
        runnerButton.setAlignmentX(0.5F);
        runnerButton.setBorderColor(new java.awt.Color(40, 40, 56));
        runnerButton.setColor(new java.awt.Color(40, 40, 56));
        runnerButton.setColorClick(new java.awt.Color(243, 222, 138));
        runnerButton.setColorOver(new java.awt.Color(140, 75, 242));
        runnerButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        runnerButton.setFontColor(new java.awt.Color(227, 216, 255));
        runnerButton.setFontColorClick(new java.awt.Color(40, 40, 56));
        runnerButton.setFontColorOver(new java.awt.Color(227, 216, 255));
        runnerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        runnerButton.setRadius(30);
        runnerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runnerButtonActionPerformed(evt);
            }
        });

        creditButton.setBackground(new java.awt.Color(40, 40, 56));
        creditButton.setText("Credit Top-up");
        creditButton.setAlignmentX(0.5F);
        creditButton.setBorderColor(new java.awt.Color(40, 40, 56));
        creditButton.setColor(new java.awt.Color(40, 40, 56));
        creditButton.setColorClick(new java.awt.Color(243, 222, 138));
        creditButton.setColorOver(new java.awt.Color(140, 75, 242));
        creditButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        creditButton.setFontColor(new java.awt.Color(227, 216, 255));
        creditButton.setFontColorClick(new java.awt.Color(40, 40, 56));
        creditButton.setFontColorOver(new java.awt.Color(227, 216, 255));
        creditButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        creditButton.setRadius(30);
        creditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runnerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(logoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(vendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(customerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(runnerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(creditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(transactionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        contentPanel.setBackground(new java.awt.Color(220, 220, 255));
        contentPanel.setPreferredSize(new java.awt.Dimension(1000, 800));

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendorButtonActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_vendorButtonActionPerformed

    private void customerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerButtonActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_customerButtonActionPerformed

    private void transactionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionButtonActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_transactionButtonActionPerformed

    private void runnerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runnerButtonActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_runnerButtonActionPerformed

    private void creditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditButtonActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_creditButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private method.RoundedButton creditButton;
    private method.RoundedButton customerButton;
    protected javax.swing.JLabel logoLabel;
    private javax.swing.JPanel menuPanel;
    private method.RoundedButton runnerButton;
    private method.RoundedButton transactionButton;
    private method.RoundedButton vendorButton;
    // End of variables declaration//GEN-END:variables
}
