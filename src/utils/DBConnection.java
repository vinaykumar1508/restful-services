package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnection {
	public static void main(String[] args) {
		
		String url ="jdbc:mysql://localhost:3306/mysql";
		String user="root";
		String password="";
		//getConnection(url,user,password);
		
	}
	
	public  Connection getConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("test");
			try {
				String url ="jdbc:mysql://localhost:3306/mysql";
				String user="root";
				String password="";
				Connection connect= DriverManager.getConnection(url,user,password);
				System.out.println("connection::"+connect);
				return connect;
				/*Statement statement=null;
				statement = connect.createStatement();
				String query="insert into user_profile values('dummy1','dummy@test.com','dummy','firstname','lastname','address',CURDATE(),'M');";
				statement.executeUpdate(query);
				System.out.println("query done!!");*/
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
