package view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = -8668618688820184387L;	
	private final Color mainBg = new Color(0,0,0);
	protected JComponent content;
	protected MenuPanel menu;
	
	public MainPanel(){
		content = null;
		menu = null;
		this.setBackground(mainBg);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public MainPanel(MainPanel mp){
		content = mp.getContent();
		menu = mp.getMenu();
		this.setBackground(mainBg);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public MainPanel(JComponent jp, MenuPanel mp){
		content = jp;
		menu = mp;
		this.setBackground(mainBg);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void  setContent(JComponent jc){
		content = jc;
	}
	
	public void setMenu(MenuPanel mp){
		menu = mp;
	}
	
	public JComponent getContent(){
		return content;
	}
	
	public MenuPanel getMenu(){
		return menu;
	}
}
