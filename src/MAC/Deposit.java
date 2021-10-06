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
import java.awt.event.ActionEvent;

public class Deposit extends JFrame {
	Vars v;
	
	protected static String phone;

	private JPanel contentPane;
	private JTextField textField;

	 protected Connection connection;
	 protected PreparedStatement ps;
	 protected PreparedStatement ps1;
	 protected ResultSet rs;

	/**
	 * Create the frame.
	 * @param userPhone 
	 */
	public Deposit(String userPhone) {
		setResizable(false);
		phone = userPhone;
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 220);
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
		
		JButton btnDeposit = new JButton("DEPOSIT");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AuthenticationPanel ap = new AuthenticationPanel();
				int deposit;
				deposit = Integer.parseInt(textField.getText());
				try {
				SQL(deposit,phone);
				if(Vars.SQLreturn == true) {
					Deposit frame = new Deposit(phone);
					JOptionPane.showMessageDialog(null, "Deposit Successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
					 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					frame.setVisible(false);
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Not enough amount!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			catch(Exception e) {
				e.printStackTrace();
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
		
		JButton btnNewButton = new JButton("CLOSE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deposit frame = new Deposit(phone);
				 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

			}
			
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnNewButton.setBounds(135, 164, 99, 31);
		contentPane.add(btnNewButton);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 Deposit frame = new Deposit(phone);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public boolean SQL(int dep, String user) throws NullPointerException {
		 final String url = "jdbc:mysql://localhost:3306/ATM";
		 final String username = "marcthe5";
		 final String password = "Myloveones1";
		 String toTransact = "ACCOUNT USER deposit an amount of " + dep;
		
		
	   	 try {

			connection = DriverManager.getConnection(url, username,password);
		   	 String updateQuery = "UPDATE accountdepot SET cash = cash + ? WHERE Phone = ? ";
		   	 String updateQuery1 = "UPDATE transactions SET transacts = ? WHERE Phone = ?";
		   	 
		   	 
		   	ps=connection.prepareStatement(updateQuery);         
		   	ps.setInt(1, dep);
		   	ps.setString(2, user);
		   	
		   	ps1=connection.prepareStatement(updateQuery1);         
		   	ps1.setString(1, toTransact);
		   	ps1.setString(2, user);
	       
		   	
	         
	         if(dep != 0 && dep > 0) {
	 	        ps.executeUpdate();
	 	        ps1.executeUpdate();
		         Vars.SQLreturn = true;
	         }
	         else {
	 	        ps.cancel();
	 	        ps1.cancel();
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
