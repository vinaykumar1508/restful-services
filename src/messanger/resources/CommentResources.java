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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import messanger.model.Comment;
import messanger.services.CommentService;


/**
 * @author ivy4296
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CommentResources {


	private CommentService commentService = new CommentService();
	
	
	/**
	 * http://localhost:8081/WebServices/rest/messages/1/comments/3 
	 *
	 */
	/*@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId){
		return commentService.getComment(messageId, commentId);
	}*/
	
	//http://localhost:8081/WebServices/rest/messages/1/comments
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId){
		return commentService.getAllComments(messageId);
	}
	
	
	/**
	 *  To add comment first add new message message3 and then add comment in it as message 1 and message2 gets reloaded every time.
	 * @author ivy4296
	 {
        	"message": "comment1",
    		"created": null,
    		"author": "author1"
    }	
    	 
	 */
	
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, String comment){
		System.out.println("addComment:messageId:"+messageId+":comment:"+comment);
		return commentService.addComment(messageId, comment);
		
	}
	
	
	/*@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId , Comment comment){
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comment removeComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId ){
		return commentService.removeComment(messageId, commentId);
	}*/
	
	
	
}
