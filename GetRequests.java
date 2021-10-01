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
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class GetRequests extends JFrame {

	private JPanel contentPane;
	private static JTextField requestAmount;
	protected GetRequests gr;
	protected JLabel reqContact;
	protected Connection connection;
	protected PreparedStatement ps;
	protected PreparedStatement ps2;
	protected PreparedStatement ps3;
	protected ResultSet rs;
	protected static String phone;
	protected String reqUser;
	protected String reqCash;
	protected int reqAmount;
    protected Statement stmt;



	/**
	 * Create the frame.
	 */
	public GetRequests(String userPhone) {
		phone = userPhone;
		//SQL(phone);
		 final String url = "jdbc:mysql://localhost:3306/ATM";
		 final String username = "marcthe5";
		 final String password = "Myloveones1";
		
		
	   	 try {

			connection = DriverManager.getConnection(url, username,password);
		   	 String retrieveQuery = "SELECT * FROM requests WHERE receiverPhone = ? ";
		   	 
		ps=connection.prepareStatement(retrieveQuery);         
		   	ps.setString(1, phone);
	      rs = ps.executeQuery();
	         
	         while(rs.next()) {
	        	 reqUser = rs.getString("requester");
	        	 reqCash = rs.getString("requestAmount");
	        	 reqAmount = Integer.valueOf(reqCash);
	        	 
	        	
	         }
	         rs.close();
	         connection.close();
	         		         
	         
	         
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 459);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 11, 415, 44);
		contentPane.add(panel);
		
		JLabel lblRequest = new JLabel("REQUESTER CONTACT ");
		panel.add(lblRequest);
		lblRequest.setForeground(Color.WHITE);
		lblRequest.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 133, 415, 44);
		contentPane.add(panel_1);
		
		JLabel lblAmountRequested = new JLabel("AMOUNT REQUESTED");
		lblAmountRequested.setForeground(Color.WHITE);
		lblAmountRequested.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel_1.add(lblAmountRequested);
		
		requestAmount = new JTextField(reqCash);
		requestAmount.setEditable(false);
		requestAmount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		requestAmount.setBounds(81, 189, 251, 39);
		contentPane.add(requestAmount);
		requestAmount.setColumns(10);
		
		reqContact = new JLabel(reqUser);
		reqContact.setBorder(new LineBorder(new Color(0, 0, 0)));
		reqContact.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		reqContact.setBounds(111, 69, 187, 38);
		contentPane.add(reqContact);
		
		JButton btnGrantRequest = new JButton("ACCEPT REQUEST");
		btnGrantRequest.setBackground(new Color(34, 139, 34));
		btnGrantRequest.setForeground(Color.WHITE);
		btnGrantRequest.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnGrantRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getUser = reqContact.getText();
				int getAmount = Integer.parseInt(requestAmount.getText());
				
				SQL(phone, getUser, getAmount);
			}
		});
		btnGrantRequest.setBounds(95, 243, 221, 44);
		contentPane.add(btnGrantRequest);
		
		JButton btnDenyrequest = new JButton("DENY REQUEST");
		btnDenyrequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GetRequests frame = new GetRequests(phone);
				 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

			}
		});
		btnDenyrequest.setForeground(Color.WHITE);
		btnDenyrequest.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnDenyrequest.setBackground(new Color(128, 0, 0));
		btnDenyrequest.setBounds(96, 297, 221, 44);
		contentPane.add(btnDenyrequest);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetRequests frame = new GetRequests(phone);
				 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

			}
		});
		btnClose.setForeground(Color.WHITE);
		btnClose.setFont(new Font("Segoe UI", Font.BOLD, 19));
		btnClose.setBackground(new Color(0, 0, 0));
		btnClose.setBounds(125, 352, 162, 40);
		contentPane.add(btnClose);
	}
	
public void SQL(String user, String reqUser, int amount) throws NullPointerException {
		 final String url = "jdbc:mysql://localhost:3306/ATM";
		 final String username = "marcthe5";
		 final String password = "Myloveones1";
		 boolean chckStmt = true;
		 
		 int defVal = 0;
		 
		
	   	 try {

			connection = DriverManager.getConnection(url, username,password);
		   	 String checkQuery = "SELECT * FROM accountdepot WHERE Phone = " + user;
		   	 String updateIncQuery = "UPDATE accountdepot SET cash = cash + ? WHERE Phone = ?";
		   	 String updateDecQuery = "UPDATE accountdepot SET cash = cash - ? WHERE Phone = ?" ;
		   	 String revertQuery = "UPDATE requests SET requester = ?, requestAmount = ? WHERE receiverPhone = ? ";
		   	 
		ps2=connection.prepareStatement(updateIncQuery);
	   	ps2.setInt(1, amount);      
		ps2.setString(2, reqUser);
		
		ps3=connection.prepareStatement(updateDecQuery);
	   	ps3.setInt(1, amount);      
		ps3.setString(2, user); 	
	
		
		//to Default Value
		ps=connection.prepareStatement(revertQuery);
		ps.setInt(1, defVal);      
		ps.setInt(2, defVal);
		ps.setString(3, user);

		//checkUserAmount
        stmt=connection.createStatement();
	   	 
	   	 rs = stmt.executeQuery(checkQuery);
	   	 while(rs.next()) {
	   		int chckCash = Integer.valueOf(rs.getString("cash"));
	   		 
	   		if(reqAmount > chckCash) {
	   			stmt.cancel();
	   			stmt.cancel();
	   			ps2.cancel();
	   			ps3.cancel();
	        	ps.cancel();	   
	        	chckStmt = false;
	        	JOptionPane.showMessageDialog(null, "Request Denied!", "Message",JOptionPane.PLAIN_MESSAGE);

	        	
	        }
	   		else {
	   		/*	stmt.executeUpdate(updateIncQuery);
	        	stmt.executeUpdate(updateDecQuery); */
	   			ps2.executeUpdate();
	   			ps3.executeUpdate();
	        	ps.executeUpdate();
	        	chckStmt = true;
	        	JOptionPane.showMessageDialog(null, "Request Accepted!", "Message",JOptionPane.PLAIN_MESSAGE);
	        	GetRequests frame = new GetRequests(phone);
				 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	        }
	   	 }
	   	 
	   	 
	         
	         connection.commit();
	         ps.close();
	         connection.close();
	         		         
	         
	         
		}
	   	 catch (Exception e) {
			e.printStackTrace();
		}
		

		}
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetRequests frame = new GetRequests(phone);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}