package exceptions;

public class EmptyListException extends Exception {
	public EmptyListException(){
		super("The list is empty");
	}
	
	public EmptyListException(String message){
		super(message);
	}
}
