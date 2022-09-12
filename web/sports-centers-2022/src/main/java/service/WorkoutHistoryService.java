package service;

import beans.WorkoutHistory;
import repository.WorkoutHistoryRepository;
import util.IdGenerator;

import java.util.ArrayList;

public class WorkoutHistoryService {
    private WorkoutHistoryRepository workoutHistoryRepository;
    private BuyerService buyerService;

    public WorkoutHistoryService() {
        workoutHistoryRepository = WorkoutHistoryRepository.init();
    }

    public void add(WorkoutHistory workout) {
        String id = "";
        do {
            id = IdGenerator.generate();
        }while (this.getById(id) != null);
        workout.setId(id);
        workoutHistoryRepository.add(workout);
    }

    public ArrayList<WorkoutHistory> getAll() {
        return workoutHistoryRepository.getAll();
    }

    public void delete(String id) {
        buyerService = new BuyerService();
        buyerService.giveAppointment(this.getById(id).getBuyer().getUsername());
        workoutHistoryRepository.remove(id);
        return;
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

    public WorkoutHistory getById(String id) {
        for (WorkoutHistory workout: workoutHistoryRepository.getAll()) {
           if(workout.getId().equals(id)) {
               return workout;
           }
        }
        return null;
    }

}
