package request;

public class RegistrationRequest {

	private String userId;
	private String firstName;
	
	public String getUserName() {
		return userId;
	}
	public void setUserName(String userName) {
		this.userId = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
