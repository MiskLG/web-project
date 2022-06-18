package beans;

import java.util.Date;

public class Subscription {
	enum SubscriptionType{ANNUAL, MONTHLY, WEEKLY, DAILY}
	enum StatusType{ACTIVE,INACTIVE}
	private String Id;
	private Date dateOfPaying;
	private Date dateOfExparation;
	private Double price;
	private StatusType status;
	private int numberOfAppointments;
	
	public Subscription(String id, Date dateOfPaying, Date dateOfExparation, Double price, StatusType status,
			int numberOfAppointments) {
		super();
		Id = id;
		this.dateOfPaying = dateOfPaying;
		this.dateOfExparation = dateOfExparation;
		this.price = price;
		this.status = status;
		this.numberOfAppointments = numberOfAppointments;
	}
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Date getDateOfPaying() {
		return dateOfPaying;
	}
	public void setDateOfPaying(Date dateOfPaying) {
		this.dateOfPaying = dateOfPaying;
	}
	public Date getDateOfExparation() {
		return dateOfExparation;
	}
	public void setDateOfExparation(Date dateOfExparation) {
		this.dateOfExparation = dateOfExparation;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType status) {
		this.status = status;
	}
	public int getNumberOfAppointments() {
		return numberOfAppointments;
	}
	public void setNumberOfAppointments(int numberOfAppointments) {
		this.numberOfAppointments = numberOfAppointments;
	}
	
	
}
