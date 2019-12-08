 package model;

import java.io.Serializable;

public class User implements Serializable{
	private String fName;
	private String lName;
	private String phone;
	private String email;
	private String cPR;
	private String city;
	private String password;
	private String description;
	private String vPassword;
	
	public User(String fName, String lName, String phone, String email, String cPR, String city, String password) {
		super();
		this.fName = fName;
	 	this.lName = lName;
		this.phone = phone;
		this.email = email;
		this.cPR = cPR;
		this.city = city;
		this.password = password;
	}
	
	public User(String fName, String lName, String phone, String email, String cPR, String city, String password, String description) {
		super();
		this.fName = fName;
	 	this.lName = lName;
		this.phone = phone;
		this.email = email;
		this.cPR = cPR;
		this.city = city;
		this.password = password;
		this.description = description;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getcPR() {
		return cPR;
	}

	public void setcPR(String cPR) {
		this.cPR = cPR;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getvPassword() {
		return vPassword;
	}

	public void setvPassword(String vPassword) {
		this.vPassword = vPassword;
	}
	
	
	
	
}
