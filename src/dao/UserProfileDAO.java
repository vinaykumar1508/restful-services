package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.text.DateFormatter;

import request.UserProfileRequest;

import utils.DBConnection;

public class UserProfileDAO {
	
	public static DBConnection dbConnection;
	public static Connection connection;
	
	public void getDBConnection(){
		dbConnection = new DBConnection();
		 connection=dbConnection.getConnection();
		//return connection;
	}
	public boolean checkUserIdExistance(String userId){
		getDBConnection();
		String query = "select userId from user_profile where userId='"+userId+"'";
		Statement statement= null;
		System.out.println("query:"+query);
		try{
			System.out.println("connection object :"+ connection);
			statement=connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			
			boolean playerAlreadyExistflag=false;
			while(resultSet.next()){
				System.out.println(resultSet.getString("userId"));
				playerAlreadyExistflag=true;
			}
			
			return playerAlreadyExistflag;
		//
		}catch(SQLException  e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createUser(UserProfileRequest userProfileRequest){
		getDBConnection();
		//
		String userId=userProfileRequest.getUserId();
		String emailId=userProfileRequest.getEmailId();
		String firstName=userProfileRequest.getFirstName();
		String lastName=userProfileRequest.getLastName();
		String password=userProfileRequest.getPassword();
		String gender=userProfileRequest.getGender();
		String address=userProfileRequest.getAddress();
		
		String pattern="YYYY-MM-dd";
		SimpleDateFormat format= new SimpleDateFormat(pattern);
		java.util.Date today = new java.util.Date();
		String date=format.format(today);
		System.out.println("date:"+date);
		java.sql.Date createdDate=null;
		try {
			java.util.Date parsed= format.parse(date);
			createdDate=new java.sql.Date(parsed.getTime());
			System.out.println("createdDate sqlDate:"+createdDate.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String createUserQuery ="insert into user_profile values('"+userId+"','"+emailId+"','"+password+"','"+firstName+"','"+lastName+"','"+address+"','"+createdDate+"','"+gender+"')";
		System.out.println("createUserQuery:"+createUserQuery);
		Statement statement= null;
		try {
			statement=connection.createStatement();
			int rowInserted= statement.executeUpdate(createUserQuery);
			if(rowInserted>0){
				System.out.println("A new user was inserted successfully!");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return false;
		
		
	}
	
	public boolean userAuthentication(String userId, String password){
		
		getDBConnection();
		String userAuthentication="select * from user_profile where userId='"+userId+"' and password='"+password+"'";
		System.out.println("userAuthentication query :"+userAuthentication);
		Statement statement= null;
		try{
			statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(userAuthentication);
			if(resultSet.next()){
				return true;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static void main(String[] args) {
		UserProfileDAO dao= new UserProfileDAO();
		//System.out.println("result:"+dao.createUser());
		//System.out.println("result:"+ dao.checkUserIdExistance("dummy1"));
	}
}
