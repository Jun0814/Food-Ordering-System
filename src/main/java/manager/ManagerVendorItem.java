/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import managefile.Data;
import managefile.Food;
import managefile.Vendor;

/**
 *
 * @author Asus
 */
public class ManagerVendorItem extends javax.swing.JFrame {
    private String vendorId;
    private String vendorName;
    private String vendorEmail;
    private String vendorPhone;
    private String stallName;
    private String stallType;
    private String imagePath;
    private String vendorStatus;
    Vendor vendor = new Vendor();
    managerAccountManager backend = new managerAccountManager();
    Data data = new Data();
    private JPanel containerPanel; // Holds the feedback panels
    private JScrollPane scrollPane;
    /**
     * Creates new form ManagerVendorItem
     */
    public ManagerVendorItem(String vendorId) {
        this.vendorId = vendorId;
        initComponents();
        this.setSize(1000,520);
        String filepath = vendor.getFilepath();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Vendor vendorDetails = backend.getVendorDetails(vendorId);
        File imageFile = new File(vendorDetails.getImagePath());
        if (imageFile.exists()){
            vendorImage.setIcon(backend.scale.processImage(vendorDetails.getImagePath(), 240, 204));
        }else{
            vendorImage.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\food-stall.png", 240, 204));
        }
        vendorNameLabel.setText(vendorDetails.getName());
        vendorEmailLabel.setText(vendorDetails.getEmail());
        vendorPhoneNumberLabel.setText(vendorDetails.getPhone());
        stallNameLabel.setText(vendorDetails.getStallName());
        stallTypeLabel.setText(vendorDetails.getStallType());
        this.imagePath = vendorDetails.getImagePath();
        statusLabel.setText(vendorDetails.getStatus());
        foodListPanel.setLayout(new BorderLayout());
        containerPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        
//        List<Food> foods = backend.getVendorFoodList(vendorId);
//        for(Food food : foods){
//            String foodId = food.getId();
//            VendorFoodPanel panel = new VendorFoodPanel(foodId);
//            containerPanel.add(panel);
//        }
        
        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setPreferredSize(new Dimension(650,300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
        scrollPane.setBorder(null);
        foodListPanel.add(scrollPane, BorderLayout.CENTER);
        
        initializeFoods();
    }
    
    private void initializeFoods() {
        List<Food> foods = backend.getVendorFoodList(vendorId);
        for(Food food : foods){
            String foodId = food.getId();
            VendorFoodPanel panel = new VendorFoodPanel(foodId, this);
            containerPanel.add(panel);
        }
    }
    
    public void refreshFoods() {
        // Check if containerPanel is not null before attempting to clear
        if (containerPanel != null) {
            containerPanel.removeAll();
        }

        // Reload feedbacks and repopulate
        initializeFoods();

        // Refresh the UI
        containerPanel.revalidate();
        containerPanel.repaint();
    }
    
    public void removeFoodPanel(VendorFoodPanel panel) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                containerPanel.remove(panel); // Remove the panel
                containerPanel.revalidate();  // Revalidate the layout
                containerPanel.repaint();     // Repaint the container to reflect the change
                
            }
        });
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        vendorDetailsPanel = new javax.swing.JPanel();
        vendorNameLabel = new javax.swing.JLabel();
        vendorEmailLabel = new javax.swing.JLabel();
        vendorPhoneNumberLabel = new javax.swing.JLabel();
        stallTypeLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        stallNameLabel = new javax.swing.JLabel();
        vendorImage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        foodListLabel = new javax.swing.JLabel();
        foodListPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vendorNameLabel.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        vendorNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vendorNameLabel.setText("Vendor Name");
        vendorNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        vendorEmailLabel.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        vendorEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vendorEmailLabel.setText("Vendor Email");
        vendorEmailLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        vendorPhoneNumberLabel.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        vendorPhoneNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vendorPhoneNumberLabel.setText("Phone Number");
        vendorPhoneNumberLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        stallTypeLabel.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        stallTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stallTypeLabel.setText("Stall Type");
        stallTypeLabel.setFocusable(false);
        stallTypeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        statusLabel.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Status");
        statusLabel.setFocusable(false);
        statusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        stallNameLabel.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        stallNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stallNameLabel.setText("Vendor Stall Name");

        javax.swing.GroupLayout vendorDetailsPanelLayout = new javax.swing.GroupLayout(vendorDetailsPanel);
        vendorDetailsPanel.setLayout(vendorDetailsPanelLayout);
        vendorDetailsPanelLayout.setHorizontalGroup(
            vendorDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stallTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vendorPhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vendorEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vendorNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(stallNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        );
        vendorDetailsPanelLayout.setVerticalGroup(
            vendorDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendorDetailsPanelLayout.createSequentialGroup()
                .addComponent(stallNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vendorNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(vendorEmailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vendorPhoneNumberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stallTypeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusLabel))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vendorImage, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendorDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(vendorImage, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vendorDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        foodListLabel.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        foodListLabel.setText("Food List");

        javax.swing.GroupLayout foodListPanelLayout = new javax.swing.GroupLayout(foodListPanel);
        foodListPanel.setLayout(foodListPanelLayout);
        foodListPanelLayout.setHorizontalGroup(
            foodListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );
        foodListPanelLayout.setVerticalGroup(
            foodListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(foodListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foodListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(foodListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(foodListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

        /* Create and display the form */

        public void run() {
            new ManagerVendorItem(vendorId).setVisible(true);
        }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel foodListLabel;
    private javax.swing.JPanel foodListPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel stallNameLabel;
    private javax.swing.JLabel stallTypeLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel vendorDetailsPanel;
    private javax.swing.JLabel vendorEmailLabel;
    private javax.swing.JLabel vendorImage;
    private javax.swing.JLabel vendorNameLabel;
    private javax.swing.JLabel vendorPhoneNumberLabel;
    // End of variables declaration//GEN-END:variables
}
