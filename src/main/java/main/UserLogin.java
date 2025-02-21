/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import Admin.HomePage;
import customer.CustomerHome;
import customer.customer_backend;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import managefile.Admin;
import managefile.Customer;
import managefile.Data;
import managefile.Manager;
import managefile.Runner;
import managefile.Vendor;
import manager.managerMain;
import method.scaleImage;
import runner.RunnerMain;
import vendor.VendorMain;


/**
 *
 * @author TPY
 */
public class UserLogin extends javax.swing.JFrame {
    
    Data data = new Data();
    scaleImage scaleImage = new scaleImage();
    protected String role;
    protected int clickCount;
    
    /**
     * Creates new form UserLogin
     */
    
    public UserLogin(String role) {
        initComponents();
        this.role = role.toLowerCase();
        usernameTextField.setText("");
        passwordTextField.setText("");
        
        titleLabel.setText("LOGIN AS " + this.role.toUpperCase());

        this.setTitle("Main Menu");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null); 
        logoLabel.setIcon(scaleImage.processImage("src\\main\\java\\image_repository\\gastrogo.png", 250, 192));
        
        leftPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        
        leftPanel.add(logoLabel, gbc);
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public void run() {
        new UserLogin(role).setVisible(true);
    }
    

    public void validateRole(){
        Boolean isFilled = false;
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if(username == null || password == null){
            isFilled = false;
            JOptionPane.showMessageDialog(null,"Please fill in your information!");
        }else{
            isFilled = true;
        }
        
        if(isFilled == true){    
            switch(this.role) {
                case "vendor":
                    if(clickCount < 3){
                        Vendor vendor = new Vendor();
                        String vendorfilepath = vendor.getFilepath();
                        String vendorid = data.retrieveData(username, password, 0, vendorfilepath);
                        System.out.println(vendorid);
                        if(vendorid != null){ 
                            JOptionPane.showMessageDialog(null,"Login Successfully!");
                            this.dispose();
                            VendorMain vendorMain = new VendorMain(vendorid);
                            vendorMain.run();
                        }else{
                            JOptionPane.showMessageDialog(null,"Login Failed!\nYou have "+(3-clickCount)+ " attempts remaining.","Login Unsuccessful",JOptionPane.ERROR_MESSAGE);
                        }
                        clickCount++;
                    }else{
                        try {
                            JOptionPane.showMessageDialog(null, "Attempt limit exceeded. Please wait for 40 seconds!", "Attempt Limit", JOptionPane.ERROR_MESSAGE);
                            Thread.sleep(40000);
                            clickCount = 0;
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    break;
                case "manager": 
                    if(clickCount < 3){
                        Manager manager = new Manager();
                        String managerfilepath = manager.getFilepath();
                        String managerid = data.retrieveData(username, password, 0, managerfilepath);
                        if(managerid != null){
                            JOptionPane.showMessageDialog(null, "Login Successfully!");
                            this.dispose();
                            managerMain mainpage = new managerMain(managerid);
                            mainpage.run();
                        }else{
                            JOptionPane.showMessageDialog(null,"Login Failed!\nYou have "+(3-clickCount)+ " attempts remaining.","Login Unsuccessful",JOptionPane.ERROR_MESSAGE);
                        }
                        clickCount++;
                    }else{
                        try {
                            JOptionPane.showMessageDialog(null, "Attempt limit exceeded. Please wait for 40 seconds!", "Attempt Limit", JOptionPane.ERROR_MESSAGE);
                            Thread.sleep(40000);
                            clickCount = 0;
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    break;
                case "admin": 
                    if(clickCount < 3){
                        Admin admin = new Admin();
                        String adminfilepath = admin.getFilepath();
                        String adminid = data.retrieveData(username, password, 0, adminfilepath);
                        if(adminid != null){
                            JOptionPane.showMessageDialog(null, "Login Successfully!");
                            HomePage mainpage = new HomePage(adminid);
                            mainpage.run();
                            this.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Login Failed!\nYou have "+(3-clickCount)+ " attempts remaining.","Login Unsuccessful",JOptionPane.ERROR_MESSAGE);
                        }
                        clickCount++;
                    }else{
                        try {
                            JOptionPane.showMessageDialog(null, "Attempt limit exceeded. Please wait for 40 seconds!", "Attempt Limit", JOptionPane.ERROR_MESSAGE);
                            Thread.sleep(40000);
                            clickCount = 0;
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    break;
                case "runner": 
                    if(clickCount < 3){
                        Runner runner = new Runner();
                        String runnerfilepath = runner.getFilepath();
                        String runnerid = data.retrieveData(username, password, 0, runnerfilepath);
                        if(runnerid != null){
                            JOptionPane.showMessageDialog(null, "Login Successfully!");
                            RunnerMain mainpage = new RunnerMain(runnerid);
                            mainpage.run();
                            this.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Login Failed!\nYou have "+(3-clickCount)+ " attempts remaining.","Login Unsuccessful",JOptionPane.ERROR_MESSAGE);
                        }
                        clickCount++;
                    }else{
                        try {
                            JOptionPane.showMessageDialog(null, "Attempt limit exceeded. Please wait for 40 seconds!", "Attempt Limit", JOptionPane.ERROR_MESSAGE);
                            Thread.sleep(40000);
                            clickCount = 0;
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    break;
                case "customer":
                    if(clickCount < 3){
                        customer_backend backend = new customer_backend();
                        String customerid = backend.validateCredentials(username, password);
                        if(customerid != null){
                            JOptionPane.showMessageDialog(null,"Login Successfully!");
                            CustomerHome homepage = new CustomerHome(customerid);
                            homepage.run();
                            this.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Login Failed!\nYou have "+(3-clickCount)+ " attempts remaining.","Login Unsuccessful",JOptionPane.ERROR_MESSAGE);
                        }
                        clickCount++;
                    }else{
                        try {
                            JOptionPane.showMessageDialog(null, "Attempt limit exceeded. Please wait for 40 seconds!", "Attempt Limit", JOptionPane.ERROR_MESSAGE);
                            Thread.sleep(40000);
                            clickCount = 0;
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Role not recognized!");            
            }
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            MainMenu main = new MainMenu();
            main.run();
            this.dispose();
        }else{
            validateRole();
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
        java.awt.GridBagConstraints gridBagConstraints;

        bgBackground = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        usernameTextField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        backButton = new method.RoundedButton();
        loginButton = new method.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        bgBackground.setPreferredSize(new java.awt.Dimension(1000, 568));
        bgBackground.setLayout(new java.awt.BorderLayout());

        leftPanel.setBackground(new java.awt.Color(39, 40, 56));
        leftPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        leftPanel.setMinimumSize(new java.awt.Dimension(500, 568));
        leftPanel.setPreferredSize(new java.awt.Dimension(500, 568));
        leftPanel.setLayout(new java.awt.GridBagLayout());

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setAlignmentX(0.5F);
        logoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 500;
        gridBagConstraints.ipady = 568;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        leftPanel.add(logoLabel, gridBagConstraints);

        bgBackground.add(leftPanel, java.awt.BorderLayout.WEST);

        rightPanel.setBackground(new java.awt.Color(126, 127, 154));
        rightPanel.setMinimumSize(new java.awt.Dimension(500, 568));
        rightPanel.setPreferredSize(new java.awt.Dimension(500, 568));
        rightPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 329;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 60, 0, 60);
        rightPanel.add(usernameTextField, gridBagConstraints);

        usernameTextField.setText("xuanhanchin@gmail.com");
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(243, 222, 138));
        usernameLabel.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 58, 0, 0);
        rightPanel.add(usernameLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 329;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 60, 0, 60);
        rightPanel.add(passwordTextField, gridBagConstraints);

        passwordTextField.setText("abcd");
        passwordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextFieldActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(243, 222, 138));
        passwordLabel.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 58, 0, 0);
        rightPanel.add(passwordLabel, gridBagConstraints);

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(243, 222, 138));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("LOGIN AS XXXXXX");
        titleLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(150, 58, 0, 49);
        rightPanel.add(titleLabel, gridBagConstraints);
        titleLabel.getAccessibleContext().setAccessibleDescription("");

        backButton.setForeground(new java.awt.Color(60, 63, 65));
        backButton.setText("BACK");
        backButton.setBorderColor(new java.awt.Color(126, 127, 154));
        backButton.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        backButton.setRadius(30);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 103;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 43, 162, 49);
        rightPanel.add(backButton, gridBagConstraints);

        loginButton.setForeground(new java.awt.Color(60, 63, 65));
        loginButton.setText("LOGIN");
        loginButton.setBorderColor(new java.awt.Color(126, 127, 154));
        loginButton.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        loginButton.setRadius(30);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 99;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 58, 162, 0);
        rightPanel.add(loginButton, gridBagConstraints);

        bgBackground.add(rightPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(bgBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(bgBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_backButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        actionPerformed(evt);
    }//GEN-LAST:event_loginButtonActionPerformed

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void passwordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private method.RoundedButton backButton;
    private javax.swing.JPanel bgBackground;
    private javax.swing.JPanel leftPanel;
    private method.RoundedButton loginButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
