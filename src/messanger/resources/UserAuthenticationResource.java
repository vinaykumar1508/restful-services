package messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import messanger.model.AuthResult;
import messanger.services.UserAuthenticationService;


@Path("/Authentication")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserAuthenticationResource {
	
	
	private UserAuthenticationService userAuthenticationService; 
	AuthResult authResult;
	
	
	/*
	 * 
	 *  http://localhost:8081/WebServices/rest/Authentication?userName=test&password=123123
	 */
	@GET
	public Response getUserAuthentication(@QueryParam("userName") String userName, @QueryParam("password") String password){
		
		System.out.println("UserAuthenticationService :: getUserAuthentication:: userName:"+userName+":password:"+password);
		userAuthenticationService= new UserAuthenticationService();
		authResult=userAuthenticationService.userAuthentication(userName, password);
		
		
		return Response.status(200).entity(authResult).build();
	}
	
}
