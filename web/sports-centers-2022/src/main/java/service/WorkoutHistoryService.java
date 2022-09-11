package service;

import beans.WorkoutHistory;
import repository.WorkoutHistoryRepository;

import java.util.ArrayList;

public class WorkoutHistoryService {
    private WorkoutHistoryRepository workoutHistoryRepository;

    public WorkoutHistoryService() {
        workoutHistoryRepository = WorkoutHistoryRepository.init();
    }

    public void add(WorkoutHistory workout) {
        workoutHistoryRepository.add(workout);
    }

    public ArrayList<WorkoutHistory> getAll() {
        return workoutHistoryRepository.getAll();
    }

    public ArrayList<WorkoutHistory> getByBuyer(String buyerId) {
        ArrayList<WorkoutHistory> wh = new ArrayList<>();
        for (WorkoutHistory workout: workoutHistoryRepository.getAll()) {
            if(workout.isStatus() == false) {
                if (workout.getBuyer().getUsername().equalsIgnoreCase(buyerId)) {
                    wh.add(workout);
                }
            }
        }
        return wh;
    }
    public ArrayList<WorkoutHistory> getByCoach(String coachId) {
        ArrayList<WorkoutHistory> wh = new ArrayList<>();
        for (WorkoutHistory workout: workoutHistoryRepository.getAll()) {
            if(workout.isStatus() == false) {
                if (workout.getCoach().getUsername().equalsIgnoreCase(coachId)) {
                    wh.add(workout);
                }
            }
        }
        return wh;
    }
    public ArrayList<WorkoutHistory> getByCoachPast(String coachId) {
        ArrayList<WorkoutHistory> wh = new ArrayList<>();
        for (WorkoutHistory workout: workoutHistoryRepository.getAll()) {
            if(workout.isStatus() == true) {
                if (workout.getCoach().getUsername().equalsIgnoreCase(coachId)) {
                    wh.add(workout);
                }
            }
        }
        return wh;
    }
    public ArrayList<WorkoutHistory> getByBuyerPast(String buyerId) {
        ArrayList<WorkoutHistory> wh = new ArrayList<>();
        for (WorkoutHistory workout: workoutHistoryRepository.getAll()) {
            if(workout.isStatus() == true) {
                if (workout.getBuyer().getUsername().equalsIgnoreCase(buyerId)) {
                    wh.add(workout);
                }
            }
        }
        return wh;
    }

}
