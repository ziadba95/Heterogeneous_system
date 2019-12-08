import java.rmi.RemoteException;
import java.sql.SQLException;

import businesslogic.LogInWSImpl;
import businesslogic.SignUpServiceImpl;
import exceptions.InvalidFormException;

public class test {
public static void main(String[] args) throws RemoteException, SQLException  {
	LogInWSImpl ws = new LogInWSImpl();
//		System.out.println(ws.logIn("0201006120", "MMMMMMMM"));
//		System.out.println(ws.checkUser(201006120, "MMMMMMMM"));
		
		SignUpServiceImpl signup = new SignUpServiceImpl();
		try {
			System.out.println(signup.signUp("nayyaa", "iuy", "IUYTRFGHJKO", "12345678", "098765437", "Vejle", "iuy@kiu.com", null));
		} catch (NumberFormatException | InvalidFormException  e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	
}
}
