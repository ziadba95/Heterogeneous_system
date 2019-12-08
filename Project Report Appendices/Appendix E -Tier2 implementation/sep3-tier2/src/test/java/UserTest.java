import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import businesslogic.LogInWSImpl;
import businesslogic.SignUpServiceImpl;
import exceptions.InvalidFormException;
import exceptions.UserNotFound;

public class UserTest {
	
	private LogInWSImpl login;
	private SignUpServiceImpl signup;
	
	@Before
	public void setup() {
		login = new LogInWSImpl();
		signup = new SignUpServiceImpl();
	}
	
	@Test 
	public void DateFormatTest1() {
		try {
			System.out.println(login.logIn(null, "12345678"));
		} catch (NumberFormatException | SQLException | UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void SignUp() {
		try {
			System.out.println(signup.signUp("naya", "", "", "", "9999", "Vejle", "jhg@yhg.cim", null));
		} catch (NumberFormatException | InvalidFormException | SQLException e ) {
			e.printStackTrace();
		}
	}
	
	

}

