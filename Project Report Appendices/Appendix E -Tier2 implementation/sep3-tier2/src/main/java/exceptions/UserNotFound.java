package exceptions;

public class UserNotFound extends Exception {
	public UserNotFound() {
		super("Logging in failed due to invalid CPR number or password");
	}
}
