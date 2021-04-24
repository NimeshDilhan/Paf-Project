package model;

import java.sql.*;

public class Project {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test1", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertProject(String projectCode, String projectName, String projectPrice, String projectDesc) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			

			String query = "INSERT INTO projects(`projectCode`, `projectName`, `projectPrice`, `projectDesc`) VALUES (?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
//			preparedStmt.setInt(1, 0);
			preparedStmt.setString(1, projectCode);
			preparedStmt.setString(2, projectName);
			preparedStmt.setString(3, projectPrice);
			preparedStmt.setString(4, projectDesc);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}catch (Exception e) {
			output = "Error while inserting the item.";
			System.out.println(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return output;
		
	}
	
	public String readProject() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border='1'><tr><th>Project Code</th><th>Project Name</th>" + "<th>Project Price</th>"
					+ "<th>Project Description</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from projects";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			

			while (rs.next()) {
				String projectID = Integer.toString(rs.getInt("projectID"));
				String projectCode = rs.getString("projectCode");
				String projectName = rs.getString("projectName");
				String projectPrice = Double.toString(rs.getDouble("projectPrice"));
				String projectDesc = rs.getString("projectDesc");
				
				output += "<tr><td>" + projectCode + "</td>";
				output += "<td>" + projectName + "</td>";
				output += "<td>" + projectPrice + "</td>";
				output += "<td>" + projectDesc + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='projects.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='projectID' type='hidden' value='" + projectID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}catch (Exception e) {
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	public String updateProject(String ID, String code, String name, String price, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE projects SET projectCode=?,projectName=?,projectPrice=?,projectDesc=? WHERE projectID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}catch (Exception e) {
			output = "Error while updating the project.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteProject(String projectID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from projects where projectID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(projectID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}catch (Exception e) {
			output = "Error while deleting the project.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
