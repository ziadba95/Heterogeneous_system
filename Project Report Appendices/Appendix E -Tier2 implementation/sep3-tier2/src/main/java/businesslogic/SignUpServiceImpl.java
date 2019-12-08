package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import db.Jdbc;
import exceptions.InvalidFormException;
import model.User;

public class SignUpServiceImpl {

	private final String SIGN_UP = "insert into justwork.\"user\"(CPR, FName, LName, email, city, password, phone) values(?,?,?,?,?,?,?); ";
	private final String CHECK_USER = "select fname from justwork.\"user\" where cpr = ?";

	Jdbc db;

//	public boolean signUp(User user) {
//		return false;
//	}

	private boolean isValidForm(String fName, String lName, String password, String vPassword, String phone,  String cPR, String city,
			String email) throws InvalidFormException, SQLException {
		ArrayList<String> errorMessage = new ArrayList<String>();
		long phoneLong = 0;
		if (fName == null) {
			errorMessage.add("You must write your first name");
		} else {
			if (fName.length() > 50) {
				errorMessage.add("First name is too long");
			}
			if (fName.isEmpty()) {
				errorMessage.add("You must write your first name");
			}
		}
		if (lName == null) {
			errorMessage.add("You must write your last name");
		} else {
			if (lName.length() > 50) {
				errorMessage.add("Last name is too long");
			}
			if (lName.isEmpty()) {
				errorMessage.add("You must write your last name");
			}
		}
		if (password == null) {
			errorMessage.add("You must choose a password");
		} else {
			if (password.length() > 50) {
				errorMessage.add("Password is too long");
			}
			if (password.length() < 8) {
				errorMessage.add("Password is too short");
			}
		}
		
		if (vPassword == null) {
			errorMessage.add("Password verification is empty");
		} else {
			if(!vPassword.equals(password)) {
				errorMessage.add("Password and password verification don't match");
			}
		}
		if (phone == null) {
			errorMessage.add("You must write your phone number");
		} else {
			if (phone.length() > 19) {
				errorMessage.add("Phone number is invalid");
			}
			try {
				Long.parseLong(phone);
			} catch (NumberFormatException e) {
				errorMessage.add("Phone number is invalid");
			}

		}
		if (cPR == null) {
			errorMessage.add("You must write a valid CPR Number");
		} else {
			if (cPR.length() > 19) {
				errorMessage.add("CPR number is too long");
			}
		}
		if (city == null) {
			errorMessage.add("You must choose your county");
		} else {
			if (city.length() > 50) {
				errorMessage.add("county is too long");
			}
		}
		if (email == null) {
			errorMessage.add("You must provide a valid email");
		} else {
			if (email.length() > 50) {

				errorMessage.add("email is too long");
			}
			if (!Pattern.matches("[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*",
					email)) {
				errorMessage.add("Invalid email");
			}
		}

		try {
			long cprLong = Long.parseLong(cPR);
			if (cprAvailable(cprLong)) {
				if (errorMessage.size() > 0) {
					throw new InvalidFormException(errorMessage);
				}
				return true;
			} else
				errorMessage.add("A user with the same CPR number is already registered in the system");
			throw new InvalidFormException(errorMessage);

		} catch (NumberFormatException e) {
			errorMessage.add("CPR number contains invalid characters");
			throw new InvalidFormException(errorMessage);
		}

	}
//
//	private boolean isValidForm(User user) {
//		if (user.getfName().length() > 50) {
//
//		}
//	}

	public String signUp(String fName, String lName, String password,String vPassword, String phone, String cPR, String city,
			String email) throws NumberFormatException, InvalidFormException, SQLException {
		if (isValidForm(fName, lName, password, vPassword, phone, cPR, city, email)) {
			User user = new User(fName, lName, phone, email, cPR, city, password);
			System.out.println(user.getfName());
			saveUser(user);
			return user.getfName();
		} else
			return null;
	}

	public boolean saveUser(User user) throws SQLException {
		// CPR, FName, LName, email, city, password, phone
		if (db == null) {
			db = new Jdbc();
		}
		db.update(SIGN_UP, Long.parseLong(user.getcPR()), user.getfName(), user.getlName(), user.getEmail(),
				user.getCity(), user.getPassword(), Long.parseLong(user.getPhone()));

		return true;
	}

	public boolean cprAvailable(long cpr) throws SQLException {
		if (db == null) {
			db = new Jdbc();
		}
		ArrayList<Object[]> table = db.query(CHECK_USER, cpr);
		if (table.size() >= 1) {
			return false;
		} else
			return true;

	}

}
