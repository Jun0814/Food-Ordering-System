/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vendor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import managefile.Data;
import method.ImageHandler;
import method.RoundedButton;
import method.RoundedPanel;
import method.primaryKey;

/**
 *
 * @author TPY
 */
public class VendorOrder extends javax.swing.JPanel {

    Data data = new Data();
    ImageHandler imageHandler = new ImageHandler();
    private String[][] orderData;
    private String userId, currentOrderId, currentOrderCategory;
    primaryKey primaryKey = new primaryKey();
    
    /**
     * Creates new form VendorStore
     */
    public VendorOrder(String userId) {
        initComponents();      
        this.userId = userId;
        orderData = data.reverse2DArray(data.retrieveDataAsArray(3, userId, "src\\main\\java\\repository\\order.txt"));
                
        intiCategoryButton();
        intiDefaultOrderCategory();
        setJScrollPane();
    }
        
    private String getCurrentOrderId() {
        return currentOrderId;
    }

    private void setCurrentOrderId(String currentFoodId) {
        this.currentOrderId = currentFoodId;
    }

    private String getCurrentOrderCategory() {
        return currentOrderCategory;
    }

    private void setCurrentOrderCategory(String currentFoodCategory) {
        this.currentOrderCategory = currentFoodCategory;
    }
    
    private void setMenuPanelHeight(){
        int orderBlockCount = 0;
        int row;
        int menuPanelHeight = 600;
        
        for (int i = 0; i < menuPanel.getComponentCount(); i++) {
            if (menuPanel.getComponent(i) instanceof JPanel) {
                orderBlockCount++;
            }
        }
        
        row = (int) orderBlockCount;
        
        if(row >= 2){
            menuPanelHeight = 600 + ((row-2)*(200));
        }else{
            menuPanelHeight = 600;
        }
        menuPanel.setPreferredSize(new Dimension(1000,menuPanelHeight));
    }
    
    private void setJScrollPane(){
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setPreferredSize(new Dimension(1000,610));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.add(scrollPane);
    }
        
