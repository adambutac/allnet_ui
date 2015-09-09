package contact.model;

import java.util.UUID;

import contact.AHRA;

/**
 * Lets start with the very basics...
 * <br>Contact: used to store information about a contact. 
 * <br>Current data: Name, username, AHRA, group type, conversations.
 * 
 * @author ajb
 * @since 08.29.2015
 */
public class Contact {	
	public enum Fields{
		AHRA("AHRA here."), 
		USER_NAME("User name here."), 
		FULL_NAME("Full name here."),
		GROUP("Group here."),
		PHONE("Phone #");
		String value;
		private Fields(String s){
			value = s;
		}

		@Override
		public String toString(){
			return value;
		}
	}
	
	private AHRA ahra;
	private String userName;
	private String fullName;
	private String group;
	private UUID uid;
	private String phoneNum;
	/**
	 * Creates a default contact. This should only be used
	 * for testing. Please use the other constructor for
	 * defining your contacts.
	 * @see allnetui.Contact#Contact(String, String, String, String)
	 */
	public Contact(){
		fullName = "Default D. Default";
		userName = "xxDefaultxx";
		ahra = new AHRA("default_ahra@");
		group = "default";	
		uid = UUID.randomUUID();
		phoneNum = "808-123-4567";
	}
		
	/**
	 * Creates a contact with the specified information.
	 * This constructor completely fills all the contacts data.
	 */
	public Contact(AHRA addr,  String g, String un, String fn){
		//at the very least, an allnet address must be supplied.
		ahra = addr;
		group = g.toLowerCase();
		userName = un;
		fullName = fn;			
		uid = UUID.randomUUID();
		phoneNum = "";
	}

	public void setAHRA(AHRA addr){
		ahra = addr;
	}
	
	public void setGroup(String g){
		group = g.toLowerCase();
	}
	
	public void setUserName(String un){
		userName = un;
	}	
	
	public void setFullName(String fn){
		fullName = fn;
	}
	
	public AHRA getAHRA(){
		return ahra;
	}
	
	public String getGroup(){
		return group;
	}
	
	public String getUserName(){
		return userName;
	}	
	
	public String getFullName(){
		return fullName;
	}
	
	public UUID getUUID(){
		return uid;
	}
	
	public String getPhoneNumber(){
		return phoneNum;
	}
	
	public String getField(Fields f){
		switch(f){
			case AHRA:
				return ahra.toString();
			case USER_NAME:
				return userName;
			case FULL_NAME:
				return fullName;
			case GROUP:
				return group;
			case PHONE:
				return phoneNum;
			default:
				return null;
		}
	}
	
	@Override
	public String toString(){
		String sep = "&";
		return "User="  + userName + sep
			 + "Name="  + fullName + sep
			 + "Group=" + group	   + sep
			 + "AHRA="  + ahra     + sep
			 + "UUID="  + uid      + sep
			 + "Phone=" + phoneNum;
	}
}