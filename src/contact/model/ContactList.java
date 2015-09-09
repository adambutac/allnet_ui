package contact.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import contact.AHRA;
import contact.model.Contact.Fields;

/**
 * A hashmap of contacts.
 * Supplies convenience methods to add, get, edit, and remove contacts.
 * @author ajb
 * @since 08.31.2015
 */
public class ContactList extends HashMap<UUID, Contact>{
	private static final long serialVersionUID = 8606271205539782685L;
	private ContactLookupTable lookupTable;
	public ContactList(){
		super();
		lookupTable = new ContactLookupTable();
	}

	//private TreeMap map;
	/**
	 * Adds the contact to the list.
	 * Duplicate contacts are allowed as long
	 * as they have different UUID values. 
	 * @param c
	 */
	public void addContact(Contact c){
		UUID uuid = c.getUUID();
		this.put(uuid, c);
		lookupTable.addContact(c);
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
		UUID uuid = c.getUUID();
		lookupTable.removeContact(c);
		return this.remove(uuid);
	}
	
	/**
	 * Removes contact with matching UUID from the list.
	 * UUID's are unique identifiers so there should only
	 * be at most 1 contact removed from the list.
	 * @param id
	 */
	public Contact removeContact(UUID uuid){
		Contact c = this.get(uuid);
		lookupTable.removeContact(c);
		return this.remove(uuid);
	}
	
	public ArrayList<UUID> getUUIDByField(Fields field, String key){
		//key = key.toLowerCase();
		ArrayList<UUID> listByField = lookupTable.get(field).get(key);
		ContactComparator cc = new ContactComparator(this, field);
		Collections.sort(listByField, cc);
		return listByField;
	}

	public Set<String> getFieldKeySet(Fields field){
		return lookupTable.get(field).keySet();
	}
	
	public static void main(String[] args){
		ContactList list = new ContactList();
		Fields field = Fields.PHONE;
		String key = "";//"808-123-4567";
		for(int i = 0; i < 500; i++)	
			list.addContact(new Contact());
		
		list.addContact(
			new Contact(
				new AHRA("foo@of_fun"),"Friends","foo64","Foo Bar Baz"));
		list.addContact(
				new Contact(
					new AHRA("bar@of_play"),"Friends","bar_bar_bar","Bar McBarbaz"));
		list.addContact(
				new Contact(
					new AHRA("baz@of_work"),"Work","Mr. Baz","Mr. Baz"));
		ArrayList<UUID> uuidList = list.getUUIDByField(field, key);
		int i = 0;
		for(UUID uuid: uuidList){
			System.out.println(list.get(uuid));
			i++;
		}
		System.out.println(i + " contacts with " + field + "=" + key);
		for(Fields f: Fields.values())
			System.out.println(list.getFieldKeySet(f));
	}
}
