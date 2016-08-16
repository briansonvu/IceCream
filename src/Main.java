import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main {

	public static int myWidth = 400;
	public static int myHeight = 400;
	public static JFrame frame;
	
	
	public static void main(String[] args) {

		ArrayList<Item> itemList = new ArrayList<Item>();
		DatabaseAccess.getALL(itemList);
	
		
		//create Frame
		frame = new JFrame("ABC IceCream Database");
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(myWidth,myHeight));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		LogoPanel logoPanel = new LogoPanel();
		frame.add(logoPanel, BorderLayout.NORTH);
		createLoginPanel(itemList);
		frame.pack();
		frame.setVisible(true);
		//center the creen
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
		
		


	}
	
	public static void createLoginPanel(ArrayList<Item> theItemList) {

		JPanel panel = new JPanel();
		frame.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton registerButton = new JButton("register");
		registerButton.setBounds(10, 80, 80, 25);
		panel.add(registerButton);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrationGUI registration = new RegistrationGUI(theItemList);
				registration.createWindow();
				frame.dispose();
			}

		});

		JButton loginButton = new JButton("Login");
		panel.add(loginButton);
		loginButton.setBounds(180, 80, 80, 25);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


				String myName = userText.getText();
				String myPassword = passwordText.getText();
				int i = DatabaseAccess.logInUser(myName, myPassword);
				if(i==1){
					DatabaseGUI dataPage = new DatabaseGUI(theItemList);
					dataPage.createWindow();
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame, "Wrong Username or Password");
				}
			}
		});


	}
}