package contact.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContactFormPanel extends JPanel{
	private static final long serialVersionUID = -94862076311994607L;
	private JTextField ahraIn;
	private JTextField unameIn;
	private JTextField fnameIn;
	private JTextField groupIn;
	private JPanel textField;
	
	public ContactFormPanel(){
		textField = new JPanel();
		ahraIn = new JTextField("AHRA");
		unameIn = new JTextField("User");
		fnameIn = new JTextField("Full name");
		groupIn = new JTextField("Group");
		
		textField.setLayout(new BoxLayout(textField,BoxLayout.Y_AXIS));
		textField.add(ahraIn);
		textField.add(unameIn);
		textField.add(fnameIn);
		textField.add(groupIn);
		this.setLayout(new BorderLayout());
		this.add(textField, BorderLayout.NORTH);
		this.setBackground(new Color(0, 0, 0));
	}
	
	public void reset(){
		ahraIn.setText("AHRA");
		unameIn.setText("User");
		fnameIn.setText("Full name");
		groupIn.setText("Group");
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