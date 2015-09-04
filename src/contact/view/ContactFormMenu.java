package contact.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.MenuPanel;

public class ContactFormMenu extends MenuPanel{
	private static final long serialVersionUID = 4722691981521418456L;
	private JButton submit;
	
	public ContactFormMenu(){
		submit = this.getDefaultButton("Done");
		this.add(submit);
	}
	
	public void addSubmitButtonListener(ActionListener al){
		submit.addActionListener(al);
	}
}
