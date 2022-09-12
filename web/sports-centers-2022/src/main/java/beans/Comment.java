package beans;

public class Comment {
	
	enum RatingScale{ONE,TWO,THREE,FOUR,FIVE;}

	private boolean approved;
	private Buyer buyer;
	private SportsFacility facility;
	private String text;
	private RatingScale rating;

	public Comment(boolean approved, Buyer buyer, SportsFacility facility, String text, RatingScale rating) {
		this.approved = approved;
		this.buyer = buyer;
		this.facility = facility;
		this.text = text;
		this.rating = rating;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
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

	public RatingScale getRating() {
		return rating;
	}

	public void setRating(RatingScale rating) {
		this.rating = rating;
	}
}
