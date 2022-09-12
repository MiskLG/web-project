package beans;

public class Comment {

	private int approved;
	private Buyer buyer;
	private SportsFacility facility;
	private String text;
	private int rating;

	public Comment(int approved, Buyer buyer, SportsFacility facility, String text, int rating) {
		this.approved = approved;
		this.buyer = buyer;
		this.facility = facility;
		this.text = text;
		this.rating = rating;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public SportsFacility getFacility() {
		return facility;
	}

	public void setFacility(SportsFacility facility) {
		this.facility = facility;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
