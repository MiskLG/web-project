package controller;

import beans.User;
import com.google.gson.Gson;
import dto.Credentials;
import service.UserService;

import static spark.Spark.path;
import static spark.Spark.post;

public class BuyerController {

    private static Gson g = new Gson();
    private static UserService userService = new UserService();
    private static String commonPath = "/rest";

    public static void init() {
        path(commonPath, () -> {
            register();
        });
    }

    public static void register() {
        post("/register", (req, res) -> {
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
}
