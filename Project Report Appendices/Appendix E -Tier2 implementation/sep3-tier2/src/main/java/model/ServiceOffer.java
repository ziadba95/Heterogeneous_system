package model;

import java.util.ArrayList;
import java.util.Date;

public class ServiceOffer extends Post{

	private String address;
	private ArrayList<Applicant> applicants;
	private double salary;
	private Date bookingTime;

	
	public ServiceOffer(String jobTitle, String jobCategory, Date bookingTime, String whoPosted,String description, String address, double salary) {
		super(jobTitle, jobCategory, description, whoPosted);
		this.address = address;
		this.salary = salary;
		this.bookingTime = bookingTime;
		applicants = new ArrayList<Applicant>();
	}
	
	public ServiceOffer(String jobTitle, String jobCategory, Date bookingTime,String whoPosted, String description, String address, double salary, ArrayList<Applicant> applicants) {
		super(jobTitle, jobCategory, description , whoPosted);
		this.address = address;
		this.salary = salary;
		this.bookingTime = bookingTime;
		this.applicants = applicants;
	}
	
	public ServiceOffer(String jobTitle, String jobCategory, Date bookingTime, String whoPosted,String description, String address, double salary, int id) {
		super(id, jobTitle, jobCategory, description, whoPosted);
		this.address = address;
		this.salary = salary;
		this.bookingTime = bookingTime;
		applicants = new ArrayList<Applicant>();
	}
	
	public ServiceOffer(String jobTitle, String jobCategory, Date bookingTime, String whoPosted,String description, String address, double salary, int id,  ArrayList<Applicant> applicants) {
		super(id, jobTitle, jobCategory, description, whoPosted);
		this.address = address;
		this.salary = salary;
		this.bookingTime = bookingTime;
		this.applicants = applicants;
	}
	
	
	
	

	public Date getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(ArrayList<Applicant> applicants) {
		this.applicants = applicants;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	 
	public void addApplicant(Applicant applicant) {
		applicants.add(applicant);
	}

	

}
