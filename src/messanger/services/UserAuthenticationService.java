package messanger.services;

import messanger.database.DBUtils;
import messanger.model.AuthResult;

public class UserAuthenticationService {
	
	
		DBUtils dbUtils= new DBUtils();
		AuthResult authResult;
	
	 
	public AuthResult userAuthentication(String userName, String password){
		
		 authResult= new AuthResult();
		
		if( userName == null || userName.trim().length() <= 0  || password == null || password.trim().length() <= 0 ){
				authResult.setResultCode(false);
				authResult.setErrorMessage("UserAuthenticationService::userAuthentication::UserName or password is empty");
		}else{
			
			try{
				boolean result= dbUtils.userAuthentication(userName, password);
				if(result){
					authResult.setResultCode(result);
				}else{
					authResult.setResultCode(false);
					authResult.setErrorMessage("UserAuthenticationService::userAuthentication::UserName and password does not match.!!");
				}
			}catch(Exception e){
				
				authResult.setResultCode(false);
				authResult.setErrorMessage("UserAuthenticationService::userAuthentication::technical error occured.!!");
			}
			
		}
		System.out.println("UserAuthenticationService::userAuthentication::auth result:"+authResult);
		return authResult;
	}
	
	public static void main(String[] args) {
		
		UserAuthenticationService authenticationService= new UserAuthenticationService();
		authenticationService.userAuthentication("test", "adasdsa");
	}
}

