package messanger.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import messanger.model.Comment;
import messanger.model.Message;
import messanger.services.CommentService;
import messanger.services.MessageService;
import messanger.services.ProfileService;


@Path("messages")

//if the below two added here then add the method will consumes/produces JSON. So if added here you can remove from the method level.
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class MessageResources {
	
	MessageService messageService = new MessageService(); 
	ProfileService profileService = new ProfileService();
	CommentService commentService = new CommentService();
	
	/**
	 * http://localhost:8081/WebServices/rest/messages
	 * JSON conversion of object is done through the use of jersey-media-moxy.jar utility method.
	 */
	
	/*
		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public List<Message> getMessages(@PathParam("profileName") String profileName){
		
		System.out.println(" MessageResources: inside getMessages");
		List<Message> messages= new ArrayList<Message>();
		messages=messageService.getAllMessage(profileName);
		if(messages ==null){
			System.out.println("MessageIds list is null hence no messages!!"+messages);
			return null;
		}
		
		System.out.println("result:"+messages);
		return messages;
		
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public Map< Long, List<Comment> > getMessages(@PathParam("profileName") String profileName){
		
		Map<Long, List< Comment>> resultSet= new HashMap<Long, List<Comment>>();
		Map<Long, String> messageResult= new HashMap<Long, String>();
		System.out.println(" MessageResources: inside getMessages");
		List<Message> messages= new ArrayList<Message>();
		messages=messageService.getAllMessage(profileName);
		if(messages ==null){
			System.out.println("MessageIds list is null hence no messages!!"+messages);
			return null;
		}else{
			
			for(Message msg : messages){
				List<Comment> allComments = new ArrayList<Comment>();
				allComments= commentService.getAllComments(msg.getId());
				
				resultSet.put(msg.getId(), allComments);
			}
			
		}
		
		System.out.println("result:"+resultSet);
		return resultSet;
		
	}
	
	/**
	 * http://localhost:8081/WebServices/rest/messages/1
	 * parameter conversion from string messageId to long messageId is taken care by jersey.
	 */
	/*@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long messageId){
		return messageService.getMessage(messageId);
	}*/
	
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long messageId){
		return messageService.getMessage(messageId);
	}
	
	/**
	 * Method POST
	 * http://localhost:8081/WebServices/rest/messages
	 * conversion of JSON request to Message object as parameter is taken care by jersey.
	 * 
	 * steps to test 
	 * set header as Content-type = application/json
	 * inside body select type:raw and select JSON .
	 * {"message": "message3",  "created": null,  "author": "author3"}
	 */
	
	//http://localhost:8081/WebServices/rest/profiles/profile1/messages
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessageToProfile(@PathParam("profileName") String profileName, Message message){
		
		
		
		Message message2 =messageService.addMessage(message);
		//profileService.addMessageId(profileName, message2.getId());
		//return messageService.addMessage(message);
		return message2;
	}
	
	
	/**
	 * http://localhost:8081/WebServices/rest/messages/1
	 * id will be figure out from path hence no need to add into JSON request.
	 * {"message": "message changed",  "created": null,  "author": "author changed"}
	 */
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId,   Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	
	/**
	 * http://localhost:8081/WebServices/rest/messages/1
	 */
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long messageId){
		messageService.removeMessage(messageId);
	}
	
	// http://localhost:8081/WebServices/rest/messages/1/comments
	
	@Path("/{messageId}/comments")
	public CommentResources getComments(){
		System.out.println("inside /{messageId}/comments");
		return new CommentResources();
	}
	
	@Path("/{messageId}/likes")
	public LikeResources getLikes(){
		
		return new LikeResources();
	}
	
}
