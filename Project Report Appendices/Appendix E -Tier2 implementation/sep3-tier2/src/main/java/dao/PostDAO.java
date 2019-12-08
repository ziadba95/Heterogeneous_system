package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import db.Jdbc;
import exceptions.EmptyListException;
import model.Applicant;
import model.ServiceOffer;
import model.ServiceRequest;

public class PostDAO {
	private Jdbc db;

	private final String POST_SERVICE_REQUEST = "insert into justwork.servicerequests(title, category, description, whoPosted) values(?,?,?,?); ";
	private final String POST_SERVICE_OFFER = "insert into justwork.serviceoffers(title, category, bookingtime, description, address, salary, whoPosted) values(?,?,?,?,?,?,?); ";
	public static final String CHECK_IF_USER_ALREADY_APPLIED = "select * from justwork.serviceoffersapplicants where applicant = ? and postid = ?;";
	private final String ADD_APPLICANT = "insert into justwork.serviceoffersapplicants(applicant,postid) values( ? , ? )";
	private final String REMOVE_APPLICANT = "DELETE FROM justwork.serviceoffersapplicants WHERE applicant = ? AND postid = ? ;";
	private final String GET_APPLICANTS = " SELECT concat(fname, ' ', lname) , description, cpr from justwork.serviceoffersapplicants \r\n"
			+ " left join justwork.\"user\" on cpr = applicant\r\n"
			+ " left join justwork.profiles on userid = applicant where postid = ?;";

	private final String GET_MY_OFFERS = "select title, category, bookingtime, postid, description, address, salary"
			+ "	from justwork.serviceoffers" + "	where whoposted = ?";

	private final String GET_MY_REQUESTS = "select title, category, description, postid"
			+ "	from justwork.servicerequests where whoposted = ?;";

	private final String DELETE_OFFER = "DELETE FROM justwork.serviceoffers WHERE postid = ? ;";
	private final String DELETE_REQUEST = "DELETE FROM justwork.servicerequest WHERE postid = ? ;";

	private final String GET_OFFER = "select title, category, bookingtime, postid, description, address, salary, whoposted"
			+ "	from justwork.serviceoffers" + "	where postid = ?";
	private final String GET_REQUEST = "select title, category, description, postid, whoposted"
			+ "	from justwork.servicerequests where postid = ?;";
	
	
	private final String GET_OFFERS_I_APPLIED_FOR = "select title, category, bookingtime, concat(fname, ' ', lname), description, address, salary , soa.postid from "
			+ "justwork.serviceoffersapplicants soa "
			+ "left join justwork.serviceoffers so on so.postid = soa.postid "
			+ "left join justwork.\"user\" on cpr = whoposted"
			+ " where applicant = ? ";

	public PostDAO() {
		db = new Jdbc();
	}

	public boolean SaveOfferToDB(ServiceOffer offer) throws SQLException {
		Timestamp time = new Timestamp(offer.getBookingTime().getTime());
		try {
			db.update(POST_SERVICE_OFFER, offer.getJobTitle(), offer.getJobCategory(), time, offer.getDescription(),
					offer.getAddress(), offer.getSalary(), Long.parseLong(offer.getWhoPosted()));
		} catch (SQLException e) {
			throw e;
		}

		return true;
	}

	public boolean SaveRequestToDB(ServiceRequest request) throws SQLException {
		try {
			db.update(POST_SERVICE_REQUEST, request.getJobTitle(), request.getJobCategory(), request.getDescription(),
					Long.parseLong(request.getWhoPosted()));

		} catch (SQLException e) {
			throw e;
		}

		return true;
	}

