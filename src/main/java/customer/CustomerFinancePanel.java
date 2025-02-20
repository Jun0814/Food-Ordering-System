/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import managefile.Customer;
import managefile.Food;
import managefile.OrderItems;
import managefile.Transaction;

/**
 *
 * @author USER
 */
public class CustomerFinancePanel extends javax.swing.JPanel {
    private String customerID;
    private String transactionID;
    private JDialog dialog;
    private JFrame frame;
    private Customer customerDetail;
    customer_backend backend = new customer_backend();
    CustomerHome homepage = new CustomerHome();

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
     * Creates new form CustomerFinancePanel
     */
    public CustomerFinancePanel(String customerID,String transactionID) {
        this.customerID = customerID;
        this.transactionID = transactionID;
        customerDetail = backend.getSpecificCustomerDetail(customerID);
        initComponents();
        List<managefile.Transaction> transactions = backend.getTransactionByID(transactionID);
        Transaction transaction = transactions.getFirst();
        String transactionType = transaction.getTransactionType();
        
        if (transactionType.equalsIgnoreCase("debit") || transactionType.equalsIgnoreCase("refund")){
            getDebitTransaction(transaction);
        }else if (transactionType.equalsIgnoreCase("credit")){
            createReceiptPanel(transaction);
        }
        
        JPanel statusPanel = homepage.createStatusPanel(transactionType);
        jPanel7.setLayout(new BorderLayout());
        jPanel7.add(statusPanel,BorderLayout.WEST);
        jLabel1.setText("Transaction #"+transaction.getTransactionID());
    }
    
