package model;

public class Applicant {
	private String name;
	private String description;
	private long cpr;
	
	public Applicant(long cpr, String name, String description) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.cpr = cpr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCpr() {
		return cpr;
	}

	public void setCpr(long cpr) {
		this.cpr = cpr;
	}
	
	
	
}
