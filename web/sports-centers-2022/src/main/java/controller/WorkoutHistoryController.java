package controller;

import beans.WorkoutHistory;
import com.google.gson.Gson;
import dto.WorkoutDTO;
import dto.WorkoutHistoryDTO;
import service.WorkoutHistoryService;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;

import static spark.Spark.get;
import static spark.Spark.path;

public class WorkoutHistoryController {
    private static Gson g = new Gson();
    private static WorkoutHistoryService workoutHistoryService = new WorkoutHistoryService();

    private static String commonPath = "/rest/workoutHistory";

    public static void init() {
        path(commonPath, () -> {
            getPast();
            getFuture();
            delete();
        });
    }
    public static void getPast() {
        get("/getPast", (req, res) -> {
            res.type("application/json");

            ArrayList<WorkoutHistory> whs;
            if(req.queryParams("type").equals("BUYER")) {
                whs = workoutHistoryService.getByBuyerPast(req.queryParams("id"));
            }
            else {
                whs = workoutHistoryService.getByCoachPast(req.queryParams("id"));
            }

            if (whs == null) {
                res.status(204);
                return res.raw();
            }
            if (whs.size() == 0) {
                res.status(204);
                return res.raw();
            }

            ArrayList<WorkoutHistoryDTO> wls = new ArrayList<>();
            for (WorkoutHistory wh: whs) {

                LocalDate localDate = Instant.ofEpochMilli(wh.getDateOfRegistration().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                String date =  localDate.getDayOfMonth()+ "/" + localDate.getMonthValue() + "/" + localDate.getYear();

                InputStream iSteamReader = new FileInputStream(wh.getWorkout().getPhoto());
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);
                base64 = "data:image/png;base64," + base64;
                WorkoutDTO w = new WorkoutDTO(wh.getWorkout().getId(), wh.getWorkout().getName(), wh.getWorkout().getType(), wh.getWorkout().getDuration().toString(),
                        wh.getCoach().getUsername(), wh.getCoach().getName(), wh.getCoach().getLastname(),
                        wh.getWorkout().getDescription(), base64);
                WorkoutHistoryDTO w1 = new WorkoutHistoryDTO(wh.getWorkout().get__facility().get_name(), w, date, wh.getBuyer().getName()+ " " + wh.getBuyer().getLastname() , wh.getId());
                wls.add(w1);
            }
            return g.toJson(wls);
        });
    }
    public static void getFuture() {
        Spark.get("/getFuture", (req, res) -> {
            res.type("application/json");

            ArrayList<WorkoutHistory> whs;
            if(req.queryParams("type").equals("BUYER")) {
                 whs = workoutHistoryService.getByBuyer(req.queryParams("id"));
            }
            else {
                whs = workoutHistoryService.getByCoach(req.queryParams("id"));
            }

            if (whs == null) {
                res.status(204);
                return res.raw();
            }
            if (whs.size() == 0) {
                res.status(204);
                return res.raw();
            }

            ArrayList<WorkoutHistoryDTO> wls = new ArrayList<>();
            for (WorkoutHistory wh: whs) {

                LocalDate localDate = Instant.ofEpochMilli(wh.getDateOfRegistration().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                String date =  localDate.getDayOfMonth()+ "/" + localDate.getMonthValue() + "/" + localDate.getYear();

                InputStream iSteamReader = new FileInputStream(wh.getWorkout().getPhoto());
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);
                base64 = "data:image/png;base64," + base64;
                WorkoutDTO w = new WorkoutDTO(wh.getWorkout().getId(), wh.getWorkout().getName(), wh.getWorkout().getType(), wh.getWorkout().getDuration().toString(),
                        wh.getCoach().getUsername(), wh.getCoach().getName(), wh.getCoach().getLastname(),
                        wh.getWorkout().getDescription(), base64);
                WorkoutHistoryDTO w1 = new WorkoutHistoryDTO(wh.getWorkout().get__facility().get_name(), w, date, wh.getBuyer().getName()+ " " + wh.getBuyer().getLastname(), wh.getId());
                wls.add(w1);
            }
            return g.toJson(wls);
        });
    }

    public static void delete() {
        Spark.delete("/delete", (req, res) -> {
            String id = req.queryParams("id");
            workoutHistoryService.delete(id);
            return res.raw();
        });
    }

}
