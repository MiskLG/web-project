package controller;

import beans.Manager;
import beans.User;
import com.google.gson.Gson;
import dto.Credentials;
import dto.ManagerInfoDTO;
import dto.UserRegisterDTO;
import service.ManagerService;
import service.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static spark.Spark.*;

public class ManagerController {
    private static Gson g = new Gson();
    private static ManagerService managerService = new ManagerService();
    private static UserService userService = new UserService();
    private static String commonPath = "/rest/managers";

    public static void init() {
        path(commonPath, () -> {
            register();
            getFree();
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
            if(data.getGender().equals("male")) {
                genderType = User.GenderType.MALE;
            }
            else {
                genderType = User.GenderType.FEMALE;
            }
            data.fillDate();
            System.out.println(data.getUsername() + data.getYear() + data.getDay() + data.getMonth() + data.getDate());
            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(data.getYear()), Integer.parseInt(data.getMonth())-1, Integer.parseInt(data.getDay()));
            Date date = cal.getTime();

            Manager manager = new Manager(data.getUsername(), data.getName(), data.getLastname(), data.getPassword(), genderType, date);
            managerService.add(manager);
            return res.raw();
        });
    }

    public static void getFree() {
        get("/getFree", (req, res) -> {
            res.type("application/json");
            ArrayList<Manager> managers = managerService.getFree();

            if (managers == null) {
                res.status(204);
                return res.raw();
            }
            if (managers.size() == 0) {
                res.status(204);
                return res.raw();
            }

            ArrayList<ManagerInfoDTO> info = new ArrayList<>();
            for (Manager manager: managers) {
                info.add(new ManagerInfoDTO(manager.getUsername(),manager.getName(),manager.getLastname()));
            }
            return g.toJson(info);
        });
    }
}
