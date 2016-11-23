package messanger.database;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class DBConnection {
	
	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("restdb");
		FindIterable<Document> findIterable;
		findIterable=db.getCollection("profiles").find();
		
		
		findIterable.forEach(new Block<Document>() {

			@Override
			public void apply(Document arg0) {
				System.out.println("document:"+arg0);
				
			}
		});
		 
		System.out.println("database:"+findIterable);
		 
		 
		
		 //DBCollection dbCollection =(DBCollection) database.getCollection("profiles");
		 
		 
		 String doc="{'name' : 'profile2'}";
		 //DBObject dbObject= (DBObject) JSON.parse(doc);
		//dbCollection.insert(dbObject);
		
		 Document document = Document.parse(doc);
		 db.getCollection("profiles").insertOne(document);
		
		
		
		
		
	}
	
	public static MongoDatabase getDBInstance(){
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("restdb");
		
		
		return database;
	}

}
