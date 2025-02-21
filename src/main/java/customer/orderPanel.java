/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import managefile.OrderItems;
import managefile.Vendor;

/**
 *
 * @author USER
 */
public class orderPanel extends javax.swing.JPanel {
    private String orderID;
    private String customerID;
    private JDialog dialog;
    private JFrame frame;
    customer_backend backend = new customer_backend();
    CustomerHome homepage = new CustomerHome();
    private String orderType;

    public JDialog getDialog() {
        return dialog;
    }

    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    /**
     * Creates new form orderPanel
     */
    public orderPanel(String customerID, String orderID) {
        this.customerID = customerID;
        this.orderID = orderID;
        initComponents();
        jPanel2.setOpaque(false);
        this.setOpaque(false);
        getDetails();
   }
    private void getDetails(){
        Map<Object, Object> allOrders = backend.getOrderByOrderID(orderID);
        List<managefile.Order> orders = (List<managefile.Order>) allOrders.get("orders");
        List<managefile.OrderItems> orderItems = (List<managefile.OrderItems>) allOrders.get("ordersItems");
        List<managefile.Food> foodItems = (List<managefile.Food>) allOrders.get("foodItems");
        List<managefile.Vendor> vendors = backend.getVendors();
        Vendor vendorDetail = new Vendor();
        for (Vendor vendor : vendors) {
            if (vendor.getId().equals(orders.getFirst().getVendorID())){
                vendorDetail = vendor;
            }
        }
        List<managefile.VendorReview> vendorReviews = backend.getOrderReviewByID(orders.getFirst().getOrderReviewID());
        
        boolean reviewGiven = false;
        if ((vendorReviews.getFirst().getRating().equals("") || vendorReviews.getFirst().getRating().equals("null")) && 
            (vendorReviews.getFirst().getComments().equals("") || vendorReviews.getFirst().getComments().equals("null"))){
            reviewGiven = true;
        }
        
        naviButton.setText("View Vendor ("+vendorDetail.getStallName()+")");
        naviButton.addActionListener(e->{
            navigateFood(orders.getFirst());
        });
        
        JPanel orderPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        for (int i = orders.size()-1; i >= 0; i--) {
            managefile.OrderItems innerOrderItem = orderItems.get(i);
            managefile.Food innerFoodItem = foodItems.get(i);
            JPanel panel = addOrderPanel(innerOrderItem,innerFoodItem);
            orderPanel.add(panel, gbc);
            gbc.gridx++;
            if (gbc.gridx == 1) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(orderPanel, BorderLayout.NORTH);
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        String description = "";
        
        orderType = orders.getFirst().getOrderType().toUpperCase();
        String orderTypeD = orders.getFirst().getOrderTypeDetails().toUpperCase();
        detailsPanel.add(homepage.createDetailLabel("Datetime:", orders.getFirst().getDatetime()));
        jLabel1.setText("Order #"+orderID+"     ("+orderType+")");
        switch (orderType) {
            case "DINE IN" -> {
                detailsPanel.add(homepage.createDetailLabel("Table Number:", "Table "+orderTypeD));
                description = String.format("<html><div style='text-align: right;'>" +
                           "<span style='font-size: 12px;'>%s</span></html>", 
                           backend.returnOrderDescription(orders.getFirst().getStatus()));
                detailsPanel.add(homepage.createDetailLabel("Total Amount: ", "RM "+String.format("%.2f", Double.parseDouble(orders.getFirst().getTotalAmount()))));
            }
            case "PICKUP" -> {
                detailsPanel.add(homepage.createDetailLabel("Pick up Time:", orderTypeD));
                description = String.format("<html><div style='text-align: right;'>" +
                           "<span style='font-size: 12px;'>%s</span></html>", 
                           backend.returnOrderDescription(orders.getFirst().getStatus()));
                detailsPanel.add(homepage.createDetailLabel("Total Amount: ", "RM "+String.format("%.2f", Double.parseDouble(orders.getFirst().getTotalAmount()))));
            }
            case "DELIVERY" -> {
                Map<Object, Object> deliveryDetails = backend.getDeliveryDetails(orderID);
                List<managefile.Delivery> deliverys = (List<managefile.Delivery>) deliveryDetails.get("deliverys");
                List<managefile.Runner> runners = (List<managefile.Runner>) deliveryDetails.get("runners");

                managefile.Delivery delivery = deliverys.getFirst();
                description = String.format("<html><div style='text-align: right;'>" +
                           "<span style='font-size: 12px;'>%s</span></html>", 
                           backend.returnDeliveryDescription(delivery.getStatus(),orders.getFirst().getStatus()));
                managefile.Runner runner = runners.getFirst();
                detailsPanel.add(homepage.createDetailLabel("Delivery Address:", orderTypeD));
                detailsPanel.add(homepage.createDetailLabel("Delivery Description:", delivery.getDescription()));
                detailsPanel.add(homepage.createDetailLabel("Total Amount: ", "RM "+String.format("%.2f", Double.parseDouble(orders.getFirst().getTotalAmount()))+" (+ RM "+String.format("%.2f", delivery.getDeliveryFees())+" Delivery fees)"));
            }
            default -> {
            }
        }
        String status = orders.getFirst().getStatus();
        if (status.equalsIgnoreCase("pending")){
            cancelButton.setVisible(true);
            cancelButton.setText("Cancel Order");
        }else if (status.equalsIgnoreCase("done") || status.equalsIgnoreCase("sent")){
            cancelButton.setVisible(true);
            cancelButton.setText("Order Received");
        }else if(status.equalsIgnoreCase("completed") || status.equalsIgnoreCase("cancel")){
            if (reviewGiven){
                cancelButton.setVisible(true);
                cancelButton.setText("Rate Order");
            }else{
                cancelButton.setVisible(false);
            }
        }
        JPanel statusPanel = homepage.createStatusPanel(status);
        
        jLabel2.setText(description);
        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(detailsPanel,BorderLayout.WEST);
        jPanel7.setLayout(new BorderLayout());
        jPanel7.add(statusPanel,BorderLayout.WEST);
        jPanel6.setLayout(new BorderLayout());
        jPanel6.add(jLabel1,BorderLayout.WEST);
        jPanel6.add(jPanel7,BorderLayout.EAST);
        
        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setPreferredSize(new Dimension(880,400));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
        scrollPane.setBorder(null);
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void navigateFood(managefile.Order order){
        homepage.closeDialog();
        CustomerFood fpage = new CustomerFood(customerID, order.getVendorID());
        fpage.run();
    }
    private JPanel addOrderPanel(managefile.OrderItems orderItems,managefile.Food foodItems){
        CustomeritemPanel panel = new CustomeritemPanel(customerID, orderItems,foodItems,frame);
        return panel;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cancelButton = new method.RoundedButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        naviButton = new method.RoundedButton();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        cancelButton.setText("Cancel Order");
        cancelButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        naviButton.setText("View Vendor");
        naviButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        naviButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naviButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(naviButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(naviButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        CustomerHome intiHomePage = new CustomerHome(customerID);
        String option = cancelButton.getText();
        if (option.equals("Cancel Order")){
            int selection = JOptionPane.showConfirmDialog(null, "Do you confirm to cancel the order?\nThe 80% of total amount will be refunded.","Cancel Confirmation",JOptionPane.WARNING_MESSAGE);
            if (selection == JOptionPane.YES_OPTION){
                try {
                    backend.updateOrderDetails(customerID, orderID);
                    homepage.closeDialog();
                    intiHomePage.run();
                } catch (IOException ex) {
                    Logger.getLogger(orderPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                return;
            }
        }else if (option.equals("Order Received")){
            try {
                if (orderType.equalsIgnoreCase("delivery")){
                    String[] options = {"1", "2", "3", "4", "5"};
                    String ratingInput = (String) JOptionPane.showInputDialog(
                        intiHomePage,
                        "Please rate your delivery (1-5):",
                        "Delivery Rating",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        "5"
                    );
                    if (ratingInput != null) {
                        String review = JOptionPane.showInputDialog(
                            intiHomePage,
                            "Please enter your review:",
                            "Delivery Review",
                            JOptionPane.PLAIN_MESSAGE
                        );

                        backend.addDeliveryReview(orderID, ratingInput, (review.trim().isEmpty() || review.equals("")) ? null : review.trim());
                        JOptionPane.showMessageDialog(
                            intiHomePage,
                            "Thank you for your feedback!",
                            "Review Submitted",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
                backend.completeOrder(customerID,  orderID);
                JOptionPane.showMessageDialog(null, "Order #"+orderID+" has been received!","Order Received",JOptionPane.INFORMATION_MESSAGE);
                homepage.closeDialog();
                intiHomePage.run();
            } catch (IOException ex) {
                Logger.getLogger(orderPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (option.equals("Rate Order")){
            String[] options = {"1", "2", "3", "4", "5"};
            String ratingInput = (String) JOptionPane.showInputDialog(
                intiHomePage,
                "Please rate your order (1-5):",
                "Vendor Rating",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                "5"
            );
            if (ratingInput != null) {
                String review = JOptionPane.showInputDialog(
                    intiHomePage,
                    "Please enter your review:",
                    "Vendor Food Review",
                    JOptionPane.PLAIN_MESSAGE
                );
                
                backend.addOrderReview(orderID, ratingInput, (review.trim().isEmpty() || review.equals("")) ? null : review.trim());

                JOptionPane.showMessageDialog(
                    intiHomePage,
                    "Thank you for your feedback!",
                    "Review Submitted",
                    JOptionPane.INFORMATION_MESSAGE
                );
                homepage.closeDialog();
                intiHomePage.run();
            }
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void naviButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naviButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_naviButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private method.RoundedButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private method.RoundedButton naviButton;
    // End of variables declaration//GEN-END:variables
}
