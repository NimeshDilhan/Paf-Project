package model;
import java.sql.*;
	public class payments
	{ //A common method to connect to the DB
		private Connection connect()
		{
			 Connection con = null;
			 try
			 {
				 Class.forName("com.mysql.jdbc.Driver");

				 	//Provide the correct details: DBServer/DBName, username, password
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/GB_Payment_DB?serverTimezone=UTC", "root", "");
				 
			 }
			 catch (Exception e)
			 {e.printStackTrace();}
			 return con;
		}
		//comment
		
		public String insertPaymentInfo(String paymentcode, String paymentType, String totalprice, String paymentStatus, String date, String time)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for inserting."; 
				}
				// create a prepared statement
				 String query = "insert into payments('PaymentID','PaymentCode','PaymentType','TotalPrice','PaymentStatus','Date'.'Time') values(?, ?, ?, ?, ?, ?, ?)";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, paymentcode);
				 preparedStmt.setString(3, paymentType);
				 preparedStmt.setDouble(4, Double.parseDouble(totalprice));
				 preparedStmt.setString(5, paymentStatus);
				 preparedStmt.setString(6, date);
				 preparedStmt.setString(7, time);
				// execute the statement
 
 
				 preparedStmt.execute();
				 con.close();
				 output = "Inserted successfully";
			}
			catch (Exception e)
			{
				 output = "Error while inserting the payment.";
				 System.err.println(e.getMessage());
				 System.out.println(e);
			}
			return output;
		}

		
		public String readPayments()
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Payment Code</th><th>Payment Name</th>" +
			 "<th>Total Price</th>" +
			 "<th>Payment Status</th>" +
			 "<th>Date</th>" +
			 "<th>Time</th>" +
			 "<th>Update</th><th>Remove</th></tr>";

			 String query = "select * from payments";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String PaymentID = Integer.toString(rs.getInt("PaymentID"));
				 String PaymentCode = rs.getString("PaymentCode");
				 String PaymentType = rs.getString("PaymentType");
				 String TotalPrice = Double.toString(rs.getDouble("TotalPrice"));
				 String PaymentStatus = rs.getString("PaymentStatus");
				 String Date = rs.getString("Date");
				 String Time = rs.getString("Time");
				 // Add into the html table
				 output += "<tr><td>" + PaymentCode + "</td>";
				 output += "<td>" + PaymentType + "</td>";
				 output += "<td>" + TotalPrice + "</td>";
				 output += "<td>" + PaymentStatus + "</td>";
				 output += "<td>" + Date + "</td>";
				 output += "<td>" + Time + "</td>";
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='payments.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='PaymentID' type='hidden' value='" + PaymentID
				 + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the payments.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}

		
		
		public String updatePaymentInfo(String paymentID,String paymentcode, String paymentType, String totalprice, String paymentStatus, String date, String time)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
	 // create a prepared statement
				 String query = "UPDATE payments SET PaymentCode=?,PaymentType=?,TotalPrice=?,PaymentStatus=?, Date=?, Time=? WHERE PaymentID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setString(1, paymentcode);
				 preparedStmt.setString(2, paymentType);
				 preparedStmt.setDouble(3, Double.parseDouble(totalprice));
				 preparedStmt.setString(4, paymentStatus);
				 preparedStmt.setString(5, date);
				 preparedStmt.setString(6, time);
				 preparedStmt.setInt(7, Integer.parseInt(paymentID));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Updated successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating the payments.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	
		
		
		public String deletePaymentInfo(String paymentID)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				// create a prepared statement
				String query = "delete from payments where PaymentID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(paymentID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the payments.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	} 