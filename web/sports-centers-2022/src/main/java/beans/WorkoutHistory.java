package beans;

import java.util.Date;

public class WorkoutHistory {
	private Date dateOfRegistration;
	private Workout workout;
	private Buyer buyer;
	private Coach coach;
	private boolean status;

	public WorkoutHistory(Date dateOfRegistration, Workout workout, Buyer buyer, Coach coach, boolean status) {
		this.dateOfRegistration = dateOfRegistration;
		this.workout = workout;
		this.buyer = buyer;
		this.coach = coach;
		this.status = status;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
