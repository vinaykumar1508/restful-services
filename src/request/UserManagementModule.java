package request;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.jersey.api.ParamException.URIParamException;

import request.User;
import request.Users;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="user-management")
@Path("/user-management")
public class UserManagementModule {

	 @XmlElement(name = "users")
	    private String uri1 = "http://localhost:8084/DrawAndOrder2/rest/user-management/users";
	 
	    @XmlElement(name = "report")
	    private String uri2 = "http://localhost:8084/DrawAndOrder2/rest/user-managemet/generate-report";
	 
	    public String getUri1() {
	        return uri1;
	    }
	 
	    public void setUri1(String uri1) {
	        this.uri1 = uri1;
	    }
	 
	    public String getUri2() {
	        return uri2;
	    }
	 
	    public void setUri2(String uri2) {
	        this.uri2 = uri2;
	    }
	
	
	 // http://localhost:8084/DrawAndOrder2/rest/user-management/
	@GET
    @Path("/")
    //@Produces("application/vnd.request.user-management+xml;charset=UTF-8;version=1")
	 @Produces("application/vnd.request.user-management+xml;charset=UTF-8;version=1")
    public UserManagementModule getServiceInfo() {
		System.out.println(" this is the / path. ");
        return new UserManagementModule();
    }
	
	//http://localhost:8084/DrawAndOrder2/rest/user-management/users/10
	@GET
	@Path("/users/{id}")
	@Produces("application/vnd.request.user-management.user+xml;charset=UTF-8;version=1")
	public User getUserById(@PathParam("id") Integer id){
		
		System.out.println("id:"+id);
		User user= new User();
		user.setId(id);
		user.setFirstName("vinay");
		user.setLastName("kumar");
		user.setUri("/user-management/users/"+id);
		return user;
		
		
	}
	
	//, @DefaultValue("false") @QueryParam("allow-admin") boolean allowAdmin
	//http://localhost:8084/DrawAndOrder2/rest/user-management/users
	
	@POST
	@Path("/users")
	@Consumes("application/vnd.request.user-management.user+xml;charset=UTF-8;version=1")
	public Response createUser(User user) throws URISyntaxException{
		System.out.println("firstName:"+user.getFirstName());
		System.out.println("lastName:"+user.getLastName());
		return Response.status(201).contentLocation(new URI("/user-management/users/123")).build();
		
	}
	
	
	@GET
	@Path("/users")
	@Produces("application/vnd.request.user-management.users+xml;charset=UTF-8;version=1")
	
	public Users getAllUsers(){
		
		User user1= new User();
		user1.setId(1);
		user1.setFirstName("User1firstName");
		user1.setLastName("user1lastName");
		user1.setUri("/user-management/users/1");
		
		User user2= new User();
		user2.setId(2);
		user2.setFirstName("User2firstName");
		user2.setLastName("user2lastName");
		user2.setUri("/user-management/users/2");
		
		Users users= new Users();
		users.setUsers(new ArrayList());
		
		users.getUsers().add(user1);
		users.getUsers().add(user2);
		return  users;
		
		
		
	}
	
	//http://localhost:8084/DrawAndOrder2/rest/user-management/users/1
	
	@DELETE
    @Path("/users/{id}")
    public Response deleteUser(@PathParam("id") int id)
            throws URISyntaxException {
        return Response.status(200).build();
    }
	
}


//http://localhost:8080/RESTfulClientTest/rest/user-management/users/10