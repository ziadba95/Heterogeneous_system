import java.sql.SQLException;
import java.util.zip.DataFormatException;

import org.junit.Before;
import org.junit.Test;

import businesslogic.NewsFeedWSImpl;
import businesslogic.PostWSImpl;
import exceptions.EmptyListException;
import exceptions.ExpiredSession;
import exceptions.InvalidFormException;
import model.ServiceOffer;

public class PostTest {
	
	private PostWSImpl test;
	private NewsFeedWSImpl nf;
	
	@Before
	public void setup() {
		test = new PostWSImpl();
		nf = new NewsFeedWSImpl();
	}
	
	@Test 
	public void DateFormatTest1() {
		try {
			System.out.println(test.getDate("03-mar-23 22:33:22"));
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(expected = InvalidFormException.class)
	public void testOfferTitleLong() {
		try {
			test.postOffer("oijuhygtfrdfghjkl;lkoijhugyftdrsedrfghjkl;kjlijuhygtfrdfghujikolhgfdsxzasdfghjkl;lkjhgfdsedrftgyhujikolkijuhygtfrddftg", "Cleaning", "hello", "98765437",  "Kollegievaenget", "03-mar-23 22:33:22", "1000");
		} catch (ExpiredSession | InvalidFormException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test(expected = InvalidFormException.class)
	public void testOfferTitleEmpty() {
		try {
			test.postOffer("", "Cleaning", "hello", "98765437",  "Kollegievaenget", "03-mar-23 22:33:22", "1000");
		} catch (ExpiredSession | InvalidFormException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testOfferTitleNull() {
		try {
			test.postOffer("juhykjhg", "Cleaning", "hello", "98765437",  "Kollegievaenget", "03-mar-23 22:33:22", "1000");
		} catch (ExpiredSession | InvalidFormException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void newsfeedTest1() throws EmptyListException {
		try {
			nf.getServiceRequestsNF(98765437);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRequestTitleLong() {
		try {
			test.postRequest("okijuhjygfc", "Cleaning", "hello", "98765437");
		} catch (ExpiredSession | InvalidFormException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test 
	public void testTimestampPostOffer() throws SQLException, EmptyListException {
		nf.getServiceOffersNF(98);
	}
	
	@Test
	public void testAddApplicant()  {
		try {
			test.addApplicant(98, 5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGETApplicant()  {
		try {
			System.out.println(test.getApplicants(30).get(0).getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
