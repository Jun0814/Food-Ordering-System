/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vendor;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.JLabel;
import javax.swing.JPanel;
import method.RoundedButton;

/**
 *
 * @author TPY
 */
public class OrderBlock extends JPanel {

    private String orderStatus, orderId, orderType, typeDetail, buttonText;
    private LocalDateTime dateTime;
    private LocalDate date;
    private LocalTime time;
    private Color edgeColor, backgroundColor, themeColor;
    
    /**
     * Creates new form foodContainer
     */
    public OrderBlock() {
        initComponents();
    }
    
    public String getOrderStatus(){
        return orderStatus;
    }
    
    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
        if (statusLabel != null) { 
            statusLabel.setText(orderStatus != null ? "Status: " + orderStatus : "Status: ");
        }
    }
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
        if (orderIdLabel != null) { 
            orderIdLabel.setText(orderId != null ? "Order Id: " + orderId : "Order ID: ");
        }
    }
    
    public String getOrderType() {
        return orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
        if (orderTypeLabel != null) { 
            orderTypeLabel.setText(orderType != null ? "Order Type: " + orderType : "Order Type: ");
        }
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;

        date = dateTime.toLocalDate();
        this.setDate(date);
        
        if (dateLabel != null) {
            dateLabel.setText(date != null ? "Date: " + this.getDate() : "Date: ");
        }

        time = dateTime.toLocalTime();
        this.setTime(time);
        if (timeLabel != null) {
            timeLabel.setText(time != null ? "Time: " + this.getTime() : "Time: ");
        }
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    public String getTypeDetails() {
        return typeDetail;
    }
    
    public void setTypeDetails(String typeDetail) {
        this.typeDetail = typeDetail;
        if (typeDetail != null) { 
            detailLabel.setText(typeDetail != null ? "Details: " + typeDetail : "Details: ");
        }
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        if(checkRoundedButton.getText() == "Check"){
            checkRoundedButton.setText(buttonText != null ? buttonText : "Review");
        }
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
        if (roundedPanel.getBackground() != null) {
            roundedPanel.setBackground(edgeColor != null ? edgeColor : Color.WHITE);
        }
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        if(roundedPanel.getBackgroundColor() != new Color(248,248,248)){
            roundedPanel.setBackgroundColor(backgroundColor != null ? backgroundColor : Color.WHITE);
        }
    }

    public Color getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(Color themeColor) {
        this.themeColor = themeColor;
        if (statusLabel != null && checkRoundedButton != null) {
            statusLabel.setForeground(themeColor != null ? themeColor : Color.BLACK);
            checkRoundedButton.setColor(themeColor != null ? themeColor : Color.BLUE);
            checkRoundedButton.setBorderColor(backgroundColor != null ? backgroundColor : Color.WHITE);
        }
    }
    
    public RoundedButton getCheckButton(){
        return checkRoundedButton;
    }
    
    public RoundedButton getAcceptButton(){
        return acceptRoundedButton;
    }
    
    public RoundedButton getRejectButton(){
        return rejectRoundedButton;
    }
    
    public RoundedButton getDoneButton(){
        return doneRoundedButton;
    }
    
    public RoundedButton getCancelButton(){
        return cancelRoundedButton;
    }
    
    public JLabel getLabel(){
        return imageLabel;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel = new method.RoundedPanel();
        imageLabel = new javax.swing.JLabel();
        orderIdLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        checkRoundedButton = new method.RoundedButton();
        orderTypeLabel = new javax.swing.JLabel();
        detailLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        rejectRoundedButton = new method.RoundedButton();
        doneRoundedButton = new method.RoundedButton();
        acceptRoundedButton = new method.RoundedButton();
        cancelRoundedButton = new method.RoundedButton();

        setMinimumSize(new java.awt.Dimension(930, 200));
        setPreferredSize(new java.awt.Dimension(930, 200));

        roundedPanel.setMinimumSize(new java.awt.Dimension(930, 200));
        roundedPanel.setPreferredSize(new java.awt.Dimension(930, 200));
        roundedPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageLabel.setForeground(new java.awt.Color(40, 40, 56));
        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setMaximumSize(new java.awt.Dimension(1438, 1125));
        imageLabel.setMinimumSize(new java.awt.Dimension(240, 200));
        imageLabel.setPreferredSize(new java.awt.Dimension(240, 200));
        roundedPanel.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 180));

        orderIdLabel.setBackground(new java.awt.Color(40, 40, 56));
        orderIdLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        orderIdLabel.setForeground(new java.awt.Color(40, 40, 56));
        orderIdLabel.setText("Order ID:");
        roundedPanel.add(orderIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 420, 30));

        statusLabel.setBackground(new java.awt.Color(40, 40, 56));
        statusLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(140, 75, 242));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusLabel.setText("Status:");
        statusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        statusLabel.setMinimumSize(new java.awt.Dimension(230, 32));
        statusLabel.setPreferredSize(new java.awt.Dimension(230, 32));
        roundedPanel.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 420, 30));

        checkRoundedButton.setBackground(new java.awt.Color(140, 75, 242));
        checkRoundedButton.setForeground(new java.awt.Color(248, 248, 248));
        checkRoundedButton.setText("Check");
        checkRoundedButton.setBorderColor(new java.awt.Color(248, 248, 248));
        checkRoundedButton.setColor(new java.awt.Color(140, 75, 242));
        checkRoundedButton.setColorClick(new java.awt.Color(60, 200, 80));
        checkRoundedButton.setColorOver(new java.awt.Color(60, 200, 80));
        checkRoundedButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        checkRoundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        checkRoundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        checkRoundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        checkRoundedButton.setRadius(20);
        checkRoundedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRoundedButtonActionPerformed(evt);
            }
        });
        roundedPanel.add(checkRoundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 200, 40));

        orderTypeLabel.setBackground(new java.awt.Color(40, 40, 56));
        orderTypeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        orderTypeLabel.setForeground(new java.awt.Color(40, 40, 56));
        orderTypeLabel.setText("Order Type:");
        roundedPanel.add(orderTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 420, 30));

        detailLabel.setBackground(new java.awt.Color(40, 40, 56));
        detailLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        detailLabel.setForeground(new java.awt.Color(40, 40, 56));
        detailLabel.setText("Details:");
        detailLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        roundedPanel.add(detailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 420, 30));

        timeLabel.setBackground(new java.awt.Color(40, 40, 56));
        timeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(40, 40, 56));
        timeLabel.setText("Time:");
        roundedPanel.add(timeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 420, 30));

        dateLabel.setBackground(new java.awt.Color(40, 40, 56));
        dateLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(40, 40, 56));
        dateLabel.setText("Date:");
        roundedPanel.add(dateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 420, 30));

        rejectRoundedButton.setBackground(new java.awt.Color(140, 75, 242));
        rejectRoundedButton.setForeground(new java.awt.Color(248, 248, 248));
        rejectRoundedButton.setText("Reject");
        rejectRoundedButton.setBorderColor(new java.awt.Color(248, 248, 248));
        rejectRoundedButton.setColor(new java.awt.Color(140, 75, 242));
        rejectRoundedButton.setColorClick(new java.awt.Color(200, 50, 130));
        rejectRoundedButton.setColorOver(new java.awt.Color(200, 50, 180));
        rejectRoundedButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rejectRoundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        rejectRoundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        rejectRoundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        rejectRoundedButton.setRadius(20);
        rejectRoundedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectRoundedButtonActionPerformed(evt);
            }
        });
        roundedPanel.add(rejectRoundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 100, 40));

        doneRoundedButton.setBackground(new java.awt.Color(140, 75, 242));
        doneRoundedButton.setForeground(new java.awt.Color(248, 248, 248));
        doneRoundedButton.setText("Done");
        doneRoundedButton.setBorderColor(new java.awt.Color(248, 248, 248));
        doneRoundedButton.setColor(new java.awt.Color(140, 75, 242));
        doneRoundedButton.setColorClick(new java.awt.Color(60, 200, 0));
        doneRoundedButton.setColorOver(new java.awt.Color(60, 200, 80));
        doneRoundedButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        doneRoundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        doneRoundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        doneRoundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        doneRoundedButton.setRadius(20);
        doneRoundedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneRoundedButtonActionPerformed(evt);
            }
        });
        roundedPanel.add(doneRoundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 100, 40));

        acceptRoundedButton.setBackground(new java.awt.Color(140, 75, 242));
        acceptRoundedButton.setForeground(new java.awt.Color(248, 248, 248));
        acceptRoundedButton.setText("Accept");
        acceptRoundedButton.setBorderColor(new java.awt.Color(248, 248, 248));
        acceptRoundedButton.setColor(new java.awt.Color(140, 75, 242));
        acceptRoundedButton.setColorClick(new java.awt.Color(60, 200, 0));
        acceptRoundedButton.setColorOver(new java.awt.Color(60, 200, 80));
        acceptRoundedButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        acceptRoundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        acceptRoundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        acceptRoundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        acceptRoundedButton.setRadius(20);
        acceptRoundedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptRoundedButtonActionPerformed(evt);
            }
        });
        roundedPanel.add(acceptRoundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 100, 40));

        cancelRoundedButton.setBackground(new java.awt.Color(140, 75, 242));
        cancelRoundedButton.setForeground(new java.awt.Color(248, 248, 248));
        cancelRoundedButton.setText("Cancel");
        cancelRoundedButton.setBorderColor(new java.awt.Color(248, 248, 248));
        cancelRoundedButton.setColor(new java.awt.Color(140, 75, 242));
        cancelRoundedButton.setColorClick(new java.awt.Color(60, 200, 0));
        cancelRoundedButton.setColorOver(new java.awt.Color(60, 200, 80));
        cancelRoundedButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cancelRoundedButton.setFontColor(new java.awt.Color(248, 248, 248));
        cancelRoundedButton.setFontColorClick(new java.awt.Color(248, 248, 248));
        cancelRoundedButton.setFontColorOver(new java.awt.Color(248, 248, 248));
        cancelRoundedButton.setRadius(20);
        cancelRoundedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelRoundedButtonActionPerformed(evt);
            }
        });
        roundedPanel.add(cancelRoundedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void checkRoundedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRoundedButtonActionPerformed
        // actionPerformed(evt);
    }//GEN-LAST:event_checkRoundedButtonActionPerformed

    private void rejectRoundedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectRoundedButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rejectRoundedButtonActionPerformed

    private void doneRoundedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneRoundedButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doneRoundedButtonActionPerformed

    private void acceptRoundedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptRoundedButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acceptRoundedButtonActionPerformed

    private void cancelRoundedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelRoundedButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelRoundedButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private method.RoundedButton acceptRoundedButton;
    private method.RoundedButton cancelRoundedButton;
    private method.RoundedButton checkRoundedButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel detailLabel;
    private method.RoundedButton doneRoundedButton;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel orderIdLabel;
    private javax.swing.JLabel orderTypeLabel;
    private method.RoundedButton rejectRoundedButton;
    private method.RoundedPanel roundedPanel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
