package ws;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import businesslogic.LogInWSImpl;
import businesslogic.NewsFeedWSImpl;
import businesslogic.PostWSImpl;
import businesslogic.ProfileBL;
import businesslogic.SignUpServiceImpl;
import exceptions.EmptyListException;
import exceptions.ExpiredSession;
import exceptions.InvalidFormException;
import exceptions.UserNotFound;
import model.Applicant;
import model.ServiceOffer;
import model.ServiceRequest;
import model.User;

@BindingType("http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
@WebService(endpointInterface = "ws.IWebservice")
public class MyWebService implements IWebservice {

	private LogInWSImpl login = new LogInWSImpl();
	private SignUpServiceImpl signUp = new SignUpServiceImpl();
	private NewsFeedWSImpl newsfeed = new NewsFeedWSImpl();
	private PostWSImpl post = new PostWSImpl();
	private ProfileBL profile = new ProfileBL();

	@Override
	public String signUp(String fName, String lName, String password,String vPassword, String phone, String cPR, String city,
			String email)
			throws NumberFormatException, InvalidFormException, RemoteException, SQLException, NotBoundException {
		return signUp.signUp(fName, lName, password, vPassword, phone, cPR, city, email);
	}

	@Override
	public boolean postOffer(String title, String category, String description, String cpr, String address,
			String bookingTime, String salary) throws ExpiredSession, InvalidFormException, SQLException {
		return post.postOffer(title, category, description, cpr, address, bookingTime, salary);
	}

	@Override
	public boolean postRequest(String title, String category, String description, String cpr)
			throws ExpiredSession, InvalidFormException, SQLException {
		return post.postRequest(title, category, description, cpr);
	}

	@Override
	public String logIn(String cPR, String password) throws RemoteException, SQLException, UserNotFound {
		return login.logIn(cPR, password);
	}

	@Override
	public ArrayList<ServiceOffer> getServiceOffersNF(long cpr) throws SQLException, EmptyListException {
		return newsfeed.getServiceOffersNF(cpr);
	}

	@Override
	public ArrayList<ServiceRequest> getServiceRequestsNF(long cpr) throws SQLException, EmptyListException {
		return newsfeed.getServiceRequestsNF(cpr);
	}

	@Override
	public ArrayList<String> getCategories() throws SQLException {
		return post.getCategories();
	}

	@Override
	public void createProfile(String cpr, String description)
			throws SQLException, UserNotFound, InvalidFormException, ExpiredSession {
		profile.createProfile(cpr, description);
	}

//	@Override
//	public void addSkillToSkillsTable(String skill) throws SQLException, InvalidFormException {
//		profile.addSkillToSkillsTable(skill);
//	}
//
//	@Override
//	public void addSkillToUser(String cpr, String skill) throws SQLException, InvalidFormException, ExpiredSession {
//		profile.addSkillToUser(cpr, skill);
//	}

	@Override
	public void editProfile(String cpr, String description, String county, String phone, String email, String password,
			String vPassword) throws SQLException, InvalidFormException, ExpiredSession {
		profile.editProfile(cpr, description, county, phone, email, password, vPassword);

	}

//	@Override
//	public void removeSkillFromUser(String cpr, String skill)
//			throws SQLException, ExpiredSession, InvalidFormException {
//		profile.removeSkillFromUser(cpr, skill);
//
//	}

	@Override
	public ArrayList<ServiceOffer> getMyServiceOffers(long cpr) throws SQLException, EmptyListException {
		return post.getMyServiceOffers(cpr);
	}

	@Override
	public ArrayList<ServiceRequest> getMyServiceRequests(long cpr) throws SQLException, EmptyListException {
		return post.getMyServiceRequests(cpr);
	}

	public User getProfile(String cpr) throws SQLException, ExpiredSession {
		return profile.getProfile(cpr);
	}

	@Override
	public void addApplicant(long cpr, int postID) throws SQLException {
		post.addApplicant(cpr, postID);

	}

	@Override
	public void deleteApplicant(long cpr, int postID) throws SQLException {
		post.deleteApplicant(cpr, postID);
	}

	@Override
	public ArrayList<Applicant> getApplicants(int postID) throws SQLException, EmptyListException {
		return post.getApplicants(postID);
	}

	@Override
	public void deleteRequest(int postID) throws SQLException {
		post.deleteRequest(postID);
		
	}

	@Override
	public void deleteOffer(int postID) throws SQLException {
		post.deleteOffer(postID);
		
	}
	
	@Override
	public ServiceOffer getServiceOffer(int postID) throws SQLException, EmptyListException{
		return post.getServiceOffer(postID);
	}

	@Override
	public ServiceRequest getServiceRequest(int postID) throws SQLException, EmptyListException {
		return post.getServiceRequest(postID);
	}

	@Override
	public ArrayList<String> getCounties() throws SQLException {
		return profile.getCounties();
	}

	@Override
	public ArrayList<ServiceOffer> getOffersIAppliedFor(long cpr) throws SQLException, EmptyListException {
		return post.getOffersIAppliedFor(cpr);
	}
	
	
	
	

}
