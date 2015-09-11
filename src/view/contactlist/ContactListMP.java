package view.contactlist;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.Contact;
import model.ContactList;
import view.MainPanel;

public class ContactListMP  extends MainPanel{
	private static final long serialVersionUID = 8873801039544617655L;
	private JScrollPane scrollClp;
	
	public ContactListMP(ContactList cl){
		super(new ContactListPanel(cl), new ContactListMenu());
		/* A seperate scroll pane is used 
		 * to hold the contacts panel. */
		int scrollSpeed = 16;
		scrollClp = new JScrollPane(this.content);
		//scrollClp.setPreferredSize(new Dimension(200,325));
		scrollClp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollClp.getVerticalScrollBar().setUnitIncrement(scrollSpeed);
		this.add(scrollClp, BorderLayout.NORTH);
		this.add(this.menu, BorderLayout.SOUTH);
	}
	
	@Override
	public ContactListPanel getContent(){
		return (ContactListPanel) this.content;
	}

	@Override
	public ContactListMenu getMenu() {
		return (ContactListMenu) this.menu;
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		ContactList list = new ContactList();
		for(int i = 0; i < 64; i++)	
			list.addContact(new Contact());

		ContactListMP view = new ContactListMP(list);
		frame.add(view);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
