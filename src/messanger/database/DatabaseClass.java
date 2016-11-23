package messanger.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import messanger.model.Message;
import messanger.model.Profile;

public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	private static Map<String,Boolean> likes= new HashMap<String,Boolean>();
	private static Map<String,ArrayList<String>> friends = new HashMap<String, ArrayList<String>>(); 
	
	public static Map<Long, Message> getMessages(){
		return messages;
	}
	
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}
	
	public static Map<String,Boolean> getLikes(){
		return likes;
	}
	
	public static Map<String,ArrayList<String>> getFriends(){
		return friends;
	}
}
