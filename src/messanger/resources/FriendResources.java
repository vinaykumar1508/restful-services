package messanger.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;



import messanger.database.DBUtils;
import messanger.model.Friends;
import messanger.model.Profile;
import messanger.services.FriendService;


@Path("/friends")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FriendResources {

	FriendService friendService= new FriendService();
	
	//http://localhost:8081/WebServices/rest/profiles/profile8/friends
	
	@GET
	public Response getFriends(@PathParam("profileName") String profileName){
		
		System.out.println("proilfename:"+profileName);
		
		DBUtils dbUtils = new DBUtils();
		
		
		ArrayList<String> friends= (ArrayList<String>) friendService.getFriends(profileName);
		System.out.println("friends:"+friends);
		if(null!=friends &&  !friends.isEmpty())
			return Response.status(200).entity(friends).build();
		else{
			return Response.status(200).entity("Friend list empty.").build();
		}
	}
	
	//String searchedProfileName
	@GET
	@Path("/search")
	public Response searchFriends(@QueryParam("searchedString") String searchedString){
		
		System.out.println("searchedProfileName:"+searchedString);
		
		Profile profile=friendService.searchFriend(searchedString);
		//Profile profile=dbUtils.getProfile(searchedProfileName);
		//System.out.println("returned profile details:"+profile);
		/*
		if(searchedString!=null )
			return Response.status(200).entity("test").build();
		else{
			return Response.status(200).entity("No serach found.").build();
		}*/
		Gson gson = new Gson();
		if(profile!=null && profile.getprofileName()!=null){
			System.out.println("searched profile:"+profile);
			
			//String newProfile=gson.toJson(profile);
			return Response.status(200).entity(profile).build();
		}else{
			String newProfile=gson.toJson("No Result found.");
			return Response.status(200).entity(profile).build();
		}
		
	}
	
	
	//data to post :  {"requester": "requester", "accepter": "acceptor","status":"0"}
	@POST
	@Path("/addFriend")
	public Response addFriends(@PathParam("profileName") String profileName , Friends friends) throws JSONException{
		System.out.println("testtttttt proilfename:"+profileName);
		//String test="{\"test\":\"test\"}";
		System.out.println("friends:::"+friends);
		
		System.out.println("my friends::"+friends.getRequester()+"::"+friends.getAccepter());
		
		
		Gson gson = new Gson();
		String newProfile=gson.toJson("test");
		
		friendService.addFriend(friends);
		
		return Response.status(200).entity(newProfile).build();
	}
}
