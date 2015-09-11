package view.contactform;

import javax.swing.JFrame;

import view.MainPanel;

public class ContactFormMP extends MainPanel{
	private static final long serialVersionUID = -276328754070774145L;

	public ContactFormMP(){
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
		ContactFormMP view = new ContactFormMP();
		frame.add(view);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
