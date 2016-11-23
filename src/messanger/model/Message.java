package messanger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {

	
	private long id;
	private String message;
	private Date created;
	private String profileName;
	
	//private Map<Long, Comment> comments = new HashMap<Long, Comment>();
	
	public Message(){
		
	}
	
	public Message(long id, String message, String profileName){
		this.id=id;
		this.message=message;
		this.profileName=profileName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getprofileName() {
		return profileName;
	}
	public void setAuthor(String profileName) {
		this.profileName = profileName;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", created="
				+ created + ", profileName=" + profileName
				+ "]";
	}
	
/*	@XmlTransient // @XmlTrasient is used not to show the below field(comment) in the xml when only message resource is called.
	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}*/

	
	
	
	
	
}
