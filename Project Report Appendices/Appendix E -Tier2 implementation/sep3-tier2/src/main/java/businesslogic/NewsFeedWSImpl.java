package businesslogic;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import db.Jdbc;
import exceptions.EmptyListException;
import model.ServiceOffer;
import model.ServiceRequest;

public class NewsFeedWSImpl {
	private Jdbc db;
	private final String GET_REQUESTS = "select title, category, description, concat(fname, ' ', lname) as name, postid"
			+ "	from justwork.servicerequests left join justwork.\"user\" on whoposted = cpr"
			+ "	where city = (select city from justwork.\"user\"  where cpr = ?) AND NOT whoposted = ?";
	private final String GET_OFFERS = "select title, category, bookingtime, concat(fname, ' ', lname) as name, description, address, salary, postid"
			+ "	from justwork.serviceoffers left join justwork.\"user\" on whoposted = cpr"
			+ "	where city = (select city from justwork.\"user\"  where cpr = ?) AND bookingtime >= now() AND NOT whoposted = ?;";

	public ArrayList<ServiceOffer> getServiceOffersNF(long cpr) throws SQLException, EmptyListException {
		if (db == null) {
			db = new Jdbc();
		}

		ArrayList<Object[]> table = db.query(GET_OFFERS, cpr, cpr);
		if(table.size() == 0) {
			throw new EmptyListException("There are no service offers in your county");
		}
		ArrayList<ServiceOffer> list = new ArrayList<ServiceOffer>();

		for (int i = 0; i < table.size(); i++) {
			Object[] row = table.get(i);
			// String jobTitle, String jobCategory, Date bookingTime, String
			// whoPosted,String description, String address, double salary
			// 2019-06-10 10:12:01.0
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ServiceOffer offer;
			if(row[4] == null) {
				row[4] = "Not specified";
			}
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

	public ArrayList<ServiceRequest> getServiceRequestsNF(long cpr) throws SQLException, EmptyListException {
		if (db == null) {
			db = new Jdbc();
		}

		ArrayList<Object[]> table = db.query(GET_REQUESTS, cpr, cpr);
		if(table.size() == 0) {
			throw new EmptyListException("There are no service requests in your county");
		}
		ArrayList<ServiceRequest> list = new ArrayList<ServiceRequest>();

		for (int i = 0; i < table.size(); i++) {
			Object[] row = table.get(i);
			// title, category, description, whoPosted
			if(row[2] == null) {
				row[2] = "Not specified";
			}
			ServiceRequest request = new ServiceRequest(row[0].toString(), row[1].toString(), row[2].toString(),
					row[3].toString(), Integer.parseInt(row[4].toString()));
			list.add(request);
		}
		return list;
	}

}
