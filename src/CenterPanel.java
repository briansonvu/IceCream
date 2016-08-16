import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CenterPanel extends JPanel{

	WestPanel west;
	EastPanel east;
	SouthPanel south;
	ArrayList<Item> itemList;

	public CenterPanel(WestPanel west, EastPanel east, SouthPanel south, ArrayList<Item> itemList) {
		super();
		this.itemList = itemList;
		this.west = west;
		this.east = east;
		this.south = south;
		makePanel();
	}

	public void makePanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		JButton b1 = new JButton("Move Right");
		JButton b2 = new JButton(" Move Left ");

		gc.insets = new Insets(30,5,30,5);
		gc.gridx = 0;
		gc.gridy = 0;
		this.add(b1, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		this.add(b2, gc);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				south.getListModel().clear();
				
				for(int i = west.getListModel().size() - 1; i >= 0; i--) {
					for(int j = 0; j < west.getSelectedIceCream().size(); j++){
						if(west.getListModel().get(i).equals(west.getSelectedIceCream().get(j))) {
							east.getListModel().addElement(west.getSelectedIceCream().get(j));
							west.getListModel().remove(i);
						}
					}
				}

				for(int i = 0; i < east.getListModel().size(); i++) {
					boolean exist = false;
					for(int j = 0; j < south.getListModel().size(); j++) {
						if(east.getListModel().getElementAt(i).equals(south.getListModel().getElementAt(j))) {
							exist = true;
						}
					}
					if(exist == false) {
						for(int j = 0; j < itemList.size(); j++) {
							if(east.getListModel().getElementAt(i).equals(itemList.get(j).itemName)) {
								int ID = itemList.get(j).ID;
								String name = itemList.get(j).itemName;
								String maker = itemList.get(j).maker;
								String date = itemList.get(j).date;
								String description = itemList.get(j).description;

								south.getListModel().addElement("ID: " + ID + "   Name: " + name + "   Maker: " + maker + "   Date: " + date + "   Description: " + description);

							}
						}
					}
				}

			}

		});

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				south.getListModel().clear();
				
				for(int i = east.getListModel().size() - 1; i >= 0; i--) {
					for(int j = 0; j < east.getSelectedItemsList().size(); j++){
						if(east.getListModel().get(i).equals(east.getSelectedItemsList().get(j))) {
							west.getListModel().addElement(east.getSelectedItemsList().get(j));
							east.getListModel().remove(i);
						}
					}
				}

				for(int i = 0; i < east.getListModel().size(); i++) {
					boolean exist = false;
					for(int j = 0; j < south.getListModel().size(); j++) {
						if(east.getListModel().getElementAt(i).equals(south.getListModel().getElementAt(j))) {
							exist = true;
						}
					}
					if(exist == false) {
						for(int j = 0; j < itemList.size(); j++) {
							if(east.getListModel().getElementAt(i).equals(itemList.get(j).itemName)) {
								int ID = itemList.get(j).ID;
								String name = itemList.get(j).itemName;
								String maker = itemList.get(j).maker;
								String date = itemList.get(j).date;
								String description = itemList.get(j).description;
								
								south.getListModel().addElement("ID: " + ID + "   Name: " + name + "   Maker: " + maker + "   Date: " + date + "   Description: " + description);

							}
						}
					}
				}

			}

		});

	}
}
