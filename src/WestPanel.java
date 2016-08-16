import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
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

public class WestPanel extends JPanel{

	public static String something;
	private ArrayList<Item> itemList;
	private DefaultListModel model;
	private ArrayList<String> selectedIceCreamList = new ArrayList<String>();
	public WestPanel(ArrayList<Item> theItemList) {
		super();
		this.itemList = theItemList;
		makePanel();
	}

	public void makePanel() {
		this.setLayout(new BorderLayout());
		JLabel label1 = new JLabel("IceCream Item List");
		JPanel back = new JPanel();
		back.add(label1);
		this.add(back, BorderLayout.NORTH);

		model = new DefaultListModel();
		for(Item e: itemList) {
			model.addElement(e.itemName);
		}

		JList myList = new JList(model);

		myList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selectedIceCreamList.clear();
				boolean adjust = myList.getValueIsAdjusting();
				if(!adjust) {

					List selected = myList.getSelectedValuesList();
					for(int i = 0; i < selected.size(); i++) {
						selectedIceCreamList.add(selected.get(i).toString());
					}
				}
			}

		});
		myList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		myList.setLayoutOrientation(JList.VERTICAL);
		myList.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(myList);
		listScroller.setPreferredSize(new Dimension(230, 200));

		JPanel backPanel = new JPanel();
		backPanel.add(listScroller);
		this.add(backPanel, BorderLayout.CENTER);


	}

	public DefaultListModel getListModel() {
		return model;
	}
	public ArrayList<String> getSelectedIceCream() {
		return selectedIceCreamList;
	}
}
