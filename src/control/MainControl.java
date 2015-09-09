package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
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
			switchView(cfmp);
			System.out.println("Switch to new contact form.");
		}
	}

	public class SearchContactListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			switchView(cfmp);
			System.out.println("Search contact button press.");
		}
	}
	
	public class ContactFormFocusListener implements FocusListener{
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
			ContactListPanel cmp = clmp.getContent();
			String sAhra = cfp.getFieldText(Contact.Fields.AHRA);
			String uname = cfp.getFieldText(Contact.Fields.USER_NAME);
			String fname = cfp.getFieldText(Contact.Fields.FULL_NAME);
			String group = cfp.getFieldText(Contact.Fields.GROUP);
			if(AHRA.validate(sAhra)){
				AHRA ahra = new AHRA(sAhra);
				Contact c = new Contact(ahra, group, uname, fname);
				ContactWidget widget = new ContactWidget(c);
				widget.addMouseListener(widgetListener);
				cmp.add(widget, 0);
				cList.addContact(c);
				System.out.println("Submit: " + c);
				switchView(clmp);
				((ContactFormPanel) cfmp.getContent()).reset();
			}
		}
		
	}
	
	public class ContactFormCancelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0){
			switchView(clmp);
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
	private JPanel mainPanel;
	private JPanel currentView;
	/* This is global so it may be referenced in
	 * one or more of the listener classes. */
	private ContactWidgetListener widgetListener;

	public MainControl(ContactList cl, JPanel mp){
		/* Link data. */
		cList = cl;
		/* Link main view. */
		mainPanel = mp;
		
		genBrute();
		
		/* Initialize views. */
		clmp = new ContactListMP(cList);
		cfmp = new ContactFormMP();
		
		ContactListPanel widgetPanel = clmp.getContent();
		ContactListMenu widgetMenu = clmp.getMenu();
		ContactFormPanel formPanel = cfmp.getContent();
		ContactFormMenu formMenu = cfmp.getMenu();
		
		/* Set listeners. */
		/* WidgetListener has to be global so 
		 * it can be added to new contact widgets. */
		widgetListener = new ContactWidgetListener();
		/* The rest do not have to be global
		 * as there will only ever be one instance
		 * of them at present. This may change.*/
		AddContactListener addButtonListener = new AddContactListener();
		SearchContactListener searchButtonListener = new SearchContactListener();
		ContactFormFocusListener focusListener = new ContactFormFocusListener();
		ContactFormSubmitListener submitListener = new ContactFormSubmitListener();
		ContactFormCancelListener cancelListener = new ContactFormCancelListener();
		widgetPanel.addContactWidgetListener(widgetListener);
		widgetMenu.addAddButtonListener(addButtonListener);
		widgetMenu.addSearchButtonListener(searchButtonListener);
		formPanel.addFormFocusListener(focusListener);
		formMenu.addSubmitButtonListener(submitListener);
		formMenu.addCancelButtonListener(cancelListener);
		/* Set current view to the contact list. */
		currentView = clmp;
		clmp.setVisible(true);
		cfmp.setVisible(false);
		mainPanel.add(clmp);
		mainPanel.add(cfmp);
		mainPanel.setPreferredSize(new Dimension(200,400));
	}
	
	private void switchView(JPanel p){
		currentView.setVisible(false);
		p.setVisible(true);
		currentView = p;
	}
	
	private final int LEN = 3;
	/* BEGIN and END are inclusive. */
	private final int BEGIN = '0';
	private final int END = 'z';
	private final int n = END - BEGIN + 1;
	private String[] bruteList;
	private int count = 0;
	
	private void genBrute(){
		for(int i = 1; i <= LEN; i++){
			count += (int)Math.pow(n, i);
		}
		bruteList = new String[count];
		count -= 1;
		genBrute("");
		for(String s: bruteList){
			//System.out.println(s);
			if(AHRA.validate(s)){
				cList.addContact(
					new Contact(
						new AHRA(s),s,s,s));
			}
		}
	}
	
	private String genBrute(String s){
		if(s.length() == LEN){
			return s;
		}
		for(int i = BEGIN; i <= END; i++){
			bruteList[count] = s + (char)i;
			count -= 1;
			genBrute(s + (char)i);
		}
		return s;
	}
	
	public int getBruteLen(){
		return bruteList.length;
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		ContactList list = new ContactList();
		MainPanel view = new MainPanel();
		MainControl ctrl = new MainControl(list, view);
		frame.add(view);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		ImageIcon ii = new ImageIcon("res/images/AllnetLogo.png");
		frame.setIconImage(ii.getImage());
		frame.setVisible(true);
		System.out.println("Created " + list.size() + " contacts out of " 
			+ ctrl.getBruteLen() + " generated values.");
	}
}
