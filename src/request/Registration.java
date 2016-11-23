package request;



import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.UserProfileDAO;

@Path("/register")
public class Registration {
	
	UserProfileDAO userProfileDAO;
	UserProfileRequest userProfileRequest;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/user")
	public Response registerUser(RegistrationRequest registrationRequest
			){
		
	System.out.println("requets:"+ registrationRequest);
//		System.out.println(" inside registerUser-- userId::"+userId+" firstName::"+firstName);
		try{
			//String userId="dummy";
			if(false){
			//if(userId.equals("")||firstName.equals("")||lastName.equals("")||emailId.equals("")||password.equals("")||confirmPassword.equals("")){
				System.out.println("inside if block");
				return Response.status(200).entity("Field can not be blank.!!").build();
			}else{
				try{
					/*dbConnection = new DBConnection();
					//dbConnection.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "");
					 *
*/					System.out.println("inside else block");
					return Response.status(200).entity("Your account has created successfully!!").build();
					
					/*userProfileDAO= new UserProfileDAO();
					System.out.println("userProfileDAO::"+userProfileDAO);	
					boolean playerAlreadyExistflag =userProfileDAO.checkUserIdExistance(userId);
					System.out.println("playerAlreadyExistflag:"+playerAlreadyExistflag);*/
					/*if(playerAlreadyExistflag){
						return Response.status(200).entity("user name is alreday taken. enter other user name.").build();
					}else{
						userProfileRequest= new UserProfileRequest();
						userProfileRequest.setUserId(userId);
						userProfileRequest.setFirstName(firstName);
						userProfileRequest.setLastName(lastName);
						userProfileRequest.setPassword(password);
						userProfileRequest.setEmailId(emailId);
						userProfileRequest.setAddress(address);
						userProfileRequest.setGender("M");
						boolean accountCreated= userProfileDAO.createUser(userProfileRequest);
						if(accountCreated){
							return Response.status(200).entity("Your account has created successfully!!").build();
						}
					}
*/					
					}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(200).entity("exception caught"+e).build();
		}
		//return Response.status(200).entity("Your account has been created successfully.!!").build();
		return null;
		
	}
	
	
}
