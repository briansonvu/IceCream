import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DatabaseGUI extends JFrame{
	
	public static int myWidth = 600;
	public static int myHeight = 720;
	ArrayList<Item> itemList;
	public DatabaseGUI(ArrayList<Item> theItemList) {
		super();
		this.itemList = theItemList;
	}
	
	public void createWindow() {
		//create Frame
		this.setTitle("ABC IceCream Database");
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(myWidth,myHeight));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
		
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE); //this.DISPOSE_ON_CLOSE  switch this in later

		LogoPanel logo = new LogoPanel();
		this.add(logo, BorderLayout.NORTH);
		
		JPanel backPanel = new JPanel();
		backPanel.setLayout(new BorderLayout());
		this.add(backPanel, BorderLayout.CENTER);
		

		
		WestPanel west = new WestPanel(this.itemList);
		backPanel.add(west, BorderLayout.WEST);
		
		SouthPanel south = new SouthPanel(this.itemList);
		backPanel.add(south, BorderLayout.SOUTH);
		
		EastPanel east = new EastPanel(this.itemList);
		backPanel.add(east, BorderLayout.EAST);
		
		CenterPanel center = new CenterPanel(west, east, south, this.itemList);
		backPanel.add(center, BorderLayout.CENTER);
		
		NorthPanel north = new NorthPanel(this.itemList, center);
		backPanel.add(north, BorderLayout.NORTH);
	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
































