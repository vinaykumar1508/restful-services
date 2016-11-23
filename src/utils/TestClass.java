package utils;

import com.google.gson.Gson;

import messanger.model.Profile;

public class TestClass {
	
	public static void main(String[] args) {
		
		Profile profile = new Profile();
		
		profile.setprofileName("test");
		profile.setEmailId("test@test.com");
		profile.setFirstName("firstName");
		profile.setLastName("lastName");
		
		Gson gson = new Gson();
		
		System.out.println(gson.toJson(profile));
	}

}
