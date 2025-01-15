/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manager;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import managefile.Data;
import managefile.Vendor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Asus
 */
public class vendorRevenue extends javax.swing.JFrame {
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
    /**
     * Creates new form vendorRevenue
     */
    public vendorRevenue(String vendorId) {
        initComponents();
        this.vendorId = vendorId;
        this.setSize(1000,500);
        String filepath = vendor.getFilepath();
        String[][] vendorDetails = data.retrieveDataAsArray(0,vendorId,filepath);
        for (String[] vendorInfo : vendorDetails) {
            this.vendorName = vendorInfo[1];
            this.vendorEmail = vendorInfo[2];
            this.vendorPhone = vendorInfo[3];
            this.stallName = vendorInfo[5];
            this.stallType = vendorInfo[6];
            this.imagePath = vendorInfo[7];
            this.vendorStatus = vendorInfo[8];
        }
        stallNameLabel.setText(stallName);
        vendorNameLabel.setText(vendorName);
        vendorEmailLabel.setText(vendorEmail);
        vendorPhoneNumberLabel.setText(vendorPhone);
        stallTypeLabel.setText(stallType);
        statusLabel.setText(vendorStatus);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        Map<String, Double> yearlyTotalRevenue = backend.getYearlyRevenue(vendorId);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Map.Entry<String, Double> entry : yearlyTotalRevenue.entrySet()){
            dataset.addValue(entry.getValue(), "Revenue", entry.getKey());
        }
        System.out.println(dataset);
        // Create chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Revenue Chart (Vendor)",   // Chart title
                "Year",            // X-axis Label
                "Amount ($)",      // Y-axis Label
                dataset
        );

        // Display the chart in the JPanel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(380, 350));
        
        chartPanel.addChartMouseListener(new ChartMouseListener(){
            @Override
            public void chartMouseClicked(ChartMouseEvent e){
                if(e.getEntity() != null){
                    String clickedYear = e.getEntity().getToolTipText();
                    String year = clickedYear.substring(clickedYear.indexOf(",") + 2, clickedYear.indexOf(")"));
                    showDailySalesChart(year);
                }
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {
                //chill
            }
        });
        
        yearlyRevenueChartPanel.removeAll();          // Remove any existing components
        yearlyRevenueChartPanel.setLayout(new BorderLayout()); // Ensure proper layout for the chart
        yearlyRevenueChartPanel.add(chartPanel, BorderLayout.CENTER); // Add the ChartPanel
        yearlyRevenueChartPanel.revalidate();         // Revalidate the panel
        yearlyRevenueChartPanel.repaint();           // Repaint to show the chart
    }
    
    private void showDailySalesChart(String year){
        System.out.println(year);
        Map<LocalDate, Double> dailySales = backend.getDailySalesForYear(year, vendorId);
        System.out.println(dailySales);
        DefaultCategoryDataset dailyDataset = new DefaultCategoryDataset();
        for (Map.Entry<LocalDate, Double> entry : dailySales.entrySet()) {
            dailyDataset.addValue(entry.getValue(), "Daily Sales", entry.getKey());
        }

        // Create a new bar chart for daily sales
        JFreeChart dailyChart = ChartFactory.createBarChart(
                "Daily Sales for " + year,   // Chart title
                "Date",                      // X-axis Label
                "Amount ($)",                // Y-axis Label
                dailyDataset
        );
        
        SwingUtilities.invokeLater(() -> {
            JFrame dailySalesFrame = new JFrame("Daily Sales - " + year);
            dailySalesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dailySalesFrame.add(new ChartPanel(dailyChart));
            dailySalesFrame.pack();
            dailySalesFrame.setLocationRelativeTo(null);
            dailySalesFrame.setVisible(true);
            dailySalesFrame.setSize(1000,600);
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
        vendorImagePanel = new javax.swing.JPanel();
        vendorDetailsPanel = new javax.swing.JPanel();
        stallNameLabel = new javax.swing.JLabel();
        vendorNameLabel = new javax.swing.JLabel();
        vendorEmailLabel = new javax.swing.JLabel();
        vendorPhoneNumberLabel = new javax.swing.JLabel();
        stallTypeLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        yearlyTotalRevenuePanel = new javax.swing.JPanel();
        yearlyTotalRevenueLabel = new javax.swing.JLabel();
        yearlyRevenueChartPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 500));

        vendorImagePanel.setBackground(java.awt.Color.black);

        javax.swing.GroupLayout vendorImagePanelLayout = new javax.swing.GroupLayout(vendorImagePanel);
        vendorImagePanel.setLayout(vendorImagePanelLayout);
        vendorImagePanelLayout.setHorizontalGroup(
            vendorImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        vendorImagePanelLayout.setVerticalGroup(
            vendorImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        stallNameLabel.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        stallNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stallNameLabel.setText("Vendor Stall Name");

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

        javax.swing.GroupLayout vendorDetailsPanelLayout = new javax.swing.GroupLayout(vendorDetailsPanel);
        vendorDetailsPanel.setLayout(vendorDetailsPanelLayout);
        vendorDetailsPanelLayout.setHorizontalGroup(
            vendorDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stallNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(stallTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vendorPhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vendorEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vendorNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        vendorDetailsPanelLayout.setVerticalGroup(
            vendorDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendorDetailsPanelLayout.createSequentialGroup()
                .addComponent(stallNameLabel)
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("Sales");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(vendorImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(vendorDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vendorImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vendorDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        yearlyTotalRevenueLabel.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        yearlyTotalRevenueLabel.setText("Total Revenue (Yearly)");

        javax.swing.GroupLayout yearlyTotalRevenuePanelLayout = new javax.swing.GroupLayout(yearlyTotalRevenuePanel);
        yearlyTotalRevenuePanel.setLayout(yearlyTotalRevenuePanelLayout);
        yearlyTotalRevenuePanelLayout.setHorizontalGroup(
            yearlyTotalRevenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yearlyTotalRevenuePanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(yearlyTotalRevenueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        yearlyTotalRevenuePanelLayout.setVerticalGroup(
            yearlyTotalRevenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yearlyTotalRevenuePanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(yearlyTotalRevenueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout yearlyRevenueChartPanelLayout = new javax.swing.GroupLayout(yearlyRevenueChartPanel);
        yearlyRevenueChartPanel.setLayout(yearlyRevenueChartPanelLayout);
        yearlyRevenueChartPanelLayout.setHorizontalGroup(
            yearlyRevenueChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        yearlyRevenueChartPanelLayout.setVerticalGroup(
            yearlyRevenueChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(yearlyTotalRevenuePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(yearlyRevenueChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(yearlyTotalRevenuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yearlyRevenueChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    public void run() {
        new vendorRevenue(vendorId).setVisible(true);
    }
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel stallNameLabel;
    private javax.swing.JLabel stallTypeLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel vendorDetailsPanel;
    private javax.swing.JLabel vendorEmailLabel;
    private javax.swing.JPanel vendorImagePanel;
    private javax.swing.JLabel vendorNameLabel;
    private javax.swing.JLabel vendorPhoneNumberLabel;
    private javax.swing.JPanel yearlyRevenueChartPanel;
    private javax.swing.JLabel yearlyTotalRevenueLabel;
    private javax.swing.JPanel yearlyTotalRevenuePanel;
    // End of variables declaration//GEN-END:variables
}
