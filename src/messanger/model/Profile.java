package messanger.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Profile {

	private long id;
	private String profileName;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private Date created;
	
	//private Map<String, Message> map = new HashMap<String, Message>();
	
	//private Map<String, List<Long>> ProfileToMsgIdMapper = new HashMap<String, List<Long>>();
	
	public Profile(long id, String profileName, String firstName, String lastName) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}

	public Profile() {
		super();
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", profileName=" + profileName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", created=" + created
				+ ",emailId=" + emailId +"]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getprofileName() {
		return profileName;
	}

	public void setprofileName(String profileName) {
		this.profileName = profileName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*@XmlTransient // @XmlTrasient is used not to show the below field(comment) in the xml when only message resource is called.
	public Map<String, Message> getMessages() {
		return map;
	}

	public void setgetMessages(Map<String, Message> map) {
		this.map = map;
	}
*/
	/*public Map<String, List<Long>> getMessageIdList() {
		return ProfileToMsgIdMapper;
	}

	public void setMessageIdList(Map<String, List<Long>> profileToMsgIdMapper) {
		ProfileToMsgIdMapper = profileToMsgIdMapper;
	}
*/	
	
	
	
}
