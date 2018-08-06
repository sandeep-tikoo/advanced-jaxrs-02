package org.arnav.javabrains.rest;

import java.io.IOException;

import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_VALUE_PREFIX = "Basic";
	private static final String SECURE_AT_RESOURCE_LEVEL = "secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		if (requestContext.getUriInfo().getPath().contains(SECURE_AT_RESOURCE_LEVEL))	{

			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				System.out.println("authToken : " + authToken);
				authToken = authToken.replaceFirst(AUTHORIZATION_VALUE_PREFIX, "");
				System.out.println("authToken : " + authToken);
				
				StringTokenizer tokenizerA = new StringTokenizer(authToken, " ");
//				String StrSpace = tokenizerA.nextToken();
				String fullCredentials = tokenizerA.nextToken();
				
				String decodedStr = Base64.decodeAsString(fullCredentials);
				System.out.println("Decoded String : " + decodedStr);
				
				StringTokenizer tokenizerB = new StringTokenizer(decodedStr, ":");
				String username = tokenizerB.nextToken();
				String password = tokenizerB.nextToken();
				
				if (username.equals("user") && password.equals("password"))	{
					return;
				}
				
			}
			Response unauthorizedAccess = Response
											.status(Response.Status.UNAUTHORIZED)
											.entity("Cannot access the resource")
											.build();
			
			requestContext.abortWith(unauthorizedAccess);
		}
	}
}
