import javax.swing.*; //importเพื่อเริ่มต้นใช้งานโปรแกรม
import java.awt.*; //ต้องimportทุกครั้งเมื่อมีการสร้างหน้าต่างGUI

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//importเพื่อรับคำสั่งของตัวAction event โดยในคำสั่งของAction Event จะประกอบด้วยJButton JTextField JMenuItem


 public class Confirmpage extends JFrame {
	//มีการประกาศ Attribute 1 ตัว
	private JButton logoutButton;//ตัวแปรประเภทJButton มีทั้งหมด 1 ตัว คือ logoutButton

	public Confirmpage(String datestamp, String timestamp,String name, String surname, String id, String appointmentTime) {
		
		
		
        setTitle("Oops! Emergency Hospital");//เซทชื่อหัวข้อบนGUI
        setSize(400, 425);//set กรอบGUI (กว้าง,ยาว)
        getContentPane().setBackground(new Color(195, 216, 242));//setBackgroundเพื่อเปลี่ยนสีพื้นหลัง(r,g,b)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 
        
        ImageIcon logoIcon = new ImageIcon("/Users/phatcharaporn.t/Documents/GitHub/OOP-project/logo.png"); // เป็นการimport fileรูปภาพเข้ามาเพื่อเก็บไว้ในตัวแปรlogoIcon
        Image img = logoIcon.getImage(); // ดึง Image ออกมาจาก ImageIcon
        Image resizedImg = img.getScaledInstance(125, 125, Image.SCALE_SMOOTH); // ปรับขนาดตามที่ต้องการ (กว้าง 130 สูง 130)
        ImageIcon resizedIcon = new ImageIcon(resizedImg); // แปลงกลับเป็น ImageIcon
        JLabel logoLabel = new JLabel(resizedIcon);
        // คำนวณตำแหน่ง X ให้อยู่ตรงกลาง
        int frameWidth = getWidth(); // ความกว้างของ JFrame 400
        int logoWidth = 125; // ความกว้างของโลโก้
        int centerX = (frameWidth - logoWidth) / 2; // (400-125)/2 =137.5 ดังนั้น center X = 137.5
        logoLabel.setBounds(centerX,37, 125, 125); // ต้องให้ขนาดตรงกับรูปภาพใหม่ setBounds เป็นการsetตำแหน่ง (x,y,w,h)
        add(logoLabel);;
        
        // Welcome Message
        JLabel welcomeLabel = new JLabel("Welcome, " + name + " " + surname, SwingConstants.LEFT);
        welcomeLabel.setBounds(10,10,300,30); 
        add(welcomeLabel);
        

       // name message
        JLabel nameLabel = new JLabel("Name: " + name , SwingConstants.CENTER);
        nameLabel.setBounds(50, 170, 300, 30);
        add(nameLabel);
        
        // name message
        JLabel surnameLabel = new JLabel("Surname: " + surname , SwingConstants.CENTER);
        surnameLabel.setBounds(50, 195, 300, 30);
        add(surnameLabel);
        
        JLabel idLabel = new JLabel("ID Card: " + id, SwingConstants.CENTER);
        idLabel.setBounds(50, 220, 300, 30);
        add(idLabel);

        // Appointment time
        JLabel appointmentLabel = new JLabel("Appointment Time: " + appointmentTime, SwingConstants.CENTER);
        appointmentLabel.setBounds(50, 245, 300, 30);
        add(appointmentLabel);
        
        //caution1
        JLabel cautionLabel = new JLabel("You should arrive 20-30 minutes",SwingConstants.CENTER);
        cautionLabel.setBounds(53, 275, 300, 20);
        cautionLabel.setForeground(Color.RED);
        add(cautionLabel);
        
       //caution2
        JLabel caution2Label = new JLabel("before the appointment time.",SwingConstants.CENTER);
        caution2Label.setBounds(53, 295, 300, 20);
        caution2Label.setForeground(Color.RED);
        add(caution2Label);
        
        
     // logout Button
        logoutButton = new JButton("Log out");
        logoutButton.setBounds(270, 345, 100, 40);
        add(logoutButton);
        
        /*จากaddActionListener เป็นการรับคำสั่งจากlogout button ไปยังตัวของactionPerformed ว่าในตัวactionPerformedมีคำสั่งว่าอะไร
        ซึ่งในactionPerformed มีคำสั่งให้ไปเช็คที่method openLoginSystem*/
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             	openLoginSystem();
             }
         });
        setVisible(true);// หากไม่ใส่ setVisible หน้าต่างGUI จะไม่แสดงผล แต่หากเปลี่ยนจากtrue เป็นfalse จะเป็นการซ่อนหน้าต่างGUI ถ้าพูดง่ายๆก็คือหน้าต่างGUIจะไม่ขึ้นทั้งสองกรณี
    }

	private void openLoginSystem() {
		this.dispose(); //ปิดหน้า confirm
	    new LoginSystem();//เปิดหน้า login
	}
}

