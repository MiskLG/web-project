package service;

import beans.Workout;
import repository.WorkoutRepository;

import java.util.ArrayList;

public class WorkoutService {
    private WorkoutRepository workoutRepository;

    public WorkoutService() {
        workoutRepository = WorkoutRepository.init();
    }

    public void add(Workout workout) {
        workoutRepository.add(workout);
    }

    public ArrayList<Workout> getAll() {
        return workoutRepository.getAll();
    }

    public Workout getById(String id) {
        for (Workout workout: workoutRepository.getAll()) {
            if (workout.getId().equalsIgnoreCase(id)) {
                return workout;
            }
        }
        return null;
    }
}
