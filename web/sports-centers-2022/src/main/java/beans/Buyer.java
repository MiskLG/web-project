package beans;

import java.util.ArrayList;
import java.util.Date;

public class Buyer extends User{
	private Subscription subscriptionId;
	private ArrayList<SportsFacility> visitedFacilities;
	private int points;
	private BuyerType buyerType;
		
	public Buyer(String username, String name, String lastname, String password, GenderType gender, Date dateOfBirth,
			Subscription subscriptionId, ArrayList<SportsFacility> visitedFacilities, int points,
			BuyerType buyerType) {
		super(username, name, lastname, password, gender, dateOfBirth, UserType.BUYER);
		this.subscriptionId = subscriptionId;
		this.visitedFacilities = visitedFacilities;
		this.points = points;
		this.buyerType = buyerType;
	}

	public Buyer(String username, String name, String lastname, String password, GenderType genderType, Date date) {
		super(username, name, lastname, password, genderType, date, UserType.BUYER);
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
	public ArrayList<SportsFacility> getVisitedFacilities() {
		return visitedFacilities;
	}
	public void setVisitedFacilities(ArrayList<SportsFacility> visitedFacilities) {
		this.visitedFacilities = visitedFacilities;
	}
	
	
}
