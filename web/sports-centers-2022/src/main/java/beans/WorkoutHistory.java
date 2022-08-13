package beans;

import java.util.Date;

public class WorkoutHistory {
	private Date dateOfRegistration;
	private Workout workout;
	private String buyer;
	private String coach;

	public WorkoutHistory(Date dateOfRegistration, Workout workout, String buyer, String coach) {
		super();
		this.dateOfRegistration = dateOfRegistration;
		this.workout = workout;
		this.buyer = buyer;
		this.coach = coach;
	}
	
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public Workout getWorkout() {
		return workout;
	}
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}

}
