package messanger.model;

public class AuthResult {

	
	private boolean resultCode;
	
	private String errorMessage;

	public boolean isResultCode() {
		return resultCode;
	}

	public void setResultCode(boolean resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "AuthResult [resultCode=" + resultCode + ", errorMessage="
				+ errorMessage + "]";
	}
	
	
}
