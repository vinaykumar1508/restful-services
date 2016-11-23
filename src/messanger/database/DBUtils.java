package messanger.database;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import messanger.model.Comment;
import messanger.model.Friends;
import messanger.model.Message;
import messanger.model.Profile;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBUtils {
	
	public static MongoDatabase dbCon;
	static{
		dbCon= DBConnection.getDBInstance();
	}
	
	public MongoCollection<Document> getCollection(String collectionName){
		return dbCon.getCollection(collectionName);
	}
	
	int x=0;
	
	public boolean insertProfile(String profileData){
		
		try{
			System.out.println("profileData:"+profileData);
			Document document = Document.parse(profileData);
			//dbCon.getCollection("profiles").insertOne(document);
			getCollection("profiles").insertOne(document);
			return true;
		}catch(Exception e)	{
			System.out.println("Exception while registration for profile:"+profileData+"::"+e);
			return false;
		}
		
	}
	
	
	public boolean insertMessage(String Message){
		
		System.out.println("Message:"+Message);
		
		try{
			Document document = Document.parse(Message);
			getCollection("messages").insertOne(document);
		}catch(Exception e){
			System.out.println("Exception while inserting Message for Message::"+e);
		}
		
		
		return false;
	}
	
	public boolean insertComment(String Comment){
		
		System.out.println("insertComment:"+Comment);
		
		try{
			Document document = Document.parse(Comment);
			getCollection("comments").insertOne(document);
		}catch(Exception e){
			System.out.println("Exception while inserting Comment for Comment::"+e);
		}
		
		
		return false;
	}
	
	public Profile getProfile(final String profileName){
		
		final Profile profile= new Profile();
		FindIterable<Document> iterable;
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("profileName", profileName);
		iterable=dbCon.getCollection("profiles").find(basicDBObject);
		
		
		iterable.forEach(new Block<Document>() {

			@Override
			public void apply(Document dbProfile) {
				System.out.println("profile:"+dbProfile);
				
				profile.setprofileName(dbProfile.getString("profileName"));
				profile.setFirstName(dbProfile.getString("firstName"));
				profile.setLastName(dbProfile.getString("lastName"));
				//profile.setCreated(Date(dbProfile.getString("createOn")));
				profile.setEmailId(dbProfile.getString("emailId"));
				profile.setPassword(dbProfile.getString("password"));			
			}
		});
		
		System.out.println("DBUtils profileName:"+profile);
		return profile;
	}
	
	
	public List<Message> getMessages(String profileName){
		
		final List<Message> result = new ArrayList<Message>();
		FindIterable<Document>  iterable;
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("profileName", profileName);
		iterable=dbCon.getCollection("messages").find(basicDBObject);
		
		
		iterable.forEach(new Block<Document>(){
		 	
			@Override
			public void apply(Document arg0) {
				final Message message= new Message();
				// TODO Auto-generated method stub
				System.out.println("message::"+arg0);
				message.setId( Long.valueOf(arg0.getString("id")).longValue());
				message.setAuthor(arg0.getString("profileName"));
				message.setMessage(arg0.getString("message"));
				
				result.add(message);
			}
			
		});
		
		System.out.println("DBUtils allMessages for profile:"+profileName+" are:"+result);
		return result;
	}
	
	
	public List<Comment> getComments(final String messageId){
		
		final List<Comment> result = new ArrayList<Comment>();
		FindIterable<Document>  iterable;
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("messageId", Integer.parseInt(messageId));
		iterable=dbCon.getCollection("comments").find(basicDBObject);
		
		
		iterable.forEach(new Block<Document>(){
		 
			@Override
			public void apply(Document arg0) {
				final Comment comment= new Comment();
				System.out.println("comment::"+arg0);
				comment.setId( Long.valueOf(arg0.getInteger("id")).longValue());
				comment.setComment(arg0.getString("comment"));
				comment.setMessageId(Long.parseLong(messageId));
				result.add(comment);
			}
			
		});
		
		System.out.println("DBUtils allcomments for message:"+messageId+" are:"+result);
		return result;
	}
	
	public int getCount(String collectionName){
		
		// need to get the message id o the latest message and then add to it.
		FindIterable<Document>  iterable;
		iterable=dbCon.getCollection(collectionName).find();
		
	    iterable.forEach(new Block<Document>(){
	    	
			@Override
			public void apply(Document arg0) {
				x++;
				
			}
			
		});
		return x;
	}
	
	
	public List<String> getFriends(String profileName){
	
		final List<String> result=new ArrayList<String>();
		FindIterable<Document> iterable;
		BasicDBObject basicDBObject= new BasicDBObject();
		basicDBObject.put("requester", profileName);
		iterable=dbCon.getCollection("friends").find(basicDBObject);
		iterable.forEach(new Block<Document>() {
			
			@Override
			public void apply(Document arg0) {
				System.out.println("getFriends friends:"+arg0.getString("accepter"));
				result.add(arg0.getString("accepter"));
				
			}
		});
		
		basicDBObject.put("accepter", profileName);
		iterable=dbCon.getCollection("friends").find(basicDBObject);
		iterable.forEach(new Block<Document>() {
			
			@Override
			public void apply(Document arg0) {
		
				System.out.println("getFriends friends:"+arg0.getString("requester"));
				result.add(arg0.getString("requester"));
			}
			
		});
		
		System.out.println("getFriends::"+result);
		return result;
	}
	
	
	
	public List<String> getSearchedProfiles(String searchedProfileName){
		
		System.out.println("searchedProfileName:"+searchedProfileName);
		final List<String> matchedProfiles= new ArrayList<String>();
		FindIterable<Document> iterable;
		BasicDBObject basicDBObject = new BasicDBObject();
		Pattern regex=Pattern.compile("aaa");
		String test="/"+searchedProfileName+"/";
		//System.out.println("regex:"+regex);
		basicDBObject.put("profileName", regex);
		iterable=dbCon.getCollection("profiles").find(basicDBObject);
		
		
		iterable.forEach(new Block<Document>() {

			@Override
			public void apply(Document dbProfile) {
				matchedProfiles.add(dbProfile.getString("profileName"));
				//System.out.println("profile:"+dbProfile);
				
			
			}
		});
		
		return matchedProfiles;
		
	}
	
	
	public boolean addFriend(String data){
		
		try{
			//System.out.println("profileData:"+profileData);
			Document document = Document.parse(data);
			//dbCon.getCollection("profiles").insertOne(document);
			getCollection("friends").insertOne(document);
			return true;
		}catch(Exception e)	{
			System.out.println("Exception while adding friend:"+data+"::"+e);
			return false;
		}
		
		
	}
	
	public boolean userAuthentication(String username,String password){
		
		final Profile profile= new Profile();
		FindIterable<Document> iterable;
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("profileName", username);
		basicDBObject.put("password", password);
		iterable=dbCon.getCollection("profiles").find(basicDBObject);
		System.out.println("itreable::"+iterable.first());
		if(null== iterable.first()){
			System.out.println("Username and password does not match.");
			return false;
		}
		
		iterable.forEach(new Block<Document>() {

			@Override
			public void apply(Document dbProfile) {
				System.out.println("profile yo:"+dbProfile);
				
				profile.setprofileName(dbProfile.getString("profileName"));
				profile.setPassword(dbProfile.getString("password"));			
			}
		});
		
		if(profile.getprofileName().equalsIgnoreCase(username) && profile.getPassword().equalsIgnoreCase(password)){
			System.out.println("User authentication successful.Returning true");
			return true;
		}else{
			System.out.println("User authentication failed.Returning false");
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
		
		System.out.println("inside main.");
		DBUtils dbUtils = new DBUtils();
		//System.out.println("ttttttttt:::"+dbUtils.getProfile("aaaaaaa"));
		//System.out.println(dbUtils.getSearchedProfiles("aaaa"));
		//System.out.println("result is:"+ dbUtils.userAuthentication("test", "12323"));
		//System.out.println("count :"+dbUtils.getMessageCount("messages"));
		String id="1";
		System.out.println("getComments:"+dbUtils.getComments(id));
	}

}
