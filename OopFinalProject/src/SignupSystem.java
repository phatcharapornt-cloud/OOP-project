import javax.swing.*; //importเพื่อเริ่มต้นใช้งานโปรแกรม
import java.awt.*; //ต้องimportทุกครั้งเมื่อมีการสร้างหน้าต่างGUI

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//importเพื่อรับคำสั่งของตัวAction event โดยในคำสั่งของAction Event จะประกอบด้วยJButton JTextField JMenuItem


public class SignupSystem extends JFrame {
	//มีการประกาศAttribute ทั้งหมด 6 ตัว
    private JTextField nameField, surnameField, idField, birthField, addressField;//ตัวแปรประเภทJTextField มีทั้งหมด 5 ตัว คือ nameField, surnameField, idField, birthField, addressField
    private JComboBox<String> bloodGroupBox;//ตัวแปรประเภท JComboBox มีทั้งหมด 1 ตัว คือ bloodGroupBox โดยข้อมูลใน bloodGroupBox เก็บข้อมูลประเภทStingไว้

    public SignupSystem() {
        setTitle("Oops! Emergency Hospital");//เซทชื่อหัวข้อบนGUI
        setSize(400, 425);//set กรอบGUI (กว้าง,ยาว)
        getContentPane().setBackground(new Color(195, 216, 242));//setBackgroundเพื่อเปลี่ยนสีพื้นหลัง(r,g,b)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        

        ImageIcon logoIcon = new ImageIcon("/Users/phatcharaporn.t/Desktop/Project/logo.png"); // Replace with the path to your logo image
        Image img = logoIcon.getImage(); // ดึง Image ออกมาจาก ImageIcon
        Image resizedImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // ปรับขนาดตามที่ต้องการ (กว้าง 50 สูง 50)
        ImageIcon resizedIcon = new ImageIcon(resizedImg); // แปลงกลับเป็น ImageIcon
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBounds(20, 10, 50, 50);// ต้องให้ขนาดตรงกับรูปภาพใหม่ setBounds เป็นการsetตำแหน่ง (x,y,w,h)
        add(logoLabel);
        
        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 70, 150, 20);
        add(nameLabel);
        
        //มีการเรียกใช้ตัวแปร nameField
        nameField = new JTextField();
        nameField.setBounds(20, 90, 360, 30);
        add(nameField);

        // Surname
        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setBounds(20, 120, 150, 20);
        add(surnameLabel);
        
        //มีการเรียกใช้ตัวแปร surnameField
        surnameField = new JTextField();
        surnameField.setBounds(20, 140, 360, 30);
        add(surnameField);

        // Birth Date
        JLabel birthLabel = new JLabel("Birth Date (DD/MM/YYYY):");
        birthLabel.setBounds(20, 170, 200, 20);
        add(birthLabel);

        //มีการเรียกใช้ตัวแปร birthField
        birthField = new JTextField();
        birthField.setBounds(20, 190, 200, 30);
        add(birthField);

        // Blood Group
        JLabel bloodLabel = new JLabel("Blood Group:");
        bloodLabel.setBounds(250, 170, 100, 20);
        add(bloodLabel);
 
        //bloodType เก็บข้อมูลเป็น array โดยข้อมูลนั้นเป็นประเภทString
        String[] bloodTypes = {"A", "B", "AB", "O"};
        //สร้างBloodComboBox โดยเรียกใช้ข้อมูลจากBloodType ที่เก็บข้อมูลกรุ๊ปเลือด ทั้งหมดนี้จะถูกเก็บไว้ในตัวแปรbloodGroupBox
        bloodGroupBox = new JComboBox<>(bloodTypes);
        bloodGroupBox.setBounds(245, 192, 130, 30);
        add(bloodGroupBox);

        // ID Card
        JLabel idLabel = new JLabel("ID Card Number:");
        idLabel.setBounds(20, 220, 150, 20);
        add(idLabel);

        //มีการเรียกใช้ตัวแปร idField
        idField = new JTextField();
        idField.setBounds(20, 240, 360, 30);
        add(idField);

        // Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 270, 150, 20);
        add(addressLabel);
        
        //มีการเรียกใช้ตัวแปร addressField
        addressField = new JTextField();
        addressField.setBounds(20, 290, 360, 30);
        add(addressField);

