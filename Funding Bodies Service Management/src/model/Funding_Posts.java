package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Funding_Posts {
	private Connection connect()
	{
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide Correct Database Details
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadjet_badget?serverTimezone=UTC", "root", "");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	public String createPost(String fundMan_Name, String Email,String Contact,String fundman_ID) {
		String output="";
		
		try {
			Connection con=connect();
			
			if(con==null)
			{
				return "Error";
			}
			LocalDate date= LocalDate.now();
			LocalTime time= LocalTime.now();
			String  query= "insert into funds(fundMan_Name,Email,Contact,fundman_ID,funded_date,funded_time)"
							+
							" values(?,?,?,?,?,?) ";
			PreparedStatement ps=con.prepareStatement(query);
			
			ps.setString(1, fundMan_Name);
			ps.setString(2, Email);
			ps.setString(3, Contact);
			ps.setString(4, fundman_ID);
			ps.setString(5, date.toString());
			ps.setString(6, time.toString());
			
			ps.execute();
			con.close();
			
			output="Insert Success";
		}
		catch (Exception e) {
			// TODO: handle exception
			output="Error while inserting the Item";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	public String read_Post() {
		String output="";
		
		try {
			Connection con=connect();
			
			if(con==null)
			{
				return "Error";
			}
			output="<table><tr><th>Fund ID</th><th>Funda Name</th><th>Email</th><th>Contact</th><th>fundman_ID</th><th>Published Date</th><th>Published Time</th><th>Update</th><th>Remove</th></tr>";
			String query="select * from Funds";
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			
			while(rs.next())
			{
				String Fund_id= Integer.toString(rs.getInt("fund_ID"));
				String Fund_Name= rs.getString("fundMan_Name");
				String Email=rs.getString("Email");
				String contact=rs.getString("Contact");
				String FundmanID = rs.getString("fundman_ID");
				String date = rs.getString("funded_date");// How to Get Date as A String - Doubt
				String time = rs.getString("funded_time");// How to Get Date as A String - Doubt
				
				output +="<tr><td>"+Fund_id+"</td>";
				output +="<td>"+Fund_Name+"</td>";
				output +="<td>"+Email+"</td>";
				output +="<td>"+contact+"</td>";
				output +="<td>"+FundmanID+"</td>";
				output +="<td>"+date+"</td>";
				output +="<td>"+time+"</td>";
				
				output +="<td><input name=\\\"btnUpdate\\\" type=\\\"button\\\" \r\n" + 
						" value=\\\"Update\\\" class=\\\"btn btn-secondary\\\"></td>\"\r\n" + 
						" + \"<td><form method=\\\"post\\\" action=\\\"posts.jsp\\\">\"\r\n" + 
						" + \"<input name=\\\"btnRemove\\\" type=\\\"submit\\\" value=\\\"Remove\\\" \r\n" + 
						" class=\\\"btn btn-danger\\\">\"\r\n" + 
						" + \"<input name=\\\"id\\\" type=\\\"hidden\\\" value=\\\"\" + id\r\n" + 
						" + \"\\\">\" + \"</form></td></tr>";
				
			}
			con.close();
			output+="</tabel>";
		} catch (Exception e) {
			// TODO: handle exception
			output="Error while reading the Posts";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String updatePost(String fund_ID,String fundMan_Name, String Email,String Contact,String fundman_ID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 LocalDate date= LocalDate.now();
	 LocalTime time= LocalTime.now();
	 String query = "UPDATE funds SET fundMan_Name=?,Email=?,Contact=?,fundman_ID=?,funded_date=?,funded_time=? WHERE fund_ID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, fundMan_Name);
	 preparedStmt.setString(2, Email);
	 preparedStmt.setString(3, Contact);
	 preparedStmt.setString(4, fundman_ID);
	 preparedStmt.setString(5, date.toString()); 
	 preparedStmt.setString(6, time.toString()); 
	 preparedStmt.setInt(7, Integer.parseInt(fund_ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	public String deletePost(String ID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from funds where fund_ID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	

} //end
