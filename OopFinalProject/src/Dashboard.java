import javax.swing.*; //importเพื่อเริ่มต้นใช้งานโปรแกรม
import java.awt.*; //ต้องimportทุกครั้งเมื่อมีการสร้างหน้าต่างGUI

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//importเพื่อรับคำสั่งของตัวAction event โดยในคำสั่งของAction Event จะประกอบด้วยJButton JTextField JMenuItem

/*import bufferedReader เพ่ือสามารถอ่านข้อมูลจากไฟล์ได้อย่างมีประสิทธิภาพและเร็วกว่าการอ่านทีละbyte โดยเราจะใช้ buffered ครอบ reader เสมอ 
  หากจะใช้reader อย่างเดียวก็ได้แต่ต้องมีการแปลงจากbyteเป็นcharแล้วส่งคืนค่ากลับ ซึ่งมันจะทำให้การอ่านข้อมูลมันช้าและไม่มีประสิทธิภาพ ดังนั้นจึงต้องใช้bufferเข้ามาช่วย*/
import java.io.BufferedReader;
import java.io.FileReader;//เปิดและอ่านข้อมูลจากไฟล์ทีละตัวอักษรโดยเราจะใส่readerไว้ในbuffered

import java.io.IOException;/*เป็นการแจ้งเตือนข้อผิดพลาด ประเภท input(I):รับ และ Output(O):ส่ง ซึ่งคราสหลักของexception คือการทำงานของI/Oที่ล้มเหลวหรือถูกขัดจังหวะในการทำงาน*/

import java.util.Date;//importเพื่อเรียกใช้คลาสDate ซึ่งคลาส Date จะเป็นคลาสที่ใช้สำหรับจัดการข้อมูลเกี่ยวกับวันที่และเวลา
import java.text.SimpleDateFormat; //import เพื่อใช้จัดรูปแบบวันที่และเวลา

import java.util.List;//ใช้เพื่อเก็บข้อมูลหลายๆตัวไว้ในรายการใดรายการหนึ่งที่เราต้องการ 
import java.util.ArrayList;//เพื่อใช้ในการเก็บข้อมูลแบบรายการ ซึ่งสามารถปรับขนาดของข้อมูลได้ตามความต้องการ



public class Dashboard extends JFrame {
/*มีการประกาศAttribute ทั้งหมด 10 ตัว */
	private JLabel dateLabel, TimeLabel; //ตัวแปรประเภทJLabal มีทั้งหมด 2ตัว คือ dataLabal และ TimeLabal
	private JButton bookButton, confirmButton, logoutButton;//ตัวแปรประเภทJButton มีทั้งหมด 3 ตัว คือ bookButton confirmButton และ logoutButton
	public String appointmentTime,datestamp,timestamp  ; //ตัวแปรประเภท String มีทั้งหมด 2ตัว คือ dataLabal และ TimeLabal
	private JComboBox<String> timeComboBox; //ตัวแปรประเภท JComboBox มีทั้งหมด 1 ตัว คือ timeComboBox โดยข้อมูลใน timeComboBox เก็บข้อมูลประเภทStingไว้
	private boolean isBooked = false; //เป็นตัวแปรประเภทboolean สำหรับเก็บสถานะการจอง แต่เริ่มต้นยังไม่มีการจอง isbooked เลยเท่ากับ false
	

 public Dashboard(String name, String surname, String id) {
    
     
     setTitle("Oops! Emergency Hospital"); //เซทชื่อหัวข้อบนGUI
     setSize(400, 425); //set กรอบGUI (กว้าง,ยาว)
     getContentPane().setBackground(new Color(195, 216, 242)); //setBackgroundเพื่อเปลี่ยนสีพื้นหลัง(r,g,b)
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setLayout(null); 
     
     
     ImageIcon logoIcon = new ImageIcon("/Users/phatcharaporn.t/Desktop/Project/logo.png"); // เป็นการimport fileรูปภาพเข้ามาเพื่อเก็บไว้ในตัวแปรlogoIcon
     Image img = logoIcon.getImage(); // ดึง Image ออกมาจาก ImageIcon
     Image resizedImg = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH); // ปรับขนาดตามที่ต้องการ (กว้าง 130 สูง 130)
     ImageIcon resizedIcon = new ImageIcon(resizedImg); // แปลงกลับเป็น ImageIcon
     JLabel logoLabel = new JLabel(resizedIcon);
     // คำนวณตำแหน่ง X ให้อยู่ตรงกลาง
     int frameWidth = getWidth(); // ความกว้างของ JFrame 400
     int logoWidth = 125; // ความกว้างของโลโก้
     int centerX = (frameWidth - logoWidth) / 2; // (400-125)/2 =137.5 ดังนั้น center X = 137.5
     logoLabel.setBounds(centerX,35, 130, 130); // ต้องให้ขนาดตรงกับรูปภาพใหม่ setBounds เป็นการsetตำแหน่ง (x,y,w,h)
     add(logoLabel);

