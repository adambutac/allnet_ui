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
		GROUP("Group here.");
		
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
	
	/**
	 * Creates a default contact. This should only be used
	 * for testing. Please use the other constructor for
	 * defining your contacts.
	 * @see allnetui.Contact#Contact(String, String, String, String)
	 */
	public Contact(){
		fullName = "Default D. Default";
		userName = "xxDefaultxx";
		ahra = new AHRA("defaultAHRA@");
		group = "default";	
		uid = UUID.randomUUID();
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
	
	public String getField(Fields f){
		switch(f){
			case AHRA:
				return getAHRA().toString();
			case USER_NAME:
				return getUserName();
			case FULL_NAME:
				return getFullName();
			case GROUP:
				return getGroup();
			default:
				return null;
		}
	}
	
	@Override
	public String toString(){
		return "User="  + userName + ":"
			 + "Name="  + fullName + ":"
			 + "Group=" + group	   + ":"
			 + "AHRA="  + ahra     + ":"
			 + "UUID="  + uid      + ":";
	}
}