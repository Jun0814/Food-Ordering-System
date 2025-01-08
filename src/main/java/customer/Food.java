/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import managefile.Vendor;

/**
 *
 * @author USER
 */
public class Food extends javax.swing.JFrame{
    private final String customerID;
    private final String vendorID;
    private JButton button;
    private String selectedQuantity = "1";
    customer_backend backend = new customer_backend();

    /**
     * Creates new form Food
     */
    public Food(String customerID,String vendorID) {
        this.customerID = customerID;
        this.vendorID = vendorID;
        initComponents();
        getVendorFoodDetails();
        Cart cart = new Cart(customerID);
        cartButton.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\trolley.png", 35, 35));
        cartButton.setFocusable(false);
        cartButton.addActionListener(e->{
            cart.run();
            this.dispose();
        });
    }
    private void getVendorFoodDetails(){
        Map<String, Object> details = backend.getSpecificVendorDetail(vendorID);
        List<Vendor> vendors = (List<Vendor>) details.get("vendors");
        List<managefile.Food> foods = (List<managefile.Food>) details.get("foods");
        for(Vendor vendor:vendors){
            File imageFile = new File(vendor.getImagePath());
            if (imageFile.exists()){
                jLabel1.setIcon(backend.scale.processImage(vendor.getImagePath(), 50, 50));
                imageStall.setIcon(backend.scale.processImage(vendor.getImagePath(), 257, 230));
            }else{
                jLabel1.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\food-stall.png", 50, 50));
                imageStall.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\food-stall.png", 257, 230));
            }
            jLabel1.setText(vendor.getStallName()+"'s Stall - "+vendor.getStallType()); 
            jLabel1.setHorizontalTextPosition(SwingConstants.LEFT);
            stallName.setText(vendor.getStallName());
            stallType.setText(vendor.getStallType());
            vendorName.setText(vendor.getName());
            phoneNum.setText(vendor.getPhone());
        }
        JPanel foodPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        for(managefile.Food food:foods){
            JPanel panel = addFoodPanel(food);
            foodPanel.setBackground(Color.LIGHT_GRAY);
            foodPanel.add(panel, gbc);
            gbc.gridx++;
            if (gbc.gridx == 1) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
        JScrollPane scrollPane = new JScrollPane(foodPanel);
        scrollPane.setPreferredSize(new Dimension(628,470));
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel addFoodPanel(managefile.Food food) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setBackground(new Color(255, 255, 204));
        panel.setPreferredSize(new Dimension(500, 400));

        JLabel image = new JLabel();
        File imageFile = new File(food.getImagepath());
        if (imageFile.exists()) {
            image.setIcon(backend.scale.processImage(food.getImagepath(), 120, 120));
        } else {
            image.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\logo.png", 120, 120));
        }
        image.setPreferredSize(new Dimension(120, 120));
        image.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel foodID = new JLabel(food.getId());
        foodID.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel foodName = new JLabel(food.getName());
        foodName.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel foodPrice = new JLabel("RM " + food.getPrice());
        foodPrice.setFont(new Font("Segoe UI", Font.BOLD, 16));
        foodPrice.setForeground(new Color(0, 153, 0));
        
        JLabel quantity = new JLabel("x"+food.getquantity());
        quantity.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JTextArea description = new JTextArea(food.getDescription());
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        description.setEditable(false);
        description.setOpaque(false);
        description.setBorder(BorderFactory.createEmptyBorder());
        JScrollPane descriptionScroll = new JScrollPane(description);
        descriptionScroll.setPreferredSize(new Dimension(350, 100));
        descriptionScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JComboBox quantitySelection = new JComboBox<>();
        for (int i = 1; i < 11; i++) {
            quantitySelection.addItem(String.valueOf(i));
        }
        
        quantitySelection.addActionListener(e->{
            selectedQuantity = (String) quantitySelection.getSelectedItem();
        });
        
        button = new JButton("Add to Cart");
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(200, 40));
        button.addActionListener(e->{
            String remarks = JOptionPane.showInputDialog(null,"Remarks","Tell me something");
            try {
                if (remarks != null) {
                    addToCart(foodID,remarks);
                    JOptionPane.showMessageDialog(null, "x"+selectedQuantity+" "+foodName.getText()+" is added into cart!");
                    quantitySelection.setSelectedItem("1");
                }
            } catch (IOException ex) {
                Logger.getLogger(Food.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //xuanhanchin@gmail.com
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        panel.add(foodID, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(image, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 2;
        panel.add(foodName, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(quantity, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 3;
        panel.add(foodPrice, gbc);
        gbc.gridx = 1;

        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(descriptionScroll, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridy = 4;
        gbc.gridx = 1;
        panel.add(quantitySelection, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 4;
        gbc.gridx = 3;
        panel.add(button, gbc);

        return panel;
    }
    
    private void addToCart(JLabel food, String remark) throws IOException {
        backend.addCartItems(customerID, food.getText(), selectedQuantity, remark);
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
        jLabel1 = new javax.swing.JLabel();
        back_button = new javax.swing.JButton();
        cartButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        imageStall = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        stallName = new javax.swing.JLabel();
        stallType = new javax.swing.JLabel();
        vendorName = new javax.swing.JLabel();
        phoneNum = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(39, 40, 56));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        back_button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        back_button.setText("Back");
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });

        cartButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cartButton.setText("Cart");
        cartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Stall Name:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Stall Type:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Vendor Name:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Phone Number:");

        stallName.setText("jLabel7");

        stallType.setText("jLabel7");

        vendorName.setText("jLabel7");

        phoneNum.setText("jLabel7");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(imageStall, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(vendorName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stallType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(stallName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(phoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(imageStall, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(stallName))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(stallType))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(vendorName))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(phoneNum))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cartButtonActionPerformed

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        Menu menupage = new Menu(customerID);
        menupage.run();
        this.dispose();
    }//GEN-LAST:event_back_buttonActionPerformed

    public void run() {
        new Food(customerID,vendorID).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_button;
    private javax.swing.JButton cartButton;
    private javax.swing.JLabel imageStall;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel phoneNum;
    private javax.swing.JLabel stallName;
    private javax.swing.JLabel stallType;
    private javax.swing.JLabel vendorName;
    // End of variables declaration//GEN-END:variables
}
