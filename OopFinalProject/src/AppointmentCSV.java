import java.io.*;
import java.text.SimpleDateFormat;//import เพื่อใช้จัดรูปแบบวันที่และเวลา

import java.time.LocalDate;//ใช้ในการสร้างและจัดรูปแบบวันที่ 
import java.time.LocalTime;//ใช้ในการสร้างและจัดรูปแบบเวลา
import java.time.format.DateTimeFormatter;//ใช้กำหนดรูปแบบของ LocalDate และ LocalTime ให้เป็น String
import java.time.format.DateTimeParseException;//ใช้สำหรับการจัดการข้อผิดพลาดเมื่อแปลง LocalDate และ LocalTime ให้เป็น String

import java.util.Date;//importเพื่อเรียกใช้คลาสDate ซึ่งคลาส Date จะเป็นคลาสที่ใช้สำหรับจัดการข้อมูลเกี่ยวกับวันที่และเวลา

import java.util.List;//ใช้เพื่อเก็บข้อมูลหลายๆตัวไว้ในรายการใดรายการหนึ่งที่เราต้องการ 
import java.util.ArrayList;//เพื่อใช้ในการเก็บข้อมูลแบบรายการ ซึ่งสามารถปรับขนาดของข้อมูลได้ตามความต้องการ

import java.util.Collections;//เพื่อใช้ในการจัดการเรียงลำดับข้อมูล



public class AppointmentCSV {
	
	//มีการเก็บไฟล์ Appointment.csv ไว้ใน APPOINTMENTS_FILE ซึ่งเป็นตัวแปรประเภท String
	public static  String APPOINTMENTS_FILE = "/Users/phatcharaporn.t/Documents/GitHub/OOP-project/Appointment.csv";
	//มีการสร้างตัวแปร FORMATTER เพื่อเก็บเวลา โดยมีการใช้ DateTimeFormatter ในการกำหนดรูปแบบของเวลา
	public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
	
	 public static void saveAppointment(String datestamp, String timestamp,String name, String surname, String id, String appointmentTime) {
		   
		    //มีการเรียกใช้method readAppointment เพื่อเก็บข้อมูลไว้ในตัวแปรappointmentในรูปแบบของรายการ โดยมีประเภทตัวแปรเป็นString
	        List<String> appointments = readAppointments(); 
	        //มีการเรียกใช้method readAppointment เพื่อเก็บข้อมูลไว้ในตัวแปรDateในรูปแบบของรายการ โดยมีประเภทตัวแปรเป็นString
	        List<String> Date = readAppointments();
	        // datestamp เป็นตัวแปรประเภท String ที่เก็บรุปแบบขของวันเดือนปี ณ ปัจจุบันโดยใช้ SimpleDateFormat ในการจัดรูปแบบของวันที่
	        datestamp = new SimpleDateFormat("yyyy-MM-dd ").format(new Date());
	        // timestamp เป็นตัวแปรประเภท String ที่เก็บรุปแบบขของเวลา ณ ปัจจุบันโดยใช้ SimpleDateFormat ในการจัดรูปแบบขอเวลา
	        timestamp = new SimpleDateFormat("HH:mm:ss ").format(new Date());
	        
	        //มีการสร้างตัวแปร newAppointment เพื่อเก็บข้อมูลappointment โดยข้อมูลนั้นจะถูกเชื่อมกันด้วยลูกน้ำจากคำสั่งString.join
	        String newAppointment = String.join(",",datestamp,timestamp, name, surname, id, appointmentTime);
	        
	        appointments.add(newAppointment);//มีการเพิ่มข้อมูลจากตัวแปรnewAppointment ไปยังตัวแปรappointments
	        sortAppointments(Date);//มีการเรียกใช้ method sortAppointments เพื่อเรียงลำดับวันเดือนปี
	        sortAppointments(appointments); // มีการเรียกใช้ method sortAppointments เรียงลำดับเวลานัดหมายของแต่ละวัน

	        writeAppointments(appointments); // มีการเรยกใช้ method writeAppointments เพื่อเขียนข้อมูล appointments ลงในไฟล์
	    }
	 
