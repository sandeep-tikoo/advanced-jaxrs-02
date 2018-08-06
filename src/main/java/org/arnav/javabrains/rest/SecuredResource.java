package org.arnav.javabrains.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path ("secured")
public class SecuredResource {

	@GET
	@Path("message")
	public String NewTest()	{
		return " This API is secured " ;
	}
	
	
	
}
