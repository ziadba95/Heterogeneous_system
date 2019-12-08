package businesslogic;



import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

import db.Jdbc;
import exceptions.UserNotFound;

public class LogInWSImpl {
	
	private Jdbc db;
	private final String LOG_IN = "select fname from justwork.\"user\" where cpr = ? and password = ? ;";

	
	
	public String logIn(String cPR, String password) throws SQLException, NumberFormatException, UserNotFound {
		// if returns true--> login is successful, it should take the user to the main page
		// if returns false--> wrong password/CPR
		// if an exception occurs--> something wrong happened with connection
	 	if(cPR == null) {
	 		throw new UserNotFound();
	 		}
		try{
	 		long cprLong = Long.parseLong(cPR);
	 		return checkUser(cprLong, password);
	 	}catch (NumberFormatException e) {
	 		throw new UserNotFound();
	 	}
		
	}
	
	public String checkUser(long cPR, String password) throws SQLException, UserNotFound {
		if(db == null) {
			db = new Jdbc();
		}
		ArrayList<Object[]> rows = db.query(LOG_IN, cPR, password);
		if(rows.size() >= 1) {
			return rows.get(0)[0].toString();
		} else throw new UserNotFound();
	}
	
	
}
