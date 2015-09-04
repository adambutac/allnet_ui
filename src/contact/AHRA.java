package contact;

public class AHRA {
	public static final String SPEC_CHAR_WHITELIST = "0123456789abcdefghijklmnopqrstuvwxyz@_-";
	private String ahra;
	
	public AHRA(){
		ahra = "default@of_work";
	}
	
	public AHRA(String address){
		if(!validate(address)){
			ahra = null;
		}else{
			ahra = address;
		}
	}
	
	/**
	 * Validates this AllnetHumanReadableAddress.
	 * <br>Valid AHRA's should be of the form username@word_word.
	 * <br>username@ is also valid, but not recommended as it is less secure.
	 * @return true only if the string is a valid AHRA
	 */
	public static boolean validate(String address){		
		/*Null adddresses are not welcome.*/
		if(address == null){
			System.out.println("AHRA is null.");
			return false;
		}
		
		/* Trim leading and trailing whitespace.
		 * Make sure it isn't empty.
		 * Check for invalid characters.*/
		address = address.trim();
		if(address.isEmpty()){
			System.out.println("AHRA is an empty string.");
			return false;
		}
		
		/* Set everything to lowercase. */
		int atCount = 0;
		address = address.toLowerCase();
		for(char c: address.toCharArray()){
			if(SPEC_CHAR_WHITELIST.indexOf(c) < 0){
				System.out.println("Character \'" + c + "\' is not allowed.");
				return false;
			}
			
			if(c == '@'){
				atCount++;
			}
			
			if(atCount > 1){
				System.out.println("Only one '@' symbol is allowed.");
				return false;
			}
		}
		
		String[] tokens = address.split("@");
		switch(tokens.length){
			case 1:
				if(address.endsWith("@")){
					System.out.println("WARNING! Your AHRA does not contain a memorable phrase.\n"
							 		 + "This allowed, but not recommended.");
					return true;
				}else{
					System.out.println("AHRA does not contain the required @ symbol.");
					return false;
				}
			case 2:
				if(tokens[0].isEmpty()){
					System.out.println("AHRA must contain a username.");
					return false;
				}else if(tokens[1].isEmpty()){
					System.out.println("WARNING! Your AHRA does not contain a memorable phrase.\n"
					 		         + "This allowed, but not recommended.");
				}
				return true;
			default:
				if(tokens.length < 1){
					System.out.println("AHRA must contain a username.");
				}else if(tokens.length > 2){
					System.out.println("AHRA contains too many \'@\' symbols.");
				}
				return false;
		}
	}
	
	@Override
	public String toString(){
		return ahra;
	}
	
	public static void main(String[] args){		
		String[] testList = {"   leading@space", "trailing@space    ", "   lead@trail  ",
							null, "", " ", "           ", "@",
							"a", "@#$%!", "@of_work", "AHRA@@",
							"aaa@aa@aa", "uname@"};
		
		for(String s: testList){
			System.out.print("Testing:" + s + ":");
			if(validate(s)){ 
				System.out.println(" pass.");
			}else{
				System.out.println(" failed.");
			}
		}
	}
}