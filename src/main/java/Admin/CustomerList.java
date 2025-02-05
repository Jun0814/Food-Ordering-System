package Admin;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CustomerList extends JPanel {
    
    public CustomerList() {
        initComponents();
        String filePath = "src\\main\\java\\repository\\customer.txt";
        populateCustomerPanel(filePath);
        setJScrollPane();
    }
    
    private String[][] readCustomerData(String filePath) {
        List<String[]> customerList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) { // Skip the first header row
                    skipHeader = false;
                    continue;
                }
                String[] customerData = line.split(",");
                customerList.add(customerData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerList.toArray(new String[0][]);
    }
    
    private void populateCustomerPanel(String filePath) {
        String[][] customers = readCustomerData(filePath);

        for (String[] customer : customers) {
            String customerId = customer[0];
            String name = customer[1];
            String email = customer[2];
            String phone = customer[3];

            UserBlock ub = new UserBlock();
            ub.setUsername(name);
            ub.setUserId(customerId);
            ub.setEmail(email);
            ub.setPhone(phone);
            
            customerPanel.add(ub);
        }

        setMenuPanelHeight();
        customerPanel.revalidate();
        customerPanel.repaint();

    }

    public void setMenuPanelHeight(){
       int userBlockCount = 0;
       int row;
       int customerPanelHeight = 600;

       // Count the number of UserBlock components in the panel
       for (int i = 0; i < customerPanel.getComponentCount(); i++) {
           if (customerPanel.getComponent(i) instanceof JPanel) {
               userBlockCount++;
           }
       }

       row = userBlockCount; 
       if (row >= 3) {
           customerPanelHeight = 600 + ((row - 3) * 250);
       } else {
           customerPanelHeight = 600;
       }

       customerPanel.setPreferredSize(new Dimension(1000, customerPanelHeight));
    }

    public void setJScrollPane() {
        customerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JScrollPane scrollPane = new JScrollPane(customerPanel);
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
        CustomerLabel = new javax.swing.JLabel();
        addCustomerButton = new method.RoundedButton();
        customerPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(126, 127, 154));
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        jPanel1.setBackground(new java.awt.Color(126, 127, 154));

        CustomerLabel.setBackground(new java.awt.Color(255, 255, 51));
        CustomerLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        CustomerLabel.setForeground(new java.awt.Color(255, 255, 51));
        CustomerLabel.setText("Customer");

        addCustomerButton.setBackground(new java.awt.Color(40, 40, 56));
        addCustomerButton.setForeground(new java.awt.Color(255, 255, 51));
        addCustomerButton.setText("Add Customer");
        addCustomerButton.setAlignmentX(0.5F);
        addCustomerButton.setBorderColor(new java.awt.Color(40, 40, 56));
        addCustomerButton.setColor(new java.awt.Color(40, 40, 56));
        addCustomerButton.setColorClick(new java.awt.Color(255, 255, 51));
        addCustomerButton.setColorOver(new java.awt.Color(140, 75, 242));
        addCustomerButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addCustomerButton.setFontColor(new java.awt.Color(255, 255, 51));
        addCustomerButton.setFontColorClick(new java.awt.Color(40, 40, 56));
        addCustomerButton.setFontColorOver(new java.awt.Color(255, 255, 51));
        addCustomerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addCustomerButton.setRadius(30);
        addCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(749, Short.MAX_VALUE)
                .addComponent(addCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(CustomerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(addCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(CustomerLabel)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        add(jPanel1);

        customerPanel.setBackground(new java.awt.Color(126, 127, 154));
        customerPanel.setAutoscrolls(true);
        customerPanel.setMinimumSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        add(customerPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerButtonActionPerformed
        // Create and configure the "Add Vendor" dialog
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle("Add Customer");
        dialog.setSize(450, 470);
        dialog.setLocationRelativeTo(this);

        dialog.setContentPane(new AddCustomer());

        dialog.setModal(true);
        dialog.setVisible(true);
    }//GEN-LAST:event_addCustomerButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CustomerLabel;
    private method.RoundedButton addCustomerButton;
    javax.swing.JPanel customerPanel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
