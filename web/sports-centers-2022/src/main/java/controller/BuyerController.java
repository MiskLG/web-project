package controller;

import beans.Buyer;
import beans.User;
import com.google.gson.Gson;
import dto.BuyerRegisterDTO;
import dto.Credentials;
import service.BuyerService;
import service.UserService;

import java.util.Calendar;
import java.util.Date;

import static spark.Spark.path;
import static spark.Spark.post;

public class BuyerController {

    private static Gson g = new Gson();
    private static BuyerService buyerService = new BuyerService();
    private static UserService userService = new UserService();
    private static String commonPath = "/rest/buyer";

    public static void init() {
        path(commonPath, () -> {
            register();
        });
    }

    public static void register() {
        post("/register", (req, res) -> {
            res.type("application/json");
            String payload = req.body();
            BuyerRegisterDTO data = g.fromJson(payload, BuyerRegisterDTO.class);
            User.GenderType genderType;
            if(data.getGender().equals("male")) {
                 genderType = User.GenderType.MALE;
            }
            else {
                genderType = User.GenderType.FEMALE;
            }
            data.fillDate();
            System.out.println(data.getUsername() + data.getYear() + data.getDay() + data.getMonth() + data.getDate());
            Calendar.getInstance().set(Integer.parseInt(data.getYear()), Integer.parseInt(data.getMonth()), Integer.parseInt(data.getDay()));
            Date date = Calendar.getInstance().getTime();

            Buyer buyer = new Buyer(data.getUsername(), data.getName(), data.getLastname(), data.getPassword(), genderType, date);
            buyerService.add(buyer);

            User user = userService.getUser(new Credentials(data.getUsername(), data.getPassword()));
            req.session().attribute("user", user);
            return g.toJson(user);
        });
    }
}
