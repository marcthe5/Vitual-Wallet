package MAC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class MainPanel extends JFrame{
	
	//params
	protected static String userPhone;

    protected static boolean visibility;


	/**
	 * Create the application.
	 */


	/**
	 * Initialize the contents of the  
	 * @param phone 
	 */
	public MainPanel(String phone) {
		userPhone = phone;

		 setResizable(false);
		 getContentPane().setBackground(Color.WHITE);
		 getContentPane().setLayout(null);
		
		JLabel lblGlobalBank = new JLabel("GLOBAL BANK");
		lblGlobalBank.setForeground(Color.WHITE);
		lblGlobalBank.setFont(new Font("Segoe UI", Font.BOLD, 33));
		lblGlobalBank.setBounds(210, 25, 235, 56);
		 getContentPane().add(lblGlobalBank);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(-12, 25, 834, 56);
		 getContentPane().add(panel);
		
		JButton btnBalanceInquiry = new JButton("BALANCE INQUIRY");
		btnBalanceInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BalanceQuery bq = new BalanceQuery(userPhone);
				bq.setVisible(true);
			}
		});
		btnBalanceInquiry.setFocusPainted(false);
		btnBalanceInquiry.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBalanceInquiry.setForeground(Color.WHITE);
		btnBalanceInquiry.setBackground(Color.DARK_GRAY);
		btnBalanceInquiry.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnBalanceInquiry.setBounds(10, 125, 176, 68);
		 getContentPane().add(btnBalanceInquiry);
		
		JButton btnDeposit = new JButton("DEPOSIT");
		btnDeposit.setFocusPainted(false);
		btnDeposit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Deposit dep = new Deposit(userPhone);
				dep.setVisible(true);
			}
		});
		btnDeposit.setForeground(Color.WHITE);
		btnDeposit.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDeposit.setBackground(Color.DARK_GRAY);
		btnDeposit.setBounds(245, 125, 176, 68);
		 getContentPane().add(btnDeposit);
		
		JButton btnWithdraw = new JButton("WITHDRAW");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Withdraw wd = new Withdraw(userPhone);
				wd.setVisible(true);
			}
		});
		btnWithdraw.setFocusPainted(false);
		btnWithdraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnWithdraw.setForeground(Color.WHITE);
		btnWithdraw.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnWithdraw.setBackground(Color.DARK_GRAY);
		btnWithdraw.setBounds(482, 125, 176, 68);
		 getContentPane().add(btnWithdraw);
		
		JButton btnTransaction = new JButton("TRANSACTION");
		btnTransaction.setFocusPainted(false);
		btnTransaction.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTransaction.setForeground(Color.WHITE);
		btnTransaction.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnTransaction.setBackground(Color.DARK_GRAY);
		btnTransaction.setBounds(10, 234, 176, 68);
		 getContentPane().add(btnTransaction);
		
		JButton btnExpressSend = new JButton("EXPRESS SEND");
		btnExpressSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExpressSend es = new ExpressSend(userPhone);
				es.setVisible(true);
			}
		});
		btnExpressSend.setFocusPainted(false);
		btnExpressSend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExpressSend.setForeground(Color.WHITE);
		btnExpressSend.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnExpressSend.setBackground(Color.DARK_GRAY);
		btnExpressSend.setBounds(245, 234, 176, 68);
		 getContentPane().add(btnExpressSend);
		
		JButton btnRequestAmount = new JButton("REQUEST AMOUNT");
		btnRequestAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Request rq = new Request(userPhone);
				rq.setVisible(true);
			}
		});
		btnRequestAmount.setFocusPainted(false);
		btnRequestAmount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRequestAmount.setForeground(Color.WHITE);
		btnRequestAmount.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnRequestAmount.setBackground(Color.DARK_GRAY);
		btnRequestAmount.setBounds(482, 234, 176, 68);
		 getContentPane().add(btnRequestAmount);
		 
		 JButton btnFetchRequests = new JButton("FETCH REQUESTS");
		 btnFetchRequests.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		GetRequests gq = new GetRequests(userPhone);
		 		gq.setVisible(true);
		 		
		 	}
		 });
		 btnFetchRequests.setForeground(Color.WHITE);
		 btnFetchRequests.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		 btnFetchRequests.setFocusPainted(false);
		 btnFetchRequests.setBackground(Color.DARK_GRAY);
		 btnFetchRequests.setBounds(245, 338, 176, 68);
		 getContentPane().add(btnFetchRequests);
		 setBounds(100, 100, 673, 446);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel window = new MainPanel(userPhone);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
