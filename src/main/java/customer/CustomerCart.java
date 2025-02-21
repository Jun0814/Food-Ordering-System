/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import managefile.Customer;
import managefile.Runner;
import managefile.Vendor;

/**
 *
 * @author USER
 */
public class CustomerCart extends javax.swing.JFrame {
    private String runnerId;
    private final String customerID;
    private String orderSelection = "dine in";
    customer_backend backend = new customer_backend();
    private JTextArea addressArea;
    private JComboBox<String> hours,minutes;
    private JTextField tableNum;
    private Double totalPrice = 0.0;
    private Double initialTotal = 0.0;
    private int totalQuantity = 0;
    private Double credit;
    private LocalTime currentTime = LocalTime.now();
    private JComboBox<String>deliverySelection;
    

    /**
     * Creates new form Cart
     */
    public CustomerCart(String customerID) {
        this.customerID = customerID;
        initComponents();
        jLabel1.setText("Cart");
        jLabel1.setHorizontalTextPosition(SwingConstants.LEFT);
        jLabel3.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\logo.png", 110, 85));
        try{
            addCartScrollPane();
        }catch(IOException e ){
            e.printStackTrace();
        }
        JPanel selectedPanel = selectionPanel();
        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(selectedPanel, BorderLayout.CENTER);
        dineButton.setEnabled(false);
        Customer customer = backend.getSpecificCustomerDetail(customerID);
        credit = customer.getCredit();
        jLabel6.setText("RM " + String.format("%.2f", credit));
    }
    private void updateSelectionPanel() {
        JPanel selectedPanel = selectionPanel();
        jPanel5.removeAll();
        jPanel5.add(selectedPanel, BorderLayout.CENTER);
        jPanel5.revalidate();
        jPanel5.repaint();
    }
    private JPanel selectionPanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel table = new JLabel();
        table.setFont(new Font("Segoe UI", Font.BOLD, 20));

