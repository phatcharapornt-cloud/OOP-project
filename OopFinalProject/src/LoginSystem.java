import javax.swing.*; //importเพื่อเริ่มต้นใช้งานโปรแกรม
import java.awt.*; //ต้องimportทุกครั้งเมื่อมีการสร้างหน้าต่างGUI

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//importเพื่อรับคำสั่งของตัวAction event โดยในคำสั่งของAction Event จะประกอบด้วยJButton JTextField JMenuItem


public class LoginSystem extends JFrame {
	//มีการประกาศAttribute 5 	ตัว
	
	/* nameField และ surnameField ถูกเก็บไว้ใน JTextField โดยJTextField สามารถใช้ได้ 2 แบบ คือ 
	   กรณี input จะเป็นการให้ผู้ใช้กรอกข้อมูลเป้นข้อความ(String) กรณีที่ 2 คือ Output คือเป็นการแสดงข้อความ 
	   ซึ่ง JTextfield มีประเภทตัวแปรเป็น String */
    private JTextField nameField, surnameField;
    
    /* JPassword ให้ผู้ใช้กรอกรหัสผ่านโดยรหัสผ่านที่กรอกจะแทนด้วยสัญลักษณ์อื่นๆ โดยมีประเภทตัวแปร
       ซึ่ง JPasswordfield มีประเภทตัวแปรเป็น char*/
    private JPasswordField idField;
    private JLabel message1Label , message2Label;
    
    /*ใช้แสดงข้อความและรูปภาพ บนGUI ที่เป็นข้อมูลเพื่อให้ตัวผู้ใช้ทราบ */

