import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EastPanel extends JPanel{
	
	ArrayList<Item> itemList;
	private DefaultListModel model;
	private ArrayList<String> selectedItemsList = new ArrayList<String>();
	public EastPanel(ArrayList<Item> theItemList) {
		super();
		this.itemList = theItemList;
		makePanel();
	}
	
	public void makePanel() {
		this.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("Selected Items");
		JPanel back = new JPanel();
		back.add(label1);
		this.add(back, BorderLayout.NORTH);
		
		model = new DefaultListModel();
		
		JList itemList = new JList(model);
		
		itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		itemList.setLayoutOrientation(JList.VERTICAL);
		itemList.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(itemList);
		listScroller.setPreferredSize(new Dimension(230, 200));
		
		JPanel backPanel = new JPanel();
		backPanel.add(listScroller);
		this.add(backPanel, BorderLayout.CENTER);
		
		itemList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selectedItemsList.clear();
				boolean adjust = itemList.getValueIsAdjusting();
				if(!adjust) {

					List selected = itemList.getSelectedValuesList();
					for(int i = 0; i < selected.size(); i++) {
						selectedItemsList.add(selected.get(i).toString());
					}
				}
				
			}
			
		});
		
	}
	
	public DefaultListModel getListModel() {
		return model;
	}
	public ArrayList<String> getSelectedItemsList() {
		return selectedItemsList;
	}

}
