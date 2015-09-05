package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import contact.AHRA;
import contact.model.Contact;
import contact.model.ContactList;
import contact.view.ContactFormMP;
import contact.view.ContactFormMenu;
import contact.view.ContactFormPanel;
import contact.view.ContactListMP;
import contact.view.ContactListMenu;
import contact.view.ContactListPanel;
import contact.view.ContactWidget;
import view.MainPanel;

public class MainControl {
	
	/* A personal note.
	 * 
	 *   I am interested in writing clean, readable and
	 * extensible code. I mention this with my control
	 * implementation in mind. 
	 *   As of now, I have laid out a list of classes, 
	 * each for its own function. I find this code is 
	 * easier to read.
	 *   On the other hand, this is not very extensible.
	 * For every new button or other Action, I create a
	 * new class to listen for it.
	 *   And yet, my contact widget listener handles all
	 * contact widgets. Maybe it is a compromise between
	 * these two implementations that will make readable,
	 * clean and extensible code.
	 * */
	
	/* ######################### *
	 * Begin Listener Class Def. *
	 * ######################### */
	
	class ContactWidgetListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			ContactWidget widget = (ContactWidget)arg0.getComponent();
			ContactListPanel clp = clmp.getContent();
			
			clp.remove(widget);
			clp.revalidate();
			clp.repaint();
			
			Contact c = cList.removeContact(widget.getUUID());
			System.out.println("Removed " + c);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			ContactWidget widget = (ContactWidget)arg0.getSource();
			Color c = widget.getBackground();
			widget.setBackground(c.darker());
			widget.repaint();
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			ContactWidget widget = (ContactWidget)arg0.getSource();
			Color c = widget.getBackground();
			widget.setBackground(c.brighter());
			widget.repaint();
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			ContactWidget widget = (ContactWidget)arg0.getSource();
			Color c = widget.getBackground();
			widget.setBackground(c.darker());
			widget.repaint();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			ContactWidget widget = (ContactWidget)arg0.getSource();
			Color c = widget.getBackground();
			widget.setBackground(c.brighter());
			widget.repaint();
		}
	}
	
	public class AddContactListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			currentView.removeAll();
			currentView.add(cfmp);
			currentView.revalidate();
			currentView.repaint();
			System.out.println("Switch to new contact form.");
		}
	}

	public class SearchContactListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Search contact button press.");
		}
	}
	
	public class ContactFormListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent arg0) {
			((JTextField)arg0.getSource()).selectAll();
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			((JTextField)arg0.getSource()).select(0, 0);
			System.out.println(((JTextField)arg0.getSource()).getText() + " lost focus.");
		}
	}
	
	public class ContactFormSubmitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ContactFormPanel cfp = cfmp.getContent();
			String sAhra = cfp.getFieldText(Contact.Fields.AHRA);
			String uname = cfp.getFieldText(Contact.Fields.USER_NAME);
			String fname = cfp.getFieldText(Contact.Fields.FULL_NAME);
			String group = cfp.getFieldText(Contact.Fields.GROUP);
			if(AHRA.validate(sAhra)){
				AHRA ahra = new AHRA(sAhra);
				Contact c = new Contact(ahra, group, uname, fname);
				ContactWidget widget = new ContactWidget(c);
				widget.addMouseListener(widgetListener);
				clmp.getContent().add(widget, 0);
				cList.addContact(c);
				System.out.println("Submit: " + c);
				currentView.removeAll();
				currentView.add(clmp);
				currentView.revalidate();
				currentView.repaint();
				((ContactFormPanel) cfmp.getContent()).reset();
			}
		}
		
	}
	
	public class ContactFormCancelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0){
			currentView.removeAll();
			currentView.add(clmp);
			currentView.revalidate();
			currentView.repaint();
			((ContactFormPanel) cfmp.getContent()).reset();
		}
	}
	
	/* ################ *
	 * End of Listeners *
	 * ################ */
	
	/* Define data. */
	private ContactList cList;
	/* Define views. */
	private ContactListMP clmp;
	private ContactFormMP cfmp;
	private JPanel currentView;
	private ContactWidgetListener widgetListener;

	public MainControl(ContactList cl, JPanel mp){
		/* Link data. */
		cList = cl;
		/* Link main view. */
		currentView = mp;
		/* Initialize views. */
		clmp = new ContactListMP(cList);
		cfmp = new ContactFormMP();
		
		/* I had to add a getContent method in the ContactListPanel.class
		 * because I put the content within a JScrollPane */
		ContactListPanel widgetPanel = clmp.getContent();
		ContactListMenu widgetMenu = clmp.getMenu();
		ContactFormPanel formPanel = cfmp.getContent();
		ContactFormMenu formMenu = cfmp.getMenu();
		
		/* Set listeners. */
		/* WidgetListener has to be global
		 * so we can add it to new contacts. */
		widgetListener = new ContactWidgetListener();
		/* The rest do not have to be global
		 * because there will only ever be one instance
		 * of them at present. This may change.*/
		AddContactListener addButtonListener = new AddContactListener();
		SearchContactListener searchButtonListener = new SearchContactListener();
		ContactFormListener textFieldListener = new ContactFormListener();
		ContactFormSubmitListener submitListener = new ContactFormSubmitListener();
		ContactFormCancelListener cancelListener = new ContactFormCancelListener();
		widgetMenu.addAddButtonListener(addButtonListener);
		widgetMenu.addSearchButtonListener(searchButtonListener);
		widgetPanel.addContactWidgetListener(widgetListener);
		formPanel.addFormListener(textFieldListener);
		formMenu.addSubmitButtonListener(submitListener);
		formMenu.addCancelButtonListener(cancelListener);
		/* Set current view to the contact list. */
		currentView.add(clmp);
		currentView.setPreferredSize(new Dimension(200,400));
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		ContactList list = new ContactList();
		for(int i = 0; i < 64; i++)
			list.addContact(new Contact(
					new AHRA(i + "@"),
					i+"", i+"", i+""));
		
		MainPanel view = new MainPanel();
		@SuppressWarnings("unused")
		MainControl ctrl = new MainControl(list, view);
		
		frame.add(view);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		System.out.println("Done!");
	}
}
