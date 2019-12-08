package ws;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import exceptions.EmptyListException;
import exceptions.ExpiredSession;
import exceptions.InvalidFormException;
import exceptions.UserNotFound;
import model.Applicant;
import model.Post;
import model.ServiceOffer;
import model.ServiceRequest;
import model.User;

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface IWebservice {
	@WebMethod
	public String signUp(@WebParam(name = "fName")String fName, 
			@WebParam(name = "lName")String lName, 
			@WebParam(name = "password")String password,
			@WebParam(name = "vPassword")String vPassword,
			@WebParam(name = "phone")String phone, 
			@WebParam(name = "cpr")String cPR, 
			@WebParam(name = "city")String city,
			@WebParam(name = "email")String email)throws NumberFormatException, InvalidFormException, RemoteException, SQLException, NotBoundException ;
	@WebMethod
	public boolean postOffer(@WebParam(name = "title")String title, 
			@WebParam(name = "category")String category, 
			@WebParam(name = "description")String description,
			@WebParam(name = "cpr")String cpr, 
			@WebParam(name = "address")String address, 
			@WebParam(name = "bookingTime")String bookingTime, 
			@WebParam(name = "salary")String salary)
			throws ExpiredSession, InvalidFormException, SQLException	;
	
	@WebMethod
	public boolean postRequest(@WebParam(name = "title")String title, 
			@WebParam(name = "category") String category, 
			@WebParam(name = "description")String description,
			@WebParam(name = "cpr")String cpr)
					throws ExpiredSession, InvalidFormException, SQLException ;
	
	@WebMethod
	public String logIn(@WebParam(name = "cpr")String cPR,
			@WebParam(name = "password")String password) throws RemoteException, SQLException, UserNotFound;
	
	
	@WebMethod
	public ArrayList<ServiceRequest> getServiceRequestsNF(@WebParam(name = "cpr")long cpr)throws SQLException, EmptyListException;
	
	
	@WebMethod
	public ArrayList<ServiceOffer> getServiceOffersNF(@WebParam(name = "cpr")long cpr) throws SQLException, EmptyListException;


	@WebMethod
	public ArrayList<String> getCategories() throws SQLException;
	
	
	@WebMethod
	public void createProfile(@WebParam(name = "CPR")String cpr,@WebParam(name = "description") String description)
			throws SQLException, UserNotFound, InvalidFormException, ExpiredSession;
	
//	@WebMethod
//	public void addSkillToSkillsTable(@WebParam(name = "Skill")String skill) throws SQLException, InvalidFormException;
//
//	@WebMethod
//	public void addSkillToUser(@WebParam(name = "CPR")String cpr,@WebParam(name = "skill")String skill) throws SQLException, InvalidFormException, ExpiredSession;
//	
	@WebMethod
	public void editProfile(@WebParam(name = "cpr")String cpr, @WebParam(name = "description") String description,@WebParam(name = "county") String county,
			@WebParam(name = "phone") String phone, @WebParam(name = "email") String email, 
			@WebParam(name = "password") String password, 
			@WebParam(name = "vPassword")String vPassword) throws SQLException, InvalidFormException, ExpiredSession;
	
//	@WebMethod
//	public void removeSkillFromUser(@WebParam(name = "CPR")String cpr,@WebParam(name = "skill")String skill) throws SQLException, ExpiredSession, InvalidFormException;
//	
 
	@WebMethod
	public ArrayList<ServiceOffer> getMyServiceOffers(@WebParam(name = "CPR")long cpr) throws SQLException, EmptyListException ;
		
	@WebMethod
	public ArrayList<ServiceRequest> getMyServiceRequests(@WebParam(name = "CPR")long cpr) throws SQLException, EmptyListException ;

	@WebMethod
	public User getProfile(@WebParam(name = "CPR")String  cpr) throws SQLException, ExpiredSession ;
	
	@WebMethod
	public void addApplicant(@WebParam(name = "CPR")long cpr,@WebParam(name = "PostID") int postID) throws SQLException;
	
	@WebMethod
	public void deleteApplicant(@WebParam(name = "CPR")long cpr,@WebParam(name = "PostID") int postID) throws SQLException;

	@WebMethod 
	public ArrayList<Applicant> getApplicants(@WebParam(name = "PostID") int postID) throws SQLException, EmptyListException;
	
	@WebMethod
	public void deleteRequest(@WebParam(name = "PostID")int postID) throws SQLException;
	
	@WebMethod
	public void deleteOffer(@WebParam(name = "PostID")int postID) throws SQLException;
	
	@WebMethod
	public ServiceOffer getServiceOffer(@WebParam(name = "PostID")int postID) throws SQLException, EmptyListException;

	@WebMethod
	public ServiceRequest getServiceRequest(@WebParam(name = "PostID")int postID) throws SQLException, EmptyListException;
	
	@WebMethod
	public ArrayList<String> getCounties() throws SQLException;
	
	@WebMethod
	public ArrayList<ServiceOffer> getOffersIAppliedFor(@WebParam(name = "CPR")long cpr) throws SQLException, EmptyListException;
}