	 private static List<String> readAppointments() {
	    	
	    	//มีการเรียกใช้method ArrayList เพื่อlist ข้อมูล appointment ออกมาในรูปแบบของรายการโดยมีประเภทของข้อมูลเป็นStrimg
	        List<String> appointments = new ArrayList<>();
	        
	        //มีการอ่าน APPOINTMENTS_FILE
	        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENTS_FILE))) {
	            String line; //มีการประกาศตัวแปร ชื่อ line เพื่อใช้เก็บข้อมูลที่อ่านมาจากไฟล์ โดยจะอ่านทีละบรรทัด
	            
	            /*เงื่อนไขใน while คือ ให้วนลูป อ่านข้อมูลในไฟล์ทีละบรรทัดจาก reader.readLine()
	              โดย !=null หมายถึง ถ้ายังมีข้อมูลให้อ่าน ก็ให้วนลูปจนกว่าไม่มีข้อมูลแล้วหลุดออกจากลูป while
	              */
	            while ((line = reader.readLine()) != null) {
	            	/* line.trim() ตัดช่องว่างระหว่างspaceทั้งหน้าและหลัง
	            	   is.Empty คือตรวจสอบว่าข้อความที่เหลือว่างหรือเปล่า
	            	 */
	            	if (line.trim().isEmpty()) continue; //เป็นการตรวจสอบว่าบรรทัดที่อ่านว่างหรือไม่ ถ้าว่างให้ข้ามไปอ่านบรรทัดถัดไป โดยที่ไม่ประมวลผลบรรทัดที่ไม่มีข้อมูล
	                
	               
	                appointments.add(line);// ถ้าบรรทัดนั้นไม่ว่าง ให้เพิ่มบรรทัดนั้นเข้าไปใน appointments
	            }
	        } 
	        //ตรวจสอบข้อผิดพลาดหากไม่พบไฟล์ ให้ขึ้นคำว่า Appointment file not found. Creating a new one.
	        catch (FileNotFoundException e) {
	        	 System.out.println("Appointment file not found. Creating a new one.");
	        } 
	        //ใช้จับผิดข้อผิดพลาดที่เกิดขึ้นขณะอ่านไฟล์
	        catch (IOException e) {
	        	
	            e.printStackTrace();
	        }
	        return appointments; //ส่งคืนค่า appointment
	    }

	 private static void sortAppointments(List<String> appointments) {
	    	//มีการเรียกใช้รูปแบบการเขียนวันเดือนปีจาก DateTimeFormatte และเก็บลงในตัวแปร dateFormatter
	    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	
	    	//มีการใช้ Collections.sort เพื่อเรียงลำดับข้อมูล appointment โดย s1,s2 เป็น Lambda Expression ใช้แทน Comparator
	        Collections.sort(appointments, (s1, s2) -> {
	        	//parts1 เก็บข้อมูลของ s1 โดย มีการSplit คือการแยกข้อมูลออกเป็นarray
	            String[] parts1 = s1.split(",");
	            //parts2 เก็บข้อมูลของ s2 โดย มีการSplit คือการแยกข้อมูลออกเป็นarray
	            String[] parts2 = s2.split(",");
	            
	            //ถ้า ข้อมูลในparts1 มากกว่า 5 และข้อมูลparts2 มากกว่า 5 ในเข้าไปทำการ compare วันที่และเวลานัดหมายใน try
	            if (parts1.length > 5 && parts2.length > 5) {
	            try {
	               
	            	LocalDate date1 = LocalDate.parse(parts1[0].trim(), dateFormatter); //วันที่ตัวที่ 1 ถูกเก็บไว้ในdate1
	                LocalDate date2 = LocalDate.parse(parts2[0].trim(), dateFormatter); //วันที่ตัวที่ 2 ถูกเก็บไว้ในdate2
	                
	                
	                int dateComparison = date1.compareTo(date2);//เปรียบเทียบdate1และdate2 ไว้ที่ตัวแปรdateComparison
	                
	                //ถ้าวันที่ใน date1 และ date2 คนละวันให้ส่งคืนค่าไปยังdateComparison เพื่อทำการcompare
	                if (dateComparison != 0) {
	                    return dateComparison;
	                }
	                
	                // แต่หากวันที่เหมือนกันจะเข้ามาทำคำสั่ง localtime เพื่อเปรียบเทียบเวลานัดหมาย ซึ่งเวลานัดหมายจะอยู่ในindexที่ 5 
	                LocalTime time1 = LocalTime.parse(parts1[5].trim(), FORMATTER);//เวลานัดหมายตัวที่ 1 จะถูกเก็บไว้ในตัวแปรtime1
	                LocalTime time2 = LocalTime.parse(parts2[5].trim(), FORMATTER);//เวลานัดหมายตัวที่ 2 จะถูกเก็บไว้ในตัวแปรtime2
	                
	                return time1.compareTo(time2);//มีการส่งคืนค่า การเปรียบเทียเวลานัดหมาย

	            	} 
	            // ใช้จับผิดข้อผิดพลาดที่เกิดขึ้นขณะเปรียบเทียบข้อมูล
	            catch (DateTimeParseException e) {
	            	e.printStackTrace();
	               
	            }  
	            
	            }
	            return 0;//ถ้าข้อมูลไม่ครบให้ส่งคืนค่า 0 คือไม่ต้องมีการสลับลำดับ
	        }
	        );
	    }
	 
	    
	private static void writeAppointments(List<String> appointments) {
	    	// มีการเขียนข้อมูลลงใน APPOINTMENTS_FILE 
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENTS_FILE))) {
	        	
	            //นำข้อมูลappointments แต่ละคนมาใช้งาน โดยมีการเก็บไว้ในตัวแปร appointment ประเภท String โดยมีการเก็บข้อมูลเป็นarray
	            for (String appointment : appointments) {
	                writer.write(appointment);//เขียนข้อมูลappointmentลงในไฟล์
	                writer.newLine();// มีการขึ้นบรรทัดใหม่เมื่อบันทึกข้อมูลเสร็จ
	            }
	            writer.flush();// เป็นการrecheckข้อมูลทั้งหมดถูกบันทึกไว้ใน BufferedWriter แล้วจริงๆ
	            
	        } 
	        //ใช้จับผิดข้อผิดพลาดที่เกิดขึ้นขณะเขียนไฟล์
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}

