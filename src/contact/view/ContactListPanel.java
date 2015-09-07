package contact.view;

import java.awt.Color;
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
		this.setBackground(new Color(0, 0, 0));
		populate(list);
	}
	
	private void populate(ContactList list){
		if(list == null || list.isEmpty())
			return;

		Contact c;
		ContactWidget widget;
		for(Map.Entry<UUID, Contact> ent: list.entrySet()){
			c = ent.getValue();
			widget = new ContactWidget(c);
			// This will be interesting later.
			// widget.setVisible(false);
			//Contact cont;
			//int index = 0;
			//for(Component comp: this.getComponents()){
				//cont = list.get(((ContactWidget)comp).getUUID());
				//if(cont.getAHRA().toString().compareTo(c.getAHRA().toString()) < 0)
				//	index++;				
				//else
				//	break;
			//}
			
			this.add(widget);//, index);
		}
	}
	
	public void addContactWidgetListener(MouseListener l){
		for(Component widget: this.getComponents()){
			((ContactWidget)widget).addMouseListener(l);
		}
	}
}
