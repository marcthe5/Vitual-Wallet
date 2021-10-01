package MAC;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class Request extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTextField cnTextField;
	private JTextField amountTextField;
	protected static String phone;
	protected Connection connection;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected boolean chckStmt = false;


	/**
	 * Create the frame.
	 * @param userPhone 
	 */
	public Request(String userPhone) {
		setResizable(false);
		phone = userPhone;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblContactNumber = new JLabel("CONTACT NUMBER");
		lblContactNumber.setForeground(Color.WHITE);
		lblContactNumber.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblContactNumber.setBounds(112, 6, 198, 40);
		contentPane.add(lblContactNumber);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(-2, 9, 485, 39);
		contentPane.add(panel);
		
		JLabel lblRequestAmount = new JLabel("REQUEST AMOUNT");
		lblRequestAmount.setForeground(Color.WHITE);
		lblRequestAmount.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblRequestAmount.setBounds(113, 121, 198, 40);
		contentPane.add(lblRequestAmount);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 123, 485, 39);
		contentPane.add(panel_1);
		
		cnTextField = new JTextField();
		cnTextField.setBounds(76, 61, 265, 36);
		contentPane.add(cnTextField);
		cnTextField.setColumns(10);
		
		amountTextField = new JTextField();
		amountTextField.setColumns(10);
		amountTextField.setBounds(77, 178, 265, 36);
		contentPane.add(amountTextField);
		
		JButton btnNewButton = new JButton("REQUEST");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(34, 139, 34));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int getAmount = Integer.parseInt(amountTextField.getText());
				String getContact = cnTextField.getText();
				SQL(getContact,getAmount,phone);
				
				if(Vars.SQLreturn == true) {
					JOptionPane.showMessageDialog(null, "Request Sent!","Message",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Request Error!","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(82, 226, 253, 36);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("RESET");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.DARK_GRAY);
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cnTextField.setText(null);
				amountTextField.setText(null);
				
			}
		});
		btnCancel.setBounds(82, 274, 117, 36);
		contentPane.add(btnCancel);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Request frame = new Request(phone);
				 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

			}
		});
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnClose.setForeground(Color.WHITE);
		btnClose.setBounds(218, 274, 117, 36);
		contentPane.add(btnClose);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Request frame = new Request(phone);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public boolean SQL(String contact, int amount, String user) throws NullPointerException {
		 final String url = "jdbc:mysql://localhost:3306/ATM";
		 final String username = "marcthe5";
		 final String password = "Myloveones1";
		 Statement stmt;
		
		
	   	 try {

			connection = DriverManager.getConnection(url, username,password);
		   	 String updateQuery = "UPDATE requests SET requester = ?, requestAmount = ? WHERE receiverPhone = ? ";
		   	 String retrieveQuery = "SELECT * FROM accounts";
		   	 
		   	ps=connection.prepareStatement(updateQuery);         	
		   	ps.setString(1, user);
		   	ps.setInt(2, amount);
		   	ps.setString(3, contact);
	      
		    stmt=connection.prepareStatement(retrieveQuery);      	
            rs=stmt.executeQuery(retrieveQuery);
            
            String findAccount;
            
             if(rs.next() == true) {
         	   findAccount = rs.getString("Phone");
         	   if(contact.equals(findAccount) && (!contact.equals(user))) {
    	 	        chckStmt = true;
    	 	       
    	 	        
         	   }
         	   else {
         		    chckStmt = false;
    	 	        JOptionPane.showMessageDialog(null, "No Account Receiver Found!","Invalid Account!",JOptionPane.ERROR_MESSAGE);

         	   }
            } 
            
	         if(amount != 0 && amount > 0 && (chckStmt == true)) {
	 	        ps.executeUpdate();
		         Vars.SQLreturn = true;
	         }
	         else {
	 	        ps.cancel();
	        	Vars.SQLreturn = false;
	         }
	         
			
	         
	         
	         ps.close();
	         connection.close();
	         		         
	         
	         
		} catch (Exception e) {
			e.printStackTrace();
       	 Vars.SQLreturn = false;

		}
		return Vars.SQLreturn;

		}
}
