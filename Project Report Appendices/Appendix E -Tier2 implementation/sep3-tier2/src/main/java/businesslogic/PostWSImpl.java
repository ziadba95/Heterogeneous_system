package businesslogic;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

import dao.PostDAO;
import db.Jdbc;
import exceptions.EmptyListException;
import exceptions.ExpiredSession;
import exceptions.InvalidFormException;
import model.Applicant;
import model.ServiceOffer;
import model.ServiceRequest;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.DataFormatException;

public class PostWSImpl {

	// first it should check if the ArrayList of string are all valid
	// then if its valid it will create a service post object
	// save it to the database using dbConnection

	private PostDAO db;

	public java.util.Date getDate(String date) throws DataFormatException {
		// 27-May-19 08:36:00 AM
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
		try {
			java.util.Date dateUtil = format.parse(date);
			return dateUtil;
		} catch (Exception e) {
			throw new DataFormatException("The date has an invalid format");
		}
	}


	public boolean postOffer(String title, String category, String description, String cpr, String address,
			String bookingTime, String salary) throws ExpiredSession, InvalidFormException, SQLException {
		if (isValidOffer(title, category, description, cpr, address, bookingTime, salary)) {
			try {
				if(description == null) {
					description = "Not specified";
				}
				ServiceOffer offer = new ServiceOffer(title, category, getDate(bookingTime), cpr, description, address,
						Double.parseDouble(salary));
				instantiateDB();
				return db.SaveOfferToDB(offer);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	private boolean isValidOffer(String title, String category, String description, String cpr, String address,
			String bookingTime, String salary) throws ExpiredSession, InvalidFormException {
		ArrayList<String> errorMessage = new ArrayList<String>();
		double salaryAsDouble;
		if (cpr == null) {
			throw new ExpiredSession("Session Has Expired");
		} else {
			if (cpr.isEmpty()) {
				throw new ExpiredSession("Session Has Expired");
			}
		}
		if (title == null) {
			errorMessage.add("The service title is empty");
		} else {
			if (title.isEmpty()) {
				errorMessage.add("The service title is empty");
			}
			if (title.length() > 50) {
				errorMessage.add("Title must not be more than 50 characters");
			}
		}
		if (category == null) {
			errorMessage.add("Service category cannot be empty");
		} else {
			if (category.isEmpty()) {
				errorMessage.add("Service category cannot be empty");
			}
			if (category.length() > 50) {
				errorMessage.add("Service category cannot be more than 50 characters long");
			}
		}
		if (description == null) {
			description = "Not specified";
		} else {
			if (description.length() > 300) {
				errorMessage.add("Service description can't be more than 300 characters");
			}
			if (description.isEmpty()) {
				description = "Not specified";
			}
		}
		if (address == null) {
			errorMessage.add("The Address is empty");
		} else {
			if (address.isEmpty()) {
				errorMessage.add("The Address is empty");
			}
			if (address.length() > 50) {
				errorMessage.add("Address must not be more than 50 characters");
			}
		}
		if (salary == null) {
			errorMessage.add("Salary can't be empty");
		} else {
			if (salary.isEmpty()) {
				errorMessage.add("Salary can't be empty");
			}
		}
		// 27-May-19 08:36:00 AM
		try {
			getDate(bookingTime);
			salaryAsDouble = Double.parseDouble(salary);

		} catch (DataFormatException e) {
			errorMessage.add(e.getMessage());
		} catch (NumberFormatException e) {
			errorMessage.add("Salary must only contain numbers");
		} finally {
			if (errorMessage.size() > 0) {
				throw new InvalidFormException(errorMessage);
			}
		}
		return true;
	}

	public boolean postRequest(String title, String category, String description, String cpr)
			throws ExpiredSession, InvalidFormException, SQLException {
		if (isValidRequest(title, category, description, cpr)) {
			try {
				if(description == null) {
					description = "Not specified";
				}
				ServiceRequest request = new ServiceRequest(title, category, description, cpr);
				instantiateDB();
				return db.SaveRequestToDB(request);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	private boolean isValidRequest(String title, String category, String description, String cpr)
			throws ExpiredSession, InvalidFormException {
		ArrayList<String> errorMessage = new ArrayList<String>();
		if (cpr == null) {
			throw new ExpiredSession("Session Has Expired");
		} else {
			if (cpr.isEmpty()) {
				throw new ExpiredSession("Session Has Expired");
			}
		}
		if (title == null) {
			errorMessage.add("The service title is empty");
		} else {
			if (title.isEmpty()) {
				errorMessage.add("The service title is empty");
			}
			if (title.length() > 50) {
				errorMessage.add("Title must not be more than 50 characters");
			}
		}
		if (category == null) {
			errorMessage.add("Service category cannot be empty");
		} else {
			if (category.isEmpty()) {
				errorMessage.add("Service category cannot be empty");
			}
			if (category.length() > 50) {
				errorMessage.add("Service category cannot be more than 50 characters long");
			}
		}
		if (description == null) {
			description = "Not specified";
		} else {
			if (description.length() > 300) {
				errorMessage.add("Service description can't be more than 300 characters");
			}
			if (description.isEmpty()) {
				description = "Not specified";
			}
		}
		if (errorMessage.size() > 0) {
			throw new InvalidFormException(errorMessage);
		}
		return true;
	}

	public ArrayList<String> getCategories() throws SQLException {
		instantiateDB();
		return db.getCategories();
	}


	public ArrayList<ServiceRequest> getMyServiceRequests(long cpr) throws SQLException, EmptyListException {
		instantiateDB();
		return db.getMyServiceRequests(cpr);

	}

	public ArrayList<ServiceOffer> getMyServiceOffers(long cpr) throws SQLException, EmptyListException {
		instantiateDB();
		return db.getMyServiceOffers(cpr);
	}
	
	public void addApplicant(long cpr, int postID) throws SQLException {
		instantiateDB();
		if(!db.alreadyApplied(cpr, postID)) {
			db.addApplicant(cpr, postID);
		}
	}
	
	public void deleteApplicant(long cpr, int postID) throws SQLException {
		instantiateDB();
		if(db.alreadyApplied(cpr, postID)) {
			db.deleteApplicant(cpr, postID);
		}
	}
	
	public ArrayList<Applicant> getApplicants(int postID) throws SQLException, EmptyListException{
		instantiateDB();
		return db.getApplicants(postID);
	}
	
	public void deleteRequest(int postID) throws SQLException {
		instantiateDB();
		db.deleteRequest(postID);
	}
	
	public void deleteOffer(int postID) throws SQLException {
		instantiateDB();
		db.deleteOffer(postID);
	}
	
	private void instantiateDB() {
		if(db == null) {
			db = new PostDAO();
		}
	}
	
	public ServiceOffer getServiceOffer(int postID) throws SQLException, EmptyListException {
		instantiateDB();
		return db.getServiceOffer(postID);
	}
	
	public ServiceRequest getServiceRequest(int postID) throws SQLException, EmptyListException {
		instantiateDB();
		return db.getServiceRequest(postID);
	}
	
	public ArrayList<ServiceOffer> getOffersIAppliedFor(long cpr) throws SQLException, EmptyListException{
		instantiateDB();
		return db.getOffersIAppliedFor(cpr);
	}
}
