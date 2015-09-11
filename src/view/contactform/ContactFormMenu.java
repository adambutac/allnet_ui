package view.contactform;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.MenuPanel;

public class ContactFormMenu extends MenuPanel{
	private static final long serialVersionUID = 4722691981521418456L;
	private JButton submit;
	private JButton cancel;
	
	public ContactFormMenu(){
		submit = this.getDefaultButton("Done");
		cancel = this.getDefaultButton("Cancel");
		this.add(cancel);
		this.add(submit);
	}
	
	public void addSubmitButtonListener(ActionListener al){
		submit.addActionListener(al);
	}
	
	public void addCancelButtonListener(ActionListener al){
		cancel.addActionListener(al);
	}
}
