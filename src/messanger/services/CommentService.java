package messanger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import messanger.database.DBUtils;
import messanger.database.DatabaseClass;
import messanger.model.Comment;
import messanger.model.Message;



public class CommentService {

	private Map<Long, Message> messages= DatabaseClass.getMessages();
	DBUtils dbUtils = new DBUtils();
	
	public List<Comment> getAllComments(Long messageId){
		
		return dbUtils.getComments(messageId.toString()); 
	}
	
	
	/*public Comment getComment(Long messageId, Long commentId){
		
		//Map<Long, Comment> allCommnets = messages.get(messageId).getComments();
		//return allCommnets.get(commentId);
	}*/
	
	public Comment addComment(Long messageId, String comment){
		
		//Map<Long, Comment> allComments = messages.get(messageId).getComments();
		//comment.setId(allComments.size()+1);
		//allComments.put(comment.getId(), comment);
		int count=dbUtils.getCount("comments");
		Comment newComment= new Comment(count+1, comment, null, null, messageId);
		Gson gson = new Gson();
		String newCom= gson.toJson(newComment);
		dbUtils.insertComment(newCom);
		
		return newComment;
	}
	
	/*public Comment updateComment(Long messageId, Comment comment){
		
		Map<Long, Comment> allComments = messages.get(messageId).getComments();
		if(comment.getId()<=0){
			return null;
		}
		
		allComments.put(comment.getId(), comment);
		return comment;
	}*/
	
	
/*public Comment removeComment(Long messageId, Long commentId){
		
		Map<Long, Comment> allCommnets = messages.get(messageId).getComments();
		return allCommnets.remove(commentId);
	}*/
}
