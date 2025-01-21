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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import managefile.Data;
import method.ImageHandler;
import method.RoundedButton;
import method.RoundedPanel;

/**
 *
 * @author TPY
 */
public class VendorHistory extends javax.swing.JPanel {

    Data data = new Data();
    ImageHandler imageHandler = new ImageHandler();
    private String[][] orderData;
    private String userId;
    
    /**
     * Creates new form VendorStore
     */
    public VendorHistory(String userId) {
        initComponents();
        this.userId = userId;
        orderData = data.reverse2DArray(data.retrieveDataAsArray(3, userId, "src\\main\\java\\repository\\order.txt"));
        
        intiCategoryButton();
        intiDefaultPanel();
        setHorizontalScrollPane();
        this.add(jLabel2);
        setVerticalScrollPane();
    }
    
    private void setMenuPanelHeight(){
        int orderBlockCount = 0;
        int row;
        int menuPanelHeight = 590;
        
        for (int i = 0; i < menuPanel.getComponentCount(); i++) {
            if (menuPanel.getComponent(i) instanceof JPanel) {
                orderBlockCount++;
            }
        }
        
        row = (int) orderBlockCount;
        
        if(row >= 2){
            menuPanelHeight = 590 + ((row-2)*(200));
        }else{
            menuPanelHeight = 590;
        }
        menuPanel.setPreferredSize(new Dimension(1000,menuPanelHeight));
    }
    
    private void setVerticalScrollPane(){
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setPreferredSize(new Dimension(1000,590));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.add(scrollPane);
    }
    
    private void setSubCategoryWidth() {
        int dateBlockCount = 0;
        int row;
        int subCategoryWidth = 0;
        
        for (int i = 0; i < subCategoryPanel.getComponentCount(); i++) {
            if (subCategoryPanel.getComponent(i) instanceof RoundedButton) {
                dateBlockCount++;
            }
        }
        
        row = (int) dateBlockCount;
        
        if(row >= 6){
            subCategoryWidth = 1000 + ((row-6)*(150));
        }else{
            subCategoryWidth = 1000;
        }
        subCategoryPanel.setPreferredSize(new Dimension(subCategoryWidth,60));
    }
    
    private void setHorizontalScrollPane(){
        subCategoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JScrollPane scrollPane2 = new JScrollPane(subCategoryPanel);
        scrollPane2.setPreferredSize(new Dimension(1000,60));
        scrollPane2.setBorder(BorderFactory.createEmptyBorder());
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane2.getHorizontalScrollBar().setUnitIncrement(10);
            
        JScrollBar hScrollBar = scrollPane2.getHorizontalScrollBar();
        hScrollBar.setPreferredSize(new Dimension(6,6));
        
        this.add(scrollPane2);
    }
    
    private void intiDefaultPanel(){
        int year = LocalDate.now().getYear();
        String yearString = String.valueOf(year);
        intiSubCategoryButton("Yearly");
        intiMenuPanel(yearString, "Yearly");
    }
    
