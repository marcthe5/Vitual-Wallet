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

public class ExpressSend extends JFrame {
	Vars v;
    
	protected static String userP;
	
	private JPanel contentPane;
	private JTextField phone_textfield;
	private JTextField amount_textfield;
	protected int amount;
	protected String phone;
	
	
	 protected Connection connection;
	 protected PreparedStatement ps ;
	 protected ResultSet rs;
	 protected String receiverLN;
	 protected String receiverFN;
	 protected String receiverName;

	 


	/**
	 * Create the frame.
	 * @param userPhone 
	 */
	public ExpressSend(String userPhone) {
		userP = userPhone;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 324);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInputAmount = new JLabel("RECEIVER PHONE NUMBER");
		lblInputAmount.setForeground(Color.WHITE);
		lblInputAmount.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblInputAmount.setBounds(66, 11, 246, 37);
		contentPane.add(lblInputAmount);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 11, 380, 37);
		contentPane.add(panel);
		
		phone_textfield = new JTextField();
		phone_textfield.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		phone_textfield.setBorder(new LineBorder(Color.DARK_GRAY));
		phone_textfield.setBounds(45, 59, 286, 37);
		contentPane.add(phone_textfield);
		phone_textfield.setColumns(10);
		
		JButton btnDeposit = new JButton("SEND");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				phone = phone_textfield.getText();
				amount = Integer.parseInt(amount_textfield.getText());
				
				SQL(userP,phone,amount);
				if(Vars.SQLreturn == true) {
					 ExpressSend frame = new ExpressSend(userP);
					 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Amount Not Enough!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDeposit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeposit.setFocusPainted(false);
		btnDeposit.setForeground(Color.WHITE);
		btnDeposit.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnDeposit.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnDeposit.setBackground(new Color(34, 139, 34));
		btnDeposit.setBounds(42, 236, 119, 46);
		contentPane.add(btnDeposit);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ExpressSend frame = new ExpressSend(userP);
				 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				frame.setVisible(false);
			}
		});
		btnClose.setForeground(Color.WHITE);
		btnClose.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnClose.setFocusPainted(false);
		btnClose.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.setBounds(219, 236, 119, 46);
		contentPane.add(btnClose);
		
		JLabel lblInputAmount_1 = new JLabel("INPUT AMOUNT");
		lblInputAmount_1.setForeground(Color.WHITE);
		lblInputAmount_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblInputAmount_1.setBounds(110, 120, 148, 37);
		contentPane.add(lblInputAmount_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 122, 380, 37);
		contentPane.add(panel_1);
		
		amount_textfield = new JTextField();
		amount_textfield.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		amount_textfield.setColumns(10);
		amount_textfield.setBorder(new LineBorder(Color.DARK_GRAY));
		amount_textfield.setBounds(45, 170, 286, 37);
		contentPane.add(amount_textfield);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 ExpressSend frame = new ExpressSend(userP);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public boolean SQL(String user, String contact, int cash) throws NullPointerException {
		 final String url = "jdbc:mysql://localhost:3306/ATM";
		 final String username = "marcthe5";
		 final String password = "Myloveones1";
		 Statement stmt;
		 int chckCash;
		 boolean chckStmt = true;
		 
	   	 try {

			connection = DriverManager.getConnection(url, username,password);
		   	 String checkQuery = "SELECT * FROM accountdepot WHERE Phone = " + user;
		   	 String updateQuery = "UPDATE accountdepot SET cash = cash + ? WHERE Phone = ?";
		   	 String getreceiverName = "SELECT * FROM accounts WHERE Phone = ?";
		   	 
		   	
		   	
		    ps=connection.prepareStatement(updateQuery);

		   	ps.setInt(1, cash);
		   	ps.setString(2, contact);
	      	        
	        
	        //checkUserAmount
	        stmt=connection.createStatement();
		   	 
		   	 rs = stmt.executeQuery(checkQuery);
		   	 while(rs.next()) {
		   		 chckCash = Integer.valueOf(rs.getString("cash"));
		   		 
		   		if(cash > chckCash) {
		        	ps.cancel();	   
		        	chckStmt = false;
		        	
		        }
		   		else {
		        	ps.executeUpdate();
		        	chckStmt = true;
		        }
		   	 }
		   	 
	        //getReceiverName
		   	ps=connection.prepareStatement(getreceiverName);
	        ps.setString(1, contact);
	        
	        rs = ps.executeQuery();
	        
	        while(rs.next()) {
	        	receiverLN = rs.getString("Lastname");
	        	receiverFN = rs.getString("Firstname");
	        	receiverName = receiverLN + "," + receiverFN;
	        	
	        }

	         
	         if(cash != 0 && cash > 0 && (chckStmt == true)) {
             JOptionPane.showMessageDialog(null, "Successfully Sent to " + receiverName.toUpperCase() + " !" +"\n with an Amount of " + cash, "Message", JOptionPane.INFORMATION_MESSAGE);
	        	 Vars.SQLreturn = true;
	         }
	         else {
	        	 Vars.SQLreturn = false;
	         }
	         ps.close();
	         rs.close();
	         connection.close();
	         		         
	         
	         
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Vars.SQLreturn;

		}
	
}