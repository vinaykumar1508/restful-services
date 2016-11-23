package messanger.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import messanger.services.LikeService;

@Path("/likes")
public class LikeResources {

	LikeService likeService= new LikeService();
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String doLike( @PathParam("messageId") long messageId ,@QueryParam("profileName") String profileName, @QueryParam("action") String action) {
		
		String result = "false";
		
		if("like".equalsIgnoreCase(action)){
			
			
		}
		if("unlike".equalsIgnoreCase(action)){
			
		}
		
		System.out.println("ïnside doLike profileName:"+profileName);
		if( likeService.doLike(messageId, profileName)==null){
			result= "true";
		}
		
		return result;
	}
	
	
}
