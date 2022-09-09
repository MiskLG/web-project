package controller;

import beans.Coach;
import beans.SportsFacility;
import beans.Workout;
import com.google.gson.Gson;
import dto.WorkoutAddDTO;
import dto.WorkoutDTO;
import service.CoachService;
import service.ManagerService;
import service.SportsFacilityService;
import service.WorkoutService;
import spark.utils.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;

import static spark.Spark.*;

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
            getByFacility();
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

    public static void getByFacility() {
        get("/getByFacility", (req, res) -> {
            res.type("application/json");
            ArrayList<Workout> workouts = facilityService.getById(req.queryParams("id")).get_content();
            ArrayList<WorkoutDTO> workoutDTOS = new ArrayList<>();
            for(Workout w1 : workouts) {
                Workout workout = workoutService.getById(w1.getId());
                InputStream iSteamReader = new FileInputStream(workout.getPhoto());
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);
                base64 = "data:image/png;base64," + base64;
                WorkoutDTO dto = new WorkoutDTO(workout.getId(), workout.getName(), workout.getType(), workout.getDuration().toString(),
                        workout.getCoach().getUsername(), workout.getCoach().getName(), workout.getCoach().getLastname(),
                        workout.getDescription(), base64);
                workoutDTOS.add(dto);
            }
            return g.toJson(workoutDTOS);
        });
    }

}
