/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vendor;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import managefile.Data;
import method.scaleImage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author TPY
 */
public class VendorRevenue extends javax.swing.JPanel {
    
    Data data = new Data();
    scaleImage scaleImage = new scaleImage();
    private String userId;
    private String[][] orderData, reviewData;

    /**
     * Creates new form VendorStore
     */
    public VendorRevenue(String userId) {
        initComponents();
        this.userId = userId;
        orderData = data.reverse2DArray(data.retrieveDataAsArray(3, userId, "src\\main\\java\\repository\\order.txt"));
        String imagePath = data.retrieveData(userId, 7, "src\\main\\java\\repository\\vendor.txt");
        reviewData = data.retrieveDataAsArray(1, userId, "src\\main\\java\\repository\\orderreview.txt");
        
        orderImage.setIcon(scaleImage.processImage("src\\main\\java\\image_repository\\order-food.png", 110, 110));
        revenueImage.setIcon(scaleImage.processImage("src\\main\\java\\image_repository\\money.png", 110, 110));
        growthImage.setIcon(scaleImage.processImage("src\\main\\java\\image_repository\\interest-rate.png", 110, 110));
        logoImage.setIcon(scaleImage.processImage(imagePath, 150, 150));
        
        intiComboBoxes();
        intiStatistics();
        intiChart();

        monthComboBox.addActionListener(e ->  {
            String selectedMonth = (String) monthComboBox.getSelectedItem();
            monthComboBox.setSelectedItem(selectedMonth);
            reinitializePanel();
        });

        yearComboBox.addActionListener(e -> {
            String selectedYear = (String) yearComboBox.getSelectedItem();
            yearComboBox.setSelectedItem(selectedYear);
            reinitializePanel();
        });
    }
    
    private void reinitializePanel(){
        ratingLabel.setText("");
        growthRate.setText("");
        revenueNumber.setText("");
        orderNumber.setText("");
        
        intiStatistics();
        intiChart();
        
        if(ratingLabel.getText().equalsIgnoreCase("")){
            ratingLabel.setText("0.0/5.0");
        }
    }
    
