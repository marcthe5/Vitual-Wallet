package MAC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AuthenticationPanel2 extends Vars{


	private JFrame frmGlobalBank;
	private JTextField PIN;
	protected String InputtedPin;
	protected static String userPhone;

	/**
	 * Create the application.
	 * @param inputtedPhone 
	 */
	public AuthenticationPanel2(String inputtedPhone) {
		userPhone = inputtedPhone;
		initialize(userPhone);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 AuthenticationPanel2 window = new AuthenticationPanel2(userPhone) ;
					window.frmGlobalBank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}


	/**
	 * Initialize the contents of the frame.
	 * @param phone 
	 */
	private void initialize(String phone) {
		frmGlobalBank = new JFrame();
		frmGlobalBank.setTitle("GLOBAL BANK");
		frmGlobalBank.getContentPane().setBackground(Color.WHITE);
		frmGlobalBank.setBounds(100, 100, 420, 252);
		frmGlobalBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGlobalBank.getContentPane().setLayout(null);
		
		JLabel lblGlobalBank = new JLabel("GLOBAL BANK");
		lblGlobalBank.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblGlobalBank.setBounds(10, 11, 145, 36);
		frmGlobalBank.getContentPane().add(lblGlobalBank);
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		separator.setBounds(139, 30, 287, 2);
		frmGlobalBank.getContentPane().add(separator);
		
		PIN = new JPasswordField();
		PIN.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		PIN.setBorder(new LineBorder(Color.DARK_GRAY));
		PIN.setBounds(94, 74, 217, 41);
		frmGlobalBank.getContentPane().add(PIN);
		PIN.setColumns(10);
		
		JLabel lblEnterPin = new JLabel("ENTER MPIN");
		lblEnterPin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEnterPin.setBounds(154, 47, 98, 26);
		frmGlobalBank.getContentPane().add(lblEnterPin);
		
		JButton btnRegister = new JButton("NEW CLIENT");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrationPanel rp = new RegistrationPanel();
				rp.main(null);
 			}
		});
		btnRegister.setFocusPainted(false);
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(128, 0, 0));
		btnRegister.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnRegister.setBounds(125, 172, 159, 36);
		frmGlobalBank.getContentPane().add(btnRegister);
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.setFocusPainted(false);
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setBackground(Color.DARK_GRAY);
		btnEnter.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnEnter.setBounds(125, 125, 159, 36);
		frmGlobalBank.getContentPane().add(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 MainPanel mp = new MainPanel(phone);
				 InputtedPin = PIN.getText();
				 SQL(phone);
				 if(SQLreturn == true) {
					 mp.setVisible(true);
					 
					 frmGlobalBank.setVisible(false);					 			 
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "Invalid pin!", "ERROR", JOptionPane.ERROR_MESSAGE);

				 }				 
			}
		});
	}
	public boolean SQL(String user) {
		GetSet gs = new GetSet();
	   	 try {
			connection = DriverManager.getConnection(url, username,password);
		   	 String compareQuery = "SELECT * FROM accounts WHERE Phone = ? AND pin = ?" ;
	         ps=connection.prepareStatement(compareQuery);
	         
	         ps.setString(1, user);
	         ps.setString(2, InputtedPin);
	         rs =  ps.executeQuery();       
	       
				 if(rs.next()) {
					 SQLreturn = true;
				 }	
				 else {
					 SQLreturn = false;	 
				 }
				 rs.close();
				 connection.close();
				 }
				 catch(Exception e) {
					 e.printStackTrace();
				 }
	   	 
		return SQLreturn;
		
}
}

		