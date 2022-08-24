package controller;

import beans.User;
import com.google.gson.Gson;
import dto.Credentials;
import service.UserService;

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
            String payload = req.body();
            Credentials credentials = g.fromJson(payload, Credentials.class);

            User user = userService.getUser(credentials);

            if (user == null) {
                res.status(401);
                res.body("Incorrect username or password. Please try again");
                return res.body();
            }

            req.session().attribute("user", user);
            return g.toJson(user);
        });
    }
    public static void logout(){
        post("/logout", (req, res) -> {
            res.type("application/json");
            req.session().removeAttribute("user");
            res.status(200);
            return res.body();
        });
    }

    public static void getUsers() {
        get("/users", (req, res) -> {
            return g.toJson(userService.getAll());
        });
    }

    public static void add(){
        get("/add", (req, res) -> {
            res.type("application/json");
            /*
            userService.addUser(new Buyer("test", "test", "test" , "123", User.GenderType.MALE, new Date(),
                    new Subscription("0", new Date(), new Date(), 12.2, Subscription.StatusType.ACTIVE, Subscription.SubscriptionType.DAILY, 0),
                    new ArrayList<SportsFacility>(), 0, new BuyerType("asd",12.0,12000)));
            */
            return "SUCCESS";
        });
    }
}
