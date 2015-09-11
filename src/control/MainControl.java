package control;

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

import model.AHRA;
import model.Contact;
import model.Contact.Fields;
import model.ContactList;
import view.MainPanel;
import view.contactform.ContactFormMP;
import view.contactform.ContactFormMenu;
import view.contactform.ContactFormPanel;
import view.contactlist.ContactListMP;
import view.contactlist.ContactListMenu;
import view.contactlist.ContactListPanel;
import view.contactlist.ContactWidget;

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
			//ContactWidget widget = (ContactWidget)arg0.getComponent();
			//Contact c = cList.get(widget.getUUID());
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			ContactWidget widget = (ContactWidget)arg0.getSource();
			widget.highlight(true);
			//System.out.println("Mouse entered.");
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			ContactWidget widget = (ContactWidget)arg0.getSource();
			widget.highlight(false);
			//System.out.println("Mouse exited.");
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			ContactWidget widget = (ContactWidget)arg0.getSource();
			widget.pressed(true);
			//System.out.println("Mouse pressed.");
		}
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			ContactWidget widget = (ContactWidget)arg0.getSource();
			widget.pressed(false);
			//System.out.println("Mouse released.");
		}
	}
	
	public class AddContactListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switchView(cfmp);
			//System.out.println("Switch to new contact form.");
		}
	}

	public class SearchContactListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			switchView(cfmp);
			//System.out.println("Search contact button press.");
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
			//System.out.println(((JTextField)arg0.getSource()).getText() + " lost focus.");
		}
	}
	
	public class ContactFormSubmitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ContactFormPanel cfp = cfmp.getContent();
			ContactListPanel clp = clmp.getContent();
			String sAhra = cfp.getFieldText(Contact.Fields.AHRA);
			String uname = cfp.getFieldText(Contact.Fields.USER_NAME);
			String fname = cfp.getFieldText(Contact.Fields.FULL_NAME);
			String group = cfp.getFieldText(Contact.Fields.GROUP);
			if(AHRA.validate(sAhra)){
				AHRA ahra = new AHRA(sAhra);
				Contact c = new Contact(ahra, group, uname, fname);
				cList.addContact(c);
				final ContactWidget widget = clp.addContact(c, Fields.AHRA);
				System.out.println("Submit: " + c);
				switchView(clmp);
				((ContactFormPanel) cfmp.getContent()).reset();
				(new Thread(){
					@Override
					public void run(){
						widget.animateAdd();	
					}
				}).start();
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
		/* WidgetListener is no longer global.
		 * ContactWidget now has a reference
		 * which is set in addContactWidgetlistener() */
		ContactWidgetListener widgetListener = new ContactWidgetListener();
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
	
	private final int LEN = 2;
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
		int group = 0;
		for(String s: bruteList){
			//System.out.println(s);
			if(AHRA.validate(s)){
				cList.addContact(
					new Contact(
						new AHRA(s),group%3+"",s,s));
				group++;
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
		frame.setPreferredSize(new Dimension(800,400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		ImageIcon ii = new ImageIcon("res/images/AllnetLogo.png");
		frame.setIconImage(ii.getImage());
		frame.setVisible(true);
		System.out.println("Created " + list.size() + " contacts out of " 
			+ ctrl.getBruteLen() + " generated values.");
	}
}
