package controller;

import beans.User;
import com.google.gson.Gson;
import dto.Credentials;
import dto.UserDTO;
import dto.UserInfoDTO;
import dto.UserSearchDTO;
import service.UserService;

import java.util.ArrayList;

import static spark.Spark.*;

public class UserController {

    /*private static Gson g = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    */
    private static Gson g = new Gson();
    private static UserService userService = new UserService();
    private static String commonPath = "/rest/user";

    public static void init() {
        path(commonPath, () -> {
            login();
            logout();
            getUsers();
            add();
            current();
            search();
            getById();
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

            UserInfoDTO info = new UserInfoDTO(user.getUsername(),user.getUserType().toString());
            req.session().attribute("user", info);
            return g.toJson(info);
        });
    }
    public static void logout(){
        post("/logout", (req, res) -> {
            res.type("application/json");
            req.session().removeAttribute("user");
            res.status(200);
            return "";
        });
    }

    public static void getUsers() {
        get("/getAll", (req, res) -> {
            res.type("application/json");

            ArrayList<UserDTO> list = new ArrayList<>();
            for (User user: userService.getAll()) {
                list.add(new UserDTO(user.getName(),user.getLastname(), user.getUsername(), user.getUserType().toString(),user.getGender().toString(), user.getDateOfBirth().toString()));
            }

            return g.toJson(list);
        });
    }

    public static void current() {
        get("/current", (req, res) -> {
            res.type("application/json");
            if (req.session().attribute("user") != null)
                return g.toJson((UserInfoDTO)req.session().attribute("user"));
            else
                return "NOUSER";
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
    public static void search(){
        post("/search", (req, res) -> {
            res.type("application/json");
            res.status(200);

            String payload = req.body();
            UserSearchDTO data = g.fromJson(payload, UserSearchDTO.class);

            ArrayList<User> users = userService.getAll();
            if (users == null) {
                res.status(204);
                res.body("No users found");
                return res.raw();
            }

            ArrayList<UserDTO> list = new ArrayList<>();

            UserService.SortingParameter parameter;
            if(data.getSortParameter().equals("NAME")) {
                parameter = UserService.SortingParameter.NAME;
            } else if (data.getSortParameter().equals("USERNAME")) {
                parameter = UserService.SortingParameter.USERNAME;
            } else {
                parameter = UserService.SortingParameter.LASTNAME;
            }

            UserService.SortingOrientation orientation;
            if(data.getSortOrientation().equals("ASC")) {
                orientation = UserService.SortingOrientation.ASC;
            }
            else {
                orientation = UserService.SortingOrientation.DESC;
            }
            User.UserType type;
            if(data.getFilterType().equalsIgnoreCase("ADMIN")) {
                type = User.UserType.ADMIN;
                users = userService.filter(type, users);
            } else if (data.getFilterType().equalsIgnoreCase("BUYER")) {
                type = User.UserType.BUYER;
                users = userService.filter(type, users);
            }else if (data.getFilterType().equalsIgnoreCase("MANAGER")) {
                type = User.UserType.MANAGER;
                users = userService.filter(type, users);
            } else if(data.getFilterType().equalsIgnoreCase("COACH")){
                type = User.UserType.COACH;
                users = userService.filter(type, users);
            }


            users = userService.search(data.getName(), data.getLastname(), data.getUsername(), users);
            users = userService.sort(parameter,orientation,users);

            for (User user: users) {
                list.add(new UserDTO(user.getName(),user.getLastname(), user.getUsername(), user.getUserType().toString(),user.getGender().toString(), user.getDateOfBirth().toString()));
            }

            return g.toJson(list);
        });
    }
    public static void getById() {
        get("/getById", (req, res) -> {
            res.type("application/json");
            User.UserType type;
            if(req.queryParams("type").equalsIgnoreCase("ADMIN")) {
                type = User.UserType.ADMIN;
            } else if ( req.queryParams("type").equalsIgnoreCase("BUYER")) {
                type = User.UserType.BUYER;
            }else if ( req.queryParams("type").equalsIgnoreCase("MANAGER")) {
                type = User.UserType.MANAGER;
            } else{
                type = User.UserType.COACH;
            }
            User user = userService.getUserByIdAndType(req.queryParams("username"), type);
            UserDTO dto = new UserDTO(user.getName(),user.getLastname(),user.getUsername(),user.getUserType().toString(), user.getGender().toString(), user.getDateOfBirth().toString());

            return g.toJson(dto);
        });
    }
}
