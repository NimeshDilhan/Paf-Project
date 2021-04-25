package Com;

import model.Funding_Posts;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/FundingPosts")
public class Funding_Post_Service {
	Funding_Posts funding_obj=new Funding_Posts();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPosts()
	{
		return funding_obj.read_Post();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createPost(
			@FormParam("funds_Name") String fundMan_Name,
			@FormParam("mail") String Email,
	@FormParam("contact") String Contact,
@FormParam("funder_ID") String fundman_ID){
		String output= funding_obj.createPost(fundMan_Name, Email, Contact, fundman_ID); //date issue
				return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String postData)
	{
		//Convert the input string to a JSON object
		JsonObject postObj = new JsonParser().parse(postData).getAsJsonObject();
		
		String fund_ID = postObj.get("fund_ID").getAsString();
		String fundMan_Name = postObj.get("fund_name").getAsString();
		String Email = postObj.get("mail").getAsString();
		String Contact = postObj.get("contact").getAsString();

		String fundman_ID = postObj.get("funder_ID").getAsString();

		
		
		String output=funding_obj.updatePost(fund_ID, fundMan_Name, Email, Contact, fundman_ID);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String funderData)
	{
		Document doc= Jsoup.parse(funderData,"",Parser.xmlParser());
		
		String itemID=doc.select("fund_ID").text();
		
		String output=funding_obj.deletePost(itemID);
		return output;
	}
}
