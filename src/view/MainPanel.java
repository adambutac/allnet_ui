package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import contact.model.Contact;
import contact.model.ContactList;
import contact.view.ContactFormMenu;
import contact.view.ContactFormPanel;
import contact.view.ContactListMP;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = -8668618688820184387L;	
	private final Color mainBg = new Color(0,0,0);
	protected JComponent content;
	protected MenuPanel menu;
	
	public MainPanel(){
		content = null;
		menu = null;
		this.setBackground(mainBg);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public MainPanel(MainPanel mp){
		content = mp.getContent();
		menu = mp.getMenu();
		this.setBackground(mainBg);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public MainPanel(JComponent jp, MenuPanel mp){
		content = jp;
		menu = mp;
		this.setBackground(mainBg);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void  setContent(JComponent jc){
		content = jc;
	}
	
	public void setMenu(MenuPanel mp){
		menu = mp;
	}
	
	public JComponent getContent(){
		return content;
	}
	
	public MenuPanel getMenu(){
		return menu;
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		ContactList list = new ContactList();
		list.addContact(new Contact());
		list.addContact(new Contact());
		list.addContact(new Contact());
		
		/* A main panel can be constructed with a
		 * given Component, and menu panel. */
		ContactFormPanel formPanel = new ContactFormPanel();
		ContactFormMenu formMenu = new ContactFormMenu();
		MainPanel fmp = new MainPanel(formPanel, formMenu);
		
		/* Alternatively, you can create yo
		 * ur own
		 * class that extends the MainPanel.class */
		ContactListMP cmp = new ContactListMP(list);
		
		/* A PanelSwitcher should be defined to encapsulate
		 * your extending classes. It can be initialized
		 * with one of your extending classes. */
		//MainPanel mainPanel = new MainPanel(cmp);
		
		/* To switch to another view, simply call the
		 * MainPanel#switchTo(MainPanel p) providing the
		 * MainPanel you wish to switch to. */
		//mainPanel.switchTo(fmp);
		
		/* You can even switch between views. 
		 * ###############################################
		 * !!WARNING!! THIS DOESN'T WORK THE WAY YOU THINK
		 * ###############################################
		 * This might be useful at some point, but for now
		 * I don't recommended doing this. It isn't very 
		 * clean and can be confusing. It is an inherent 
		 * problem from MainPanel.class */
		// Switch Form view to contact view.
		//fmp.switchTo(cmp);
		// Switch what is displayed (form) to contact view
		// which has been switched with the form view.
		//mainPanel.switchTo(fmp);
		
		//frame.add(mainPanel);
		frame.setMinimumSize(new Dimension(200,400));
		frame.setPreferredSize(new Dimension(200,400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
