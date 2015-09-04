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
			System.out.println("Trying to add...");
		}
		/*
		@Override
		public void actionPerformed(ActionEvent e) {
			ContactListPanel clp = mainPanel.getContactListPanel();
			Contact c = new Contact();
			ContactWidget widget = new ContactWidget(c);

			list.addContact(c);

			widget.addMouseListener(new ContactWidgetListener());
			clp.add(widget);
			clp.revalidate();
			clp.repaint();
			
			System.out.println("Addded " + c);
		}
		*/
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
			System.out.println(((JTextField)arg0.getSource()).getText() + " lost focus.");
		}
		
	}
	
	public class ContactFormSubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ContactFormPanel cfp = cfmp.getContent();
			String sAhra = cfp.getAHRAText();
			String uname = cfp.getUsernameText();
			String fname = cfp.getFullnameText();
			String group = cfp.getGroupText();
			if(AHRA.validate(sAhra)){
				AHRA ahra = new AHRA(sAhra);
				Contact c = new Contact(ahra, group, uname, fname);
				ContactWidget widget = new ContactWidget(c);
				widget.addMouseListener(widgetListener);
				cList.addContact(c);
				((ContactListPanel)clmp.getContent()).add(widget);
				System.out.println("Submit: " + c);
				currentView.removeAll();
				currentView.add(clmp);
				currentView.revalidate();
				currentView.repaint();
				((ContactFormPanel) cfmp.getContent()).reset();
			}
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
		 * of them (at least at present). */
		AddContactListener addButtonListener = new AddContactListener();
		SearchContactListener searchButtonListener = new SearchContactListener();
		ContactFormListener textFieldListener = new ContactFormListener();
		ContactFormSubmitListener submitListener = new ContactFormSubmitListener();
		widgetMenu.addAddButtonListener(addButtonListener);
		widgetMenu.addSearchButtonListener(searchButtonListener);
		widgetPanel.addContactWidgetListener(widgetListener);
		formPanel.addAHRAFocusListener(textFieldListener);
		formPanel.addUsernameFocusListener(textFieldListener);
		formPanel.addFullnameFocusListener(textFieldListener);
		formPanel.addGroupFocusListener(textFieldListener);
		formMenu.addSubmitButtonListener(submitListener);
		/* Set current view to the contact list. */
		//clmp.switchTo(currentView);
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
	}
}
