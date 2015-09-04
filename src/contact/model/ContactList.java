package contact.model;

import java.util.HashMap;
import java.util.UUID;

/**
 * A hashmap of contacts.
 * Supplies convenience methods to add, get, edit, and remove contacts.
 * @author ajb
 * @since 08.31.2015
 */
public class ContactList extends HashMap<UUID, Contact>{
	private static final long serialVersionUID = 8606271205539782685L;
	
	/**
	 * Adds the contact to the list.
	 * Duplicate contacts are allowed.
	 * @param c
	 */
	public void addContact(Contact c){
		this.put(c.getUUID(), c);
	}

	/**
	 * Retrieve contact in the list.
	 * @param c
	 */
	public Contact getContact(Contact c){
		return this.get(c.getUUID());
	}
	
	/**
	 * Retrieve contact in the list given the unique id.
	 * @param id
	 */
	public Contact getContact(UUID id){
		return this.get(id);
	}
	
	/**
	 * Removes contact from the list.
	 * @param c
	 */
	public Contact removeContact(Contact c){
		return this.remove(c.getUUID());
	}
	
	/**
	 * Removes contact with matching UUID from the list.
	 * UUID's are unique identifiers so there should only
	 * be at most 1 contact removed from the list.
	 * @param id
	 */
	public Contact removeContact(UUID id){
		return this.remove(id);
	}
}
