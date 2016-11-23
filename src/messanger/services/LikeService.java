package messanger.services;

import java.util.Map;

import messanger.database.DatabaseClass;


public class LikeService {
	
	Map<String,Boolean> likes= DatabaseClass.getLikes();

	public String doLike( long messageId, String profileName){
		String identifier=messageId+"_"+profileName;
		Boolean result= likes.put(identifier, true);
		System.out.println("result:"+result);
		if(result==null){
			System.out.println("ProfileName:"+profileName+" has liked the message:"+messageId);
			return "true";
		}else{
			System.out.println("ProfileName:"+profileName+" has already liked the message:"+messageId);
			return "false";
		}
		
	}
	
	public String unLike( long messageId, String profileName){
		String identifier=messageId+"_"+profileName;
		Boolean result=likes.remove(identifier);
		if(result!=null){
			System.out.println("record found for idenifier:"+identifier+" hence deleting.");
			return "true";
		}
		else{
			System.out.println("No record found in Like Map hence returning false.");
			return "false";
		}
		
		
	}
}
