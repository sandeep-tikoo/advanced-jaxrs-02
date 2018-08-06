package org.arnav.javabrains.rest;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

@Path ("test")
@XmlRootElement

//@Singleton
//If the @Singleton annotation is used, each time when the resource is hitted, 
//it will not initialize, means on each request a new instance of the class is not created. 
public class MyResource {
		
	public static Integer Count = 0;
	@QueryParam("name") private String queryParam; 
//	private int Count;
//when use use static in the variable declaration, the value will not be initialised when the class is called, 
//it will keep the last session's value
//	
//	public static List<Long> addCallCount()	{
//		return CallCount;
//	}
		
    	@GET
//		public TestObject testMethod(@Context UriInfo uriInfo)	{
//    	Since now this method conditionally returns two different types of object, so changed return type of method to <Object>
    	@Produces(MediaType.APPLICATION_JSON)
    	public Object testMethod(@Context UriInfo uriInfo)	{
    		
    		System.out.println("queryParam: " + queryParam);
    		
			String passLink = uriInfo.getAbsolutePath().toString();

			// Call constructor, Pass link and last Counter to it and 
			//it will return with setted values in the object fields.
			TestObject testObjectIns = new TestObject(passLink, Count ); 
			Count = testObjectIns.getCallCounter(); //Store Call counter in the sesssion
			if (Count > 20)	{
				ErrorMessage error = new ErrorMessage("Too many calls", 999, "Please restart the server", passLink);
				return error;
			}
			return testObjectIns; // Return setted java object, which will later be converted into JSON
		}
    	
    	@GET
    	@Path ("writer")
    	//Based on Accept header in the request header this will call the appropriate 
    	//Message body writer and send the payload and set the content type
    	@Produces(value = {MediaType.TEXT_PLAIN, "text/shortDate"})
    	//SEVERE: MessageBodyWriter not found for media type=text/plain, type=class java.util.Date, genericType=class java.util.Date.
    	public Date testMessageBodyWriter()	{
    		System.out.println("In GET writer end point");
    		return Calendar.getInstance().getTime();
//    		return "Why this then???";
    		}
    	
//    	@GET
//    	@Path ("customMediaTypeWriter")
//    	@Produces(value = {MediaType.TEXT_PLAIN, "text/ShortDate"})
//    	//SEVERE: MessageBodyWriter not found for media type=text/plain, type=class java.util.Date, genericType=class java.util.Date.
//    	public Date testcustomMediaTypeWriter()	{
//    		System.out.println("In GET writer end point");
//    		return Calendar.getInstance().getTime();
//    		}
}