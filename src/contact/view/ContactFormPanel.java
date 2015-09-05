package contact.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContactFormPanel extends JPanel{
	private static final long serialVersionUID = -94862076311994607L;
	/* Default strings for text fields.
	 * My feelings on final objects are
	 * if you don't need to see them,
	 * make them private. */
	private final String ahraDef = "AHRA@memorable_phrase";
	private final String unameDef = "User";
	private final String fnameDef = "Full name";
	private final String groupDef = "Group";
	/* Text fields used for creating a
	 * new contact. */
	private JTextField ahraIn;
	private JTextField unameIn;
	private JTextField fnameIn;
	private JTextField groupIn;
	/* A panel to hold everything. */
	private JPanel textField;
	
	public ContactFormPanel(){
		/* Initialize our objects. */
		textField = new JPanel();
		ahraIn = new JTextField(ahraDef);
		unameIn = new JTextField(unameDef);
		fnameIn = new JTextField(fnameDef);
		groupIn = new JTextField(groupDef);
		/* Add text fields to our textField panel. */
		textField.add(ahraIn);
		textField.add(unameIn);
		textField.add(fnameIn);
		textField.add(groupIn);
		/* Make everything look nice. */
		textField.setLayout(new BoxLayout(textField,BoxLayout.Y_AXIS));		
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(0, 0, 0));
		/* Add the textField panel to this. */
		this.add(textField, BorderLayout.NORTH);
	}
	
	public void reset(){
		ahraIn.setText(ahraDef);
		unameIn.setText(unameDef);
		fnameIn.setText(fnameDef);
		groupIn.setText(groupDef);
	}
	
	public void addAHRAFocusListener(FocusListener fl){
		ahraIn.addFocusListener(fl);
	}
	
	public void addUsernameFocusListener(FocusListener fl){
		unameIn.addFocusListener(fl);
	}
	
	public void addFullnameFocusListener(FocusListener fl){
		fnameIn.addFocusListener(fl);
	}
	
	public void addGroupFocusListener(FocusListener fl){
		groupIn.addFocusListener(fl);
	}
	
	public String getAHRAText(){
		return ahraIn.getText();
	}
	
	public String getUsernameText(){
		return unameIn.getText();
	}
	
	public String getFullnameText(){
		return fnameIn.getText();
	}
	
	public String getGroupText(){
		return groupIn.getText();
	}
}