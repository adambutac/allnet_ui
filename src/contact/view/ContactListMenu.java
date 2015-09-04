package contact.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.MenuPanel;

public class ContactListMenu extends MenuPanel{
	private static final long serialVersionUID = -1513008632557139095L;
	private JButton add;
	private JButton search;
	
	public ContactListMenu(){
		add = this.getDefaultButton("+");
		search = this.getDefaultButton("Q");
		
		this.setLayout(new BorderLayout());
		this.add(add, BorderLayout.EAST);
		this.add(search,BorderLayout.WEST);
	}
	
	public void addAddButtonListener(ActionListener al){
		add.addActionListener(al);
	}
	
	public void addSearchButtonListener(ActionListener al){
		search.addActionListener(al);
	}
}