    private void intiDefaultOrderCategory(){
        for (String[] data : orderData) {
            try{
                String orderType = data.length > 5 ? data[5].trim() : "";
                if(orderType != null){
                    intiMenuPanel(orderType);
                    break;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void intiCategoryButton() {
        Map<String, RoundedButton> foodTypeButtons = new HashMap<>();
        
        for (String[] data : orderData) {
            try {
                String orderType = data.length > 5 ? data[5].trim().toUpperCase() : "General";
                
                RoundedButton orderTypeButton = foodTypeButtons.get(orderType);
                if (orderTypeButton == null) {
                    
                    orderTypeButton = new RoundedButton();
                    orderTypeButton.setPreferredSize(new Dimension(150, 50)); 
                    orderTypeButton.setText(orderType);
                    orderTypeButton.setRadius(20);
                    
                    orderTypeButton.setBorderColor(new Color(200,200,255));
                    orderTypeButton.setColor(new Color(255,255,255));
                    orderTypeButton.setColorClick(new Color(243,222,138));
                    orderTypeButton.setColorOver(new Color(140,75,242));
                    orderTypeButton.setFontColor(Color.black);
                    orderTypeButton.setFontColorClick(Color.black);
                    orderTypeButton.setFontColorOver(Color.white);
                    
                    orderTypeButton.addActionListener(e -> {
                        intiMenuPanel(orderType);
                    });
                    
                    foodTypeButtons.put(orderType, orderTypeButton);
                    categoryPanel.add(orderTypeButton);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void intiMenuPanel(String orderCategory){
        closeMenuPanel();
        
        for (String[] orderDatas : orderData) {
            try {
                String orderType = orderDatas.length > 5 ? orderDatas[5].trim() : "";
                String orderStatus = orderDatas.length > 9 ? orderDatas[9].trim() : "";
                if (orderCategory.equalsIgnoreCase(orderType)) {
                    if(orderStatus.equalsIgnoreCase("Pending") || orderStatus.equalsIgnoreCase("Accept") || orderStatus.equalsIgnoreCase("Reject") || orderStatus.equalsIgnoreCase("Done")){
                        
                    String orderId = orderDatas.length > 0 ? orderDatas[0].trim() : "";
                    String customerId = orderDatas.length > 1 ? orderDatas[1].trim() : "";
                    String deliveryId = orderDatas.length > 2 ? orderDatas[2].trim() : "";
                    String reviewId = orderDatas.length > 4 ? orderDatas[4].trim() : "";
                    String type = orderDatas.length > 5 ? orderDatas[5].trim() : "";
                    String typeDetails = orderDatas.length > 6 ? orderDatas[6].trim() : "";
                    
                    String dateTime = orderDatas.length > 7 ? orderDatas[7].trim() : "";
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                    LocalDateTime datetime = LocalDateTime.parse(dateTime, formatter);
                    
                    double orderTotalAmount = orderDatas.length > 8 ? Math.round(Double.parseDouble(orderDatas[8].trim()) * 100.0) / 100.0 : 0.0;
                    orderTotalAmount = Double.parseDouble(String.format("%.2f", orderTotalAmount));
                    String formattedOrderTotalAmount = String.format("%.2f", orderTotalAmount);
                    
                    OrderBlock orderBlock = new OrderBlock();
                    orderBlock.setOrderType(type.toUpperCase());
                    orderBlock.setOrderStatus(orderStatus);
                    orderBlock.setOrderId(orderId);
                    orderBlock.setDateTime(LocalDateTime.MIN);
                    orderBlock.setTypeDetails(typeDetails);
                    orderBlock.setDateTime(datetime);
                    orderBlock.setThemeColor(new Color(140,75,242));
                    orderBlock.setEdgeColor(new Color(200,200,255));
                    orderBlock.setBackgroundColor(new Color(255,255,255));
                    
                    BufferedImage loadedImage;
                    if(orderType.equalsIgnoreCase("dine in")){
                        loadedImage = imageHandler.loadImage("src\\main\\java\\image_repository\\dine-in.png");
                    }else if(orderType.equalsIgnoreCase("pickup")){
                        loadedImage = imageHandler.loadImage("src\\main\\java\\image_repository\\pick-up.png");
                    }else if(orderType.equalsIgnoreCase("delivery")){
                        loadedImage = imageHandler.loadImage("src\\main\\java\\image_repository\\food-delivery.png");
                    }else{
                        loadedImage = imageHandler.loadImage("src\\main\\java\\image_repository\\dine-in.png");
                    }
                    JLabel label = orderBlock.getLabel();
                    label.setBounds(0, 0, 240, 200);
                    imageHandler.displayImageOnLabel(loadedImage, label);
                                        
                    RoundedButton checkButton = orderBlock.getCheckButton();
                    RoundedButton acceptButton = orderBlock.getAcceptButton();
                    RoundedButton rejectButton = orderBlock.getRejectButton();
                    RoundedButton doneButton = orderBlock.getDoneButton();
                    RoundedButton cancelButton = orderBlock.getCancelButton();
                    
                    if(orderStatus.equalsIgnoreCase("cancel")){
                        doneButton.setVisible(false);
                        acceptButton.setVisible(false);
                        rejectButton.setVisible(false);
                        cancelButton.setVisible(false);
                    }else if(orderStatus.equalsIgnoreCase("completed")){
                        doneButton.setVisible(false);
                        acceptButton.setVisible(false);
                        rejectButton.setVisible(false);
                        cancelButton.setVisible(false);
                    }else{
                        checkButton.setVisible(true);
                        acceptButton.setVisible(true);
                        rejectButton.setVisible(true);
                        doneButton.setVisible(true);
                        cancelButton.setVisible(true);
                    }
                    
                    acceptButton.addActionListener(e ->{
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to accept the order?","Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            data.updateData(orderId, 9, "Accept", "src\\main\\java\\repository\\order.txt");
                            JOptionPane.showMessageDialog(null, "The order has been successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            reinitializeToMenuPanel();
                        }
                    });
                    
                    rejectButton.addActionListener(e ->{
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to reject the order?","Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            data.updateData(orderId, 9, "Reject", "src\\main\\java\\repository\\order.txt");
                            JOptionPane.showMessageDialog(null, "The order has been successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            reinitializeToMenuPanel();
                        }
                    });
                                        
                    doneButton.addActionListener(e -> {
                        int response = JOptionPane.showConfirmDialog(
                            null, 
                            "Are you sure you want to make the order done?", 
                            "Confirmation", 
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                        );

                        if (response == JOptionPane.YES_OPTION) {
                            data.updateData(orderId, 9, "Done", "src\\main\\java\\repository\\order.txt");
                            data.updateData(orderId, 1, "Done", 5, "src\\main\\java\\repository\\orderitems.txt");

                            String[] ids = data.retrieveIdsFromFile("src\\main\\java\\repository\\notifications.txt");
                            List<String> idList = Arrays.asList(ids);
                            String notificationsId = primaryKey.incrementPrimaryKey(idList);

                            LocalDate currentDate = LocalDate.now();
                            LocalTime currentTime = LocalTime.now();
                            String notificationTime = currentDate.toString()+"T" + currentTime.toString().split("\\.")[0];

                            String newNotifications = notificationsId + "," 
                                    + "Your order: " + orderId + " is done|" 
                                    + "Price: " + formattedOrderTotalAmount + "|" 
                                    + "Time Order: " + datetime + "|" 
                                    + "," 
                                    + notificationTime + "," 
                                    + customerId;
                            data.insertData(newNotifications, "src\\main\\java\\repository\\notifications.txt");

                            JOptionPane.showMessageDialog(
                                null, 
                                "The order has been successfully updated!\nA notification is sent to the customer.", 
                                "Success", 
                                JOptionPane.INFORMATION_MESSAGE
                            );

                            reinitializeToMenuPanel();
                        }
                    });
                    
                    cancelButton.addActionListener(e ->{
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the order?","Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            data.updateData(orderId, 9, "Cancel", "src\\main\\java\\repository\\order.txt");
                            data.updateData(orderId, 2,"Cancel", 5, "src\\main\\java\\repository\\orderItems.txt");
                            JOptionPane.showMessageDialog(null, "The order has been successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            reinitializeToMenuPanel();
                        }
                    });
                    
                    checkButton.addActionListener(e -> {
                        closeAllJDialog();
                        intiPopUp(orderId);
                    });
                    
                    menuPanel.add(orderBlock);
                    setCurrentOrderCategory(orderCategory);
                    setMenuPanelHeight();
                    orderBlock.repaint();
                    orderBlock.revalidate();
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        menuPanel.repaint();
        menuPanel.revalidate();
    }
    
    private void intiPopUp(String orderId){
        
        JDialog dialog = new JDialog();
        dialog.setUndecorated(false);
        dialog.setBackground(new Color(200,200,255));
        dialog.setLayout(new BorderLayout());

        RoundedPanel container = new RoundedPanel();
        container.setCornerRadius(25);
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.setBackgroundColor(new Color(200,200,255));
        
        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setPreferredSize(new Dimension(1000, 350));
                        
        String[][] orderItemData = data.reverse2DArray(data.retrieveDataAsArray(1, orderId, "src\\main\\java\\repository\\orderitems.txt"));
        String foodName = null, formattedorderItemTotalAmount, imagePath = null;
        
        for (String[] orderItemDatas : orderItemData) {
            try{
                String orderReferenceId = orderItemDatas.length > 1 ? orderItemDatas[1].trim() : "";
                if(orderReferenceId.equalsIgnoreCase(orderId)){
                    
                    String foodId = orderItemDatas.length > 2 ? orderItemDatas[2].trim() : "";
                    String quantity = orderItemDatas.length > 3 ? orderItemDatas[3].trim() : "";
                    double orderItemTotalAmount = orderItemDatas.length > 4 ? Math.round(Double.parseDouble(orderItemDatas[4].trim()) * 100.0) / 100.0 : 0.0;
                    orderItemTotalAmount = Double.parseDouble(String.format("%.2f", orderItemTotalAmount));
                    formattedorderItemTotalAmount = String.format("%.2f", orderItemTotalAmount);
                    String orderItemsStatus = orderItemDatas.length > 5 ? orderItemDatas[5].trim() : "";
                    String orderIemsRemark = orderItemDatas.length > 6 ? orderItemDatas[6].trim() : "";
                    
                    String[][] foodData = data.retrieveDataAsArray(0, foodId, "src\\main\\java\\repository\\food.txt");
                    
                    for (String[] data : foodData) {
                        String firstId = data.length > 0 ? data[0].trim() : "";
                        if (foodId.equalsIgnoreCase(firstId)) {
                            foodName = data.length > 1 ? data[1].trim() : "";
                            imagePath = data.length > 5 ? data[5].trim() : "";
                        }
                    }
                    
                    OrderPopUp popUp = new OrderPopUp();
                    popUp.setFoodId(foodId);
                    popUp.setFoodName(foodName);
                    popUp.setQuantity(quantity);
                    popUp.setTotalAmount(formattedorderItemTotalAmount);
                    popUp.setStatus(orderItemsStatus);
                    popUp.setRemark(orderIemsRemark);
                    popUp.setEdgeColor(new Color(200,200,255));
                    
                    BufferedImage loadedImage = imageHandler.loadImage(imagePath);
                    JLabel label = popUp.getLabel();
                    label.setBounds(0, 0, 240, 200);
                    imageHandler.displayImageOnLabel(loadedImage, label);
                    
                    RoundedButton doneButton  = popUp.getDoneRoundedButton();
                    RoundedButton pendingButton  = popUp.getPendingRoundedButton();
                    RoundedButton cancelButton  = popUp.getCancelRoundedButton();
                    
                    if(orderItemsStatus.equalsIgnoreCase("pending")){
                        pendingButton.setVisible(false);
                        doneButton.setVisible(true);
                        cancelButton.setVisible(true);
                    }else if(orderItemsStatus.equalsIgnoreCase("done")){
                        pendingButton.setVisible(true);
                        doneButton.setVisible(false);
                        cancelButton.setVisible(false);
                    }else if(orderItemsStatus.equalsIgnoreCase("cancel")){
                        pendingButton.setVisible(true);
                        doneButton.setVisible(false);
                        cancelButton.setVisible(false);
                    }
                    
                    doneButton.addActionListener(e ->{
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to make the order completed?","Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            closeAllJDialog();
                            data.updateData(orderId, 1, "Done", 5, "src\\main\\java\\repository\\orderitems.txt");
                            JOptionPane.showMessageDialog(null, "The order has been successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            reinitializeToPopUp();
                        }
                    });
                    
                    pendingButton.addActionListener(e ->{
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to make the order pending?","Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            closeAllJDialog();
                            data.updateData(orderId, 1, "Pending", 5, "src\\main\\java\\repository\\orderitems.txt");
                            JOptionPane.showMessageDialog(null, "The order has been successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            reinitializeToPopUp();
                        }
                    });
                    
                    cancelButton.addActionListener(e ->{
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the order?","Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            closeAllJDialog();
                            data.updateData(orderId, 1, "Cancel", 5, "src\\main\\java\\repository\\orderitems.txt");
                            JOptionPane.showMessageDialog(null, "The order has been successfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            reinitializeToPopUp();
                        }
                    });
                    
                    container.add(popUp);
                    setCurrentOrderId(orderId);
                    popUp.repaint();
                    popUp.revalidate();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
            
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setVisible(true);
        addDragFunctionality(dialog);
        dialog.pack();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation(
            (screenSize.width - dialog.getWidth()) / 2,
            (screenSize.height - dialog.getHeight()) / 2
        );
        
        menuPanel.repaint();
        menuPanel.revalidate();
    }
    
    private void closeMenuPanel(){
        Component[] componentList = menuPanel.getComponents();
        for(Component c : componentList){
            if(c instanceof OrderBlock){
                menuPanel.remove(c);
            }
        }
    }
    
    private void closeAllJDialog(){
        for (Window window : Window.getWindows()) {
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                dialog.dispose();
            }
        }
    }
    
    private void reinitializeToPopUp() {
        orderData = data.reverse2DArray(data.retrieveDataAsArray(3, userId, "src\\main\\java\\repository\\order.txt"));
        removeAll();
        revalidate();
        repaint();
        initComponents();
        setJScrollPane();
        intiCategoryButton();
        intiDefaultOrderCategory();
        intiMenuPanel(getCurrentOrderCategory());
        intiPopUp(getCurrentOrderId());
    }
    
    private void reinitializeToMenuPanel(){
        orderData = data.reverse2DArray(data.retrieveDataAsArray(3, userId, "src\\main\\java\\repository\\order.txt"));
        removeAll();
        revalidate();
        repaint();
        initComponents();
        setJScrollPane();
        intiCategoryButton();
        intiDefaultOrderCategory();
        intiMenuPanel(getCurrentOrderCategory());
    }
    
    private static void addDragFunctionality(JDialog dialog) {
        final int[] mouseX = {0};
        final int[] mouseY = {0};

        dialog.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX[0] = e.getX();
                mouseY[0] = e.getY();
            }
        });
        
        dialog.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = dialog.getLocation().x + e.getX() - mouseX[0];
                int y = dialog.getLocation().y + e.getY() - mouseY[0];
                dialog.setLocation(x, y);
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

        jLabel2 = new javax.swing.JLabel();
        categoryPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(200, 200, 255));
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        jLabel2.setBackground(new java.awt.Color(39, 40, 56));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(140, 75, 242));
        jLabel2.setText("Order Category");
        add(jLabel2);

        categoryPanel.setBackground(new java.awt.Color(200, 200, 255));
        categoryPanel.setPreferredSize(new java.awt.Dimension(1000, 60));
        add(categoryPanel);

        jLabel1.setBackground(new java.awt.Color(39, 40, 56));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 75, 242));
        jLabel1.setText("Customer Order");
        add(jLabel1);

        menuPanel.setBackground(new java.awt.Color(200, 200, 255));
        menuPanel.setAutoscrolls(true);
        menuPanel.setMinimumSize(new java.awt.Dimension(1000, 600));
        menuPanel.setName(""); // NOI18N
        menuPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        add(menuPanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel menuPanel;
    // End of variables declaration//GEN-END:variables
}