     // Welcome Message
     JLabel welcomeLabel = new JLabel("Welcome, " + name + " " + surname, SwingConstants.LEFT);
     welcomeLabel.setBounds(10,7,300,30); 
     add(welcomeLabel);

     // Date & Time
     dateLabel = new JLabel("", SwingConstants.CENTER);
     dateLabel.setFont(new Font("Arial", Font.PLAIN, 14)); //plain ตัวบาง
     dateLabel.setBounds(53, 170, 300 , 30);
     add(dateLabel);
     
     //มีการเรียกใช้ตัวแปร TimeLabal
     TimeLabel = new JLabel("", SwingConstants.CENTER);
     TimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
     TimeLabel.setBounds(75, 190, 240, 30);
     add(TimeLabel);

     updateDateTime(); //เรียกใช้method ที่ชื่อว่า updateDateTime()
     startTimer(); //เรียกใช้method startTimer()

     // Available Time
     JLabel timeLabel = new JLabel("Available Time:");
     timeLabel.setBounds(20, 220, 150, 20);
     add(timeLabel);
     
     //สร้างbox เพื่อเก็บเวลาลงในtimeComboBox
     timeComboBox = new JComboBox<>();
     timeComboBox.setBounds(20, 250, 200, 30);
     add(timeComboBox);

     populateAvailableTimes();//เรียกใช้ method :  populateAvailableTimes()

     // Book Button
     bookButton = new JButton("Book Appointment");
     bookButton.setBounds(230, 250, 150, 30);
     add(bookButton);
     /*จากaddActionListener เป็นการรับคำสั่งจากbook button ไปยังตัวของactionPerformed ว่าในตัวactionPerformedมีคำสั่งว่าอะไร
     ซึ่งในactionPerformed มีคำสั่งให้ไปเช็คที่method bookAppointment เพื่อทำการbookเวลาในการเข้าตรวจ*/
     bookButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 bookAppointment();
         }	
      }
      );
     
     //caution1
     JLabel cautionLabel = new JLabel("You should arrive 20-30 minutes",SwingConstants.CENTER);
     cautionLabel.setBounds(53, 290, 300, 20);
     cautionLabel.setForeground(Color.RED);
     add(cautionLabel);
     
     //caution2
     JLabel caution2Label = new JLabel("before the appointment time.",SwingConstants.CENTER);
     caution2Label.setBounds(53, 315, 300, 20);
     caution2Label.setForeground(Color.RED);
     add(caution2Label);
     
     logoutButton = new JButton("Log out");
     logoutButton.setBounds(30,345, 100, 40);
     add(logoutButton);
     logoutButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         	openLoginSystem();
         }
     });

     // Confirm Button
     confirmButton = new JButton("Confirm");
     confirmButton.setBounds(270, 345, 100, 40);
     confirmButton.setEnabled(false);
     add(confirmButton);
     /*จากaddActionListener เป็นการรับคำสั่งจากconfirm button ไปยังตัวของactionPerformed ว่าในตัวactionPerformedมีคำสั่งว่าอะไร
     ซึ่งในactionPerformed มีคำสั่งให้ไปเช็คที่method openConfirmPage โดยmethod openConfirmPageมีการเรียกใช้ข้อมูล datastamp timestamp name surname 
     id and appointmentTime ไปใช้ต่อยังmethod*/
     confirmButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 openConfirmPage(datestamp,timestamp,name, surname, id, appointmentTime);
         }
     });
     

     setVisible(true); // หากไม่ใส่ setVisible หน้าต่างGUI จะไม่แสดงผล แต่หากเปลี่ยนจากtrue เป็นfalse จะเป็นการซ่อนหน้าต่างGUI ถ้าพูดง่ายๆก็คือหน้าต่างGUIจะไม่ขึ้นทั้งสองกรณี
 }

