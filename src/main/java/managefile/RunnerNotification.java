/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author Asus
 */
public class RunnerNotification {
    private String notificationId;
    private String runnerId;
    private String orderId;
    private String status;
    private String filepath = "src\\main\\java\\repository\\runnerNotification.txt";
    
    public RunnerNotification(){}
    
    public RunnerNotification(String notificationId, String runnerId, String orderId, String status) {
        this.notificationId = notificationId;
        this.runnerId = runnerId;
        this.orderId = orderId;
        this.status = status;
    }
    
    public String getNotificationId(){
        return notificationId;
    }
    
    public void setNotificationId(String notificationId){
        this.notificationId = notificationId;
    }
    
    public String getOrderId(){
        return orderId;
    }
    
    public void setOrderId(String orderId){
        this.orderId = orderId;
    }
    
    public String getRunnerId(){
        return runnerId;
    }
    
    public void setRunnerId(String runnerId){
        this.runnerId = runnerId;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getFilepath(){
        return filepath;
    }
    
    @Override
    public String toString() {
        return "RunnerNotification{" +
                "notificationId='" + notificationId + '\'' +
                ", runnerId='" + runnerId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
