package view;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	private static final long serialVersionUID = -1513008632557139095L;
	private final Color bg = new Color(50,50,50);
		
	public MenuPanel(){
		this.setBackground(bg);
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
	}
	
	protected JButton getDefaultButton(String text){
		Color buttonBg = new Color(50,50,120);
		Color buttonFg = new Color(255,255,255);
		JButton result = new JButton(text);
		result.setBackground(buttonBg);
		result.setForeground(buttonFg);
		return result;
	}
}
