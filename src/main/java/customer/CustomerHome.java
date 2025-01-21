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
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormatSymbols;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import main.UserLogin;
import managefile.Customer;
import managefile.OrderItems;
import managefile.readFile;
import method.scaleImage;
import vendor.VendorStore;

/**
 *
 * @author USER
 */
public class CustomerHome extends javax.swing.JFrame implements ActionListener{
    private String customerID;
    customer_backend backend = new customer_backend();
    String[] monthString = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    /**
     * Creates new form Home
     */
    
    public CustomerHome(String customerID) {
        this.customerID = customerID;
        System.out.println("hihi");
        initComponents();
        Customer customerDetails = backend.getSpecificCustomerDetail(customerID);
        jLabel3.setText(customerDetails.getName());
        jLabel2.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\logo.png", 180, 144));
        logout.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\log-out.png", 40, 40));
        logout.setFocusable(false);
        logout.addActionListener(this);
        getOrderDetails();
    }
    
    public CustomerHome(){}
    public Color getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "completed","accept","done","resolved": 
                return new Color(39, 174, 96);
            case "pending": 
                return new Color(243, 156, 18);
            case "processing","credit","refund":
                return new Color(52, 152, 219);
            case "debit","cancel": 
                return new Color(231, 76, 60);
            default: 
                return new Color(99, 115, 116);
        }
    }
    
    public void getOrderDetails(){
        Map<Object, Object> allOrders = backend.getOrder(customerID);
        List<managefile.Order> orders = (List<managefile.Order>) allOrders.get("orders");
        List<managefile.OrderItems> orderItems = (List<managefile.OrderItems>) allOrders.get("ordersItems");
        
        JPanel orderPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        HashSet<String> processedOrderIds = new HashSet<>();
        
        boolean hasActiveOrders = false;

        for (int i = orders.size() - 1; i >= 0; i--) {
            managefile.Order innerOrder = orders.get(i);
            String orderID = innerOrder.getOrderID();
            String status = innerOrder.getStatus().toLowerCase();

            if (!processedOrderIds.contains(orderID) && !(status.equals("completed") || status.equals("cancel"))) {
                hasActiveOrders = true;
                processedOrderIds.add(orderID);
                JPanel panel = addOrderPanel(innerOrder);
                orderPanel.add(panel, gbc);
                gbc.gridx++;
                if (gbc.gridx == 1) {
                    gbc.gridx = 0;
                    gbc.gridy++;
                }
            }
        }

        if (hasActiveOrders) {
            jLabel1.setText("My Order");
        } else {
            for (int i = orders.size() - 1; i >= 0; i--) {
                managefile.Order innerOrder = orders.get(i);
                String orderID = innerOrder.getOrderID();
                String status = innerOrder.getStatus().toLowerCase();

                if (!processedOrderIds.contains(orderID) && (status.equals("completed") || status.equals("cancel"))) {
                    jLabel1.setText("Order Again");
                    processedOrderIds.add(orderID);
                    JPanel panel = addOrderPanel(innerOrder);
                    orderPanel.add(panel, gbc);
                    break;
                }
            }
        }

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(orderPanel, BorderLayout.NORTH);
        
        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setPreferredSize(new Dimension(628,460));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
        scrollPane.setBorder(null);
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(scrollPane, BorderLayout.CENTER);
    }
    
    public JPanel createStatusPanel(String status) {
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        statusPanel.setBackground(null);

        JLabel statusIcon = new JLabel("●");
        
        statusIcon.setForeground(getStatusColor(status));

        JLabel statusLabel = new JLabel(status.toUpperCase());
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusLabel.setForeground(getStatusColor(status));

        statusPanel.add(statusIcon);
        statusPanel.add(statusLabel);
        return statusPanel;
    }
    
    public JPanel createRatingPanel(String status,int size) {
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        statusPanel.setBackground(null);

        JLabel statusIcon = new JLabel();
        statusIcon.setIcon(backend.scale.processImage("src\\main\\java\\image_repository\\star.png", 20, 20));
        
        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, size));
        statusLabel.setForeground(getStatusColor(status));

        statusPanel.add(statusIcon);
        statusPanel.add(statusLabel);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        return statusPanel;
    }
    public JLabel createStyledLabel(String text, int size, boolean bold) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, size));
        label.setForeground(new Color(51, 51, 51));
        return label;
    }

    public JPanel createDetailLabel(String title, String value) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(null);

        JLabel titleLabel = createStyledLabel(title, 14, true);
        JLabel valueLabel = createStyledLabel(value, 14, false);

        panel.add(titleLabel);
        panel.add(valueLabel);
        return panel;
    }
    public JPanel createDetailLabel2(String title, String value,String type) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setBackground(null);

        JLabel titleLabel = createStyledLabel(title, 14, true);
        JLabel valueLabel = createStyledLabel(value, 14, false);
        if (type.equals("pos")){
            valueLabel.setForeground(new Color(0, 102, 204));
        }else if (type.equals("neg")){
            valueLabel.setForeground(new Color(204, 0, 0));
        }else {
            valueLabel.setForeground(getStatusColor(type));
        }
        panel.add(titleLabel);
        panel.add(valueLabel);
        return panel;
    }
    
    public String formatDateTime(String datetime) {
        String[] parts = datetime.split("T");
        if (parts.length != 2) {
            return datetime;
        }

        String[] dateParts = parts[0].split("-");
        String formattedDate = String.format("%s %s %s",dateParts[2],getMonthName(dateParts[1]),dateParts[0]);

        String time = parts[1];
        if (time.length() >= 5) {
            time = time.substring(0, 5);
        }

        return String.format("<html><div style='text-align: right;'>" +
                           "<span style='font-size: 12px;'>%s</span><br>" +
                           "<span style='color: #666666; font-size: 10px;'>%s</span></div></html>", 
                           formattedDate, time);
    }

    public String getMonthName(String monthNumber) {
        int month = Integer.parseInt(monthNumber);
        return monthString[month - 1];
    }
    
    public JPanel addOrderPanel(managefile.Order orders) {
        // Create main rounded panel
        method.RoundedPanel panel = new method.RoundedPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(980, 160));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Add some padding

        // Create header panel (Order ID and Date)
        JPanel headerPanel = new JPanel(new BorderLayout(20, 0)); // Add gap between components
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15)); // Add bottom margin

        JLabel orderIdLabel = createStyledLabel("Order #" + orders.getOrderID(), 18, true);
        JLabel dateLabel = createStyledLabel(formatDateTime(orders.getDatetime()), 14, false);
        headerPanel.add(orderIdLabel, BorderLayout.WEST);
        headerPanel.add(dateLabel, BorderLayout.EAST);

        // Create details panel (Order Type and Details)
        JPanel detailsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 18)); // Add vertical margins

        // Add order type and details
        String orderType = orders.getOrderType().toUpperCase();
        switch (orderType) {
            case "DINE IN" -> {
                detailsPanel.add(createDetailLabel("Order Type:", orderType));
                detailsPanel.add(createDetailLabel("Table Number:", orders.getOrderTypeDetails()));
            }
            case "PICKUP" -> {
                detailsPanel.add(createDetailLabel("Order Type:", orderType));
                detailsPanel.add(createDetailLabel("Pick up Time:", orders.getOrderTypeDetails()));
            }
            case "DELIVERY" -> {
                detailsPanel.add(createDetailLabel("Order Type:", orderType));
                detailsPanel.add(createDetailLabel("Delivery Address:", orders.getOrderTypeDetails()));
            }
            default -> {
            }
        }

        // Create bottom panel (Status and Total)
        JPanel bottomPanel = new JPanel(new BorderLayout(20, 0)); // Add gap between components
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Add top margin

        // Create status panel
        JPanel statusPanel = createStatusPanel(orders.getStatus());

        // Create total amount label
        JLabel totalLabel = new JLabel("Total Amount: RM " + String.format("%.2f", Double.parseDouble(orders.getTotalAmount())));
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalLabel.setForeground(new Color(51, 51, 51));

        // Add components to bottom panel
        bottomPanel.add(statusPanel, BorderLayout.WEST);
        bottomPanel.add(totalLabel, BorderLayout.EAST);

        // Add all sections to main panel
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(detailsPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                viewOrderDetails(orders.getOrderID(),CustomerHome.this);
            }
        });

        return panel;
    }
    public void viewOrderDetails(String orderID,JFrame frame) {
        orderPanel orderPanel = new orderPanel(customerID,orderID);
        orderPanel.setOrderID(orderID);

        JDialog dialog = new JDialog(frame, true);
        orderPanel.setDialog(dialog);
        orderPanel.setFrame(frame);
        
        dialog.setResizable(false);
        dialog.setSize(new Dimension(950, 482));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.add(orderPanel, BorderLayout.CENTER);
        dialog.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(
            (screenSize.width - dialog.getWidth()) / 2,
            (screenSize.height - dialog.getHeight()) / 2
        );

        dialog.setVisible(true);

        orderPanel.repaint();
        orderPanel.revalidate();
    }
    public void addCart(String customerID, managefile.OrderItems orderItems,managefile.Food foodItems,JFrame frame){
        JDialog dialog = new JDialog(frame, true);
        dialog.setResizable(false);
        dialog.setSize(new Dimension(950, 482));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        reOrderCartPanel cartPanel = new reOrderCartPanel(customerID, orderItems,foodItems,dialog);
        dialog.add(cartPanel, BorderLayout.CENTER);
        dialog.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(
            (screenSize.width - dialog.getWidth()) / 2,
            (screenSize.height - dialog.getHeight()) / 2
        );

        dialog.setVisible(true);
        cartPanel.repaint();
        cartPanel.revalidate();
    }
    
    public void closeDialog(){
        for (Window window : Window.getWindows()) {
            if (window instanceof JDialog) {
                JDialog dialog1 = (JDialog) window;
                dialog1.dispose();
            }else if (window instanceof JFrame) {
                JFrame frame1 = (JFrame) window;
                frame1.dispose();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        inquiries = new javax.swing.JButton();
        finance = new javax.swing.JButton();
        menu = new javax.swing.JButton();
        notification = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        order = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(235, 148, 134));

        inquiries.setBackground(new java.awt.Color(243, 222, 138));
        inquiries.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        inquiries.setText("Inquiries");
        inquiries.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        inquiries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inquiriesActionPerformed(evt);
            }
        });

        finance.setBackground(new java.awt.Color(243, 222, 138));
        finance.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        finance.setText("Finance");
        finance.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        finance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financeActionPerformed(evt);
            }
        });

        menu.setBackground(new java.awt.Color(243, 222, 138));
        menu.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        menu.setText("Menu");
        menu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActionPerformed(evt);
            }
        });

        notification.setBackground(new java.awt.Color(243, 222, 138));
        notification.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        notification.setText("Notifications");
        notification.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        notification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notificationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(finance, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(inquiries, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(notification, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(finance, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inquiries, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(notification, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(39, 40, 56));

        jLabel2.setText("jLabel2");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Welcome Back,");

        logout.setBackground(new java.awt.Color(39, 40, 56));
        logout.setBorder(null);
        logout.setBorderPainted(false);
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Welcome Back,");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );

        order.setBackground(new java.awt.Color(243, 222, 138));
        order.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        order.setText("All Orders");
        order.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("Order Again");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(order, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(order, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inquiriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inquiriesActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_inquiriesActionPerformed

    private void financeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeActionPerformed
actionPerformed(evt);    }//GEN-LAST:event_financeActionPerformed

    private void menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActionPerformed
actionPerformed(evt);    }//GEN-LAST:event_menuActionPerformed

    private void orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderActionPerformed
actionPerformed(evt);    }//GEN-LAST:event_orderActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutActionPerformed

    private void notificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notificationActionPerformed
        // TODO add your handling code here:
        actionPerformed(evt);
    }//GEN-LAST:event_notificationActionPerformed

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==menu){
            CustomerMenu page = new CustomerMenu(customerID);
            page.run();
            this.dispose();
        }else if (e.getSource()==finance){
            CustomerFinance page = new CustomerFinance(customerID);
            page.run();
            this.dispose();
        }else if (e.getSource()==inquiries){
            CustomerInquiries page = new CustomerInquiries(customerID);
            page.run();
            this.dispose();
        }else if (e.getSource()==order){
            CustomerOrder page = new CustomerOrder(customerID);
            page.run();
            this.dispose();
        }else if (e.getSource()==logout){
            UserLogin loginpage = new UserLogin("customer");
            loginpage.run();
            this.dispose();
        }else if (e.getSource()==notification){
            CustomerNotification page = new CustomerNotification(customerID);
            page.run();
            this.dispose();
        }
    }
    public void run() {
        new CustomerHome(customerID).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton finance;
    private javax.swing.JButton inquiries;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton logout;
    private javax.swing.JButton menu;
    private javax.swing.JButton notification;
    private javax.swing.JButton order;
    // End of variables declaration//GEN-END:variables
}