        switch (orderSelection) {
            case "dine in" -> {
                table.setText("DINE IN (exp. 001)");
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(table, gbc);
                tableNum = new JTextField();
                tableNum.setFont(new Font("Segoe UI", Font.BOLD, 20));
                tableNum.setPreferredSize(new Dimension(300,60));
                tableNum.setBorder(BorderFactory.createTitledBorder("Table Number"));
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth=2;
                panel.add(tableNum, gbc);
            }
            case "pickup" -> {
                table.setText("PICK UP");
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(table, gbc);
                JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
                timePanel.setBorder(BorderFactory.createTitledBorder("Choose your time to pickup"));
                timePanel.setPreferredSize(new Dimension(300,60));
                hours = new JComboBox<>();
                for (int i = 0; i < 24; i++) {
                    hours.addItem(String.format("%02d", i));
                }   hours.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                minutes = new JComboBox<>();
                for (int i = 0; i < 60; i += 10) {
                    minutes.addItem(String.format("%02d", i));
                }   minutes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                JLabel separator = new JLabel(" : ");
                separator.setFont(new Font("Segoe UI", Font.BOLD, 16));
                timePanel.add(hours);
                timePanel.add(separator);
                timePanel.add(minutes);
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                panel.add(timePanel, gbc);
            }
            case "delivery" -> {
                table.setText("DELIVERY");
                gbc.gridx = 0;
                gbc.gridy = 0;
                panel.add(table, gbc);
                deliverySelection = new JComboBox<>();
                deliverySelection.addItem("Standard Delivery");
                deliverySelection.addItem("Fast Delivery");
                deliverySelection.addActionListener(e->{
                    updateDeliveryPrice();
                });
                gbc.gridx = 1;
                gbc.gridy = 0;
                panel.add(deliverySelection, gbc);
                addressArea = new JTextArea();
                addressArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                addressArea.setBorder(BorderFactory.createTitledBorder("Delivery Address"));
                JScrollPane addressScroll = new JScrollPane(addressArea);
                addressScroll.setPreferredSize(new Dimension(300, 60));
                addressScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                panel.add(addressScroll, gbc);
            }
            default -> {
            }
        }
        return panel;
    }
    
    private void addCartScrollPane() throws IOException{
        Map<Object, Object> carts = backend.getCart(customerID);
        List<managefile.Cart> cartList = (List<managefile.Cart>) carts.get("carts");
        List<managefile.Food> foodList = (List<managefile.Food>) carts.get("foods");
        
        List<managefile.Vendor> vendors = backend.getVendors();
        for (Vendor vendor : vendors) {
            if (!cartList.isEmpty()){
                if (cartList.getFirst().getVendorID().equals(vendor.getId())){
                    jLabel1.setText(vendor.getName());
                    File imageFile = new File(vendor.getImagePath());
                    if (imageFile.exists()) {
                        jLabel1.setIcon(backend.scale.processImage(vendor.getImagePath(), 50, 50));
                    }else{
                        jLabel1.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\trolley.png", 50, 50));
                    }
                    jLabel1.setOpaque(true);
                    jLabel1.setBackground(Color.WHITE);
                    jLabel1.setForeground(Color.BLACK);
                    jLabel1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            super.mouseReleased(e);
                            CustomerFood fpage = new CustomerFood(customerID, cartList.getFirst().getVendorID());
                            fpage.run();
                            CustomerCart.this.dispose();
                        }
                    });
                }
            }else{
                jLabel1.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\trolley.png", 50, 50));
            }
        }
        JPanel cartPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        for (managefile.Cart cart:cartList){
            for (managefile.Food food:foodList){
                if (cart.getFoodID().equals(food.getId())){
                    JPanel panel = addCartPanel(cart,food);
                    panel.setBackground(Color.LIGHT_GRAY);
                    cartPanel.add(panel, gbc);
                    gbc.gridx++;
                    if (gbc.gridx == 1) {
                        gbc.gridx = 0;
                        gbc.gridy++;
                    }
                    int quantity = Integer.parseInt(cart.getQuantity());
                    double price = Double.parseDouble(food.getPrice());
                    totalQuantity += quantity;
                    double subTotal = quantity*price;
                    initialTotal  += subTotal;
                    break;
                }
            }
        }
        defaultSelection();
        
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setPreferredSize(new Dimension(710,390));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(scrollPane, BorderLayout.WEST);
    }
    
    private JPanel addCartPanel(managefile.Cart cart, managefile.Food food) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setBackground(new Color(255, 255, 204));
        panel.setPreferredSize(new Dimension(650, 160));

        JComboBox<String> cartItemQuan = new JComboBox<>();
        int maxQuantity = Integer.parseInt(cart.getQuantity());
        for (int i = 1; i <= maxQuantity+5; i++) {
            cartItemQuan.addItem(String.valueOf(i));
        }
        cartItemQuan.setSelectedItem(cart.getQuantity());
        cartItemQuan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(cartItemQuan, gbc);
        cartItemQuan.addActionListener(e->{
            try {
                backend.updateCartItems(cart.getCartID(),customerID,(String) cartItemQuan.getSelectedItem());
                reload();
            } catch (IOException ex) {
                Logger.getLogger(CustomerCart.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JLabel image = new JLabel();
        File imageFile = new File(food.getImagepath());
        if (imageFile.exists()) {
            image.setIcon(backend.scale.processImage(food.getImagepath(), 80, 80));
        } else {
            image.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\food-stall.png", 80, 80));
        }
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        panel.add(image, gbc);

        JLabel cartItemName = new JLabel(food.getName());
        cartItemName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(cartItemName, gbc);

        JLabel cartItemSubPrice = new JLabel("RM " + String.format("%.2f", Integer.parseInt(cart.getQuantity()) * Double.parseDouble(food.getPrice())));
        cartItemSubPrice.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(cartItemSubPrice, gbc);

        JTextArea cartItemRemarks = new JTextArea(cart.getRemarks());
        cartItemRemarks.setLineWrap(true);
        cartItemRemarks.setWrapStyleWord(true);
        cartItemRemarks.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cartItemRemarks.setOpaque(false);
        cartItemRemarks.setEnabled(false);
        cartItemRemarks.setBorder(BorderFactory.createEmptyBorder());
        JScrollPane remarkScroll = new JScrollPane(cartItemRemarks);
        remarkScroll.setPreferredSize(new Dimension(350, 60));
        remarkScroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(remarkScroll, gbc);

        JButton deleteItem = new method.RoundedButton();
        deleteItem.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\bin.png", 30, 30));
        deleteItem.setPreferredSize(new Dimension(60, 60));
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(deleteItem, gbc);
        deleteItem.addActionListener(e->{
            try {
                backend.removeCartItems(cart.getCartID(),customerID, food.getId());
                reload();
            } catch (IOException ex) {
                Logger.getLogger(CustomerCart.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return panel;
    }
    
    private void reload(){
        run();
        this.dispose();
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
        jLabel3 = new javax.swing.JLabel();
        back_button = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        placeOrderButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        dineButton = new method.RoundedButton();
        takeButton = new method.RoundedButton();
        deliveryButton = new method.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(39, 40, 56));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        back_button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        back_button.setText("Back");
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Total Amount ( items):");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("RM 888");

        placeOrderButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        placeOrderButton.setText("Place Order");
        placeOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeOrderButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Balance Left:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("RM");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(85, 85, 85)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(80, 80, 80)
                .addComponent(placeOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(placeOrderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        dineButton.setText("DINE IN");
        dineButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dineButtonActionPerformed(evt);
            }
        });

        takeButton.setText("PICK UP");
        takeButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        takeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeButtonActionPerformed(evt);
            }
        });

        deliveryButton.setText("DELIVERY");
        deliveryButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deliveryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dineButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(takeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deliveryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(takeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deliveryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        CustomerMenu menupage = new CustomerMenu(customerID);
        menupage.run();
        this.dispose();
    }//GEN-LAST:event_back_buttonActionPerformed

    private void placeOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOrderButtonActionPerformed
        try{
            Map<Object, Object> carts = backend.getCart(customerID);
            List<managefile.Cart> cartList = (List<managefile.Cart>) carts.get("carts");
            List<managefile.Food> foodList = (List<managefile.Food>) carts.get("foods");
            boolean orderPlaced = false;
            boolean runnerAvailable = false;
            boolean availableTime = false;
            List<managefile.Runner> runners = backend.getRunner();
            
            LocalTime start = LocalTime.of(8, 0);
            LocalTime end = LocalTime.of(23, 0);
            
            if (carts.isEmpty()){
                orderPlaced = false;
                JOptionPane.showMessageDialog(null, "Please add something into your cart!");
            }
//            if (currentTime.isBefore(start) || currentTime.isAfter(end)) {
//                orderPlaced = false;
//                JOptionPane.showMessageDialog(null, "Our opening hours is from 9:00a.m. to 6:00p.m. only!","Place Order Failed", JOptionPane.WARNING_MESSAGE);
//            }else{
//                availableTime = true;
//            }
            availableTime = true;
            if (credit>totalPrice){
                if (orderSelection.equals("delivery")){
                    for (Runner runner : runners) {
                        if (runner.getStatus().equalsIgnoreCase("Available")) {
                            this.runnerId = runner.getId();
                            System.out.println(runnerId);
                            runnerAvailable = true;
                            break;
                        }
                    }
                    if (addressArea.getText() != null && !addressArea.getText().trim().isEmpty()) {
                        if (runners.isEmpty()){
                            runnerAvailable = false;
                            JOptionPane.showMessageDialog(null, "No runner in the system!");
                        }
                        if (runnerAvailable && availableTime){
                            backend.addOrder(customerID,cartList,foodList,orderSelection,addressArea.getText(), totalPrice,totalPrice-initialTotal,runnerId);
                            orderPlaced = true;
                        }
                    }else if (!addressArea.getText().trim().toLowerCase().contains(",") ){
                        JOptionPane.showMessageDialog(null, "Do not contain comma ','!","Place Order Failed",JOptionPane.WARNING_MESSAGE);
                    } else if (!addressArea.getText().trim().toLowerCase().contains("bukit jalil")){
                        JOptionPane.showMessageDialog(null, "Please enter Bukit Jalil area location!","Place Order Failed",JOptionPane.WARNING_MESSAGE);
                    } else{
                        JOptionPane.showMessageDialog(null, "Please enter your delivery location!","Place Order Failed",JOptionPane.WARNING_MESSAGE);
                    }
                } else if (orderSelection.equals("dine in")){
                    if (tableNum.getText() != null && !tableNum.getText().trim().isEmpty()) {
                        String tableNumber = tableNum.getText().trim();
                        
                        if (backend.scale.isNumeric(tableNumber)) {
                            int tableNumValue = Integer.parseInt(tableNumber);
                            if (availableTime && (tableNumValue<=200 && 0<tableNumValue)){
                                backend.addOrder(customerID, cartList,foodList, orderSelection, tableNumber, totalPrice,0,null);
                                orderPlaced = true;
                            }else{
                                JOptionPane.showMessageDialog(null, "Please enter a valid table number!", "Place Order Failed", JOptionPane.WARNING_MESSAGE);
                            } 
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter a valid table number!","Place Order Failed", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Table number cannot be empty!","Place Order Failed", JOptionPane.WARNING_MESSAGE);
                    }
                } else if (orderSelection.equals("pickup")){
                    String selectedHour = (String) hours.getSelectedItem();
                    String selectedMin = (String)minutes.getSelectedItem();
                    String timeString = selectedHour + ":" + selectedMin; 
                    LocalTime pickupTime = LocalTime.parse(timeString);
                    
                    if (!pickupTime.isBefore(start) && !pickupTime.isAfter(end)) {
                        if (pickupTime.isBefore(currentTime)){
                            orderPlaced = false;
                            JOptionPane.showMessageDialog(null, "Please enter valid pickup time!\nNow is already "+currentTime.toString().split("\\.")[0],"Place Order Failed", JOptionPane.WARNING_MESSAGE);                
                        } else{
                            backend.addOrder(customerID,cartList,foodList,orderSelection,timeString,totalPrice,0,null);
                            orderPlaced = true;
                        }
                    } else{
                        orderPlaced = false;
                        JOptionPane.showMessageDialog(null, "Our opening hours is from 9:00a.m. to 6:00p.m. only!","Place Order Failed", JOptionPane.WARNING_MESSAGE);
                    }
                }
                if (orderPlaced){
                    JOptionPane.showMessageDialog(null, "Your order is on placed!");
                    backend.removeCart(customerID);
                    backend.updateCredit(customerID,totalPrice,"Debit");
                    CustomerHome homepage = new CustomerHome(customerID);
                    homepage.run();
                    this.dispose();
                }
            }else{
                int option = JOptionPane.showConfirmDialog(null, "Please top up your balance!", "Insufficient Balance",JOptionPane.WARNING_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    CustomerFinance f = new CustomerFinance(customerID);
                    f.run();
                    this.dispose();
                }
            }
        }catch (IOException ex) {
            Logger.getLogger(CustomerCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_placeOrderButtonActionPerformed

    private void takeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takeButtonActionPerformed
        orderSelection = "pickup";
        int price = totalQuantity*2;
        totalPrice = initialTotal + price;
        updateText(" (incl. RM 2 takeaways container per item)");
        updateSelectionPanel();
        takeButton.setEnabled(false);
        deliveryButton.setEnabled(true);
        dineButton.setEnabled(true);
    }//GEN-LAST:event_takeButtonActionPerformed
    private void updateDeliveryPrice() {
        String optionDelivery = (String) deliverySelection.getSelectedItem();
        if (optionDelivery.equals("Standard Delivery")) {
            totalPrice = initialTotal + 5;
            updateText(" (incl. RM 5 delivery fees)");
        } else {
            totalPrice = initialTotal + 8;
            updateText(" (incl. RM 8 delivery fees)");
        }
    }
    private void deliveryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryButtonActionPerformed
        orderSelection = "delivery";
        if (deliverySelection == null) {
            deliverySelection = new JComboBox<>(new String[]{"Standard Delivery", "Fast Delivery"});
            deliverySelection.setSelectedItem("Standard Delivery");
        }

        updateDeliveryPrice();
        updateSelectionPanel();
        takeButton.setEnabled(true);
        deliveryButton.setEnabled(false);
        dineButton.setEnabled(true);
    }//GEN-LAST:event_deliveryButtonActionPerformed

    private void dineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dineButtonActionPerformed
        defaultSelection();
    }//GEN-LAST:event_dineButtonActionPerformed
    private void defaultSelection(){
        totalPrice = initialTotal * 1.1;
        orderSelection = "dine in";
        updateText(" (incl. 10% service tax)");
        updateSelectionPanel();
        takeButton.setEnabled(true);
        deliveryButton.setEnabled(true);
        dineButton.setEnabled(false);
    }
    
    private void updateText(String text){
        jLabel2.setText("Total Amount "+"("+totalQuantity+" items):");
        jLabel4.setText("RM " + String.format("%.2f", initialTotal)+" + RM "+String.format("%.2f", totalPrice-initialTotal)+text);
        placeOrderButton.setText("<html>Place Order\nRM " + String.format("%.2f", totalPrice));
    }
    /**
     * @param args the command line arguments
     */
    public void run() {
        new CustomerCart(customerID).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_button;
    private method.RoundedButton deliveryButton;
    private method.RoundedButton dineButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton placeOrderButton;
    private method.RoundedButton takeButton;
    // End of variables declaration//GEN-END:variables
}
