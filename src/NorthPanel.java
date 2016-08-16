import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NorthPanel extends JPanel{

	ArrayList<Item> itemList;
	CenterPanel center;


	public NorthPanel(ArrayList<Item> theItemList, CenterPanel center) {
		super();
		this.center = center;
		this.itemList = theItemList;
		makePanel();
	}

	public void makePanel() {
		this.setLayout(new FlowLayout());
		JButton b1 = new JButton("Add");
		JButton b2 = new JButton("Edit");
		JButton b3 = new JButton("Delete");
		JButton b4 = new JButton("Sort");

		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);


		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String itemName = JOptionPane.showInputDialog("Input Item Name");

				while(true) {
					boolean condition = true;
					for(Item e: itemList ) {
						if(e.itemName.equals(itemName) || itemName.equals("")) {
							itemName = JOptionPane.showInputDialog("Input Item Name");
							condition = false;
							continue;
						}
					}
					if(condition) {
						break;
					}
				}

				String maker = JOptionPane.showInputDialog("Enter Maker Name");
				while(true) {
					if(!maker.equals("")) {
						break;
					} else {
						maker = JOptionPane.showInputDialog("Enter Maker Name");
					}
				}
				String date = JOptionPane.showInputDialog("Enter Date Made");
				while(true) {
					if(!date.equals("")) {
						break;
					} else {
						date = JOptionPane.showInputDialog("Enter Date Made");
					}
				}
				String description = JOptionPane.showInputDialog("Enter Item Description");
				while(true) {
					if(!description.equals("")) {
						break;
					} else {
						description = JOptionPane.showInputDialog("Enter Item Description");
					}
				}

				ArrayList<Integer> listID = DatabaseAccess.getID();
				int ID = 1;
				for(int i = 0; i < listID.size(); i++) {
					if(listID.get(i) == ID) {
						ID++;
					}
				}

				Item newItem = new Item(ID, itemName, maker, date, description);
				itemList.add(newItem);
				DatabaseAccess.addItem(ID, itemName, maker, date, description);

				center.west.getListModel().addElement(newItem.itemName);
			}

		});

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("Enter the name of item you want to edit.");
				while(true) {
					if(!name.equals("")) {
						break;
					} else {
						name = JOptionPane.showInputDialog("Enter the name of item you want to edit.");
					}
				}

				boolean exist = false;
				for(int i = 0; i < itemList.size(); i++) {
					if(itemList.get(i).itemName.equals(name)) {
						exist = true;
						break;
					}
				}

				if(exist == true) {

					String field = JOptionPane.showInputDialog("Enter the field you would like to update: Item_Name, Maker, Imported_Date, or Description");
					while(true) {
						if(!field.equals("")) {
							break;
						} else {
							field = JOptionPane.showInputDialog("Enter the field you would like to update: Item_Name, Maker, Imported_Date, or Description");
						}
					}

					switch(field) {

					case "Item_Name":
						String newItemName = JOptionPane.showInputDialog("Input new Item Name.");
						while(true) {
							if(!newItemName.equals("")) {
								break;
							} else {
								newItemName = JOptionPane.showInputDialog("Input new Item Name.");
							}
						}
						DatabaseAccess.updateItem(field, newItemName, name);

						for(int i = 0; i < center.west.getListModel().size(); i++ ) {
							if(center.west.getListModel().getElementAt(i).equals(name)) {
								center.west.getListModel().remove(i);
								center.west.getListModel().addElement(newItemName);
							}
						}
						for(int i = 0; i < center.east.getListModel().size(); i++ ) {
							if(center.west.getListModel().getElementAt(i).equals(name)) {
								center.east.getListModel().remove(i);
								center.east.getListModel().addElement(newItemName);
							}
						}

						break;
					case "Maker":

						String maker = JOptionPane.showInputDialog("Input new maker.");
						while(true) {
							if(!maker.equals("")) {
								break;
							} else {
								newItemName = JOptionPane.showInputDialog("Input new maker.");
							}
						}
						DatabaseAccess.updateItem(field, maker, name);

						break;
					case "Imported_Date":

						String date = JOptionPane.showInputDialog("Input new make date.");
						while(true) {
							if(!date.equals("")) {
								break;
							} else {
								date = JOptionPane.showInputDialog("Input new make date.");
							}
						}
						DatabaseAccess.updateItem(field, date, name);
						break;
					case "Description":
						String description = JOptionPane.showInputDialog("Input new Description.");
						while(true) {
							if(!description.equals("")) {
								break;
							} else {
								description = JOptionPane.showInputDialog("Input new Description.");
							}
						}
						DatabaseAccess.updateItem(field, description, name);
						break;
					default:
					}

				} else {
					JOptionPane.showMessageDialog(center, "Item Does not Exist!");
				}
			}

		});

		b3.addActionListener(new ActionListener() { // delete

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String itemName = JOptionPane.showInputDialog("Enter Item Name to be deleted.");
				while(true) {
					if(!itemName.equals("")) {
						break;
					} else {
						itemName = JOptionPane.showInputDialog("Enter Maker Name");
					}
				}

				boolean exist = false;
				for(int i = 0; i < itemList.size(); i++) {
					if(itemList.get(i).itemName.equals(itemName)) {
						exist = true;
						break;
					}
				}

				if(exist == true) {
					for(int i = 0; i < center.west.getListModel().size(); i++) {

						if(center.west.getListModel().getElementAt(i).equals(itemName)) {
							center.west.getListModel().remove(i);
							break;
						}
					}
					for(int i = 0; i < center.east.getListModel().size(); i++) {

						if(center.east.getListModel().getElementAt(i).equals(itemName)) {
							center.east.getListModel().remove(i);
							break;
						}
					}

					DatabaseAccess.deleteItem(itemName);

					center.south.getListModel().clear();

					for(int i = 0; i < center.east.getListModel().size(); i++) {
						boolean exist2 = false;
						for(int j = 0; j < center.south.getListModel().size(); j++) {
							if(center.east.getListModel().getElementAt(i).equals(center.south.getListModel().getElementAt(j))) {
								exist2 = true;
							}
						}
						if(exist2 == false) {
							for(int j = 0; j < itemList.size(); j++) {
								if(center.east.getListModel().getElementAt(i).equals(itemList.get(j).itemName)) {
									int ID = itemList.get(j).ID;
									String name = itemList.get(j).itemName;
									String maker = itemList.get(j).maker;
									String date = itemList.get(j).date;
									String description = itemList.get(j).description;

									center.south.getListModel().addElement("ID: " + ID + "   Name: " + name + "   Maker: " + maker + "   Date: " + date + "   Description: " + description);

								}
							}
						}
					}
				}
			}

		});

		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				//sorts west panel

				ArrayList<String> temp = DatabaseAccess.getTheItemNameList();
				center.west.getListModel().removeAllElements();

				for(int i = 0; i < temp.size(); i++) {
					center.west.getListModel().addElement(temp.get(i));
				}

				
				for(int i = center.east.getListModel().size() - 1; i >= 0; i--) {
					for(int j = center.west.getListModel().size() - 1; j >= 0; j--) {
						if(center.west.getListModel().getElementAt(j).equals(center.east.getListModel().getElementAt(i))) {
							
							center.west.getListModel().remove(j);
						}
					}
				}

				//sorts east panel
				ArrayList<String> tempEast = new ArrayList<String>();

				for(int i = 0; i < temp.size(); i++) {
					for(int j = 0; j < center.east.getListModel().size(); j++) {
						if(center.east.getListModel().getElementAt(j).equals(temp.get(i))) {
							tempEast.add(temp.get(i));
						}
					}
				}

				center.east.getListModel().removeAllElements();
				for(int i = 0; i < tempEast.size(); i++) {
					center.east.getListModel().addElement(tempEast.get(i));
				}

				//sorts south panel

				ArrayList<String> tempSouth = new ArrayList<String>();

				for(int i = 0; i < temp.size(); i++) {

					String item;
					int ID = itemList.get(i).ID;
					String name = itemList.get(i).itemName;
					String maker = itemList.get(i).maker;
					String date = itemList.get(i).date;
					String description = itemList.get(i).description;

					item = "ID: " + ID + "   Name: " + name + "   Maker: " + maker + "   Date: " + date + "   Description: " + description;

					for(int j = 0; j < center.south.getListModel().size(); j++) {
						if(center.south.getListModel().getElementAt(j).equals(item)) {
							tempSouth.add(item);
						}
					}
				}

				center.south.getListModel().removeAllElements();
				for(int i = 0; i < tempSouth.size(); i++) {
					center.south.getListModel().addElement(tempSouth.get(i));
				}
			}

		});
	}
}
