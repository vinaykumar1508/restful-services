package messanger.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

public class FacebookIntegration {

	public static void main(String[] args) {
		
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true);
		configurationBuilder.setOAuthAppId("1683886785159795");
		configurationBuilder.setOAuthAppSecret("0f6672540154fcfeb2d25fd0c752e861");
		configurationBuilder.setOAuthAccessToken("1683886785159795|1SPzPQhsQr9ZKi1ExsjAxVe_kZE");
		configurationBuilder.setOAuthPermissions("email, publish_stream, id, name, first_name, last_name, read_stream , generic");
		configurationBuilder.setUseSSL(true);
		configurationBuilder.setJSONStoreEnabled(true);
		
		Configuration configuration = configurationBuilder.build();
		FacebookFactory facebookFactory = new FacebookFactory(configuration);
		Facebook facebook = facebookFactory.getInstance();
		
		
		try 
		{
			String searchPost= "ABJ";
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm");
			String fileName = "D:\\others\\learning\\WebServices\\WebContent\\files" + searchPost + "_" + format.format(date) + ".txt";
			String searchResults;
			
				searchResults = getFacebookPostes(facebook, searchPost);
			
		
			File file = new File(fileName); 
			if (!file.exists()){ 
			
				file.createNewFile();
				FileWriter fw = new FileWriter(file.getAbsoluteFile()); 
				BufferedWriter bw = new BufferedWriter(fw); 
				bw.write(searchResults); 
				bw.close(); 
				System.out.println("Completed");
			}	
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static String getFacebookPostes(Facebook facebook, String searchPost) throws FacebookException{
		
		String searchResult = "Item : " + searchPost + "\n"; 
		StringBuffer searchMessage = new StringBuffer();
		ResponseList<Post> results= facebook.getPosts(searchPost);
		
		for(Post post : results){
			
			System.out.println("posted messgaes:"+post.getMessage());
			System.out.println("size:"+post.getComments().size());
			searchMessage.append(post.getMessage() + "\n");
			
			for(int i=0;i<post.getComments().size();i++){
				
				searchMessage.append(post.getComments().get(i).getFrom().getName()+",");
				searchMessage.append(post.getComments().get(i).getMessage()+",");
				searchMessage.append(post.getComments().get(i).getCreatedTime()+",");
				searchMessage.append(post.getComments().get(i).getLikeCount()+",");
			}
			
		}
		
		//String feedString = getFacebookFeed(facebook, searchPost); 
		searchResult = searchResult + searchMessage.toString(); 
		//searchResult = searchResult + feedString; return searchResult;
		
		return searchResult;
	}
	
	// This method is used to get Facebook feeds based on the search string setabove 
	public static String getFacebookFeed(Facebook Facebook, String searchPost) throws FacebookException{  
		
		String searchResult = ""; 
		StringBuffer searchMessage = new StringBuffer(); 
		ResponseList<Post> results = Facebook.getFeed(searchPost); 
		for (Post post : results){ 
		
			System.out.println(post.getMessage()); 
			searchMessage.append(post.getFrom().getName() + ", "); 
			searchMessage.append(post.getMessage() + ", "); 
			searchMessage.append(post.getCreatedTime() + "\n"); 
		} 
		
		searchResult = searchResult + searchMessage.toString(); 
		return searchResult; 
	}
	
	
}
