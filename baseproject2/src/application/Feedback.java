package application;

public class Feedback {
	
	private int feedback_Number;
	private int feedback_Rating;
	private int customer_id;
	private String cleaness;
	private String suggestion;
	private String service;
	private String quality;
	
	public Feedback() {
		super();
	}

	public Feedback(int customer_id, int feedback_Number, int feedback_Rating,
			String cleaness, String suggestion, String service, String quality) {
		super();
		this.feedback_Number = feedback_Number;
		this.customer_id = customer_id;
		this.feedback_Rating = feedback_Rating;
		this.cleaness = cleaness;
		this.suggestion = suggestion;
		this.service = service;
		this.quality = quality;
	}

	public int getFeedback_Number() {
		return feedback_Number;
	}

	public void setFeedback_Number(int feedback_Number) {
		this.feedback_Number = feedback_Number;
	}

	public int getFeedback_Rating() {
		return feedback_Rating;
	}

	public void setFeedback_Rating(int feedback_Rating) {
		this.feedback_Rating = feedback_Rating;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCleaness() {
		return cleaness;
	}

	public void setCleaness(String cleaness) {
		this.cleaness = cleaness;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}
}
