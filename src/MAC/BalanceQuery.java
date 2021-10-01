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

public class BalanceQuery extends JFrame {
	GetSet gs = new GetSet();
	Vars v;
    protected static JTextField cash;
    protected static String userPhone;
    
    protected static Connection connection;
    protected static PreparedStatement ps ;
    protected static ResultSet rs;
    protected String getCash;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 * @param string 
	 * @param phone 
	 */
	public BalanceQuery(String string) {
		userPhone = string;
		try {
			//SQL(userPhone);
			
			 final String url = "jdbc:mysql://localhost:3306/ATM";
			 final String username = "marcthe5";
			 final String password = "Myloveones1";
			 Statement stmt;
			connection = DriverManager.getConnection(url, username,password);
			   	String retrieveQuery = "SELECT * FROM accountdepot WHERE Phone = " + userPhone;

			   	stmt=connection.createStatement();
			   	rs = stmt.executeQuery(retrieveQuery);

		        while(rs.next()) {
		        	getCash = rs.getString("cash");
		        	
		        }
		        stmt.close();
		        rs.close();
		        connection.close();
		   	    }
		      catch(Exception e) {
		    	  e.printStackTrace();
		      }
		
		
        setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 196);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInputAmount = new JLabel("BALANCE");
		lblInputAmount.setBounds(139, 13, 106, 30);
		contentPane.add(lblInputAmount);
		lblInputAmount.setForeground(Color.WHITE);
		lblInputAmount.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 11, 380, 37);
		contentPane.add(panel);
		
		JButton btnDeposit = new JButton("CLOSE");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 BalanceQuery frame = new BalanceQuery(userPhone);
				 frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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
		
	    cash = new JTextField(getCash);
	    cash.setEnabled(false);
		cash.setForeground(Color.BLACK);
		cash.setFont(new Font("Segoe UI", Font.BOLD, 18));
		cash.setBounds(69, 52, 232, 46);
		contentPane.add(cash);
	}
	/* public static boolean SQL(String user) throws NullPointerException, SQLException {
		 final String url = "jdbc:mysql://localhost:3306/ATM";
		 final String username = "marcthe5";
		 final String password = "Myloveones1";
		 Statement stmt;
		
	   	 try {

			connection = DriverManager.getConnection(url, username,password);
		   	String retrieveQuery = "SELECT * FROM accountdepot WHERE Phone = " + user;

		   	stmt=connection.createStatement();
		   	rs = stmt.executeQuery(retrieveQuery);

	        while(rs.next()) {
	        	String getCash = rs.getString("cash");
	        cash.setText(getCash);
	        	
	        }
	        stmt.close();
	        rs.close();
	        connection.close();
	   	    }
	      catch(Exception e) {
	    	  e.printStackTrace();
	        }
		return true;
}
*/
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 BalanceQuery frame = new BalanceQuery(userPhone);

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


	
