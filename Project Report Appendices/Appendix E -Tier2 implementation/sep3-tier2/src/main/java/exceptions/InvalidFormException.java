package exceptions;

import java.util.ArrayList;

public class InvalidFormException extends Exception{
	
	private ArrayList<String> messages;
	
	public InvalidFormException(ArrayList<String> messages) {
		super(getMessage(messages));
		this.messages = messages;
	}
	
	public static String getMessage(ArrayList<String> messages) {
		String returnString = "";
		for (int i = 0; i < messages.size(); i++) {
			returnString = messages.get(i) + "\n";
		}
		return returnString;
	}
	
	public String getMessage() {
		String returnString = "";
		for (int i = 0; i < messages.size(); i++) {
			returnString += messages.get(i) + "\n";
		}
		return returnString;
	}
}
