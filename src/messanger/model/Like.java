package messanger.model;

public class Like {
	
	private String ProfileName;
	private long messageId;
	
	
	
	
	public String getProfileName() {
		return ProfileName;
	}
	public void setProfileName(String profileName) {
		ProfileName = profileName;
	}
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	@Override
	public String toString() {
		return "Like [ProfileName=" + ProfileName + ", messageId=" + messageId
				+ "]";
	}
	
	
	
}
