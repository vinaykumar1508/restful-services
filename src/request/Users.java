package request;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="users")
@XmlSeeAlso({User.class})
public class Users implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="user")
	private ArrayList users;
	
	
	public ArrayList getUsers(){
		return users;
	}
	
	
	public void setUsers(ArrayList users){
		this.users=users;
	}

}
