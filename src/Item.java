
public class Item {
	
	int ID;
	String itemName;
	String maker;
	String date;
	String description;

	public Item(int theID, String theItemName, String theMaker, String theDate, String theDescription) {
		this.ID = theID;
		this.itemName = theItemName;
		this.maker = theMaker;
		this.date = theDate;
		this.description = theDescription;
		
	}
	public String toString() {
		
		return "ID: " + this.ID + "     Name: " + this.itemName + "     Maker: " + this.maker + "     Date: " + this.date + "     Description: " + this.description;
	}
}
