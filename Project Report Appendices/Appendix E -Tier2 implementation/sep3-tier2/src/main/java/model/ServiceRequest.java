package model;

import java.util.Date;

public class ServiceRequest extends Post{

	public ServiceRequest(String jobTitle, String jobCategory,String description, String whoPosted) {
		super(jobTitle, jobCategory, description, whoPosted);
	}
	 
	public ServiceRequest(String jobTitle, String jobCategory,String description, String whoPosted, int id) {
		super(id, jobTitle, jobCategory, description, whoPosted);
	}

}