private void updateDateTime() {
	/* เรามีการimport java.text.SimpleDateFormat  ซึ่งภายใต้การimportนี้มีโค้ดที่ใช้ในการจัดformatของวันและเวลา 
	   จึงเสมือนว่ามีclassที่ชื่อว่าSimpleDateFormat อยู่ ดังนั้น มีการประกาศobject 2 ตัว คือ sdf และ hh 
	   โดย sdf เราจะจัดเก็บในรูปของวันจันทร์ถึงวันอาทิตย์และวันเดือนปี ณ ปัจจุบัน 
	   ส่วน hh จะเป็นการจัดเก็บเวลาอยู่ในรูปของชั่วโมง:นาที:วินาที ของเวลา ณ ปัจจุบัน */
     SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy"); 
     SimpleDateFormat hh = new SimpleDateFormat("HH:mm:ss");
     Date now = new Date();//วัน ณ ปัจจุบัน
     dateLabel.setText("Date: " + sdf.format(now));//sdf.format(now) คือ วันจันทร์ถึงวันอาทิตย์และวันเดือนปี ณ ปัจจุบัน 
     TimeLabel.setText("Time: " + hh.format(now));//hh.format(now) คือ เวลาอยู่ในรูปของชั่วโมง:นาที:วินาที ของเวลา ณ ปัจจุบัน
     
    
     datestamp = new SimpleDateFormat("yyyy-MM-dd").format(now); //datestamp จะเก็บข้อมูลวันเดือนปี ณ ปัจจุบัน
     timestamp = new SimpleDateFormat("HH:mm:ss").format(now);//timestamp จะเก็บข้อมูลเวลา ณ ปัจจุบัน
 }

 private void startTimer() {
	 //เป็นการอัพเดตวันและเวลาในทุกๆ 1 วินาที หรือ 1000 มิลลิวินาที
	 /* มีการสร้างObj. ชื่อ timer 
	    1000 คือ เป็นการทำงานของการจับเวลาทุกๆ 1000 มิลลิวิ หรือ 1 วินาที
	    e -> updateDateTime() มีการเรียกใช้method updateDateTime เพื่ออัพเดตวันที่และเวลา */
     Timer timer = new Timer(1000, e -> updateDateTime());
     timer.start();//เริ่มต้นการทำงานของTimer
 }


 private void populateAvailableTimes() {
	     
	    List<String> bookedTimes = getBookedTimes();//เรียก method getbooktime เพื่อดูรายการเวลาและดูว่าเวลานั้นถูกจองไปแล้วรึยัง
	    
	    /*อย่างแรกเราจะเช็ค loop for ของhourก่อน คือ เริ่มต้น hour ที่ 8 คือเริ่มต้นที่ 8 นาฬิกา โดยมีเงื่อนไขว่าจะต้องวนลูปจนกว่า Hour จะน้อยกว่าหรือเท่ากับ 11 โดยเพิ่มทีละ 1
	      สมมติว่าเริ่มต้นเป็น 8 ไปเช็คเงื่อนไขแล้วมันเป็นจริง มันก็เข้าไปที่loop for ของ minutes โดย loop for ของ minutes อธิบายได้ตามนี้คือ
	      เริ่มจาก minute = 0 หลังจากนั้นเข้าไปเช็คเงื่อนไขว่า minute น้อยกว่า 60 หรือไม่ถ้าน้อยกว่าจะเข้าไปทำคำสั่ง if-else เมื่อทำคำสั่งครบแล้ว minute จะเพิ่มขึ้นทีละ 15 นาที
	      เมื่อminuteมีค่ามากกว่า 60ก็จะหลุด loop for ของ minute แล้วจะไปวน loop hour จนกระทั่ง hour มากกว่า 11 ก็จะหลุดลูป */
	    
	    // แสดงรายการเวลาตั้งแต่ 08:00 - 11:45
	    for (int hour = 8; hour <=11; hour++) {
	        for (int minute = 0; minute < 60; minute += 15) {
	        	//จัดเวลาให้อยู่ในรูปของ hour:minute ex. 08.00 และค่าของเวลาจะถูกเก็บไว้ใน timeSlot
	            String timeSlot = String.format("%02d:%02d", hour, minute);
	            
	            /*ตรวจสอบว่าtimeSlot อยู่ในbookedTime หรือไม่ถ้าอยู่ให้ขึ้นคำว่า full หลัง Timeslot(เวลา08:00-11.45 and 13:00-15.45)
	              แต่ถ้าเช็คtimeSlotในbookedTime แล้วไม่พบว่าเวลาดังกล่าวถูกจองก็จะไม่ขึ้นคำว่า full*/
	            if (bookedTimes.contains(timeSlot)) { 
	                timeComboBox.addItem(timeSlot + " (FULL)");
	               
	            } else {
	                timeComboBox.addItem(timeSlot);
	            }
	        }
	    }
	    
	    /*อย่างแรกเราจะเช็ค loop for ของhourก่อน คือ เริ่มต้น hour ที่ 13 คือเริ่มต้นที่ 13 นาฬิกา โดยมีเงื่อนไขว่าจะต้องวนลูปจนกว่า Hour จะน้อยกว่าหรือเท่ากับ 15 โดยเพิ่มทีละ 1
	      สมมติว่าเริ่มต้นเป็น 13 ไปเช็คเงื่อนไขแล้วมันเป็นจริง มันก็เข้าไปที่loop for ของ minutes โดย loop for ของ minutes อธิบายได้ตามนี้คือ
	      เริ่มจาก minute = 0 หลังจากนั้นเข้าไปเช็คเงื่อนไขว่า minute น้อยกว่า 60 หรือไม่ถ้าน้อยกว่าจะเข้าไปทำคำสั่ง if-else เมื่อทำคำสั่งครบแล้ว minute จะเพิ่มขึ้นทีละ 15 นาที
	      เมื่อminuteมีค่ามากกว่า 60ก็จะหลุด loop for ของ minute แล้วจะไปวน loop hour จนกระทั่ง hour มากกว่า 16 ก็จะหลุดลูป */
	    
	    // แสดงรายการเวลาตั้งแต่ 13:00 - 15:45
	    for (int hour = 13; hour <= 15; hour++) {
	        for (int minute = 0; minute < 60; minute += 15) {
	        	//จัดเวลาให้อยู่ในรูปของ hour:minute ex. 08.00 และค่าของเวลาจะถูกเก็บไว้ใน timeSlot
	            String timeSlot = String.format("%02d:%02d", hour, minute);
	            
	            /*ตรวจสอบว่าtimeSlot อยู่ในbookedTime หรือไม่ถ้าอยู่ให้ขึ้นคำว่า full หลัง Timeslot(เวลา08:00-11.45 and 13:00-15.45)
	              แต่ถ้าเช็คtimeSlotในbookedTime แล้วไม่พบว่าเวลาดังกล่าวถูกจองก็จะไม่ขึ้นคำว่า full*/
	            if (bookedTimes.contains(timeSlot)) {
	                timeComboBox.addItem(timeSlot + " (FULL)");
	            } else {
	                timeComboBox.addItem(timeSlot);
	            }
	        }
	    }
	   
	}
 
