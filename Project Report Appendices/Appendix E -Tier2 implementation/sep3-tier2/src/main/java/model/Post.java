package model;

import java.io.Serializable;
import java.util.Date;

public abstract class Post implements Serializable{
	private String jobTitle;
	private String jobCategory;
	private int id;
	private String description;
	private String whoPosted;
	
	public Post(String jobTitle, String jobCategory,  String description, String whoPosted) {
		this.jobTitle = jobTitle;
		this.jobCategory = jobCategory;
		this.description = description;
		this.whoPosted = whoPosted;
	}
	
	public Post(int id, String jobTitle, String jobCategory,  String description, String whoPosted) {
		this.jobTitle = jobTitle;
		this.jobCategory = jobCategory;
		this.setId(id);
		this.description = description;
		this.whoPosted = whoPosted;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWhoPosted() {
		return whoPosted;
	}

	public void setWhoPosted(String whoPosted) {
		this.whoPosted = whoPosted;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
