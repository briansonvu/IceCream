import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class SouthPanel extends JPanel{

	public ArrayList<Item> itemList;
	private DefaultListModel model;
	public SouthPanel(ArrayList<Item> theItemList) {
		super();
		this.itemList = theItemList;
		makePanel();
	}
	
	public void makePanel() {
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		JLabel label = new JLabel("Selected Items Detailed Description");
		JPanel back = new JPanel();
		back.add(label);
		
		this.add(back, BorderLayout.NORTH);
		
		model = new DefaultListModel();
		
		//JPanel third = new JPanel();
		JList items = new JList(model);
		items.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		items.setLayoutOrientation(JList.VERTICAL);
		items.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(items);
		listScroller.setPreferredSize(new Dimension(570, 300));
		
		JPanel backPanel = new JPanel();
		backPanel.add(listScroller);
		this.add(backPanel, BorderLayout.CENTER);
	}
	
	public DefaultListModel getListModel() {
		return model;
	}
	
	public void setListModel(String item) {
		model.addElement(item);
	}
}
