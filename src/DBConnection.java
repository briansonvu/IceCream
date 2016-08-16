
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import com.mysql.jdbc.Statement;


// Connection class that handels DB connection.
public class DBConnection {
	
	private Connection Conn ; 
	
	
	// Initialize connection 
	 DBConnection( String theConnectionURL, String theUserName, String theUserPassword)
	 {
	  // try connect 
	       try {
	          Class.forName("com.mysql.jdbc.Driver");
	          Conn = DriverManager.getConnection(theConnectionURL, theUserName, theUserPassword);      
	       } catch (Exception e) {
	          e.printStackTrace();
	       }
	 } // end Initialization
	
		public void getALL(ArrayList<Item> list){

			StringBuilder sb = new StringBuilder();
			Statement statement;
			
			int ID;
			String itemName;
			String maker;
			String date;
			String description;

			try {
				statement = (Statement) Conn.createStatement();
				String queryString; 
				queryString = "SELECT * FROM Item";

				ResultSet rset = statement.executeQuery(queryString);

				while(rset.next()) {
					ID = rset.getInt(1);
			        itemName = rset.getString(2);
			        maker = rset.getString(3);
					date = rset.getString(4);
					description = rset.getString(5);
					
					
					Item item = new Item(ID, itemName, maker, date, description);
					list.add(item);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		public void addItem(int id, String itemName, String maker, String date, 
				String description) {
			Statement statement;
			try {
				statement = (Statement) Conn.createStatement();
				String queryString; 
				
				queryString = "INSERT INTO Item " +
						 "VALUES (" + 
						id + ", '" + 
						itemName + "', '" + 
					    maker + "', '" + 
						date + "', '" + 
						description + "')";
                   
				int rset = statement.executeUpdate(queryString);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		public void updateItem(String field, String updatedField, String theItemName) {
			Statement statement;
			try {
				statement = (Statement) Conn.createStatement();
				String queryString; 
				queryString = "update Item "
						+ "set " + field +  " = '" + updatedField + "'"
						+ " where Item_Name = '" + theItemName + "'";
				int rset = statement.executeUpdate(queryString);

			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
			
		public void deleteItem(String theItemName) {
			Statement statement;
			try {
				statement = (Statement) Conn.createStatement();
				String queryString; 
				
				queryString = "DELETE FROM Item " +
						"WHERE Item_Name = '" +  theItemName + "';";
                
				int rset = statement.executeUpdate(queryString);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		public ArrayList<String> sortItem() {
			
			ArrayList<String> tempItemList = new ArrayList<>();
			
			Statement statement;
			try {
				statement = (Statement) Conn.createStatement();
				String queryString; 
				queryString = "select Item_Name from Item order by Item_Name";
                
				
				ResultSet rset = statement.executeQuery(queryString);
				
				while(rset.next()) {
					tempItemList.add(rset.getString(1));
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
			return tempItemList;
		}
		
		public ArrayList<Integer> getID() {
			ArrayList<Integer> tempItemList = new ArrayList<Integer>();
			
			Statement statement;
			try {
				statement = (Statement) Conn.createStatement();
				
				String queryString = "select ID from Item order by ID;";
                
				
				ResultSet rset = statement.executeQuery(queryString);
				
				while(rset.next()) {
					tempItemList.add(rset.getInt(1));
					System.out.println(rset.getString(1));
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
			return tempItemList;
		}

		public int logInUser(String theUsername, String thePassword){
			Statement statement;
			try {
				statement = (Statement) Conn.createStatement();
				
				String queryString = "Select count(*) as a from user where user_id='"+theUsername+"'and pwd = '"+thePassword+"'";
                
				ResultSet rset = statement.executeQuery(queryString);
				rset.first();
				if(rset.getInt(1)==1)
					return 1;
				else 
					return 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}	
		}
		
		public void addUser(String theUsername, String thePassword){
			Statement statement;
			try {
				statement = (Statement) Conn.createStatement();
				
				String queryString = "INSERT INTO USER(user_id,pwd)VALUES('"+theUsername+"','"+thePassword+"')";
                
				statement.executeUpdate(queryString);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
}

