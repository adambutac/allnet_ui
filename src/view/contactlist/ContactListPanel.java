package view.contactlist;

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

import model.Contact;
import model.Contact.Fields;
import model.ContactComparator;
import model.ContactList;

/**
 * This is simply a panel that is used
 * to view a contact list.
 * @author Adam B
 *
 */
public class ContactListPanel extends JPanel{
	private static final long serialVersionUID = 1901404644020363565L;
	private MouseListener mListener;
	private ContactList cList;
	
	public ContactListPanel(ContactList list){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(0, 0, 0));
		cList = list;
		populate(list, Fields.GROUP);
	}
	
	private void populate(ContactList list, Fields f){
		if(list == null || list.isEmpty())
			return;

		Contact c;
		ContactWidget widget;
		Set<String> keySet = list.getFieldKeySet(f);
		List<String> keys = new ArrayList<String>(keySet);
		Collections.sort(keys);
		System.out.println(keys);
		for(String key: keys){
			ArrayList<UUID> uuids = list.getUUIDByField(f, key);
			for(UUID id:uuids){
				c = list.get(id);
				widget = new ContactWidget(c);
				this.add(widget);
			}
		}
	}
	
	public ContactWidget addContact(Contact cAdd, Fields f){
		/* Create our new widget. */
		ContactWidget cAddW = new ContactWidget(cAdd);
		cAddW.addMouseListener(mListener);
		
		ContactComparator cc = new ContactComparator(cList, f);
		UUID cGetUUID, cAddUUID;
		cAddUUID = cAdd.getUUID();
		int count = 0;
		for(Component comp: this.getComponents()){
			cGetUUID = ((ContactWidget) comp).getUUID();
			if(cc.compare(cGetUUID, cAddUUID) < 0){
				count++;
			}
		}
		this.add(cAddW, count);
		return cAddW;
	}
	
	public void addContactWidgetListener(MouseListener ml){
		mListener = ml;
		for(Component widget: this.getComponents()){
			((ContactWidget)widget).addMouseListener(ml);
		}
	}
}
