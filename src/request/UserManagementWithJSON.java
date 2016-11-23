package request;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;



@Path("/user-mgmt-json")
public class UserManagementWithJSON {
	@Context
    UriInfo uriInfo;
	
	//http://localhost:8084/DrawAndOrder2/rest/user-mgmt-json/users/1
	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public Response getUserByIDJson(@PathParam("id") Integer id){
		User user = new User();
		user.setId(id);
		user.setFirstName("vinay");
		user.setLastName("kumar");
		
		return Response.status(200).entity(user).build();
		
	}
	
	
	//http://localhost:8084/DrawAndOrder2/rest/user-mgmt-json/showlink
	@GET
	@Path("/showlink")
	@Produces("application/json")
	public Response showLink() throws IOException, JSONException{
		 
		InputStream inputStream = new FileInputStream("D:\\Workspace\\DrawAndOrder2\\src\\otherFiles\\jsonFile");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		String jsonlink="";
		String links= null;
		while ((links=bufferedReader.readLine())!=null){
			jsonlink +=links+"\n";
		}
		
		JSONObject jsonObject = new JSONObject(jsonlink);
		
		return Response.status(200).entity(jsonObject).build();
	}
	
	
	//http://localhost:8084/DrawAndOrder2/rest/user-mgmt-json/link
	@GET
	@Path("/link")
	@Produces("application/json")
	public JSONArray getUsersAsJsonArray() {
		
		/*
		UriBuilder uri = UriBuilder.fromResource(UserManagementWithJSON.class);
		return Response.created(uri.build(1)).build();
		Response response= Response.ok().link("http://oracle.com", "parent").build();
		return response;*/
		
		
		        JSONArray uriArray = new JSONArray();
		        
		            UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		            URI userUri = ub.path("http://localhost:8084/DrawAndOrder2/rest/user-management/users/10").build();
		            uriArray.put(userUri.toASCIIString());
		        
		        return uriArray;
		    }
		
	}

