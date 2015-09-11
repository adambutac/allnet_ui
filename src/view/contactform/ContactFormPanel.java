package view.contactform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import generic.InputForm;
import model.Contact;

public class ContactFormPanel extends JPanel{
	private static final long serialVersionUID = -94862076311994607L;
	/* Text fields used for creating a
	 * new contact. */
	private InputForm form;
	/* A panel to hold everything. */
	private JPanel textField;
	
	public ContactFormPanel(){
		/* Initialize our objects. */
		textField = new JPanel();
		/* Initialize input form with contact fields. */
		form = new InputForm(Contact.Fields.values());
		/* Add input form to our textField panel. */
		textField.add(form);
		/* Make everything look nice. */
		textField.setLayout(new BoxLayout(textField,BoxLayout.Y_AXIS));		
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(0, 0, 0));
		/* Add the textField panel to this. */
		this.add(textField, BorderLayout.NORTH);
	}
	
	public String getFieldText(String name){
		return form.getText(name);
	}
	
	@SuppressWarnings("rawtypes")
	public String getFieldText(Enum e){
		return form.getText(e);
	}
	
	public void addFormFocusListener(FocusListener fl){
		form.addFocusListener(fl);
	}
	
	public void reset(){
		form.reset();
	}
}