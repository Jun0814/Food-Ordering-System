/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Asus
 */
public class NotificationService {
    private ExecutorService executor = Executors.newCachedThreadPool();
    
    public void notifyRunner(String runnerId, String deliveryId){
        executor.execute(()->{
            System.out.println("Notifying runner" + runnerId + "order:" + deliveryId);
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });
    }
}
