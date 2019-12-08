package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import db.Jdbc;
import model.User;

public class ProfileDAO {
	private final String CREATE_PROFILE = "insert into justwork.profiles(userid,description) values(?,?)";
//	private final String CREATE_SKILL = "insert into justwork.skills(skill) values(?)";
//	private final String ADD_SKILL_TO_USER = "insert into justwork.Userskills(userid,skill) values(?,?)";
	private final String EDIT_PROFILE = "UPDATE justwork.profiles SET description=? WHERE userid = ? ;";
//	private final String REMOVE_SKILL_FROM_USER = "DELETE FROM justwork.userskills WHERE userid = ? AND skill = ? ;";
//	private final String IS_SKILL_IN_DB = "SELECT skill from justwork.Skills where lower(skill) = ?;";
//	private final String IS_SKILL_ASSIGNED_TO_USER = "SELECT skill from justwork.UserSkills where userid = ? AND skill = ?;";
	private final String DELETE_PROFILE = "DELETE FROM justwork.profiles WHERE userid = ? ;";
	private final String GET_PROFILE = "SELECT fname, lname, phone,email,cpr,city,description from justwork.\"user\" left join justwork.profiles on userid = cpr where cpr = ?;";
	private final String EDIT_USER = "UPDATE justwork.\"user\" SET city = ? , phone = ? , email = ? WHERE cpr = ? ;";
	private final String EDIT_PASSWORD = "UPDATE justwork.\"user\" SET password = ? WHERE cpr = ? ;";
	private final String GET_CITIES = "SELECT * FROM justwork.city;";
	
	private Jdbc db;
	
	public ProfileDAO() {
		db = new Jdbc();
	}
	
	public void createProfile(long cpr, String description) throws SQLException {
		db.update(CREATE_PROFILE, cpr,description);
	}
	
//	public void addSkillToSkillsTable(String skill) throws SQLException {
//		db.update(CREATE_SKILL, skill);
//	}
	
//	public void addSkillToUser(String cpr, String skill) throws SQLException {
//		db.update(ADD_SKILL_TO_USER, cpr, skill);
//	}
	
	public void editProfile(long cpr, String description,String county, long phone, String email) throws SQLException {
		//UPDATE justwork.\"user\" SET city = ? , phone = ? , email = ? WHERE cpr = ? ;
		//EDIT_PROFILE = "UPDATE justwork.profiles SET description=? WHERE userid = ? ;";
		//	private final String DELETE_PROFILE = "DELETE FROM justwork.profiles WHERE userid = ? ;";
		//	private final String CREATE_PROFILE = "insert into justwork.profiles(userid,description) values (?,?)";
		db.update(DELETE_PROFILE, cpr);
		db.update(CREATE_PROFILE, cpr,description);
		db.update(EDIT_USER,county,phone, email, cpr);
	}
	
	public void editPassword(long cpr, String password) throws SQLException {
		//	private final String EDIT_PASSWORD = "UPDATE justwork.\"user\" SET password = ? WHERE cpr = ? ;";
		db.update(EDIT_PASSWORD, password, cpr);
	}
	
//	public void removeSkillFromUser(long cpr, String skill) throws SQLException {
//		db.update(REMOVE_SKILL_FROM_USER, cpr, skill);
//	}
	
//	public boolean IsSkillAlreadyInDB(String skill) throws SQLException
//	{
//		skill = skill.toLowerCase();
//		ArrayList<Object[]> table = db.query(IS_SKILL_IN_DB, skill);
//		if(table.size() >= 1) {
//			return true;
//		}
//		else return false;
//	}
	
//	public boolean IsSkillAssignedToUser(String cpr, String skill) throws SQLException {
//		ArrayList<Object[]> table = db.query(IS_SKILL_ASSIGNED_TO_USER,cpr, skill);
//		if(table.size() >= 1) {
//			return true;
//		}
//		else return false;
//	}
	
	public void deleteProfile(long cpr) throws SQLException {
		
		db.update(DELETE_PROFILE, cpr);
	}
	
	public User getProfile(long cpr) throws SQLException {
		ArrayList<Object[]> table  = db.query(GET_PROFILE, cpr);
		Object[] row = table.get(0);
		if (row[6] == null) {
			row[6] = new String();
			row[6] = "Not specified";
		}
		User user = new User(row[0].toString(), row[1].toString(), row[2].toString(), 
				row[3].toString(), row[4].toString(), 
				row[5].toString(), "", row[6].toString());
		
		return user;
	}
	
	public ArrayList<String> getCounties() throws SQLException{
		ArrayList<Object[]> table = db.query(GET_CITIES);
		ArrayList<String> list = new ArrayList<String>();
		
		for (int i = 0; i < table.size(); i++) {
			list.add(table.get(i)[0].toString());
		}
		return list;
	}
	
	
}
