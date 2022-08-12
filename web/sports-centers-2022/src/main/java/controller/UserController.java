package controller;

import beans.*;
import com.google.gson.Gson;
import service.UserService;

import java.util.ArrayList;
import java.util.Date;

import static spark.Spark.*;

public class UserController {

    /*private static Gson g = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    */
    private static Gson g = new Gson();
    private static UserService userService = new UserService();
    private static String commonPath = "/rest";

    public static void init() {
        path(commonPath, () -> {
            login();
            logout();
            add();
        });
    }
    public static void login(){
        post("/login", (req, res) -> {
            res.type("application/json");
            return "SUCCESS";
        });
    }
    public static void logout(){
        get("/login", (req, res) -> {
            res.type("application/json");
            return "SUCCESS";
        });
    }
    public static void add(){
        get("/add", (req, res) -> {
            res.type("application/json");
            userService.addUser(new Buyer("test", "test", "test" , "123", User.GenderType.MALE, new Date(),
                    new Subscription("0", new Date(), new Date(), 12.2, Subscription.StatusType.ACTIVE, Subscription.SubscriptionType.DAILY, 0),
                    new ArrayList<SportsFacility>(), 0, new BuyerType("asd",12.0,12000)));
            return "SUCCESS";
        });
    }
}
