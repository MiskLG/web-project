package beans;

import java.util.Date;

public class WorkoutHistory {
	private Date dateOfRegistration;
	private Workout workout;
	private Buyer buyer;
	private Coach coach;

	public WorkoutHistory(Date dateOfRegistration, Workout workout, Buyer buyer, Coach coach) {
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
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}

}