//เป็น method ที่ list เวลาที่ถูกจอง
 private List<String> getBookedTimes() {
	 
	    List<String> bookedTimes = new ArrayList<>();
	    
	    // การใช้ SimpleDateFormat เพื่อกำหนดรูปแบบ และจากคำสั่ง new Date() เป็นการดึงวัน ณ ปัจจุบัน
	    // โดยนำ ณ ปัจจุบันเก็บใน todayDate
	    String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // วันที่ปัจจุบัน

	   // การใช้ BufferedReader ในการอ่านข้อมูลใน APPOINTMENTS_FILE ที่อยู่ใน class AppointmentCSV
	    try (BufferedReader reader = new BufferedReader(new FileReader(AppointmentCSV.APPOINTMENTS_FILE))) {
	        String line;// ประกาศตัวแปรประเภท String ชื่อ line
	        
	        // อ่านข้อมูลในไฟล์ CSV ที่ละบรรทัด และแยกข้อมูลในแต่ละบรรทัดด้วยเครื่องหมาย , และตัดคำทับ แล้วหยุด loop
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            
	            /* แยกข้อความจาก Line (Date,time,name,surname,Id card,Appointment Time) เป็น array
	             * parts[0] = Date
	             * parts[1] = time
	             * parts[2] = name
	             * parts[3] = surname
	             * parts[4] = Id card
	             * parts[5] = Appointment Time
	             */

	            // ข้อมูลต้องมีอย่างน้อย 6 ถึงจะเข้าไปทำคำสั่งใน if ถ้าข้อมูลไม่ครบจะถูกข้ามต่อ
	            if (parts.length >= 6) { 
	                String appointmentDate = parts[0].trim();  // วันที่ index 0 จะถูกเก็บไว้ที่ appointmentDate
	                String appointmentTime = parts[5].trim();  // เวลานัดหมายอยู่ที่ index 5 จะถูกเก็บไว้ที่ appointmentTime
	                
	             // เช็ควันที่และเวลานัดหมายตรงกันหรือไม่ วันที่เป็นเวลาที่กำหนด แล้วนำเวลาที่กำหนดไปเพิ่มใน bookedTimes
	                if (todayDate.equals(appointmentDate)) {  
	                    bookedTimes.add(appointmentTime);  
	                }
	            }
	        }
	    } 
	    catch (IOException e) {
	    	// ถ้าเกิดข้อผิดพลาดในการอ่านไฟล์จะแสดงนี้ว่า Error reading appointments
	        System.out.println("Error reading appointments: " + e.getMessage());
	    }
	    return bookedTimes;// คืนค่าเวลาที่ถูกจอง
	}


 private void bookAppointment() {
	    //ดึงค่าเวลาจากผู้ใชที่ทำการเลือกเวลาจากtimeComboBox แล้วมีการแปลงข้อมูลเป็นString แล้วเก็บข้อมูลไว้ที่selectedTime
	 	//ที่ต้องแปลงเป็นString เนื่องจาก timeComboBox เป็นobj.นึงในJComboBox ทำให้ไม่สามารถใชคืนค่าข้อมูลได้โดยตรงเลต้องแปลงเป็นประเภทStrimgก่อนที่จะคืนค่าช้อมูล
	    String selectedTime = (String) timeComboBox.getSelectedItem(); 
	    
	    /*เป็นการเช็คว่าผู้ใช้เลือกเวลาแล้วหรือยัง ถ้าselection=null คือ ผู้ใช้ยังไม่มีการเลือกเวลาจะไม่ทำคำสั่งใดๆ
	      แต่ถ้าผู้ใช้มีการเลือกเวลาแล้ว (selectedTime =!= null) จะไปทำคำสั่งในif*/
	    
	    if (selectedTime != null) { 
	        isBooked = true; //มีการเก็บสถานะการจอง ว่ามีการจองเกิดขึ้นแล้ว isbooked จึงเท่ากับ true
	        bookButton.setEnabled(false); // เมื่อมีการจองโดยการกดปุ่ม Book Appointment แล้ว ผู้ใช้จะไม่สามารถกดปุ่มได้อีก เนื่องจากได้ทำการปิดปุ่มไว้เมื่อมีการจองเวลา
	        confirmButton.setEnabled(true); // หลังจากนั้นปุ่มconfirmจะเปิดขึ้น เมื่อปิดปุ่มBook Appointment เพื่อให้ผู้ใช้confirmเวลาอีก 1 รอบ
	        //availableTimes.remove(selectedTime);
	        appointmentTime = selectedTime; //**เก็บเวลานัดหมายที่ถูกเลือก**

	        JOptionPane.showMessageDialog(this, "Book appointment at " + appointmentTime + ". Please enter confirm");
	    }
	}
 
 

 
 private void openConfirmPage(String datestamp, String timestamp,String name, String surname, String id, String appointmentTime) {
	    this.dispose(); // ปิด dashboard
	    AppointmentCSV.saveAppointment(datestamp,timestamp,name, surname, id, appointmentTime);//save ข้อมูลไปยังmethod saveAppointment ในคลาส AppointmentCVS
	    new Confirmpage(datestamp,timestamp,name, surname, id, appointmentTime);
	
	   
	}
 
 private void openLoginSystem() {
	  this.dispose(); //ปิดหน้าDashboard
	  new LoginSystem();//เปิดหน้าLodin System
}
}