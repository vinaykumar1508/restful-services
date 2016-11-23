package request;


import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.UserProfileDAO;


@Path("/login")
public class LoginCheck {

	UserProfileDAO userProfileDAO;
	
	@POST
	@Path("/add")
	public Response addUser(
			@FormParam("username") String userName,
			@FormParam("password") String password ){
		boolean isValidUser = false;
		userProfileDAO= new UserProfileDAO();
		System.out.println("userName:"+userName+"::" + "Password:"+password);
		try{
			isValidUser = userProfileDAO.userAuthentication(userName, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(isValidUser)
			return javax.ws.rs.core.Response.status(200).entity("Hi There.!! "+ userName).build();
		else
			return javax.ws.rs.core.Response.status(200).entity("Username or password does not match").build();
					
	}
	
	@GET
	@Path("/check")
	@Produces(MediaType.APPLICATION_JSON)
	public void checkMethod(){
		System.out.println("inside checkMethod.");
		//return javax.ws.rs.core.Response.status(200).entity("sssssssssssssssssss").build();
		
	}
	
	@POST
	@Path("/forgetPassword")
	public Response forgetPassword(
			@FormParam("username") String userName,
			@FormParam("newPassword") String newPassword,
			@FormParam("retypePassword") String retypePassword){
		
		System.out.println("userName:"+userName+ " newPassword:"+newPassword+" retypePassword:"+retypePassword);
		if(!userName.equals("") && !newPassword.equals("") && !retypePassword.equals("")){
			System.out.println("inside if.");
			if(newPassword.equals(retypePassword)){
				return Response.status(200).entity("Your password has been changed succesfully.!!").build();
			}else{
				return Response.status(200).entity("Retype Password not matching.").build();
			}
			
		}else{
			System.out.println("inside else");
			
			return Response.status(200).entity("Please enter all the details.").build();
		}
		
		//return javax.ws.rs.core.Response.status(200).entity("Hi There.!! "+ userName+" Change you password.").build();
		
	}
	
	@GET
	@Path("/suggestName")
	public Response suggestUserName(){
		return Response.status(200).entity("vinay").build();
		
	}
}
