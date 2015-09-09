package contact.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import contact.model.Contact;
import contact.model.Contact.Fields;
import contact.model.ContactList;

/**
 * This is simply a panel that is used
 * to view a contact list.
 * @author Adam B
 *
 */
public class ContactListPanel extends JPanel{
	private static final long serialVersionUID = 1901404644020363565L;
	
	public ContactListPanel(ContactList list){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(0, 0, 0));
		populate(list);
	}
	
	private void populate(ContactList list){
		if(list == null || list.isEmpty())
			return;

		Contact c;
		ContactWidget widget;
		Set<String> keySet = list.getFieldKeySet(Fields.AHRA);
		List<String> keys = new ArrayList<String>(keySet);
		Collections.sort(keys);
		System.out.println(keys);
		for(String key: keys){
			ArrayList<UUID> uuids = list.getUUIDByField(Fields.AHRA, key);
			for(UUID id:uuids){
				c = list.get(id);
				widget = new ContactWidget(c);
				this.add(widget);
			}
		}
		//for(Map.Entry<UUID, Contact> ent: list.entrySet()){
		//	c = ent.getValue();
		//	widget = new ContactWidget(c);
						
			//Contact cont;
			//int index = 0;
			//for(Component comp: this.getComponents()){
				//cont = list.get(((ContactWidget)comp).getUUID());
				//if(cont.getAHRA().toString().compareTo(c.getAHRA().toString()) < 0)
				//	index++;				
				//else
				//	break;
			//}
			
		//	this.add(widget);//, index);
		//}
	}
	
	public void addContactWidgetListener(MouseListener l){
		for(Component widget: this.getComponents()){
			((ContactWidget)widget).addMouseListener(l);
		}
	}
}
