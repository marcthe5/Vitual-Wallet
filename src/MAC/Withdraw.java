package MAC;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Withdraw extends JFrame {
	Vars v;
	protected static String phone;

	private JPanel contentPane;
	private JTextField textField;
	

	 protected Connection connection;
	 protected PreparedStatement ps ;
	 protected ResultSet rs;




	/**
	 * Create the frame.
	 * @param userPhone 
	 */
	public Withdraw(String userPhone) {
		phone = userPhone;
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 196);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInputAmount = new JLabel("INPUT AMOUNT");
		lblInputAmount.setForeground(Color.WHITE);
		lblInputAmount.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblInputAmount.setBounds(110, 11, 148, 37);
		contentPane.add(lblInputAmount);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 11, 380, 37);
		contentPane.add(panel);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBorder(new LineBorder(Color.DARK_GRAY));
		textField.setBounds(45, 59, 286, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnDeposit = new JButton("WITHDRAW");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int withdraw;
				withdraw = Integer.parseInt(textField.getText());
				SQL(withdraw,phone);
				if(Vars.SQLreturn == true) {
					JOptionPane.showMessageDialog(null, "Withdraw Successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
					 Withdraw frame = new Withdraw(phone);
					 frame.setVisible(false);				
					 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

				}
				else {
					JOptionPane.showMessageDialog(null, "Not enough amount!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDeposit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeposit.setFocusPainted(false);
		btnDeposit.setForeground(Color.WHITE);
		btnDeposit.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnDeposit.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDeposit.setBackground(Color.DARK_GRAY);
		btnDeposit.setBounds(125, 107, 119, 46);
		contentPane.add(btnDeposit);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 Withdraw frame = new Withdraw(phone);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public boolean SQL(int with,String user) throws NullPointerException {
		 final String url = "jdbc:mysql://localhost:3306/ATM";
		 final String username = "marcthe5";
		 final String password = "Myloveones1";
		 ResultSet rs;
		 Statement stmt;
		 boolean chckStmt = true;
	   	 try {

			connection = DriverManager.getConnection(url, username,password);
		   	String updateQuery = "UPDATE accountdepot SET cash = cash - ? WHERE Phone = ? ";
		   	String checkQuery = "SELECT * FROM accountdepot WHERE Phone = " + user;
		   	
		   	
		   	ps=connection.prepareStatement(updateQuery);       
		   	ps.setInt(1, with);
		   	ps.setString(2, user);
	      
	        
	        
	        //checkBalance
	        stmt=connection.createStatement();  
	      
	        rs = stmt.executeQuery(checkQuery);
	        
            while(rs.next()) {
            	
            	int getCash = Integer.valueOf(rs.getString("cash"));
            	
	        if(with > getCash) {
	        	ps.cancel();	   
	        	JOptionPane.showMessageDialog(null, "Amount Not Enough!","Error",JOptionPane.ERROR_MESSAGE);
	        	chckStmt = false;
	        	
	        }
	        else {
	        	Vars.SQLreturn = true;
	        	ps.executeUpdate();
	        	chckStmt = true;
	        }
	        }
	        if(with != 0 && with > 0 && (chckStmt == true)) {
	        	 Vars.SQLreturn = true;
	         }
	        else {
	        	 Vars.SQLreturn = false;
	         }
	         ps.close();
	         connection.close();
	         		         
	         
	         
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Vars.SQLreturn;

		}
}
