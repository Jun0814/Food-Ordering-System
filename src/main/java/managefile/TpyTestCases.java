/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managefile;

/**
 *
 * @author TPY
 */
public class TpyTestCases {    
    public static void main(String[] args) {
        Vendor2 vendor = new Vendor2();
        Data data = new Data();
    
        String filepath = vendor.getFilepath();
        vendor.setName("tpy");
        vendor.setEmail("tpy@gmail.com");
        vendor.setPassword("123");
        vendor.setPhone("016");
        String content = vendor.getContent();
        
        data.appendData(content, filepath);
        
        Vendor2 vendor2 = new Vendor2("xh","xh@gmail.com","123","016");
        String content2 = vendor2.getContent();
        
        data.appendData(content2, filepath);
    }
}