    private void intiComboBoxes() {
        Set<String> uniqueMonths = new HashSet<>();
        Set<String> uniqueYears = new HashSet<>();

        for (String[] orderDatas : orderData) {
            try {
                String vendorId = orderDatas.length > 3 ? orderDatas[3].trim() : "";
                if (vendorId.equalsIgnoreCase(userId)) {
                    String dateTime = orderDatas.length > 7 ? orderDatas[7].trim() : "";
                    String[] date = dateTime.split("T");
                    if (date.length > 0) { 
                        String[] dateComponents = date[0].split("-");
                        if (dateComponents.length >= 2) {
                            String year = dateComponents[0];
                            String month = dateComponents[1];

                            uniqueYears.add(year);
                            uniqueMonths.add(month);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        monthComboBox.removeAllItems();
        yearComboBox.removeAllItems();

        uniqueMonths.stream().sorted().forEach(monthComboBox::addItem);
        uniqueYears.stream().sorted().forEach(yearComboBox::addItem);
    }
    
    private void intiStatistics(){
        int order = 0;
        double revenue = 0;
        String selectedYear = yearComboBox.getSelectedItem() != null ? yearComboBox.getSelectedItem().toString() : "";
        String selectedMonth = monthComboBox.getSelectedItem() != null ? monthComboBox.getSelectedItem().toString() : "";
        String year = "", month = "";
        double growthRateValue = 0.0;
        double totalRating = 0.0, numberOfRating = 0.0, ratingCollection = 0.0, previousMonthRevenue = 0.0, currentMonthRevenue = 0.0;
        List<Double> revenueList = new ArrayList<>();
        
        for (String[] orderDatas : orderData) {
            try {
                String vendorId = orderDatas.length > 3 ? orderDatas[3].trim() : "";
                String orderStatus = orderDatas.length > 9 ? orderDatas[9].trim() : "";
                
                if (vendorId.equalsIgnoreCase(userId)) {
                    String dateTime = orderDatas.length > 7 ? orderDatas[7].trim() : "";
                    String[] date = dateTime.split("T");
                    if (date.length > 0) {
                        String[] dateComponents = date[0].split("-");
                        if (dateComponents.length >= 2) {
                            year = dateComponents[0];
                            month = dateComponents[1];
                            
                            if(year.equalsIgnoreCase(selectedYear) && month.equalsIgnoreCase(selectedMonth)){
                                if(orderStatus.equalsIgnoreCase("Completed") || orderStatus.equalsIgnoreCase("Done")){
                                    order ++ ;
                                    String totalOrder = String.valueOf(order++);
                                    orderNumber.setText(totalOrder);
                                    
                                    double orderTotalAmount = orderDatas.length > 8 ? Math.round(Double.parseDouble(orderDatas[8].trim()) * 100.0) / 100.0 : 0.0;
                                    orderTotalAmount = Double.parseDouble(String.format("%.2f", orderTotalAmount));
                                    revenue += orderTotalAmount; 
                                    String formattedOrderTotalAmount = String.format("%.2f", revenue);
                                    revenueNumber.setText("RM" + formattedOrderTotalAmount);
                                }
                            }
                            
                            if(year.equalsIgnoreCase(selectedYear)){
                                if(orderStatus.equalsIgnoreCase("Completed") || orderStatus.equalsIgnoreCase("Done")){                                    
                                    double orderTotalAmount = orderDatas.length > 8 ? Math.round(Double.parseDouble(orderDatas[8].trim()) * 100.0) / 100.0 : 0.0;
                                    orderTotalAmount = Double.parseDouble(String.format("%.2f", orderTotalAmount));
                                    revenue += orderTotalAmount; 
                                    revenueList.add(revenue);
                                }
                            }
                        }
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
        try {
            if (revenueList.size() >= 2) {
                previousMonthRevenue = revenueList.get(revenueList.size() - 1);
                currentMonthRevenue = revenueList.get(revenueList.size() - 2);

                if (previousMonthRevenue > 0) {
                    growthRateValue = ((currentMonthRevenue - previousMonthRevenue) / previousMonthRevenue) * 100.0;
                } else {
                    growthRateValue = 0.0;
                }
            } else {
                growthRateValue = 0.0;
            }

            String formattedGrowthRate = String.format("%.1f", growthRateValue) + "%";
            growthRate.setText(formattedGrowthRate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (String[] reviewDatas : reviewData) {
            try {
                String vendorId = reviewDatas.length > 1 ? reviewDatas[1].trim() : "";
                if(year.equals(selectedYear) && month.equals(selectedMonth)){
                    if (vendorId.equalsIgnoreCase(userId)) {
                        String ratingData = reviewDatas.length > 2 ? reviewDatas[2] : "null";
                        if(ratingData.equalsIgnoreCase("null")){
                            continue;
                        }else{
                            double rating = reviewDatas.length > 2 ? Math.round(Double.parseDouble(reviewDatas[2].trim()) * 1.0) / 1.0 : 0.0;
                            numberOfRating ++;
                            ratingCollection += rating;
                            totalRating = (ratingCollection/numberOfRating);
                            String formattedTotalRating = String.format("%.1f", totalRating);
                            ratingLabel.setText(formattedTotalRating + "/5.0");
                        }
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void intiChart() {
        closeChart();
        
        String selectedYear = yearComboBox.getSelectedItem() != null ? yearComboBox.getSelectedItem().toString() : "";
        Map<String, Double> monthlyRevenue = new HashMap<>();
        String year = "", month = "";

        for (String[] orderDatas : orderData) {
            try {
                String vendorId = orderDatas.length > 3 ? orderDatas[3].trim() : "";
                String orderStatus = orderDatas.length > 9 ? orderDatas[9].trim() : "";

                if (vendorId.equalsIgnoreCase(userId)) {
                    String dateTime = orderDatas.length > 7 ? orderDatas[7].trim() : "";
                    String[] date = dateTime.split("T");
                    if (date.length > 0) {
                        String[] dateComponents = date[0].split("-");
                        if (dateComponents.length >= 2) {
                            year = dateComponents[0];
                            month = dateComponents[1];
                        }
                    }

                    if (year.equals(selectedYear)) {
                        if (orderStatus.equalsIgnoreCase("Completed") || orderStatus.equalsIgnoreCase("Done")) {
                            String orderAmount = orderDatas.length > 8? orderDatas[8] : "null";
                            double orderTotalAmount = 0;
                            if(orderAmount != "null"){
                                orderTotalAmount = orderDatas.length > 8
                                    ? Math.round(Double.parseDouble(orderDatas[8].trim()) * 100.0) / 100.0
                                    : 0.0;
                                orderTotalAmount = Double.parseDouble(String.format("%.2f", orderTotalAmount));
                            }else{
                                continue;
                            }
                            monthlyRevenue.put(month, monthlyRevenue.getOrDefault(month, 0.0) + orderTotalAmount);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : monthlyRevenue.entrySet()) {
            String monthName = getMonthName(entry.getKey());
            dataset.addValue(entry.getValue(), "Revenue", monthName);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Monthly Revenue for " + selectedYear, // Chart title
            "Month",                              // X-axis Label
            "Revenue (RM)",                      // Y-axis Label
            dataset                               // Dataset
        );
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(450, 450));
        barChart.setBackgroundPaint(new Color(200,200,255)); 
        
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE); // Background color of the plot
        plot.setDomainGridlinePaint(Color.GRAY); // Vertical gridlines
        plot.setRangeGridlinePaint(Color.GRAY);  // Horizontal gridlines

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("Arial", Font.PLAIN, 14)); // Font for axis label
        domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 12)); // Font for tick labels
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotate labels

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("Arial", Font.PLAIN, 14)); // Font for axis label
        rangeAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 12)); // Font for tick labels
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); // Use integer ticks

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(79, 129, 189));
        renderer.setDrawBarOutline(false);
        
        menuPanel.setLayout(new FlowLayout());
        menuPanel.add(chartPanel);
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    private String getMonthName(String month) {
        switch (month) {
            case "01": return "January";
            case "02": return "February";
            case "03": return "March";
            case "04": return "April";
            case "05": return "May";
            case "06": return "June";
            case "07": return "July";
            case "08": return "August";
            case "09": return "September";
            case "10": return "October";
            case "11": return "November";
            case "12": return "December";
            default: return "Unknown";
        }
    }
    
    private void closeChart(){
        menuPanel.removeAll();
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel1 = new method.RoundedPanel();
        orderImage = new javax.swing.JLabel();
        orderTitle1 = new javax.swing.JLabel();
        orderNumber = new javax.swing.JLabel();
        roundedPanel2 = new method.RoundedPanel();
        yearComboBox = new javax.swing.JComboBox<>();
        monthComboBox = new javax.swing.JComboBox<>();
        revenueNumber = new javax.swing.JLabel();
        revenueTitle = new javax.swing.JLabel();
        revenueImage = new javax.swing.JLabel();
        roundedPanel3 = new method.RoundedPanel();
        growthImage = new javax.swing.JLabel();
        growthTitle = new javax.swing.JLabel();
        growthRate = new javax.swing.JLabel();
        roundedPanel4 = new method.RoundedPanel();
        logoImage = new javax.swing.JLabel();
        ratingTitle = new javax.swing.JLabel();
        ratingLabel = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(200, 200, 255));
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel1.setBackground(new java.awt.Color(200, 200, 255));
        roundedPanel1.setForeground(new java.awt.Color(200, 200, 255));
        roundedPanel1.setBorderColor(new java.awt.Color(200, 200, 255));
        roundedPanel1.setPreferredSize(new java.awt.Dimension(320, 150));
        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        orderImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roundedPanel1.add(orderImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 130));

        orderTitle1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        orderTitle1.setForeground(new java.awt.Color(140, 75, 242));
        orderTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orderTitle1.setText("Total Order Done");
        roundedPanel1.add(orderTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        orderNumber.setFont(new java.awt.Font("Segoe UI Semibold", 0, 60)); // NOI18N
        orderNumber.setForeground(new java.awt.Color(140, 75, 242));
        orderNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orderNumber.setText("8");
        roundedPanel1.add(orderNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 150, 90));

        add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 29, -1, -1));

        roundedPanel2.setBackground(new java.awt.Color(200, 200, 255));
        roundedPanel2.setForeground(new java.awt.Color(200, 200, 255));
        roundedPanel2.setBorderColor(new java.awt.Color(200, 200, 255));
        roundedPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundedPanel2.add(yearComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 150, 60));

        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundedPanel2.add(monthComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 190, 60));

        revenueNumber.setFont(new java.awt.Font("Segoe UI Semibold", 0, 35)); // NOI18N
        revenueNumber.setForeground(new java.awt.Color(140, 75, 242));
        revenueNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revenueNumber.setText("8");
        roundedPanel2.add(revenueNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 200, 80));

        revenueTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        revenueTitle.setForeground(new java.awt.Color(140, 75, 242));
        revenueTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revenueTitle.setText("Total Revenue");
        roundedPanel2.add(revenueTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 130, -1));

        revenueImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roundedPanel2.add(revenueImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 130, 118));

        add(roundedPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 400, 312));

        roundedPanel3.setBackground(new java.awt.Color(200, 200, 255));
        roundedPanel3.setForeground(new java.awt.Color(200, 200, 255));
        roundedPanel3.setBorderColor(new java.awt.Color(200, 200, 255));
        roundedPanel3.setPreferredSize(new java.awt.Dimension(320, 150));
        roundedPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        growthImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roundedPanel3.add(growthImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 10, 130, 130));

        growthTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        growthTitle.setForeground(new java.awt.Color(140, 75, 242));
        growthTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        growthTitle.setText("Growth Rate");
        roundedPanel3.add(growthTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 130, -1));

