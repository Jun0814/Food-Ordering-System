package Admin;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import method.ImageHandler;

public class VendorList extends javax.swing.JPanel {
    
    ImageHandler imageHandler = new ImageHandler();
    
    public VendorList() {
        initComponents();
        String filePath = "src\\main\\java\\repository\\vendor.txt";
        populateVendorPanel(filePath);
        setJScrollPane();
    }
    
    private String[][] readVendorData(String filePath) {
        List<String[]> vendorList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) { // Skip the first header row
                    skipHeader = false;
                    continue;
                }
                String[] runnerData = line.split(",");
                vendorList.add(runnerData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vendorList.toArray(new String[0][]);
    }
    
    private void populateVendorPanel(String filePath) {
        String[][] vendors = readVendorData(filePath);

        for (String[] vendor : vendors) {
            String vendorId = vendor[0];
            String name = vendor[1];
            String email = vendor[2];
            String phone = vendor[3];
            String stallName = vendor[5];
            String imagePath = vendor[7];

            VendorBlock vb = new VendorBlock();
            vb.setUsername(name);
            vb.setUserId(vendorId);
            vb.setEmail(email);
            vb.setPhone(phone);
            vb.setStallName(stallName);
            
            BufferedImage loadedImage = imageHandler.loadImage(imagePath);
            JLabel label = vb.getLabel();
            label.setBounds(0, 0, 150, 150);
            
            if (loadedImage != null) {
                imageHandler.displayImageOnLabel(loadedImage, label);
            } else {
                label.setText("No Image Available");
            }
            
            vendorPanel.add(vb);
        }

        setMenuPanelHeight();
        vendorPanel.revalidate();
        vendorPanel.repaint();

    }

    public void setMenuPanelHeight(){
       int userBlockCount = 0;
       int row;
       int vendorPanelHeight = 600;

       // Count the number of UserBlock components in the panel
       for (int i = 0; i < vendorPanel.getComponentCount(); i++) {
           if (vendorPanel.getComponent(i) instanceof JPanel) {
               userBlockCount++;
           }
       }

       row = userBlockCount; 
       if (row >= 3) {
           vendorPanelHeight = 600 + ((row - 3) * 250);
       } else {
           vendorPanelHeight = 600;
       }

       vendorPanel.setPreferredSize(new Dimension(1000, vendorPanelHeight));
    }

    public void setJScrollPane() {
        vendorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JScrollPane scrollPane = new JScrollPane(vendorPanel);
        scrollPane.setPreferredSize(new Dimension(1000,590));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.add(scrollPane);
        this.revalidate(); 
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        VendorLabel = new javax.swing.JLabel();
        addVendorButton = new method.RoundedButton();
        vendorPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(126, 127, 154));
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        jPanel1.setBackground(new java.awt.Color(126, 127, 154));

        VendorLabel.setBackground(new java.awt.Color(255, 255, 51));
        VendorLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        VendorLabel.setForeground(new java.awt.Color(255, 255, 51));
        VendorLabel.setText("Vendor");

        addVendorButton.setBackground(new java.awt.Color(40, 40, 56));
        addVendorButton.setForeground(new java.awt.Color(255, 255, 51));
        addVendorButton.setText("Add Vendor");
        addVendorButton.setAlignmentX(0.5F);
        addVendorButton.setBorderColor(new java.awt.Color(40, 40, 56));
        addVendorButton.setColor(new java.awt.Color(40, 40, 56));
        addVendorButton.setColorClick(new java.awt.Color(255, 255, 51));
        addVendorButton.setColorOver(new java.awt.Color(140, 75, 242));
        addVendorButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addVendorButton.setFontColor(new java.awt.Color(255, 255, 51));
        addVendorButton.setFontColorClick(new java.awt.Color(40, 40, 56));
        addVendorButton.setFontColorOver(new java.awt.Color(255, 255, 51));
        addVendorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addVendorButton.setRadius(30);
        addVendorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVendorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(749, Short.MAX_VALUE)
                .addComponent(addVendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(VendorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(addVendorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(VendorLabel)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        add(jPanel1);

        vendorPanel.setBackground(new java.awt.Color(126, 127, 154));
        vendorPanel.setAutoscrolls(true);
        vendorPanel.setMinimumSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout vendorPanelLayout = new javax.swing.GroupLayout(vendorPanel);
        vendorPanel.setLayout(vendorPanelLayout);
        vendorPanelLayout.setHorizontalGroup(
            vendorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        vendorPanelLayout.setVerticalGroup(
            vendorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        add(vendorPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void addVendorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVendorButtonActionPerformed
        // Create and configure the "Add Vendor" dialog
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle("Add Vendor");
        dialog.setSize(450, 600);
        dialog.setLocationRelativeTo(this);

        dialog.setContentPane(new AddVendor());

        dialog.setModal(true);
        dialog.setVisible(true);
    }//GEN-LAST:event_addVendorButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VendorLabel;
    private method.RoundedButton addVendorButton;
    private javax.swing.JPanel jPanel1;
    javax.swing.JPanel vendorPanel;
    // End of variables declaration//GEN-END:variables
}
