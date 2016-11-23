package messanger.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import messanger.database.DBUtils;
import messanger.database.DatabaseClass;
import messanger.model.Friends;
import messanger.model.Profile;

public class FriendService {
	
	Map<String, ArrayList<String>> friends = DatabaseClass.getFriends();
	DBUtils dbUtils = new DBUtils();
	
	public FriendService() {
		
		ArrayList<String> defaultFriends = new ArrayList<String>();
		defaultFriends.add("profile1");
		defaultFriends.add("profile2");
		
		//Map<String, ArrayList<String> > friendMap = new HashMap<String, ArrayList<String>>();
		friends.put("profile1", defaultFriends);
	}

	public boolean addFriend(Friends friend){
		
		try{
			/*ArrayList<String> friendList = friends.get(user);
			friendList.add(friend);
			*/
			
			
			
			Gson gson = new Gson();
			String newfriend=gson.toJson(friend);
			
			dbUtils.addFriend(newfriend);
			return true;
		}catch(Exception e){
			//System.out.println("FriendService:addFriend:Exception occured while addng friend for user:"+user);
			return false;
		}
	}
	
	public List<String> getFriends(String user){
		
		ArrayList<String> friendList = new ArrayList<String>();
		
		friendList= (ArrayList<String>) dbUtils.getFriends(user);
		
		
		return friendList;
	}
	
	public boolean removeFriend(String user, String friend){
		
		ArrayList<String> friendList = new ArrayList<String>();
		friendList= friends.get(user);
		
		Iterator<String> itr = friendList.iterator();
		while(itr.hasNext()){
			if(itr.next().equalsIgnoreCase(friend)){
				itr.remove();
				return true;
			}
		}
		System.out.println("Friendlist for the user:"+user +" is:"+friendList);
		
		return false;
	}
	
	public Profile searchFriend(String searchedProfileName){
		Profile profile=null;
		
		
		profile= dbUtils.getProfile(searchedProfileName);
		
		return profile;
		
	}
}
