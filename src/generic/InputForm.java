package generic;

import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputForm extends JPanel{
	private static final long serialVersionUID = 928546803453724560L;
	private HashMap<String,JTextField> fieldMap;
	
	public InputForm(String ... strings){
		fieldMap = new HashMap<String, JTextField>();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JTextField field;
		for(String s: strings){
			field = new JTextField(s);
			fieldMap.put(s, field);
			this.add(field);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public InputForm(Enum ... enums){
		fieldMap = new HashMap<String, JTextField>();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JTextField field;
		for(Enum e: enums){
			field = new JTextField(e.toString());
			fieldMap.put(e.name(), field);
			this.add(field);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public String getText(Enum e){
		JTextField tf = fieldMap.get(e.name());
		if(tf != null){
			return tf.getText();
		}else{
			return null;
		}
	}
	
	public String getText(String name){
		JTextField tf = fieldMap.get(name);
		if(tf != null){
			return tf.getText();
		}else{
			return null;
		}
	}
	
	public void setText(String name, String text){
		JTextField tf = fieldMap.get(name);
		if(tf != null){
			tf.setText(text);
		}
	}
	
	public void reset(){
		String name;
		JTextField field;
		for(Map.Entry<String, JTextField> 
				comp: fieldMap.entrySet()){
			name = comp.getKey();
			field = comp.getValue();
			field.setText(name);
		}
	}
	
	@Override
	public void addFocusListener(FocusListener fl){
		JTextField field;
		for(Map.Entry<String, JTextField> 
				comp: fieldMap.entrySet()){
			field = comp.getValue();
			field.addFocusListener(fl);
		}
	}
}
