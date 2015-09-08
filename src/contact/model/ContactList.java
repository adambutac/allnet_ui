package contact.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private HashMap	<Fields, HashMap<
						String, ArrayList<UUID>>> mapMapList;
	public ContactList(){
		super();
		mapMapList = new HashMap<Fields,HashMap<
									String,ArrayList<UUID>>>();
		HashMap<String, ArrayList<UUID>> mapList;
		for(Fields field: Fields.values()){
			mapList = new HashMap<String, ArrayList<UUID>>();
			mapMapList.put(field, mapList);
		}
	}

	//private TreeMap map;
	/**
	 * Adds the contact to the list.
	 * Duplicate contacts are allowed.
	 * @param c
	 */
	public void addContact(Contact c){
		UUID uuid = c.getUUID();
		this.put(uuid, c);
		
		String key;
		ArrayList<UUID> uuidList;
		HashMap<String, ArrayList<UUID>> mapList;
		for(Fields field:Fields.values()){
			key = c.getField(field);
			mapList = mapMapList.get(field);
			uuidList = mapList.get(key);
			if(uuidList == null){
				uuidList = new ArrayList<UUID>();
			}
			uuidList.add(uuid);
			mapList.put(key, uuidList);
		}
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
		
		String key;
		ArrayList<UUID> uuidList;
		HashMap<String, ArrayList<UUID>> mapList;
		for(Fields field:Fields.values()){
			key = c.getField(field);
			mapList = mapMapList.get(field);
			uuidList = mapList.get(key);
			if(uuidList == null){
				uuidList = new ArrayList<UUID>();
			}
			uuidList.add(uuid);
			mapList.put(key, uuidList);
		}
		
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
		String key;
		ArrayList<UUID> uuidList;
		HashMap<String, ArrayList<UUID>> mapList;
		for(Fields field:Fields.values()){
			key = c.getField(field);
			mapList = mapMapList.get(field);
			uuidList = mapList.get(key);
			if(uuidList == null){
				uuidList = new ArrayList<UUID>();
			}
			uuidList.add(uuid);
			mapList.put(key, uuidList);
		}
		return this.remove(uuid);
	}
	
	public ArrayList<UUID> getByField(Fields field, String key){
		key = key.toLowerCase();
		ArrayList<UUID> listByField = mapMapList.get(field).get(key);
		ContactComparator cc = new ContactComparator(this, field);
		Collections.sort(listByField, cc);
		return listByField;
	}

	public Set<String> getFieldKeySet(Fields field){
		return mapMapList.get(field).keySet();
	}
	
	public class ContactComparator implements Comparator<UUID>{
		private ContactList list;
		private Fields field;		
		public ContactComparator(ContactList cl, Fields f){
			list = cl;
			field = f;
		}

		@Override
		public int compare(UUID arg0, UUID arg1) {
			Contact c0 = list.get(arg0);
			Contact c1 = list.get(arg1);
			int comVal =  compareByField(c0, c1, field);
			
			if(comVal != 0)
				return comVal;
			comVal =  compareByField(c0, c1, Fields.USER_NAME);
			if(comVal != 0)
				return comVal;
			comVal = compareByField(c0, c1, Fields.GROUP);
			if(comVal != 0)
				return comVal;
			comVal = compareByField(c0, c1, Fields.FULL_NAME);
			if(comVal != 0)
				return comVal;
			
			return compareByField(c0, c1, Fields.AHRA);
		}
		
		private int compareByField(Contact c0, Contact c1, Fields f){
			String s0 = c0.getField(f);
			String s1 = c1.getField(f);
			return s0.compareToIgnoreCase(s1);
		}
	}
	
	public static void main(String[] args){
		ContactList list = new ContactList();
		for(int i = 0; i < 64; i++)	
			list.addContact(new Contact());
		
		list.addContact(
			new Contact(
				new AHRA("foo@of_fun"),"Friends","foo64","Foo Bar Baz"));
		list.addContact(
				new Contact(
					new AHRA("bar@of_play"),"Friends","bar_bar_bar","Bar Mc.Barbaz"));
		list.addContact(
				new Contact(
					new AHRA("baz@of_work"),"Work","Mr. Baz","Mr. Baz"));
		ArrayList<UUID> uuidList = list.getByField(Fields.GROUP, "work");
		int i = 0;
		for(UUID uuid: uuidList){
			System.out.println(list.get(uuid));
			i++;
		}
		System.out.println(i);
		System.out.println(list.getFieldKeySet(Fields.GROUP));
		System.out.println(list.getFieldKeySet(Fields.USER_NAME));
		System.out.println(list.getFieldKeySet(Fields.FULL_NAME));
	}
}
