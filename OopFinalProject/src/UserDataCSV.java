import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataCSV {
	
	//file Userdata.csv ถูกเก็บไว้ในตัวแปร USER_FILE โดยเป็นตัวแปรประเภท String
    private static final String USER_FILE = "/Users/phatcharaporn.t/Documents/GitHub/OOP-project/Userdata.csv";

    
    public static void saveUser(String name, String surname, String id, String birthDate, String bloodGroup, String address) {
    	
    	/* มีการเขียนข้อมูลลงใน USER_FILE และมีการใช้ OutputStreamWriter ในการกำหนดรหัสไฟล์เป็น UTF-8 เพื่อป้องกันปัญหาเรื่องการเก็บอักขระที่ไม่รองรับในไฟล์ */
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(USER_FILE,true), "UTF-8"))) {
        	//เป็นการเชื่อมข้อมูล name surname id birthDate bloodGroup address ด้วยลูกน้ำโดยจะมีการtrim เอาไว้ เพื่อเอาspaceที่ไม่ต้องการออกจากข้อมูลแต่ละตัวก่อนที่จะนำมารวมกัน
            String userData = String.join(",", name.trim(), surname.trim(), id.trim(), birthDate.trim(), bloodGroup.trim(), address.trim());
            writer.write(userData);//เขียนข้อมูล UserData ลงในไฟล์
            writer.newLine(); // มีการขึ้นบรรทัดใหม่เมื่อบันทึกข้อมูลเสร็จ
            writer.flush(); // เป็นการrecheckข้อมูลทั้งหมดถูกบันทึกไว้ใน BufferedWriter แล้วจริงๆ
           
           } 
        //ใช้จับผิดข้อผิดพลาดที่เกิดขึ้นขณะเขียนไฟล์
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //เป็นmethodที่ใช้โหลดข้อมูลผู้ใช้จากไฟล์
    public static List<String[]> loadUsers() {
    	
    	// มีการสร้างตัวแปร ชื่อ users เพื่อเก็บข้อมูลที่ได้มาจากไฟล์โดยข้อมูลนั้นจะถูกเก็บเป็น array และมีประเภทของข้อมูลเป็น String
        List<String[]> users = new ArrayList<>();
        
        /*สร้าง obj. ชื่อ file เพื่อใช้เก็บ USER_FILE โดยมีการไปเรียกใช้คลาสที่ชื่อว่า FILE ซึ่งเราได้ทำการimport java.io.*
          จึงไม่จำเป็นต้องเขียนคลาส File ออกมา*/
        File file = new File(USER_FILE);
        
        //ถ้าไฟล์ ไม่มีอยู่(!file.exists()) ให้ส่งคืนค่า users
        //การใช้คำสั่งนี้จะช่วยป้องกัน FileNotFoundException หากเกิดการพยายามหาไฟล์ที่ไม่มีอยู่
        if (!file.exists()) {
            return users; // Return empty list if file does not exist
        }
        //มีการอ่านข้อมูลใน USER_FILE
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line; //มีการประกาศตัวแปร ชื่อ line เพื่อใช้เก็บข้อมูลที่อ่านมาจากไฟล์ โดยจะอ่านทีละบรรทัด
            
            /*เงื่อนไขใน while คือ ให้วนลูป อ่านข้อมูลในไฟล์ทีละบรรทัดจาก reader.readLine()
              โดย !=null หมายถึง ถ้ายังมีข้อมูลให้อ่าน ก็ให้วนลูปจนกว่าไม่มีข้อมูลแล้วหลุดออกจากลูป while
              */
            while ((line = reader.readLine()) != null) {
            	
            	/* line.trim() ตัดช่องว่างระหว่างspaceทั้งหน้าและหลัง
         	       is.Empty คือตรวจสอบว่าข้อความที่เหลือว่างหรือเปล่า
         	     */
                if (line.trim().isEmpty()) continue; //เป็นการตรวจสอบว่าบรรทัดที่อ่านว่างหรือไม่ ถ้าว่างให้ข้ามไปอ่านบรรทัดถัดไป
                
                
                
                // เป็นการตัดแบ่งข้อมูลเป็นarray ex. "paksom,123,female" ก็จะแปลงเป็น "paksom","123","female"
                String[] user = line.split("\\s*,\\s*"); 
                
                /*เป็นการตรวจสอบว่าข้อมูลน้อยกว่า 3ไหม ถ้าน้อยกว่า 3 นั้นหมายข้อมูลไม่ถูกต้อง 
                   เนื่องจากข้อมูลไม่ครบ(name surname id)และถ้าไม่ถูกเพิ่มข้อมูลลงใน users
                   แต่หากข้อมูลนั้นครบถ้วนก็จะถูกเพิ่มข้อมูลลงในusers
                   */
                if (user.length >= 3) { 
                    users.add(user);
                }
            }
        } 
        //ใช้จับผิดข้อผิดพลาดที่เกิดขึ้นขณะเขียนไฟล์
        catch (IOException e) {
            e.printStackTrace();
        }
       
        return users; //คืนค่าlist users ที่เก็บข้อมูลของผู้ใช้ที่โหลดจากไฟล์
    }
    
    public static boolean validateUser(String name, String surname, String id) {
    	
    	//เรียกใช้method loadUsers เพื่อโหลดข้อมูลผู้ใช้ที่เก็บอยู่ในรูปแบบของรายการ โดยข้อมูลในรายการเป็นประเภทString
        List<String[]> users = loadUsers();
        
        //นำข้อมูลของผู้ใช้ใน users แต่ละคนมาใช้งาน โดยมีการเก็บไว้ในตัวแปร user ประเภท String โดยมีการเก็บข้อมูลเป็นarray
        for (String[] user : users) {
        	
            // ตรวจสอบข้อมูลใน user ว่ามีข้อมูลอย่างน้อยอยู่ 3 ส่วเพื่อป้องกันความผิดพลาดของข้อมูลในusers เผื่อเกิดกรณีที่ข้อมูลไม่ครบใน users
            if (user.length >= 3) {
            	
                String storedName = user[0].trim();//index 0 เก็บข้อมุล name ไว้ใน storedName
                String storedSurname = user[1].trim();//index 1 เก็บข้อมุล surname ไว้ใน storedSurname
                String storedId = user[2].trim();//index 2 เก็บข้อมุล id ไว้ใน storedId

                /* เปรียบเทียบข้อมูลที่ป้อนกับข้อมูลที่อ่านมาจาก CSV โดย 
                name เทียบกับ storedName  surname เทียบกับ storeSurname  id เทียบกับ storedId หากตรงกันทั้ง 3 ตัว
                จะมีการreturn true คือพบว่าข้อมูลผู้ใช้งานตรงกับในไฟล์ CSV
                */
                if (name.equals(storedName) && surname.equals(storedSurname) && id.equals(storedId)) {
                    return true; // พบผู้ใช้ที่ตรงกัน
                }
            }
        }
        return false; // หากชื่อผู้ใช้ไม่ตรงกันจะreturn false คือไม่พบข้อมูล
    }
    
}