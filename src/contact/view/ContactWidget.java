package contact.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import contact.model.Contact;

public class ContactWidget extends JPanel{
	private static final long serialVersionUID = -7484468699043821112L;	
	public static final int HEIGHT = 50;
	private final Color bg = new Color(255,255,255);
	private JLabel text;
	private JLabel icon;
	private UUID id;

	public ContactWidget(Contact c){
		/* Set variables. */
		String name = c.getAHRA() + "";
		ImageIcon img = new ImageIcon("res/images/AllnetLogo_x50.png");
		Dimension dim = new Dimension(Integer.MAX_VALUE, HEIGHT);
		
		/* Set our data. */
		id = c.getUUID();
		text = new JLabel(name);
		icon = new JLabel();
		icon.setIcon(img);
		
		/* Set panel properties. */
		this.setName(name);
		this.setMaximumSize(dim);
		this.setBackground(bg);
		this.setLayout(new BorderLayout());
		this.add(icon, BorderLayout.WEST);
		this.add(text, BorderLayout.CENTER);
	}
	
	public ContactWidget(Contact c, ImageIcon img){
		/* Set variables. */
		String name = c.getAHRA() + "";
		Dimension dim = new Dimension(Integer.MAX_VALUE, HEIGHT);
		
		/* Set our data. */
		id = c.getUUID();
		text = new JLabel(name);
		icon = new JLabel();
		icon.setIcon(img);
		
		/* Set panel properties. */
		this.setName(name);
		this.setMaximumSize(dim);
		this.setLayout(new BorderLayout());
		this.add(icon, BorderLayout.WEST);
		this.add(text, BorderLayout.CENTER);
	}
	
	/**
	 * Get the UUID of the contact this widget represents.
	 * @return UUID of contact.
	 */
	public UUID getUUID(){
		return id;
	}
}