    private void getDebitTransaction(Transaction transaction){
        String orderid = transaction.getGeneralID();
        Map<Object,Object> allOrders = backend.getOrderByOrderID(orderid);
        List<managefile.Order> orders = (List<managefile.Order>) allOrders.get("orders");
        List<managefile.OrderItems> orderItems = (List<managefile.OrderItems>) allOrders.get("ordersItems");
        List<managefile.Food> foodItems = (List<managefile.Food>) allOrders.get("foodItems");
        
        Map<Object, Object> deliveryDetails = backend.getDeliveryDetails(orders.getFirst().getOrderID());
        List<managefile.Delivery> deliverys = (List<managefile.Delivery>) deliveryDetails.get("deliverys");
        List<managefile.Runner> runners = (List<managefile.Runner>) deliveryDetails.get("runners");

        System.out.println(deliverys);
        managefile.Delivery delivery = !deliverys.isEmpty() ? deliverys.getFirst():null;
        System.out.println(delivery);
        
        JPanel transactionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (int i = orders.size()-1; i >= 0; i--) {
            managefile.OrderItems innerOrderItem = orderItems.get(i);
            managefile.Food innerFoodItem = foodItems.get(i);
            CustomeritemPanel cp = new CustomeritemPanel(customerID, innerOrderItem, innerFoodItem, frame);
            transactionPanel.add(cp, gbc);
            gbc.gridx++;
            if (gbc.gridx == 1) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        String orderType = orders.getFirst().getOrderType().toUpperCase();
        String orderTypeD = orders.getFirst().getOrderTypeDetails().toUpperCase();
        detailsPanel.add(homepage.createDetailLabel("Order ID:", "#"+orders.getFirst().getOrderID()));
        detailsPanel.add(homepage.createDetailLabel2("Order Status:", orders.getFirst().getStatus(),orders.getFirst().getStatus()));
        detailsPanel.add(homepage.createDetailLabel("Order Type:", orderType));
        detailsPanel.add(homepage.createDetailLabel("Datetime:", orders.getFirst().getDatetime()));
        switch (orderType) {
            case "DINE IN" -> {
                detailsPanel.add(homepage.createDetailLabel("Table Number:", "Table "+orderTypeD));
            }
            case "PICKUP" -> {
                detailsPanel.add(homepage.createDetailLabel("Pick up Time:", orderTypeD));
            }
            case "DELIVERY" -> {
                detailsPanel.add(homepage.createDetailLabel("Delivery Address:", orderTypeD));
                detailsPanel.add(homepage.createDetailLabel("Delivery Description:", delivery.getDescription()));
            }
            default -> {
            }
        }
        String description = String.format("<html><div style='text-align: right;'>" +
                   "<span style='font-size: 12px;'>%s</span></html>", 
                   backend.returnTransactionDescription(transaction.getTransactionType(),transaction.getAmount()));
        switch (transaction.getTransactionType().toUpperCase()) {
            case "CREDIT","REFUND" -> {
                detailsPanel.add(homepage.createDetailLabel2("Total Amount: ", "+ RM "+String.format("%.2f", Double.parseDouble(orders.getFirst().getTotalAmount())),"pos"));
            }
            case "DEBIT" -> {
                if (orderType.equals("DELIVERY")){
                    detailsPanel.add(homepage.createDetailLabel2("Total Amount: ", "- RM "+String.format("%.2f", Double.parseDouble(orders.getFirst().getTotalAmount()))+" (+ RM "+String.format("%.2f", delivery.getDeliveryFees())+" Delivery fees)","neg"));
                }else{
                detailsPanel.add(homepage.createDetailLabel2("Total Amount: ", "- RM "+String.format("%.2f", Double.parseDouble(orders.getFirst().getTotalAmount())),"neg"));
                
                }
            }
        }
        String status = orders.getFirst().getStatus();
        JPanel statusPanel = homepage.createStatusPanel(status);
        
        jLabel2.setText(description);
        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(detailsPanel,BorderLayout.WEST);
        jPanel7.setLayout(new BorderLayout());
        jPanel7.add(statusPanel,BorderLayout.WEST);
        jPanel6.setLayout(new BorderLayout());
        jPanel6.add(jLabel1,BorderLayout.WEST);
        jPanel6.add(jPanel7,BorderLayout.EAST);
        
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(transactionPanel, BorderLayout.NORTH);
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
    
    private void createReceiptPanel(Transaction transaction) {
        String text = "You have top up successfully by "+transaction.getTopupType().toLowerCase()+". Here is your receipt.";
        String description = String.format("<html><div style='text-align: right;'>" +
                           "<span style='font-size: 12px;'>%s</span></html>", text);
        jLabel2.setText(description);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(229, 231, 235), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel iconLabel = new JLabel();
        iconLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        iconLabel.setForeground(new Color(31, 41, 55));
        iconLabel.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\logo.png", 105, 98));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(iconLabel);
        panel.add(Box.createVerticalStrut(2));

        JLabel heading = new JLabel("CASTROGO Food Court Receipt");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setForeground(new Color(31, 41, 55));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(heading);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(229, 231, 235));
        separator.setMaximumSize(new Dimension(300, 1));
        panel.add(Box.createVerticalStrut(15));
        panel.add(separator);
        panel.add(Box.createVerticalStrut(15));

        addDetailRow(panel, "Transaction ID:", transaction.getTransactionID());
        addDetailRow(panel, "Customer ID:", transaction.getCustomerID()+"   "+customerDetail.getName());
        addDetailRow(panel, "Transaction Date:", transaction.getDatetime());
        if (!transaction.getGeneralID().equals("null") && transaction.getGeneralID()!=null){
            addDetailRow(panel, "Admin ID:", transaction.getGeneralID());
        }
        addDetailRow(panel, "Top-up Method:", transaction.getTopupType());
        addDetailRow(panel, "Transaction Type:", transaction.getTransactionType());

        panel.add(Box.createVerticalStrut(5));
        JSeparator balanceSeparator = new JSeparator();
        balanceSeparator.setForeground(new Color(229, 231, 235));
        balanceSeparator.setMaximumSize(new Dimension(300, 1));
        panel.add(balanceSeparator);
        panel.add(Box.createVerticalStrut(10));
        
        addDetailRow(panel, "Previous Balance:", "RM " + String.format("%.2f", customerDetail.getCredit() - Double.parseDouble(transaction.getAmount())));
        addDetailRow(panel, "Current Balance:", "RM " + String.format("%.2f", customerDetail.getCredit()));

        panel.add(Box.createVerticalStrut(15));
        JSeparator amountSeparator = new JSeparator();
        amountSeparator.setForeground(new Color(229, 231, 235));
        amountSeparator.setMaximumSize(new Dimension(300, 1));
        panel.add(amountSeparator);
        panel.add(Box.createVerticalStrut(15));

        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new BoxLayout(amountPanel, BoxLayout.Y_AXIS));
        amountPanel.setBackground(new Color(239, 246, 255));
        amountPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(219, 234, 254), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        amountPanel.setMaximumSize(new Dimension(300, 70));

        JLabel amountLabel = new JLabel("Total Amount: RM " + String.format("%.2f", Double.parseDouble(transaction.getAmount())));
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        amountLabel.setForeground(new Color(37, 99, 235));
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        amountPanel.add(amountLabel);
        panel.add(amountPanel);

        panel.add(Box.createVerticalStrut(15));
        JLabel thankYouLabel = new JLabel("Thank you!");
        thankYouLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        thankYouLabel.setForeground(new Color(107, 114, 128));
        thankYouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(thankYouLabel);

        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(panel, BorderLayout.CENTER);
    }

    private void addDetailRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.setBackground(Color.WHITE);
        rowPanel.setMaximumSize(new Dimension(400, 30));

        // Enhanced label styling
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelComponent.setForeground(new Color(107, 114, 128));  // Gray text
        labelComponent.setPreferredSize(new Dimension(150, 20));

        // Enhanced value styling
        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        valueComponent.setForeground(new Color(31, 41, 55));  // Dark gray text

        rowPanel.add(labelComponent);
        rowPanel.add(valueComponent);
        panel.add(rowPanel);
        panel.add(Box.createVerticalStrut(5));  // Add spacing between rows
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(287, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("jLabel1");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
