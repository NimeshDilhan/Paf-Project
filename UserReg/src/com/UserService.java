package com;
import java.sql.*;
import util.DBconnection;
import model.*;


public class UserService {
Connection con = null;
	
	public UserService() {
		
		con = DBconnection.connecter();
	}
	
	
	
	
	public String insertUsers(User User)
	 {
		String query = " insert into Users(`userid`,`username`,`name`,`password')"
				  + " values (?,?, ?, ?)";
		  
	 String output;
		try {	
				PreparedStatement preparedStatement = con.prepareStatement(query); 
				preparedStatement.setString(1, User.getUserid());
				preparedStatement.setString(2, User.getUsername());
				preparedStatement.setString(3, User.getName());
				preparedStatement.setString(4,  User.getPassword());

				preparedStatement.execute();
				 con.close();
			  output = "Inserted successfully";
			
		} catch (SQLException e) {
		    output = "Error while inserting the User.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String readUsers()
	 {
	 String output = "";
	 try
	 {
	
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 //output = "<table border=\"1\"><tr><th>Fund ID</th><th>Funding Agency</th><th>Address</th><th>Phone</th><th>Prject ID</th><th>Fund Amount</th></tr>";
	 
	 output = "<table border=\"1\"><tr><th>User name</th><th>Name</th><th>Password</th></tr>";
	 String query = "select * from Users";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String userid = rs.getString("userid");
		 String username = rs.getString("username");
		 String name = rs.getString("name");
		 String password = rs.getString("password");
	     // Add into the html table
		 output += "<tr><td>" + userid + "</td>";
		 output += "<td>" + username + "</td>";
		 output += "<td>" + name + "</td>";
		 output += "<td>" + password + "</td>";
	 
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the Users.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String updateUsers(User Users){
	
	 String query = "UPDATE Users SET username=?,name=?,password=? WHERE userid=?";
	 String output = "";
	 try
	 {
	 
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 PreparedStatement preparedStatement = con.prepareStatement(query);
	 
	 	
		preparedStatement.setString(1, Users.getUsername());
		preparedStatement.setString(2, Users.getName());
		preparedStatement.setString(3,  Users.getPassword());
		preparedStatement.setString(4, Users.getUserid());
				
		preparedStatement.execute();
		con.close();
		output = "Updated successfully";
	}
	 catch (Exception e)
	 {
	 output = "Error while updating the Users.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String deleteUsers(User Users){
		String query = "delete from Users where userid=?";
		String output;
		
		
		try {
			
	 if (con == null){
		 return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 
	 PreparedStatement preparedStatement = con.prepareStatement(query);
	 // binding values
	 preparedStatement.setString(1,Users.getUserid() );
	 // execute the statement
	 if(preparedStatement.execute()) {
		 output = "Deleted successfully";
	 }else {
		 output = "Deleted successfully";
	 }
		
	 con.close();
	 
	 }catch (Exception e){
	 output = "Error while deleting the Users.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String login(User user) {
		String output = " ";
		
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			//String query = "SELECT * FROM user WHERE id=? and password=?";
			
			String query = "SELECT * FROM user WHERE id="+user.getUserid()+ " and password='"+user.getPassword()+"'";
			
			System.out.println(query);
			
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			
			if (!results.isBeforeFirst() ) {    
			    System.out.println("Invalid User Credentials"); 
			    
			    output = "Invalid User Credentials !!!!";
			}
			// iterate through the rows in the result set
			while (results.next()) {
				
				output = "Valid User, Welcome "+results.getString("first_name")+" "+results.getString("last_name")+", User Role: "+results.getString("user_role");				   
			}

			
		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

}
