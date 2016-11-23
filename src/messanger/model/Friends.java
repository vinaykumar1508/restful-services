package messanger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Friends {

	
/*	private Map<String, ArrayList<String>> friends = new HashMap<String , ArrayList<String>>();

	public Map<String, ArrayList<String>> getFriends() {
		return friends;
	}

	public void setFriends(Map<String, ArrayList<String>> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "Friends [friends=" + friends + "]";
	}

	public Friends(Map<String, ArrayList<String>> friends) {
		super();
		this.friends = friends;
	}*/

	public Friends() {
		super();
	}
	
	private String requester;
	private String accepter;
	private String status;
	

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

		

	
}