    private void intiCategoryButton() {
        Map<String, RoundedButton> foodTypeButtons = new HashMap<>();
        String[]ButtonData = {"Daily", "Monthly", "Quarterly", "Yearly"};
        
        for (String data : ButtonData) {
            try {                
                RoundedButton orderTypeButton = foodTypeButtons.get(data);
                if (orderTypeButton == null) {
                    
                    orderTypeButton = new RoundedButton();
                    orderTypeButton.setPreferredSize(new Dimension(150, 50)); 
                    orderTypeButton.setText(data);
                    orderTypeButton.setRadius(20);
                    
                    orderTypeButton.setBorderColor(new Color(200,200,255));
                    orderTypeButton.setColor(new Color(255,255,255));
                    orderTypeButton.setColorClick(new Color(243,222,138));
                    orderTypeButton.setColorOver(new Color(140,75,242));
                    orderTypeButton.setFontColor(Color.black);
                    orderTypeButton.setFontColorClick(Color.black);
                    orderTypeButton.setFontColorOver(Color.white);
                    
                    orderTypeButton.addActionListener(e -> {
                        intiSubCategoryButton(data);
                    });
                    
                    foodTypeButtons.put(data, orderTypeButton);
                    categoryPanel.add(orderTypeButton);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void intiSubCategoryButton(String category) {
        closeButtonInSubCategory();
                
        Map<String, RoundedButton> foodTypeButtons = new HashMap<>();

        for (String[] data : orderData) {
            try {
                String dateTime = data.length > 7 ? data[7].trim() : "";
                if (dateTime.isEmpty()) continue;
                
                String buttonKey  = generateTimeKey(dateTime, category);
                
                if (!foodTypeButtons.containsKey(buttonKey)) {
                    RoundedButton orderTypeButton = new RoundedButton();
                    orderTypeButton.setPreferredSize(new Dimension(150, 50));
                    orderTypeButton.setText(buttonKey);
                    orderTypeButton.setRadius(20);
                    
                    orderTypeButton.setBorderColor(new Color(200, 200, 255));
                    orderTypeButton.setColor(new Color(255, 255, 255));
                    orderTypeButton.setColorClick(new Color(243, 222, 138));
                    orderTypeButton.setColorOver(new Color(140, 75, 242));
                    orderTypeButton.setFontColor(Color.black);
                    orderTypeButton.setFontColorClick(Color.black);
                    orderTypeButton.setFontColorOver(Color.white);
                    
                    orderTypeButton.addActionListener(e -> intiMenuPanel(orderTypeButton.getText(), category));
                    
                    foodTypeButtons.put(buttonKey, orderTypeButton);
                    subCategoryPanel.add(orderTypeButton);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setSubCategoryWidth();
        subCategoryPanel.revalidate();
        subCategoryPanel.repaint();
    }

    private void intiMenuPanel(String buttonKey,String category){
        closeMenuPanel();
        
        for (String[] orderDatas : orderData) {
            try {
                String dateTime = orderDatas.length > 7 ? orderDatas[7].trim() : "";
                String status = orderDatas.length > 9 ? orderDatas[9].trim() : "";
                String reviewDate  = generateTimeKey(dateTime, category);
        
                if (status.equalsIgnoreCase("done") || status.equalsIgnoreCase("completed") || status.equalsIgnoreCase("cancel")) {
                    if(reviewDate.equalsIgnoreCase(buttonKey)){
                        String orderId = orderDatas.length > 0 ? orderDatas[0].trim() : "";
                        String customerId = orderDatas.length > 1 ? orderDatas[1].trim() : "";
                        String deliveryId = orderDatas.length > 2 ? orderDatas[2].trim() : "";
                        String reviewId = orderDatas.length > 4 ? orderDatas[4].trim() : "";
                        String orderType = orderDatas.length > 5 ? orderDatas[5].trim() : "";
                        String typeDetails = orderDatas.length > 6 ? orderDatas[6].trim() : "";

                        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                        LocalDateTime datetime = LocalDateTime.parse(dateTime, formatter);

                        double orderTotalAmount = orderDatas.length > 8 ? Math.round(Double.parseDouble(orderDatas[8].trim()) * 100.0) / 100.0 : 0.0;
                        orderTotalAmount = Double.parseDouble(String.format("%.2f", orderTotalAmount));
                        String formattedOrderTotalAmount = String.format("%.2f", orderTotalAmount);
                        String orderStatus = orderDatas.length > 9 ? orderDatas[9].trim() : "";

                        OrderBlock orderBlock = new OrderBlock();
                        orderBlock.setOrderType(orderType.toUpperCase());
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
                        cancelButton.setVisible(false);
                        acceptButton.setVisible(false);
                        rejectButton.setVisible(false);
                        doneButton.setVisible(false);

                        checkButton.addActionListener(e -> {
                            closeAllJDialog();
                            intiPopUp(orderId);
                        });

                        menuPanel.add(orderBlock);
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
                    doneButton.setVisible(true);
                    pendingButton.setVisible(false);
                    cancelButton.setVisible(true);
                    
                    
                    doneButton.addActionListener(e -> {
                        JOptionPane.showMessageDialog(null, "Go to the vendor order page or admin to update the status!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    });
                    
                    cancelButton.addActionListener(e -> {
                        JOptionPane.showMessageDialog(null, "Go to the vendor order page or admin to update the status!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    });
                    
                    container.add(popUp);
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
    
    private String generateTimeKey(String dateTime, String category) {
        String[] date = dateTime.split("T");
        String[] dateComponents = date[0].split("-");
        
        if (dateComponents[0].length() < 3) {
            throw new IllegalArgumentException("Invalid date format: " + dateTime);
        }
        
        String year = dateComponents[0];
        String month = dateComponents[1];
        String day = dateComponents[2];
        
        String buttonKey = "";
        
        switch (category.toLowerCase()) {
            case "daily":
                buttonKey = String.format("%s-%s-%s", year, month, day);
                break;
            case "monthly":
                buttonKey = String.format("%s-%s", year, month);
                break;
            case "quarterly":
                // Convert month to integer and determine the quarter
                int monthInt = Integer.parseInt(month);
                String quarter = "Q" + ((monthInt - 1) / 3 + 1);
                buttonKey = String.format("%s %s", year, quarter);
                break;
            case "yearly":
                buttonKey = year;
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }
        return buttonKey;
    }
    
    private void closeMenuPanel(){
        Component[] componentList = menuPanel.getComponents();
        for(Component c : componentList){
            if(c instanceof OrderBlock){
                menuPanel.remove(c);
            }
        }
    }
    
    private void closeButtonInSubCategory(){
        Component[] componentList = subCategoryPanel.getComponents();
        for(Component c : componentList){
            if(c instanceof RoundedButton){
                subCategoryPanel.remove(c);
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

        categoryPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        subCategoryPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(200, 200, 255));
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        categoryPanel.setBackground(new java.awt.Color(200, 200, 255));
        categoryPanel.setMinimumSize(new java.awt.Dimension(10, 55));
        categoryPanel.setPreferredSize(new java.awt.Dimension(1000, 55));
        add(categoryPanel);

        jLabel1.setBackground(new java.awt.Color(39, 40, 56));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 75, 242));
        jLabel1.setText("Timeframe");
        add(jLabel1);

        subCategoryPanel.setBackground(new java.awt.Color(200, 200, 255));
        subCategoryPanel.setMinimumSize(new java.awt.Dimension(1000, 55));
        subCategoryPanel.setPreferredSize(new java.awt.Dimension(1000, 55));

        javax.swing.GroupLayout subCategoryPanelLayout = new javax.swing.GroupLayout(subCategoryPanel);
        subCategoryPanel.setLayout(subCategoryPanelLayout);
        subCategoryPanelLayout.setHorizontalGroup(
            subCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        subCategoryPanelLayout.setVerticalGroup(
            subCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        add(subCategoryPanel);

        jLabel2.setBackground(new java.awt.Color(39, 40, 56));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(140, 75, 242));
        jLabel2.setText("Customer Order History");
        add(jLabel2);

        menuPanel.setBackground(new java.awt.Color(200, 200, 255));
        menuPanel.setAutoscrolls(true);
        menuPanel.setMinimumSize(new java.awt.Dimension(1000, 590));
        menuPanel.setName(""); // NOI18N
        menuPanel.setPreferredSize(new java.awt.Dimension(1000, 590));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        add(menuPanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel subCategoryPanel;
    // End of variables declaration//GEN-END:variables
}
