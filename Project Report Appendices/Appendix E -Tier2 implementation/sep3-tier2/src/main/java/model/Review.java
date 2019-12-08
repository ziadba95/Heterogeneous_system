package model;

public class Review {
	private long reviewer;
	private long reviewed;
	private String body;
	private int stars;
	
	
	public Review(long reviewer, long reviewed, String body, int stars) {
		super();
		this.reviewer = reviewer;
		this.reviewed = reviewed;
		this.body = body;
		this.stars = stars;
	}


	public long getReviewer() {
		return reviewer;
	}


	public void setReviewer(long reviewer) {
		this.reviewer = reviewer;
	}


	public long getReviewed() {
		return reviewed;
	}


	public void setReviewed(long reviewed) {
		this.reviewed = reviewed;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public int getStars() {
		return stars;
	}


	public void setStars(int stars) {
		this.stars = stars;
	}
	
	
	
	
	
}
