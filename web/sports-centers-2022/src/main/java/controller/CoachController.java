package controller;

import beans.Coach;
import beans.User;
import com.google.gson.Gson;
import dto.Credentials;
import dto.UserInfoExpandedDTO;
import dto.UserRegisterDTO;
import service.CoachService;
import service.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static spark.Spark.*;

public class CoachController {
    private static Gson g = new Gson();
    private static CoachService coachService = new CoachService();
    private static UserService userService = new UserService();
    private static String commonPath = "/rest/coaches";

    public static void init() {
        path(commonPath, () -> {
            register();
            getAll();
        });
    }

    public static void register() {
        post("/register",(req, res) -> {
            res.type("application/json");
            String payload = req.body();
            UserRegisterDTO data = g.fromJson(payload, UserRegisterDTO.class);

            if(userService.getUser(new Credentials(data.getUsername(), data.getPassword())) != null) {
                res.body("Username is taken");
                res.status(400);
                return res.raw();
            }

            User.GenderType genderType;
            if(data.getGender().equalsIgnoreCase("male")) {
                genderType = User.GenderType.MALE;
            }
            else {
                genderType = User.GenderType.FEMALE;
            }
            data.fillDate();
            System.out.println(data.getUsername() + data.getYear() + data.getDay() + data.getMonth() + data.getDate());
            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(data.getYear()), Integer.parseInt(data.getMonth())-1, Integer.parseInt(data.getDay()), 0, 0, 0);
            Date date = cal.getTime();

            Coach coach = new Coach(data.getUsername(), data.getName(), data.getLastname(), data.getPassword(), genderType, date);
            coachService.add(coach);
            return res.raw();
        });
    }

    public static void getAll() {
        get("/getAll", (req, res) -> {
            res.type("application/json");
            ArrayList<Coach> coaches = coachService.getAll();

            if (coaches == null) {
                res.status(204);
                return res.raw();
            }
            if (coaches.size() == 0) {
                res.status(204);
                return res.raw();
            }

            ArrayList<UserInfoExpandedDTO> info = new ArrayList<>();
            for (Coach coach: coaches) {
                info.add(new UserInfoExpandedDTO(coach.getUsername(),coach.getName(),coach.getLastname()));
            }
            return g.toJson(info);
        });
    }
}
