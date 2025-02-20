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
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.IOException; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import managefile.Vendor;
import managefile.VendorReview1;

/**
 *
 * @author USER
 */
public class CustomerFood extends javax.swing.JFrame{
    private final String customerID;
    private final String vendorID;
    private JButton button;
    private String selectedQuantity = "1";
    customer_backend backend = new customer_backend();
    CustomerHome homepage = new CustomerHome();
    private JPanel foodPanel;

    /**
     * Creates new form Food
     */
    public CustomerFood(String customerID,String vendorID) {
        this.customerID = customerID;
        this.vendorID = vendorID;
        initComponents();
        getVendorFoodDetails();
        CustomerCart cart = new CustomerCart(customerID);
        cartButton.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\trolley.png", 35, 35));
        cartButton.setFocusable(false);
        cartButton.addActionListener(e->{
            cart.run();
            this.dispose();
        });
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jLabel7.setText(jTextField1.getText());
                getVendorFoodDetails();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jLabel7.setText(jTextField1.getText());
                getVendorFoodDetails();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                jLabel7.setText(jTextField1.getText());
                getVendorFoodDetails();
            }
        });
    }
    private void getVendorFoodDetails(){
        CustomerMenu cm = new CustomerMenu(customerID);
        Map<Object, Object> details = backend.getSpecificVendorDetail(vendorID);
        List<Vendor> vendors = (List<Vendor>) details.get("vendors");
        List<managefile.Food> foods = (List<managefile.Food>) details.get("foods");
        
        List<VendorReview1> reviewData = backend.setVendorReviews(vendorID);
        
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
            double aveRating = cm.countRating(vendor,reviewData);
            JPanel ratingPanel = homepage.createRatingPanel(String.format("%.1f",aveRating),16);
            jPanel5.setLayout(new BorderLayout());
            jPanel5.add(ratingPanel,BorderLayout.CENTER);
        }
        foodPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        String query = jTextField1.getText().trim().toLowerCase();
        for(managefile.Food food:foods){
            if (query.isEmpty() || food.getCategory().toLowerCase().contains(query) || food.getName().toLowerCase().contains(query)) {
                JPanel panel = addFoodPanel(vendors.getFirst(),food,null);
                foodPanel.setBackground(Color.LIGHT_GRAY);
                foodPanel.add(panel, gbc);
                gbc.gridx++;
                if (gbc.gridx == 1) {
                    gbc.gridx = 0;
                    gbc.gridy++;
                }
            }
        }
        reviewButton.addActionListener(e->{
            viewReviewRatings(vendorID,this);
        });
        JScrollPane scrollPane = new JScrollPane(foodPanel);
        scrollPane.setPreferredSize(new Dimension(628,400));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
        jPanel4.setLayout(new BorderLayout());
        jPanel4.removeAll();
        jPanel4.add(scrollPane, BorderLayout.CENTER);
        jPanel4.revalidate();
        jPanel4.repaint();
    }
    
    public JPanel addFoodPanel(Vendor vendor,managefile.Food food,JDialog dialog) {
        try {
            Map<Object, Object> carts = backend.getCart(customerID);
            List<managefile.Cart> cartList = (List<managefile.Cart>) carts.get("carts");
            List<managefile.Food> foodList = (List<managefile.Food>) carts.get("foods");
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE;
            
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            panel.setBackground(new Color(255, 255, 204));
            panel.setPreferredSize(new Dimension(600, 420));
            
            JLabel image = new JLabel();
            File imageFile = new File(food.getImagepath());
            if (imageFile.exists()) {
                image.setIcon(backend.scale.processImage(food.getImagepath(), 150, 150));
            } else {
                image.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\logo.png", 150, 150));
            }
            image.setPreferredSize(new Dimension(150, 150));
            image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            JLabel foodID = new JLabel(food.getId());
            foodID.setFont(new Font("Segoe UI", Font.BOLD, 18));
            
            JLabel foodName = new JLabel(food.getName());
            foodName.setFont(new Font("Segoe UI", Font.BOLD, 20));
            
            JLabel foodPrice = new JLabel("RM " + String.format("%.2f", Double.parseDouble(food.getPrice())));
            foodPrice.setFont(new Font("Segoe UI", Font.BOLD, 16));
            foodPrice.setForeground(new Color(0, 153, 0));
            
            JLabel cate = new JLabel("("+food.getCategory()+")");
            cate.setFont(new Font("Segoe UI", Font.BOLD, 16));
            
            JTextArea description = new JTextArea(food.getDescription());
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            description.setEditable(false);
            description.setOpaque(false);
            description.setBorder(BorderFactory.createEmptyBorder());
            JScrollPane descriptionScroll = new JScrollPane(description);
            descriptionScroll.setPreferredSize(new Dimension(350, 85));
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
                if (vendor.getStatus().equalsIgnoreCase("available") && food.getStatus().equalsIgnoreCase("available")){
                    addCartButton(cartList,food);
                }else if (!food.getStatus().equalsIgnoreCase("available")){
                    JOptionPane.showMessageDialog(null, "The food is currently unavailable!", "Unavailable food", JOptionPane.WARNING_MESSAGE);
                }else if (!vendor.getStatus().equalsIgnoreCase("available")){
                    JOptionPane.showMessageDialog(null, "Vendor is currently unavailable!", "Unavailable vendor", JOptionPane.WARNING_MESSAGE);
                }
            });
            
            //xuanhanchin@gmail.com
            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 5;
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
            panel.add(cate, gbc);
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
            
        } catch (IOException ex) {
            Logger.getLogger(CustomerFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void navigateToCart(){
        CustomerCart cartpage = new CustomerCart(customerID);
        for (Window window : Window.getWindows()) {
            if (window instanceof JDialog) {
                JDialog dialog1 = (JDialog) window;
                dialog1.dispose();
            }else if (window instanceof JFrame) {
                JFrame frame1 = (JFrame) window;
                frame1.dispose();
            }
        }
        cartpage.run();
    }
    
    private void addCartButton(List<managefile.Cart> cartList,managefile.Food food){
        String remarks = JOptionPane.showInputDialog(null,"Remarks","Tell me something");
        try {
            if (remarks != null) {
                boolean sameVendor = true;
                for (managefile.Cart cart : cartList) {
                    if (!cart.getVendorID().equals(food.getVendorid())) {
                        sameVendor = false;
                        break;
                    }
                }
                if (sameVendor) {
                    addToCart(food.getId(),food.getVendorid(),remarks);
                    JOptionPane.showMessageDialog(null, "x" + selectedQuantity + " " + food.getName()+ " is added into cart!");
                    navigateToCart();
                } else {
                    int selection = JOptionPane.showConfirmDialog(null, "You can't order different vendors in an order!\nDo you want to remove the existing cart?");
                    if (selection == JOptionPane.YES_OPTION) {
                        backend.removeCart(customerID);
                        addToCart(food.getId(),food.getVendorid(),remarks);
                        JOptionPane.showMessageDialog(null, "x" + selectedQuantity + " " + food.getName() + " is added into cart!");
                        navigateToCart();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add item into cart.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CustomerFood.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addToCart(String foodid,String vendorid,String remark) throws IOException {
        backend.addCartItems(customerID, foodid, selectedQuantity,vendorid, remark);
    }
    
    public void viewReviewRatings(String vendorid,JFrame frame) {
        reviewPanel reviewPanel = new reviewPanel(customerID,vendorid);

        JDialog dialog = new JDialog(frame, true);
        reviewPanel.setDialog(dialog);

        dialog.setResizable(false);
        dialog.setSize(new Dimension(950, 482));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.add(reviewPanel, BorderLayout.CENTER);
        dialog.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(
            (screenSize.width - dialog.getWidth()) / 2,
            (screenSize.height - dialog.getHeight()) / 2
        );

        dialog.setVisible(true);

        reviewPanel.repaint();
        reviewPanel.revalidate();
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
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        reviewButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Stall Name:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Stall Type:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Vendor Name:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Phone Number:");

        stallName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        stallName.setText("jLabel7");

        stallType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        stallType.setText("jLabel7");

        vendorName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        vendorName.setText("jLabel7");

        phoneNum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneNum.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Ratings:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        reviewButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        reviewButton.setText("View Reviews");
        reviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(reviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(50, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(79, 79, 79)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(vendorName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stallType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(phoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(stallName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(imageStall, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(imageStall, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(stallName))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(stallType))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(vendorName))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(phoneNum))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Query:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cartButtonActionPerformed

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        CustomerMenu menupage = new CustomerMenu(customerID);
        menupage.run();
        this.dispose();
    }//GEN-LAST:event_back_buttonActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void reviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewButtonActionPerformed

    public void run() {
        new CustomerFood(customerID,vendorID).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_button;
    private javax.swing.JButton cartButton;
    private javax.swing.JLabel imageStall;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel phoneNum;
    private javax.swing.JButton reviewButton;
    private javax.swing.JLabel stallName;
    private javax.swing.JLabel stallType;
    private javax.swing.JLabel vendorName;
    // End of variables declaration//GEN-END:variables
}
