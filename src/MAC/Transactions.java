package MAC;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;

public class Transactions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transactions frame = new Transactions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Transactions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 556);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGlobalBank = new JLabel("GLOBAL BANK");
		lblGlobalBank.setForeground(Color.WHITE);
		lblGlobalBank.setBackground(Color.WHITE);
		lblGlobalBank.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblGlobalBank.setBounds(147, 7, 138, 42);
		contentPane.add(lblGlobalBank);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setForeground(Color.DARK_GRAY);
		panel.setBounds(-2, 6, 440, 48);
		contentPane.add(panel);
		
		JSeparator seperator_top = new JSeparator();
		seperator_top.setForeground(Color.DARK_GRAY);
		seperator_top.setBounds(0, 64, 435, 3);
		contentPane.add(seperator_top);
		
		JLabel t1 = new JLabel("New label");
		t1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t1.setBounds(10, 66, 417, 28);
		contentPane.add(t1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(0, 91, 435, 3);
		contentPane.add(separator);
		
		JLabel t2 = new JLabel("New label");
		t2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t2.setBounds(10, 92, 417, 28);
		contentPane.add(t2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBounds(0, 118, 435, 3);
		contentPane.add(separator_1);
		
		JLabel t3 = new JLabel("New label");
		t3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t3.setBounds(10, 120, 417, 28);
		contentPane.add(t3);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.DARK_GRAY);
		separator_2.setBounds(0, 146, 435, 3);
		contentPane.add(separator_2);
		
		JLabel t4 = new JLabel("New label");
		t4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t4.setBounds(10, 147, 417, 28);
		contentPane.add(t4);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.DARK_GRAY);
		separator_3.setBounds(0, 174, 435, 3);
		contentPane.add(separator_3);
		
		JLabel t5 = new JLabel("New label");
		t5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t5.setBounds(10, 175, 417, 28);
		contentPane.add(t5);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.DARK_GRAY);
		separator_4.setBounds(0, 201, 435, 3);
		contentPane.add(separator_4);
		
		JLabel t6 = new JLabel("New label");
		t6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t6.setBounds(9, 203, 417, 28);
		contentPane.add(t6);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.DARK_GRAY);
		separator_5.setBounds(-1, 229, 435, 3);
		contentPane.add(separator_5);
		
		JLabel t7 = new JLabel("New label");
		t7.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t7.setBounds(9, 229, 417, 28);
		contentPane.add(t7);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(Color.DARK_GRAY);
		separator_6.setBounds(0, 257, 435, 3);
		contentPane.add(separator_6);
		
		JLabel t8 = new JLabel("New label");
		t8.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t8.setBounds(9, 257, 417, 28);
		contentPane.add(t8);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.DARK_GRAY);
		separator_7.setBounds(0, 286, 435, 3);
		contentPane.add(separator_7);
		
		JLabel t9 = new JLabel("New label");
		t9.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t9.setBounds(9, 284, 417, 28);
		contentPane.add(t9);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setForeground(Color.DARK_GRAY);
		separator_8.setBounds(0, 313, 435, 3);
		contentPane.add(separator_8);
		
		JLabel t10 = new JLabel("New label");
		t10.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t10.setBounds(9, 312, 417, 28);
		contentPane.add(t10);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setForeground(Color.DARK_GRAY);
		separator_9.setBounds(0, 339, 435, 3);
		contentPane.add(separator_9);
		
		JLabel t11 = new JLabel("New label");
		t11.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t11.setBounds(8, 339, 417, 28);
		contentPane.add(t11);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setForeground(Color.DARK_GRAY);
		separator_10.setBounds(0, 366, 435, 3);
		contentPane.add(separator_10);
		
		JLabel t12 = new JLabel("New label");
		t12.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t12.setBounds(8, 365, 417, 28);
		contentPane.add(t12);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setForeground(Color.DARK_GRAY);
		separator_11.setBounds(0, 393, 435, 3);
		contentPane.add(separator_11);
		
		JLabel t13 = new JLabel("New label");
		t13.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t13.setBounds(8, 393, 417, 28);
		contentPane.add(t13);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setForeground(Color.DARK_GRAY);
		separator_12.setBounds(0, 422, 435, 3);
		contentPane.add(separator_12);
		
		JLabel t14 = new JLabel("New label");
		t14.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t14.setBounds(8, 420, 417, 28);
		contentPane.add(t14);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setForeground(Color.DARK_GRAY);
		separator_13.setBounds(0, 450, 435, 3);
		contentPane.add(separator_13);
		
		JLabel t15 = new JLabel("New label");
		t15.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		t15.setBounds(8, 449, 417, 28);
		contentPane.add(t15);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setForeground(Color.DARK_GRAY);
		separator_14.setBounds(0, 479, 435, 3);
		contentPane.add(separator_14);
	}
}
