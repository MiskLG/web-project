package controller;

import beans.Coach;
import beans.SportsFacility;
import beans.Workout;
import com.google.gson.Gson;
import dto.WorkoutAddDTO;
import service.CoachService;
import service.ManagerService;
import service.SportsFacilityService;
import service.WorkoutService;

import static spark.Spark.path;
import static spark.Spark.post;

public class WorkoutController {
    private static Gson g = new Gson();
    private static WorkoutService workoutService = new WorkoutService();
    private static CoachService coachService = new CoachService();
    private static ManagerService managerService = new ManagerService();
    private static SportsFacilityService facilityService = new SportsFacilityService();
    private static String commonPath = "/rest/workouts";

    public static void init() {
        path(commonPath, () -> {
            add();
        });
    }

    public static void add() {
        post("/add",(req, res) -> {
            res.type("application/json");
            String payload = req.body();
            WorkoutAddDTO data = g.fromJson(payload, WorkoutAddDTO.class);

            Double duration = 0.0;
            if(!data.getDuration().isEmpty()) {
                duration = Double.parseDouble(data.getDuration());
            }

            SportsFacility facility = facilityService.getById(managerService.getById(data.getManager()).getFacility().getId());
            Coach coach = coachService.getById(data.getCoach());

            Workout workout = new Workout("",data.getName(), data.getType().toUpperCase(),duration, facility, coach, data.getDescription(), data.getImage());

            workoutService.add(workout);
            facilityService.addContent(facility, workout);
            return res.raw();
        });
    }

}
