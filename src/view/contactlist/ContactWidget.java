package view.contactlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Contact;

public class ContactWidget extends JPanel{
	private static final long serialVersionUID = -7484468699043821112L;	
	private static final int HEIGHT = 50;
	private static final int GROW = 20;
	private final Color bg = new Color(255,255,255);
	private final Color highlight = new Color(200,255,255);
	private final Color pressed = new Color(150,200,200);
	private JLabel text;
	private JLabel icon;
	private Contact contact;
	private boolean blowUp;
	
	public ContactWidget(Contact c){
		/* Link contact object. */
		contact = c;
		/* Set variables. */
		String name = c.getAHRA() + "";
		ImageIcon img = new ImageIcon("res/images/AllnetLogo_x50.png");
		Dimension dim = new Dimension(Integer.MAX_VALUE, HEIGHT);
		
		/* Set our data. */
		text = new JLabel(name);
		icon = new JLabel();
		icon.setIcon(img);
		blowUp = true;
		/* Set panel properties. */
		this.setName(name);
		this.setSize(dim);
		this.setBackground(bg);
		this.setLayout(new BorderLayout());
		this.add(icon, BorderLayout.WEST);
		this.add(text, BorderLayout.CENTER);
	}
	
	public ContactWidget(Contact c, ImageIcon img){
		/* Link contact. */
		contact = c;
		/* Set variables. */
		String name = c.getAHRA() + "";
		Dimension dim = new Dimension(Integer.MAX_VALUE, HEIGHT);
		
		/* Set our data. */
		text = new JLabel(name);
		icon = new JLabel();
		icon.setIcon(img);
		blowUp = true;
		/* Set panel properties. */
		this.setName(name);
		this.setMaximumSize(dim);
		this.setLayout(new BorderLayout());
		this.add(icon, BorderLayout.WEST);
		this.add(text, BorderLayout.CENTER);
	}
	
	public void toggleBlowUp(){
		Rectangle r = this.getBounds();
		if(blowUp){
			text.setText(contact.toString());
			r.grow(0, GROW);
			this.setBounds(r);
			this.revalidate();
			//this.repaint();
			blowUp = false;
		}else{
			text.setText(contact.getAHRA() + "");
			r.grow(0, -GROW);
			this.setBounds(r);
			this.revalidate();
			//this.repaint();
			blowUp = true;
		}
	}
	
	public void highlight(boolean b){
		if(b){
			this.setBackground(highlight);
		}else{
			this.setBackground(bg);
		}
		this.repaint();
		//this.getParent().repaint();
	}
	
	public void pressed(boolean b){
		if(b){
			this.setBackground(pressed);
		}else{
			this.setBackground(highlight);
		}
		this.repaint();
	}
	
	public void animateAdd(){
		try{
			for(int i = 0; i < 220; i++){
				Thread.sleep(10);
				this.setBackground(new Color(i, 255, i));
				this.repaint();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	/**
	 * Get the UUID of the contact this widget represents.
	 * @return UUID of contact.
	 */
	public UUID getUUID(){
		return contact.getUUID();
	}
}