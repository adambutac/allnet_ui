package view.contactform;

import javax.swing.JFrame;

import view.Page;

public class ContactFormPage extends Page{
	private static final long serialVersionUID = -276328754070774145L;

	public ContactFormPage(){
		super(new ContactFormPanel(), new ContactFormMenu());
		this.add(this.content);
		this.add(this.menu);
	}

	@Override
	public ContactFormPanel getContent() {
		return (ContactFormPanel) this.content;
	}

	@Override
	public ContactFormMenu getMenu() {
		return (ContactFormMenu) this.menu;
	}
	
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		ContactFormPage view = new ContactFormPage();
		frame.add(view);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
