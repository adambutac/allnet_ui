package contact.view;

import java.awt.Component;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.UUID;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import contact.model.Contact;
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
		populate(list);
	}
	
	private void populate(ContactList list){
		if(list == null || list.isEmpty())
			return;

		Contact c;
		ContactWidget widget;
		
		for(Map.Entry<UUID, Contact> comp: list.entrySet()){
			c = comp.getValue();
			widget = new ContactWidget(c);
			this.add(widget);
		}
	}
	
	public void addContactWidgetListener(MouseListener l){
		for(Component widget: this.getComponents()){
			((ContactWidget)widget).addMouseListener(l);
		}
	}
}
