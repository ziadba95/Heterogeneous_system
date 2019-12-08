package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProfileDAO;
import exceptions.ExpiredSession;
import exceptions.InvalidFormException;
import exceptions.UserNotFound;
import model.User;

public class ProfileBL {
	private ProfileDAO db;

	public ProfileBL() {
		db = new ProfileDAO();
	}

	public void createProfile(String cpr, String description)
			throws SQLException, UserNotFound, InvalidFormException, ExpiredSession {
		ArrayList<String> errorMessage = new ArrayList<String>();
		if(cpr == null) {
			throw new ExpiredSession("Session has expired");
		}
		try {
			Long.parseLong(cpr);
		}catch(NumberFormatException e) {
			throw new ExpiredSession("Session has expired");
		}
		if (description == null) {
			errorMessage.add("Description can't be empty");
			throw new InvalidFormException(errorMessage);
		} else {
			if (description.isEmpty()) {
				errorMessage.add("Description can't be empty");
				throw new InvalidFormException(errorMessage);
			}
			if (description.length() > 300) {
				errorMessage.add("Description is too long");
			}
			if (errorMessage.size() >= 1) {
				throw new InvalidFormException(errorMessage);
			} else {
				db.createProfile(Long.parseLong(cpr), description);
			}

		}

	}

//	public void addSkillToSkillsTable(String skill) throws SQLException, InvalidFormException {
//		ArrayList<String> errorMessage = new ArrayList<String>();
//		
//		if (skill == null) {
//			errorMessage.add("Skill name can't be empty");
//			throw new InvalidFormException(errorMessage);
//		} else {
//			if (skill.isEmpty()) {
//				errorMessage.add("Skill name can't be empty");
//				throw new InvalidFormException(errorMessage);
//			}
//			if (skill.length() > 50) {
//				errorMessage.add("Skill name can't be more than 50 characters long");
//				throw new InvalidFormException(errorMessage);
//			}
//
//		}
//		if (!db.IsSkillAlreadyInDB(skill)) {
//			db.addSkillToSkillsTable(skill);
//		}
//
//	}

//	public void addSkillToUser(String cpr, String skill) throws SQLException, InvalidFormException, ExpiredSession {
//		ArrayList<String> errorMessage = new ArrayList<String>();
//		if (skill == null) {
//			errorMessage.add("Skill name can't be empty");
//			throw new InvalidFormException(errorMessage);
//		} else {
//			if (cpr == null) {
//				throw new ExpiredSession("Session has expired");
//			} else {
//				if (skill.isEmpty()) {
//					errorMessage.add("Skill name can't be empty");
//					throw new InvalidFormException(errorMessage);
//				}
//				if (skill.length() > 50) {
//					errorMessage.add("Skill name can't be more than 50 characters long");
//					throw new InvalidFormException(errorMessage);
//				}
//				if (!db.IsSkillAssignedToUser(cpr, skill)) {
//					db.addSkillToUser(cpr, skill);
//				}
//
//			}
//		}
//	}

	public void editProfile(String cpr, String description, String county, String phone, String email, String password,
			String vPassword) throws SQLException, InvalidFormException, ExpiredSession {
		boolean changePassword = true;
		ArrayList<String> errorMessage = new ArrayList<String>();
		User user = getProfile(cpr);
		if (cpr == null) {
			throw new ExpiredSession("Session has expired");
		} else {
			if (description == null) {
				description = "Not Specified";
			}
			if (county == null) {
				county = user.getCity();
			}
			if (phone == null) {
				phone = new String(user.getPhone() + "");
			}
			if (email == null) {
				email = user.getEmail();
			}

			if (description.trim().isEmpty()) {
				description = "Not Specified";
			}
			if (county.trim().isEmpty()) {
				county = user.getCity();
			}
			if (phone.trim().isEmpty()) {
				phone = new String(user.getPhone() + "");
			}
			if (email.trim().isEmpty()) {
				email = user.getEmail();
			}

			if (description.length() > 300) {
				errorMessage.add("Description can't be longer than 300 characters ");
			}
			if (county.length() > 50) {
				errorMessage.add("County can't be longer than 50 characters ");
			}
			if (phone.length() > 21) {
				errorMessage.add("Phone number is invalid");

			}
			if (email.length() > 50) {
				errorMessage.add("Email can't be longer than 50 characters ");
			}
			try {
				Long.parseLong(phone);
			}catch(NumberFormatException e) {
				errorMessage.add("Phone number is invalid");
			}
			try {
				Long.parseLong(cpr);
			}catch(NumberFormatException e) {
				throw new ExpiredSession("Session has expired");
			}

			if (password == null) {
				changePassword = false;
			} else {
				if (password.isEmpty()) {
					changePassword = false;
				} else {
					if (password.length() > 50) {
						errorMessage.add("Password can't be longer than 50 characters");
					} else {
						if (vPassword == null) {
							errorMessage.add("Password verification is empty");
						} else {
							if (vPassword.isEmpty()) {
								errorMessage.add("Password verification is empty");
							} else {
								if (!vPassword.equals(password)) {
									errorMessage.add("Password verification doesn't match the password");
								}
							}
						}
					}
				}
			}
			if (errorMessage.size() > 0) {
				throw new InvalidFormException(errorMessage);
			} else {
				if (changePassword) {
					db.editPassword(Long.parseLong(cpr), password);
				}
				db.editProfile(Long.parseLong(cpr), description, county, Long.parseLong(phone), email);
			}

		}

	}

//	public void removeSkillFromUser(String cpr, String skill)
//			throws SQLException, ExpiredSession, InvalidFormException {
//		ArrayList<String> errorMessage = new ArrayList<String>();
//		if (cpr == null) {
//			throw new ExpiredSession("Session has expired");
//		} else {
//			try {
//				Long.parseLong(cpr);
//			} catch(NumberFormatException e) {
//				throw new ExpiredSession("Session has expired");
//			}
//			if (skill == null) {
//				errorMessage.add("Skill can't be empty");
//				throw new InvalidFormException(errorMessage);
//			} else {
//				db.removeSkillFromUser(Long.parseLong(cpr), skill);
//			}
//
//		}
//	}

	public User getProfile(String cpr) throws SQLException, ExpiredSession {
		try {
			long cprASLong = Long.parseLong(cpr);
			return db.getProfile(cprASLong);
		} catch (NumberFormatException e) {
			throw new ExpiredSession("Session has expired");
		}

	}
	
	public ArrayList<String> getCounties() throws SQLException{
		return db.getCounties();
	}
}
