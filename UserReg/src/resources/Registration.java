package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import model.*;
import com.*;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Registration")
public class Registration {
	
	User UserObj = new User();
	// Read API
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readUsers() {
			UserService UserObj = new UserService();

			return UserObj.readUsers();

		}
		
		// Insert API
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertUsers(String UserData) {
			JsonObject UserObject = new JsonParser().parse(UserData).getAsJsonObject();

			String userid = UserObject.get("userid").getAsString();
			String username = UserObject.get("username").getAsString();
			String name = UserObject.get("name").getAsString();
			String password = UserObject.get("password").getAsString();
	

			UserService UserObject2 = new UserService();

			UserObj.setUserid(userid);
			UserObj.setUsername(username);
			UserObj.setName(name);
			UserObj.setPassword(password);

			String output =UserObject2.insertUsers(UserObj);
			return output;
		}
		
		
		
		// Update API
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateUsers(String UserData) {
			// Convert the input string to a JSON object
			JsonObject UserObject = new JsonParser().parse(UserData).getAsJsonObject();
			// Read the values from the JSON object
			String userid = UserObject.get("userid").getAsString();
			String username = UserObject.get("username").getAsString();
			String name = UserObject.get("name").getAsString();
			String password = UserObject.get("password").getAsString();	

			UserService UserObject1 = new UserService();
			
			UserObj.setUserid(userid);
			UserObj.setUsername(username);
			UserObj.setName(name);
			UserObj.setPassword(password);


			
			String output = UserObject1.updateUsers(UserObj);
			return output;
		}
		
		// Delete API
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteUsers(String UserData) {

			JsonObject UserObject = new JsonParser().parse(UserData).getAsJsonObject();

			String UserId = UserObject.get("userid").getAsString();

			UserService UserObject2 = new UserService();
			UserObj.setUserid(UserId);

			String output = UserObject2.deleteUsers(UserObj);
			return output;
		}
}
