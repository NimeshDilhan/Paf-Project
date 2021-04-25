package com;
import model.payments;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/payments")
public class paymentService1
{
	 payments paymentObj = new payments();
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readPayments()
		 {
			return paymentObj.readPayments();
		 }
		
		//Insert Service
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertPaymentInfo(
		 @FormParam("paymentcode") String paymentcode, 
		 @FormParam("paymentType") String paymentType, 
		 @FormParam("totalprice") String totalprice, 
		 @FormParam("paymentStatus") String paymentStatus,
		 @FormParam("date") String date,
		 @FormParam("time") String time) 
		{ 
			String output = paymentObj.insertPaymentInfo(paymentcode, paymentType, totalprice, paymentStatus, date, time);
			return output; 
		}
		
		//Update Service
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updatePaymentInfo(String paymentData) 
		{ 
			
			 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
		
			 String paymentID = paymentObject.get("PaymentID").getAsString(); 
			 String paymentcode = paymentObject.get("PaymentCode").getAsString(); 
			 String paymentType = paymentObject.get("PaymentType").getAsString(); 
			 String totalprice = paymentObject.get("TotalPrice").getAsString(); 
			 String paymentStatus = paymentObject.get("PaymentStatus").getAsString(); 
			 String date = paymentObject.get("Date").getAsString(); 
			 String time = paymentObject.get("Time").getAsString(); 
			 String output = paymentObj.updatePaymentInfo(paymentID, paymentcode, paymentType, totalprice, paymentStatus, date, time);
			 return output; 
		}
		//delete service
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deletePaymentInfo(String paymentData) 
		{ 
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
			 
	
			 String PaymentID = doc.select("PaymentID").text(); 
			 String output = paymentObj.deletePaymentInfo(PaymentID);
			 return output; 
		}
		
}