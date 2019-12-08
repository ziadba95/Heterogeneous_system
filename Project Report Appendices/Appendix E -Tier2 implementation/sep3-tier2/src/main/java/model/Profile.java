package model;

import java.util.ArrayList;

public class Profile {
	private long cpr;
	private String description;
	
	public Profile(long cpr, String description, ArrayList<String> skills) {
		super();
		this.cpr = cpr;
		this.description = description;
	}

	public long getCpr() {
		return cpr;
	}

	public void setCpr(long cpr) {
		this.cpr = cpr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
}
