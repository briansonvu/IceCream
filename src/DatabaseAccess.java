
import java.util.ArrayList;

public class DatabaseAccess {
	static String connectionUrl = "jdbc:mysql://localhost:3306/SonNow";
	static String connectionUser = "root";
	static String connectionPassword = "123456";
	
	static DBConnection db = new DBConnection(connectionUrl, connectionUser, connectionPassword);
	
	public static ArrayList<String> getTheItemNameList() {
		return db.sortItem();
	}
	
	public static void getALL(ArrayList<Item> list){
		db.getALL(list);
	}
	
	public static void addItem(int id, String itemName, String maker, String date, 
			String description) {
		db.addItem(id, itemName, maker, date, description);
	}
	
	public static void updateItem(String field, String updatedField, String theItemName) {
		db.updateItem(field, updatedField, theItemName);
	}
	
	public static void deleteItem(String theItemName) {
		db.deleteItem(theItemName);
	}
	public static ArrayList<Integer> getID() {
		return db.getID();
	}
	
	public static int logInUser(String username, String password){
		return db.logInUser(username, password);
	}
	
	public static void addUser(String username,String password){
		db.addUser(username, password);
	}

}
