package beans;

import java.util.ArrayList;
import java.util.Date;

public class Coach extends User{
	private ArrayList<WorkoutHistory> workoutHistory;

	public Coach(String username, String name, String lastname, String password, GenderType gender, Date dateOfBirth,
			UserType userType, ArrayList<WorkoutHistory> workoutHistory) {
		super(username, name, lastname, password, gender, dateOfBirth, userType);
		this.workoutHistory = workoutHistory;
	}

	public ArrayList<WorkoutHistory> getWorkoutHistory() {
		return workoutHistory;
	}

	public void setWorkoutHistory(ArrayList<WorkoutHistory> workoutHistory) {
		this.workoutHistory = workoutHistory;
	}
}
