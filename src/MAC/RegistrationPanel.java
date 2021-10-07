package MAC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class RegistrationPanel extends Vars {


	private JFrame frame;
	private JTextField ln;
	private JTextField fn;
	private JTextField contact;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationPanel window = new RegistrationPanel();

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrationPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws NumberFormatException {


		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(-2, -28, 358, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegistrationPanel = new JLabel("Registration Panel");
		lblRegistrationPanel.setFont(new Font("Segoe UI", Font.PLAIN, 21));
		lblRegistrationPanel.setBounds(85, 13, 190, 42);
		frame.getContentPane().add(lblRegistrationPanel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-2, 64, 375, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblLastName = new JLabel("LAST NAME");
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLastName.setBounds(132, 83, 101, 22);
		frame.getContentPane().add(lblLastName);
		
		ln = new JTextField();
		ln.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		ln.setBounds(70, 113, 207, 29);
		frame.getContentPane().add(ln);
		ln.setColumns(10);
		
		JLabel lblFirstName = new JLabel("FIRST NAME");
		lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFirstName.setBounds(130, 167, 101, 22);
		frame.getContentPane().add(lblFirstName);
		
		fn = new JTextField();
		fn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		fn.setColumns(10);
		fn.setBounds(70, 196, 207, 29);
		frame.getContentPane().add(fn);
		
		JButton btnNewButton = new JButton("REGISTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lname=ln.getText();
				fname=fn.getText();
				phone=contact.getText();
				
				
				mpin = Short.parseShort(JOptionPane.showInputDialog("Please input your custom mpin(4-Digits Only)"));
				mpinLength = String.valueOf(mpin).length();
			    if(mpinLength == 4) {
			    	SQL();	
			   
			    }
			    if(SQLreturn == true) {
			    	JOptionPane.showMessageDialog(null, "Account Created Successfully!","Successful",JOptionPane.PLAIN_MESSAGE);
			    }
			    else {
			    	JOptionPane.showMessageDialog(null, "MPIN must be (4 DIGITS)", "Error!",JOptionPane.ERROR_MESSAGE);
			    }
			    
			    
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setBounds(96, 330, 159, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ln.setText(null);
				fn.setText(null);
				contact.setText(null);
			}
		});
		btnReset.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnReset.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnReset.setBackground(Color.WHITE);
		btnReset.setBounds(37, 396, 111, 29);
		frame.getContentPane().add(btnReset);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
			}
		});
		btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClose.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnClose.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnClose.setBackground(Color.WHITE);
		btnClose.setBounds(201, 396, 111, 29);
		frame.getContentPane().add(btnClose);
		
		JLabel lblContact = new JLabel("CONTACT");
		lblContact.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblContact.setBounds(137, 246, 101, 22);
		frame.getContentPane().add(lblContact);
		
		contact = new JTextField();
		contact.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contact.setColumns(10);
		contact.setBounds(72, 275, 207, 29);
		frame.getContentPane().add(contact);
	}
	public boolean SQL() {
   	 try {
 		boolean p = false;
 		int reqAm = 0;
		 String toTransact = "ACCOUNT USER deposit an amount of " + SQLdep();

		connection = DriverManager.getConnection(url, username,password);
	   	 String insertQuery = "INSERT INTO accounts(Pin,Phone,Lastname,Firstname) VALUES(?,?,?,?)";
		 String insertQuery2 = "INSERT INTO accountdepot(Phone) SELECT Phone FROM accounts";
	   	 String updateQuery3 = "UPDATE accountdepot SET cash = ? WHERE Phone = ? ";
	   	 String insertQuery4 = "INSERT INTO accountdepot(accountId) VALUES(?)";
		 String insertQuery5 = "INSERT INTO requests(receiverPhone) SELECT Phone FROM accounts";
	   	 String insertQuery6 = "INSERT INTO requests(requester,requestAmount) VALUES(?,?) ";
	   	 String insertQuery7 = "INSERT INTO transactions(Phone,transacts) VALUES(?,?)";
		
         ps1=connection.prepareStatement(insertQuery);
         ps1.setShort(1, mpin);
         ps1.setString(2, phone);
         ps1.setString(3, lname);
         ps1.setString(4, fname);
         
         ps2=connection.prepareStatement(insertQuery2);
 
         
         ps3=connection.prepareStatement(updateQuery3);
         ps3.setInt(1,SQLdep());
         ps3.setString(2,phone);
         
         ps4=connection.prepareStatement(insertQuery4);
         ps4.setInt(1, id);
         
         ps5=connection.prepareStatement(insertQuery5);
         
         ps6=connection.prepareStatement(insertQuery6);
         ps6.setInt(1, reqAm);
         ps6.setInt(2, reqAm);
         
         
         ps7=connection.prepareStatement(insertQuery7);
         ps7.setString(1, phone);
         ps7.setString(2, toTransact);
         
        
         if(fn.getText().isEmpty() || ln.getText().isEmpty() || contact.getText().isEmpty()) {
       	  p = false;
        	 ps1.cancel();
        	 ps2.cancel();
        	 ps3.cancel();
        	 ps4.cancel();
        	 ps5.cancel();
        	 ps6.cancel();
        	 ps7.cancel();
        	 JOptionPane.showMessageDialog(null, "Validation Error!","ERROR",JOptionPane.ERROR_MESSAGE);
       	     
        	
         }
         else {
       	  p = true;
        	 ps1.executeUpdate();
        	 ps2.executeUpdate();
        	 ps3.executeUpdate();
        	 ps4.executeUpdate();
        	 ps5.executeUpdate();
        	 ps6.executeUpdate();
        	 ps7.executeUpdate();
        	  
        	
         }
         
         if(p == true) {
        	 SQLreturn = true;
         }
         else {
        	 SQLreturn = false;
         }
        
         
         ps1.close();
         ps2.close();
         ps3.close();
         ps4.close();
         ps5.close();
         ps6.close();
         ps7.close();
         connection.close();
         
       
         
         JOptionPane.showMessageDialog(null,"Welcome " + fname.toUpperCase() + "!");
         
         
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();

	}
	return SQLreturn;

	}
	public int SQLdep() {
		deposit = Integer.parseInt(JOptionPane.showInputDialog("Please Deposit a Amount"));
        if(deposit != 0 && deposit > 0) {
       return deposit;
}
        else {
			JOptionPane.showMessageDialog(null, "Not enough amount!", "Error", JOptionPane.ERROR_MESSAGE);
			return 0;
        }
		
	}
}
