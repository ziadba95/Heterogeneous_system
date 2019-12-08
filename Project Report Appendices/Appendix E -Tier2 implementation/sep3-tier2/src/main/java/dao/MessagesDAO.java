package dao;

import db.Jdbc;

public class MessagesDAO {
	private Jdbc db;
	private final String GET_CONTACT_LIST= "SELECT DISTINCT ";
	
	public MessagesDAO() {
		db = new Jdbc();
	}
	
	 
}