    public LoginSystem() {
        setTitle("Oops! Emergency Hospital"); //เซทชื่อหัวข้อบนGUI
        setSize(400, 425);//set กรอบGUI (กว้าง,ยาว)
        getContentPane().setBackground(new Color(195, 216, 242));//setBackgroundเพื่อเปลี่ยนสีพื้นหลัง(r,g,b)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        ImageIcon logoIcon = new ImageIcon("/Users/phatcharaporn.t/Desktop/Project/logo.png"); // เป็นการimport fileรูปภาพเข้ามาเพื่อเก็บไว้ในตัวแปรlogoIcon
        Image img = logoIcon.getImage(); // ดึง Image ออกมาจาก ImageIcon
        Image resizedImg = img.getScaledInstance(125, 125, Image.SCALE_SMOOTH); // ปรับขนาดตามที่ต้องการ (กว้าง 125 สูง 125)
        ImageIcon resizedIcon = new ImageIcon(resizedImg); // แปลงกลับเป็น ImageIcon
        JLabel logoLabel = new JLabel(resizedIcon);
        // คำนวณตำแหน่ง X ให้อยู่ตรงกลาง
        int frameWidth = getWidth(); // ความกว้างของ JFrame 400
        int logoWidth = 125; // ความกว้างของโลโก้
        int centerX = (frameWidth - logoWidth) / 2; // (400-125)/2 =137.5 ดังนั้น center X = 137.5
        logoLabel.setBounds(centerX, 5, 125, 125); // ต้องให้ขนาดตรงกับรูปภาพใหม่ setBounds เป็นการsetตำแหน่ง (x,y,w,h)
        add(logoLabel);

        //name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 130, 100, 20);
        add(nameLabel);
        
        //เป็นช่องเพื่อใส่ name ,มีการเรียกใช้attribute : nameField
        nameField = new JTextField();
        nameField.setBounds(30, 150, 340, 30);
        add(nameField);

        //surname
        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setBounds(30, 190, 100, 20);
        add(surnameLabel);

        //เป็นช่องเพื่อใส่ surname ,มีการเรียกใช้ตัวแปร : surnameField
        surnameField = new JTextField();
        surnameField.setBounds(30, 210, 340, 30);
        add(surnameField);
        
        //id-card
        JLabel idLabel = new JLabel("ID Card Number:");
        idLabel.setBounds(30, 250, 150, 20);
        add(idLabel);
        
        //เป็นช่องเพื่อใส่ id field ,มีการเรียกใช้ตัวแปร : idField
        idField = new JPasswordField();
        idField.setBounds(30, 270, 340, 30);
        add(idField);

        //login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(30, 345, 100, 40);
        /*จากaddActionListener เป็นการรับคำสั่งจากlogin button ไปยังตัวของactionPerformed ว่าในตัวactionPerformedมีคำสั่งว่าอะไร
          ซึ่งในactionPerformed มีคำสั่งให้ไปเช็คที่method checkLogin เมื่อผู้ใช้กดการทำงานที่loginButton*/
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });
        add(loginButton);
        
        //sign up button
        JButton signupButton = new JButton("Sign up");
        signupButton.setBounds(270, 345, 100, 40);
        /*จากaddActionListener เป็นการรับคำสั่งจากsignup button ไปยังตัวของactionPerformed ว่าในตัวactionPerformedมีคำสั่งว่าอะไร
        ซึ่งในactionPerformed มีคำสั่งให้ไปเช็คที่method openSignupSystem เมื่อผู้ใช้กดการทำงานที่signup button*/
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openSignupSystem();
            }
        }
        );
        add(signupButton);
        
        //มีการเรียกใช้attribute :  message1Label
        message1Label = new JLabel("", SwingConstants.LEFT);
        //SwingConstants.LEFT เป็นการบ่งบอกตำแหน่งของข้อความว่าให้ชิดทางไหน อย่างกรณีนี้เป็นLEFT ก็คือข้อควาจะชิดทางซ้าย
        //หากต้องการให้ข้อความชิดทางขวาหรือข้อความจัดอยู่ตรงกลางให้เปลี่ยนจากคำว่าLEFT เป็น RIGHT และ CENTER ตามลำดับ
        message1Label.setBounds(30, 294, 300, 30);
        message1Label.setForeground(Color.RED);
        /*setForeground เป็นการsetสีของข้อความนั้นๆ สามารถใส่เป็นชื่อได้เลย หรือจะใส่เป็นcodeสี ที่เป็น(r,g,b)ก็ได้เช่นกันแต่จะ
        ต้องเปลี่ยนโค้ดข้างในsetForegroundจากColor.REDเป็น new Color(195, 216, 242)*/
        add(message1Label);
        
        //มีการเรียกใช้attribute :  message2Label
        message2Label = new JLabel("", SwingConstants.LEFT);
        message2Label.setBounds(30, 314, 300, 30);
        message2Label.setForeground(Color.RED);
        add(message2Label);

        

        setVisible(true); // หากไม่ใส่ setVisible หน้าต่างGUI จะไม่แสดงผล แต่หากเปลี่ยนจากtrue เป็นfalse จะเป็นการซ่อนหน้าต่างGUI ถ้าพูดง่ายๆก็คือหน้าต่างGUIจะไม่ขึ้นทั้งสองกรณี
    }

    private void checkLogin() {
    	/*มีการประกาศตัวแปรชื่อ name ประเภทString และมีการดึงข้อความจากJTextField ชื่อ nameField
    	  โดยการ trim() เป็ฯการลบspaceข้างหน้าและข้างหลังข้อความออก*/
        String name = nameField.getText().trim();
        /*มีการประกาศตัวแปรชื่อ surname ประเภทString และมีการดึงข้อความจากJTextField ชื่อ surnameField
  	      โดยการ trim() เป็ฯการลบspaceข้างหน้าและข้างหลังข้อความออก*/
        String surname = surnameField.getText().trim();
        /*มีการประกาศตัวแปรชื่อ id ประเภทString และมีการดึงข้อความจากJPasswordField ชื่อ idField
	      โดยการ trim() เป็ฯการลบspaceข้างหน้าและข้างหลังข้อความออก
	      แต่จะสังเกตได้ว่ามีการเพิ่ม new String ขึ้นมาเนื่องจาก JPasswordField มีประเภทเป็นchar ดังนั้นต้องเปลี่ยนcharเป็นString 
	      เพื่อนำไอดีที่กรอกไปเช็คข้อมูลใน UserDataCSV ซึ่งข้อมูลในนั้นเป็นStringทั้งหมด*/
        String id = new String(idField.getPassword()).trim();
        
        
        /*จากคำสั่ง if-else คือ ถ้าข้อมูลname surname id ใน method validateUser ของ class UserDataCSV 
          ตรงกับข้อมูลของผู้ใช้งานที่กรอกในหน้าlogin จะขึ้นคำว่าLodin Successful แล้วไปเช็คต่อที่ method openDashBoard */
        if (UserDataCSV.validateUser(name, surname, id)) { 
            JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            openDashboard(name, surname, id);
        /*หาก name surname or id card ไม่ถูกต้องจะขึ้นคำว่า Invalid Name-Surname or ID Card! and 
          If you have not signed up yet, please sign up.*/
        } else {
            message1Label.setText("Invalid Name-Surname or ID Card!");
            message2Label.setText("If you have not signed up yet, please sign up.");
           
        }
    }

    private void openDashboard(String name, String surname, String id) {
        this.dispose(); // ปิด Login ลง
        new Dashboard(name, surname, id); // แล้วเปิดหน้า Dashboard รวมถึงส่งข้อมูล name surname และ id ไปยังหน้า Dashboard ด้วย
    }
   
    private void openSignupSystem() {
        this.dispose(); // ปิด Login ลง
        new SignupSystem();//เปิดหน้า SignupSystem
    }
    public static void main(String[] args) {
    	new  LoginSystem(); // เปิดหน้า LoginSystem เมื่อเริ่มต้นใช้งาน
    }
}