        growthRate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 35)); // NOI18N
        growthRate.setForeground(new java.awt.Color(140, 75, 242));
        growthRate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        growthRate.setText("8");
        roundedPanel3.add(growthRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 130, 70));

        add(roundedPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 191, -1, -1));

        roundedPanel4.setBackground(new java.awt.Color(200, 200, 255));
        roundedPanel4.setForeground(new java.awt.Color(200, 200, 255));
        roundedPanel4.setBorderColor(new java.awt.Color(200, 200, 255));
        roundedPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roundedPanel4.add(logoImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 170));

        ratingTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ratingTitle.setForeground(new java.awt.Color(140, 75, 242));
        ratingTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ratingTitle.setText("Rating");
        roundedPanel4.add(ratingTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 130, -1));

        ratingLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 35)); // NOI18N
        ratingLabel.setForeground(new java.awt.Color(140, 75, 242));
        ratingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ratingLabel.setText("8");
        roundedPanel4.add(ratingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 130, 70));

        add(roundedPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 29, 190, 310));

        menuPanel.setBackground(new java.awt.Color(200, 200, 255));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 1000, 450));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel growthImage;
    private javax.swing.JLabel growthRate;
    private javax.swing.JLabel growthTitle;
    private javax.swing.JLabel logoImage;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JComboBox<String> monthComboBox;
    private javax.swing.JLabel orderImage;
    private javax.swing.JLabel orderNumber;
    private javax.swing.JLabel orderTitle1;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JLabel ratingTitle;
    private javax.swing.JLabel revenueImage;
    private javax.swing.JLabel revenueNumber;
    private javax.swing.JLabel revenueTitle;
    private method.RoundedPanel roundedPanel1;
    private method.RoundedPanel roundedPanel2;
    private method.RoundedPanel roundedPanel3;
    private method.RoundedPanel roundedPanel4;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables
}
