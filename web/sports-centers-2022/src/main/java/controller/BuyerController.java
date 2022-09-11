package controller;

import beans.Buyer;
import beans.Subscription;
import beans.User;
import com.google.gson.Gson;
import dto.*;
import service.BuyerService;
import service.UserService;

import java.util.Calendar;
import java.util.Date;

import static spark.Spark.*;

public class BuyerController {

    private static Gson g = new Gson();
    private static BuyerService buyerService = new BuyerService();
    private static UserService userService = new UserService();
    private static String commonPath = "/rest/buyer";

    public static void init() {
        path(commonPath, () -> {
            register();
            getSubscription();
            getDiscount();
            subscribe();
        });
    }

    public static void register() {
        post("/register", (req, res) -> {
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

            Buyer buyer = new Buyer(data.getUsername(), data.getName(), data.getLastname(), data.getPassword(), genderType, date);
            buyer.setPoints(0);
            buyerService.add(buyer);
            UserInfoDTO info = new UserInfoDTO(buyer.getUsername(),buyer.getUserType().toString());
            req.session().attribute("user", info);
            return g.toJson(info);
        });
    }
    public static void getSubscription() {
        get("/getSubscription", (req, res) -> {
            res.type("application/json");
            Buyer buyer = buyerService.getById(req.queryParams("id"));
            if(buyer.getSubscriptionId() == null) {
                res.status(204);
                return res.raw();
            }
            Subscription subscription = buyer.getSubscriptionId();
            SubscriptionExpandedDTO dto = new SubscriptionExpandedDTO();
            dto.setId(subscription.getId());
            dto.setPrice(subscription.getPrice().toString());
            dto.setNumberOfAppointments(Integer.toString(subscription.getNumberOfAppointments()));
            dto.setType(subscription.getType().toString());
            dto.setStatus(subscription.getStatus().toString());
            dto.setDate1(subscription.getDateOfPaying().toString());
            dto.setDate2(subscription.getDateOfExparation().toString());

            return g.toJson(dto);
        });
    }
    public static void getDiscount() {
        get("/getDiscount", (req, res) -> {
            res.type("application/json");
            Buyer buyer = buyerService.getById(req.queryParams("id"));
            return g.toJson(buyer.getBuyerType().getDiscount());
        });
    }

    public static void subscribe() {
        post("/subscribe", (req, res) -> {
            res.type("application/json");
            String payload = req.body();
            BuyerSubscribeDTO data = g.fromJson(payload, BuyerSubscribeDTO.class);

            Buyer buyer = buyerService.getById(data.getUsername());

            String[] data1 = data.getSub().getDate1().split("/");
            String[] data2 = data.getSub().getDate2().split("/");

            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(data1[2]), Integer.parseInt(data1[1])-1, Integer.parseInt(data1[0]), 0, 0, 0);
            Date date1 = cal.getTime();
            cal.set(Integer.parseInt(data2[2]), Integer.parseInt(data2[1])-1, Integer.parseInt(data2[0]), 0, 0, 0);
            Date date2 = cal.getTime();

            Subscription.SubscriptionType type;
            if(data.getSub().getType().equalsIgnoreCase("weekly")) {
                type = Subscription.SubscriptionType.WEEKLY;
            }
            else if(data.getSub().getType().equalsIgnoreCase("monthly")) {
                type = Subscription.SubscriptionType.MONTHLY;
            }
            else {
                type = Subscription.SubscriptionType.ANNUAL;
            }

            Subscription.StatusType status = Subscription.StatusType.ACTIVE;

            Subscription subs = new Subscription(data.getSub().getId(),date1, date2,Double.parseDouble(data.getSub().getPrice()), status,type, Integer.parseInt(data.getSub().getNumberOfAppointments()));
            buyerService.addSubscription(buyer, subs);
            return res.raw();
        });
    }
}
