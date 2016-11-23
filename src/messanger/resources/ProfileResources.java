package messanger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import utils.MessangerCostants;
import messanger.model.Profile;
import messanger.services.ProfileService;


/**
 * @author ivy4296
 *
 */
/**
 * @author ivy4296
 *
 */
@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResources {

	
	ProfileService profileService= new ProfileService();
	//http://localhost:8081/WebServices/rest/profiles/profile2
	//http://localhost:8081/WebServices/rest/profiles/
	
	@GET
	public List<Profile> getProfiles(){
		return profileService.getAllProfiles();
	}
	
	/*@GET
	public Profile getProfiles(){
		return profileService.getAllProfiles();
	}*/
	/*
	 *{
    "id": 1,
    "profileName": "profile1",
    "firstName": "fname1",
    "lastName": "lname1",
    "created": null
  } 
	 */
	@POST
	public Response addProfile(Profile profile){
		
		Profile profileCreated= new Profile();
		profileCreated= profileService.addprofile(profile);
		System.out.println("addProfile:profileCreated:"+profileCreated);
		if(profileCreated!=null && profileCreated.getprofileName().equalsIgnoreCase(profile.getprofileName())){
			return Response.status(200).entity(profileCreated).build();
		}else{
			return Response.status(204).entity(MessangerCostants.REGISTRATION_ERROR).build();
					
		}
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName){
		System.out.println("ProfileResources:getProfile:profileName:"+profileName);
		return profileService.getprofile(profileName);
	}
	
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile){
		System.out.println("profileName:"+profileName);
		profile.setprofileName(profileName);
		return profileService.updateprofile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName){
		profileService.removeprofile(profileName);
	}
	
	
	/*
	 * http://localhost:8081/WebServices/rest/profiles/profile2/messages   ( to get the comments from profile - http://localhost:8081/WebServices/rest/profiles/profile2/messages/3/comments)
	 */
		
		@Path("/{profileName}/messages")
		public MessageResources getMessages(){
			System.out.println("inside /{profileName}/messages");
			return new MessageResources();
		}
		
		
		//http://localhost:8081/WebServices/rest/profiles/profile8/friends 
		@Path("/{profileName}/friends")
		public FriendResources getfriendResource(){
			
			System.out.println("inside /{profileName}/friends");
			return new FriendResources();
		}
		
		
}




