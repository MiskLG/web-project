package beans;

import java.util.Date;

public class Buyer extends User{
	private Subscription subscriptionId;
	private int points;
	private BuyerType buyerType;
	
	public Buyer(String username, String name, String lastname, String password, GenderType gender, Date dateOfBirth,
			UserType userType, Subscription subscriptionId, int points, BuyerType buyerType) {
		super(username, name, lastname, password, gender, dateOfBirth, userType);
		this.subscriptionId = subscriptionId;
		this.points = points;
		this.buyerType = buyerType;
	}
	
	public Subscription getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(Subscription subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public BuyerType getBuyerType() {
		return buyerType;
	}
	public void setBuyerType(BuyerType buyerType) {
		this.buyerType = buyerType;
	}
	
	
}
