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

public class RunnerList extends JPanel {
    
    public RunnerList() {
        initComponents();
        String filePath = "src\\main\\java\\repository\\runner.txt";
        populateRunnerPanel(filePath);
        setJScrollPane();
    }
    
    private String[][] readRunnerData(String filePath) {
        List<String[]> runnerList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                String[] runnerData = line.split(",");
                runnerList.add(runnerData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return runnerList.toArray(new String[0][]);
    }
    
    private void populateRunnerPanel(String filePath) {
        String[][] runners = readRunnerData(filePath);

        for (String[] runner : runners) {
            String runnerId = runner[0];
            String name = runner[1];
            String email = runner[2];
            String phone = runner[3];

            RunnerBlock rb = new RunnerBlock();
            rb.setUsername(name);
            rb.setUserId(runnerId);
            rb.setEmail(email);
            rb.setPhone(phone);
            
            runnerPanel.add(rb);
        }

        setMenuPanelHeight();
        runnerPanel.revalidate();
        runnerPanel.repaint();

    }

    public void setMenuPanelHeight(){
       int userBlockCount = 0;
       int row;
       int runnerPanelHeight = 600;

       // Count the number of UserBlock components in the panel
       for (int i = 0; i < runnerPanel.getComponentCount(); i++) {
           if (runnerPanel.getComponent(i) instanceof JPanel) {
               userBlockCount++;
           }
       }

       row = userBlockCount; 
       if (row >= 3) {
           runnerPanelHeight = 600 + ((row - 3) * 250);
       } else {
           runnerPanelHeight = 600;
       }

       runnerPanel.setPreferredSize(new Dimension(1000, runnerPanelHeight));
    }

    public void setJScrollPane() {
        runnerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JScrollPane scrollPane = new JScrollPane(runnerPanel);
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
        RunnerLabel = new javax.swing.JLabel();
        addRunnerButton = new method.RoundedButton();
        runnerPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(126, 127, 154));
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setPreferredSize(new java.awt.Dimension(1000, 800));

        jPanel1.setBackground(new java.awt.Color(126, 127, 154));

        RunnerLabel.setBackground(new java.awt.Color(255, 255, 51));
        RunnerLabel.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        RunnerLabel.setForeground(new java.awt.Color(255, 255, 51));
        RunnerLabel.setText("Runner");

        addRunnerButton.setBackground(new java.awt.Color(40, 40, 56));
        addRunnerButton.setForeground(new java.awt.Color(255, 255, 51));
        addRunnerButton.setText("Add Runner");
        addRunnerButton.setAlignmentX(0.5F);
        addRunnerButton.setBorderColor(new java.awt.Color(40, 40, 56));
        addRunnerButton.setColor(new java.awt.Color(40, 40, 56));
        addRunnerButton.setColorClick(new java.awt.Color(255, 255, 51));
        addRunnerButton.setColorOver(new java.awt.Color(140, 75, 242));
        addRunnerButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addRunnerButton.setFontColor(new java.awt.Color(255, 255, 51));
        addRunnerButton.setFontColorClick(new java.awt.Color(40, 40, 56));
        addRunnerButton.setFontColorOver(new java.awt.Color(255, 255, 51));
        addRunnerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addRunnerButton.setRadius(30);
        addRunnerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRunnerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(749, Short.MAX_VALUE)
                .addComponent(addRunnerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(RunnerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(addRunnerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RunnerLabel)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        add(jPanel1);

        runnerPanel.setBackground(new java.awt.Color(126, 127, 154));
        runnerPanel.setAutoscrolls(true);
        runnerPanel.setMinimumSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout runnerPanelLayout = new javax.swing.GroupLayout(runnerPanel);
        runnerPanel.setLayout(runnerPanelLayout);
        runnerPanelLayout.setHorizontalGroup(
            runnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        runnerPanelLayout.setVerticalGroup(
            runnerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        add(runnerPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void addRunnerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRunnerButtonActionPerformed
        // Create and configure the "Add Vendor" dialog
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle("Add Runner");
        dialog.setSize(450, 470);
        dialog.setLocationRelativeTo(this);

        dialog.setContentPane(new AddRunner());

        dialog.setModal(true);
        dialog.setVisible(true);
    }//GEN-LAST:event_addRunnerButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RunnerLabel;
    private method.RoundedButton addRunnerButton;
    private javax.swing.JPanel jPanel1;
    javax.swing.JPanel runnerPanel;
    // End of variables declaration//GEN-END:variables
}
