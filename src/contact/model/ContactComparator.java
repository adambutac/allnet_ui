package contact.model;

import java.util.Comparator;
import java.util.UUID;

import contact.model.Contact.Fields;

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
		comVal = compareByField(c0, c1, Fields.PHONE);
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