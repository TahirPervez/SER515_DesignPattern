package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class JFrame_Login extends JFrame {

	private JPanel contentPane;
	private JTextField textbox_username;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame_Login frame = new JFrame_Login();
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
	public JFrame_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		textbox_username = new JTextField();
		textbox_username.setText("");
		contentPane.add(textbox_username);
		textbox_username.setColumns(30);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = textbox_username.getText();
				System.out.println(username + " trying to log in.");
				if (verify(username, "files/BuyerInfo.txt")) { // buyer
					System.out.println(username + " is a buyer");
					
				} else if (verify(username, "files/SellerInfo.txt")) { // seller
					System.out.println(username + " is a seller");
				} else {
					System.out.println(username + " has no account");
					JDialog jDialog_loginError = new JDialog();
					jDialog_loginError.add(new JLabel("Error: Account Not Found"));
				}
				

			}
		});
		contentPane.add(btnLogin);
	}
	
	public boolean verify(String username, String filename) {
		try {
			Scanner scan = new Scanner(new File(filename));
			String str = "";
			while (scan.hasNextLine()) {
				str = scan.nextLine();
				String[] user = str.split(":");
				if (username.equals(user[0])) {
					scan.close();
					return true;
				}
			}
			scan.close();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		} 
	}

}
