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

public class RegistrationGUI extends JFrame{

	ArrayList<Item> itemList;
	public RegistrationGUI (ArrayList<Item> theItemList) {
		super();
		itemList = theItemList;
	}

	public void createWindow() {
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 300));
		this.setTitle("Registration Page");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
		
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.CENTER);
		
		LogoPanel logoPanel = new LogoPanel();
		this.add(logoPanel, BorderLayout.NORTH);
		
		JLabel userlabel = new JLabel("Create Username");
		panel.add(userlabel, BorderLayout.CENTER);
		
		JTextField username = new JTextField(20);
		panel.add(username);
		
		JLabel passwordlabel = new JLabel("Create Password");
		panel.add(passwordlabel);
		
		JPasswordField password = new JPasswordField(20);
		panel.add(password, BorderLayout.EAST);
		
		JButton registerButton = new JButton("Register");
		panel.add(registerButton);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String myName = username.getText();
				String myPassword = password.getText();
				int i = DatabaseAccess.logInUser(myName, myPassword);
				if(i==1){
					JOptionPane.showMessageDialog(panel, "Username already exists or fields are empty!");
				} else {
					DatabaseAccess.addUser(myName, myPassword);
					JOptionPane.showMessageDialog(panel, "User registered!");
					DatabaseGUI dataPage = new DatabaseGUI(itemList);
					dataPage.createWindow();
					dispose();
				}
				
			}
			
		});
		
		JButton cancelButton = new JButton("Cancel");
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});

	}
}
