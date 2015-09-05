package generic;

import java.util.HashMap;

import javax.swing.JTextField;

public class InputForm {
	private HashMap<String,JTextField> fields;
	
	public InputForm(String ... strings){
		fields = new HashMap<String, JTextField>();
		for(String s: strings){
			fields.put(s, new JTextField(s));
		}
	}
	
	public JTextField getField(String name){
		JTextField tf = fields.get(name);
		return tf;
	}
	
	public String getText(String name){
		JTextField tf = fields.get(name);
		if(tf != null){
			return tf.getText();
		}else{
			return null;
		}
	}
	
	public void setText(String name, String text){
		JTextField tf = fields.get(name);
		if(tf != null){
			tf.setText(text);
		}
	}
}
