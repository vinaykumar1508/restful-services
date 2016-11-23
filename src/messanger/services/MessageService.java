package messanger.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import messanger.database.DBUtils;
import messanger.database.DatabaseClass;
import messanger.model.Message;

public class MessageService {

	private Map<Long, Message> messages= DatabaseClass.getMessages();
	
		DBUtils dbUtils = new DBUtils();
	
	public MessageService(){
		
		messages.put(1L,new Message(1L, "message1", "author1"));
		messages.put(2L,new Message(2L, "message2", "author2"));
		
	}
	
	public List<Message> getAllMessage(String profileName){
		List<Message> list= new ArrayList<Message>();
		list= dbUtils.getMessages(profileName);
		
		
		return list;
		
	}
	
	
	
	/*public Message getMessage(Long id){
		return messages.get(id);
	}*/
	
	public Message getMessage(Long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		int count=dbUtils.getCount("messages");
		message.setId(count+1);
		messages.put(message.getId(), message);
		
		Gson gson = new Gson();
		String newMessage= gson.toJson(message);
		dbUtils.insertMessage(newMessage);
		
		return message;
	}
	
	public Message updateMessage(Message message){
		
		if(message.getId()<=0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
	
}