	public ArrayList<String> getCategories() throws SQLException {
		String sql = "select * from justwork.categoryID;";
		ArrayList<Object[]> table = db.query(sql);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < table.size(); i++) {
			list.add(table.get(i)[0].toString());
		}
		return list;
	}

	public ArrayList<ServiceOffer> getMyServiceOffers(long cpr) throws SQLException, EmptyListException {
//title, category, bookingtime, postid, description, address, salary
		String cprAsString = cpr + "";
		ArrayList<Object[]> table = db.query(GET_MY_OFFERS, cpr);
		if(table.size() == 0) {
			throw new EmptyListException("You haven't posted any service offers yet");
		}
		ArrayList<ServiceOffer> list = new ArrayList<ServiceOffer>();

		for (int i = 0; i < table.size(); i++) {
			Object[] row = table.get(i);
			// String jobTitle, String jobCategory, Date bookingTime, String
			// whoPosted,String description, String address, double salary
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ServiceOffer offer;
			if(row[4] == null) {
				row[4] = "Not specified";
			}
			try {
				
				offer = new ServiceOffer(row[0].toString(), row[1].toString(), format.parse(row[2].toString()),
						cprAsString, row[4].toString(), row[5].toString(), Double.parseDouble(row[6].toString()),
						Integer.parseInt(row[3].toString()));

				list.add(offer);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	public ServiceOffer getServiceOffer(int postID) throws SQLException, EmptyListException {

		ArrayList<Object[]> table = db.query(GET_OFFER, postID);
		ServiceOffer offer = null;
		if(table.size() == 0) {
			throw new EmptyListException("Service offer cannot be found");
		}
		Object[] row = table.get(0);
		if(row[4] == null) {
			row[4] = "Not specified";
		}
		// String jobTitle, String jobCategory, Date bookingTime, String
		// whoPosted,String description, String address, double salary
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			offer = new ServiceOffer(row[0].toString(), row[1].toString(), format.parse(row[2].toString()),
					row[7].toString(), row[4].toString(), row[5].toString(), Double.parseDouble(row[6].toString()),
					Integer.parseInt(row[3].toString()));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return offer;
	}

	public ArrayList<ServiceRequest> getMyServiceRequests(long cpr) throws SQLException, EmptyListException {

		String cprAsString = cpr + "";
		ArrayList<Object[]> table = db.query(GET_MY_REQUESTS, cpr);
		if(table.size() == 0) {
			throw new EmptyListException("You haven't posted any service requests yet");
		}
		ArrayList<ServiceRequest> list = new ArrayList<ServiceRequest>();

		for (int i = 0; i < table.size(); i++) {
			Object[] row = table.get(i);
			// title, category, description, postid
			ServiceRequest request = new ServiceRequest(row[0].toString(), row[1].toString(), row[2].toString(),
					cprAsString, Integer.parseInt(row[3].toString()));
			list.add(request);
		}
		return list;
	}

	public ServiceRequest getServiceRequest(int postID) throws SQLException, EmptyListException {

		ArrayList<Object[]> table = db.query(GET_REQUEST, postID);
		if(table.size() == 0) {
			throw new EmptyListException("Service request cannot be found");
		}
		ServiceRequest request = null;

		Object[] row = table.get(0);
		// title, category, description, postid
		request = new ServiceRequest(row[0].toString(), row[1].toString(), row[2].toString(), row[4].toString(),
				Integer.parseInt(row[3].toString()));

		return request;
	}

	public void deleteOffer(int postID) throws SQLException {
		db.update(DELETE_OFFER, postID);
	}

	public void deleteRequest(int postID) throws SQLException {
		db.update(DELETE_REQUEST, postID);
	}

	public void deleteApplicant(long cpr, int postID) throws SQLException {
		db.update(REMOVE_APPLICANT, cpr, postID);
	}

	public void addApplicant(long cpr, int postID) throws SQLException {
		db.update(ADD_APPLICANT, cpr, postID);
	}

	public boolean alreadyApplied(long cpr, int postID) throws SQLException {
		ArrayList<Object[]> table = db.query(CHECK_IF_USER_ALREADY_APPLIED, cpr, postID);
		if (table.size() >= 1) {
			return true;
		} else
			return false;
	}

	public ArrayList<Applicant> getApplicants(int postID) throws SQLException, EmptyListException {
		ArrayList<Object[]> table = db.query(GET_APPLICANTS, postID);
		if(table.size() == 0) {
			throw new EmptyListException("There are no applicants yet");
		}
		ArrayList<Applicant> applicants = new ArrayList<Applicant>();
		//concat(fname, ' ', lname) , description, cpr
		for (int i = 0; i < table.size(); i++) {
			Object[] row = table.get(i);
			if(row[1] == null) {
				row[1] = "Not specified";
			}
			Applicant applicant = new Applicant(Long.parseLong(row[2].toString()), row[0].toString(), row[1].toString());
			applicants.add(applicant);
		}

		return applicants;
	}
	
	public ArrayList<ServiceOffer> getOffersIAppliedFor(long cpr) throws SQLException, EmptyListException{
		//title, category, bookingtime, whoposted, description, address, salary , postid
		String cprAsString = cpr + "";
		ArrayList<Object[]> table = db.query(GET_OFFERS_I_APPLIED_FOR, cpr);
		if(table.size() == 0) {
			throw new EmptyListException("You haven't applied for any service offers yet");
		}
		ArrayList<ServiceOffer> list = new ArrayList<ServiceOffer>();

		for (int i = 0; i < table.size(); i++) {
			Object[] row = table.get(i);
			// String jobTitle, String jobCategory, Date bookingTime, String
			// whoPosted,String description, String address, double salary
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ServiceOffer offer;
			try {
				offer = new ServiceOffer(row[0].toString(), row[1].toString(), format.parse(row[2].toString()),
						row[3].toString(), row[4].toString(), row[5].toString(), Double.parseDouble(row[6].toString()),
						Integer.parseInt(row[7].toString()));
				
				list.add(offer);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
	
	
}
