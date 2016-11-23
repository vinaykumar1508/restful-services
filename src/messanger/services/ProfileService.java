package messanger.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import messanger.database.DBUtils;
import messanger.database.DatabaseClass;
import messanger.model.Profile;

public class ProfileService {
	
	
private static Map<String, Profile> profiles= DatabaseClass.getProfiles();
	
	final static Logger LOGGER = Logger.getLogger("REST_LOGGER");
	public ProfileService(){
	//	profiles.put("profile1", new Profile(1L,"profile1","fname1","lname1"));
	//	profiles.put("profile2", new Profile(2L,"profile2","fname2","lname2"));
		
	}
	
	static{
			LOGGER.info("creating 2 default profiles:");
			profiles.put("profile1", new Profile(1L,"profile1","fname1","lname1"));
			profiles.put("profile2", new Profile(2L,"profile2","fname2","lname2"));
			
		}
	
	public List<Profile> getAllProfiles(){
		
		System.out.println("profiles.values():"+profiles.values());
		return new ArrayList<Profile>(profiles.values());
		
	}
	

	
	public Profile getprofile(String profileName){
		DBUtils dbUtils = new DBUtils();
		System.out.println("getprofile:profileName:"+profileName);
		return dbUtils.getProfile(profileName);
		//return profiles.get(profileName);
	}
	
	public Profile addprofile(Profile profile){
		profile.setId(profiles.size()+1);
		long id=profiles.size()+1;
		Date date= new Date();
		SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz") ;
		String createdDate= ft.format(date);
		System.out.println("createdDate:"+createdDate);
		
		// Gson is to convert 
		Gson gson = new Gson();
		String newProfile=gson.toJson(profile);
		
		//String newProfile= "{\"id\":\""+id+"\",\"profileName\":\""+profile.getprofileName()+"\",\"firstName\":\""+profile.getFirstName()+"\",\"lastName\":\""+profile.getLastName()+"\",\"emailId\":\""+profile.getEmailId()+"\",\"password\":\""+profile.getPassword()+"\",\"createOn\":\""+createdDate+"\"}";
		DBUtils dbUtils = new DBUtils();
		System.out.println("newProfile:"+newProfile);
		boolean result=dbUtils.insertProfile(newProfile);
		if(result==true){
			System.out.println("ProfileService:addprofile:fetching profile from db:"+dbUtils.getProfile(profile.getprofileName()));
			return dbUtils.getProfile(profile.getprofileName());
		}else{
			return null;
		}
		//profiles.put(profile.getprofileName(), profile);
		//return profile;
	}
	
	public Profile updateprofile(Profile profile){
		System.out.println("profile:"+profile.toString());
		if(profile.getprofileName().isEmpty()){
			return null;
		}
		System.out.println("getprofile:"+profiles.get(profile.getprofileName()));
		profiles.put(profile.getprofileName(), profile);
		System.out.println("getprofile111:"+profiles.get(profile.getprofileName()));
		System.out.println("profiles:"+profiles);
		return profile;
	}
	
	public Profile removeprofile(String profileName){
		return profiles.remove(profileName);
	}

	/*public List<Long> getMessageIds(String profileName){
		
		Map<String, List<Long>> messageIdsMap =new HashMap<String, List<Long>>();
		List<Long> messageIds= new ArrayList<Long>();
		Profile profile = profiles.get(profileName);
		messageIdsMap= profile.getMessageIdList();
		System.out.println("messageIdsMap:"+messageIdsMap);
		messageIds=messageIdsMap.get(profileName);
		
		return messageIds; 
	}*/
	
	/*@SuppressWarnings("null")
	public void addMessageId(String profileName, long messageId){
		
		System.out.println("profileName:"+profileName+":messageId:"+messageId);
		Profile profile = profiles.get(profileName);
		Map<String, List<Long>> profileToMsgIdMapper= new HashMap<String, List<Long>>();
		List<Long> messageIds = new ArrayList<Long>();
		messageIds =(profile.getMessageIdList()).get(profileName);
		
		if(messageIds == null){
			messageIds = new ArrayList<Long>();
			System.out.println("messageIds:"+messageIds);
		}
		messageIds.add(messageId);
		System.out.println("messageIds after addition: "+messageIds);
		profileToMsgIdMapper.put(profileName, messageIds);
		
		profile.setMessageIdList(profileToMsgIdMapper);
		
	}*/
}