        // Sign-up Button
        JButton signupButton = new JButton("Sign up");
        signupButton.setBounds(270, 345, 100, 40);
        add(signupButton);
        /*จากaddActionListener เป็นการรับคำสั่งจากsignup button ไปยังตัวของactionPerformed ว่าในตัวactionPerformedมีคำสั่งว่าอะไร
        ซึ่งในactionPerformed มีคำสั่งให้ไปเช็คที่method saveUser */
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             	saveUser();
             }
        }
        );
       
        
        // Sign-up Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(30, 345, 100, 40);
        add(backButton);
        /*จากaddActionListener เป็นการรับคำสั่งจากback button ไปยังตัวของactionPerformed ว่าในตัวactionPerformedมีคำสั่งว่าอะไร
        ซึ่งในactionPerformed มีคำสั่งให้ไปเช็คที่method BackToLogin */
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	BackToLogin();
             }
         });
       

        setVisible(true);
    }

    private void BackToLogin() {
    	 dispose(); // ปิดหน้าต่าง Sign-up
         new LoginSystem(); // กลับไปหน้า Login
	
	}

    
	private void saveUser() {
		
        String name = nameField.getText().trim();
        /*มีการประกาศตัวแปรชื่อ name ประเภทString และมีการดึงข้อความจากJTextField ชื่อ nameField
	      โดยการ trim() เป็ฯการลบspaceข้างหน้าและข้างหลังข้อความออก*/
        String surname = surnameField.getText().trim();
        /*มีการประกาศตัวแปรชื่อ surname ประเภทString และมีการดึงข้อความจากJTextField ชื่อ surnameField
	      โดยการ trim() เป็ฯการลบspaceข้างหน้าและข้างหลังข้อความออก*/
        String id = idField.getText().trim();
        /*มีการประกาศตัวแปรชื่อ id ประเภทString และมีการดึงข้อความจากJTextField ชื่อ idField
	      โดยการ trim() เป็ฯการลบspaceข้างหน้าและข้างหลังข้อความออก*/
        String birthDate = birthField.getText().trim();
        /*มีการประกาศตัวแปรชื่อ birthDate ประเภทString และมีการดึงข้อความจากJTextField ชื่อ birthField
	      โดยการ trim() เป็ฯการลบspaceข้างหน้าและข้างหลังข้อความออก*/
        String address = addressField.getText().trim();
        /*มีการประกาศตัวแปรชื่อ address ประเภทString และมีการดึงข้อความจากJTextField ชื่อ addressField
	      โดยการ trim() เป็ฯการลบspaceข้างหน้าและข้างหลังข้อความออก*/
        String bloodGroup = (String) bloodGroupBox.getSelectedItem();
        //ดึงค่าเวลาจากผู้ใชที่ทำการเลือกเวลาจาก bloodGroupBox แล้วมีการแปลงข้อมูลเป็นString แล้วเก็บข้อมูลไว้ที่bloodGroup
	 	//ที่ต้องแปลงเป็นString เนื่องจาก bloodGroupBox เป็นobj.นึงในJComboBox ทำให้ไม่สามารถใชคืนค่าข้อมูลได้โดยตรงเลต้องแปลงเป็นประเภทStringก่อนที่จะคืนค่าช้อมูล
        
        
        //เงื่อนไขของ if คือตรวจสอบว่า name surname id birthDate address ไม่มีช่องว่างในข้อความซึ่งคำสั่งที่ใช้เช็คว่าไม่พบช่องว่างระหว่างข้อความคือ isEmpty()
        if (!name.isEmpty() && !surname.isEmpty() && !id.isEmpty() && !birthDate.isEmpty() && !address.isEmpty()) {
        	
        	//มีการsave ข้อมูลไปยังsaveUserในคลาสUserDataCSV โดยมีการส่งข้อมูลname, surname, id, birthDate, bloodGroup, address
        	UserDataCSV.saveUser(name, surname, id, birthDate, bloodGroup, address);
            JOptionPane.showMessageDialog(this, "Sign-up successful!");//หาก sign up เสร็จเรียบร้อยจะขึ้นคำว่า Sign-up successful!

            dispose(); // ปิดหน้าต่าง Sign-up
            new LoginSystem(); // กลับไปหน้า Login
           
        } 
        
        //หากพบช่องว่างระหว่างข้อความจะมาทำคำสั่งในelse โดยการขึ้นข้อความ Please fill all fields! และ Error
        else {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(false);//มีการสร้างหน้าต่างGUI ขึ้นแต่หน้าต่างนั้นถูกซ่อนไว้
    }
}
