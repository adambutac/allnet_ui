package contact.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import contact.model.Contact.Fields;
import contact.model.ContactLookupTable.FieldMap;

/**
 * Each field has a HashMap<String key, ArrayList<UUID> value>.
 * One can lookup a key for a specific Field for a contact.
 * For example: Looking up all contacts with group "default"
 *   would be as simple as retrieving the Group hashmap and
 *   using the supplied get(Object key), call get("default").
 * To get the Group hashmap, use your ContactLookupTable instance
 * to call get(Field.GROUP).
 * 
 * @author [S]
 *
 */
public class ContactLookupTable extends HashMap<Fields, FieldMap>{
	public class FieldMap extends HashMap<String, ArrayList<UUID>>{
		private static final long serialVersionUID = -7957844941899249295L;

		public FieldMap(){
			super();
		}
	}
	
	private static final long serialVersionUID = -4054811867591315128L;
	
	public ContactLookupTable(){
		super();
		FieldMap mapList;
		for(Fields field: Fields.values()){
			mapList = new FieldMap();
			this.put(field, mapList);
		}
	}
	
	public void addContact(Contact c){
		UUID uuid = c.getUUID();		
		String key;
		ArrayList<UUID> uuidList;
		HashMap<String, ArrayList<UUID>> mapList;
		for(Fields field:Fields.values()){
			key = c.getField(field);
			mapList = this.get(field);
			uuidList = mapList.get(key);
			if(uuidList == null){
				uuidList = new ArrayList<UUID>();
			}
			uuidList.add(uuid);
			mapList.put(key, uuidList);
		}
	}
	
	public void removeContact(Contact c){
		UUID uuid = c.getUUID();
		
		String key;
		ArrayList<UUID> uuidList;
		HashMap<String, ArrayList<UUID>> mapList;
		for(Fields field:Fields.values()){
			key = c.getField(field);
			mapList = this.get(field);
			uuidList = mapList.get(key);
			if(uuidList != null){
				uuidList.remove(uuid);
				mapList.put(key, uuidList);
			}
		}
	}
}
