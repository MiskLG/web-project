package controller;

import beans.Subscription;
import com.google.gson.Gson;
import dto.SubscriptionDTO;
import service.SubscriptionService;

import java.util.ArrayList;

import static spark.Spark.get;
import static spark.Spark.path;

public class SubscriptionController {
    private static Gson g = new Gson();
    private static SubscriptionService subscriptionService = new SubscriptionService();
    private static String commonPath = "/rest/subscriptions";

    public static void init() {
        path(commonPath, () -> {
            getAll();
        });
    }

    public static void getAll() {
        get("/getAll", (req, res) -> {
            res.type("application/json");

           ArrayList<Subscription> subscriptions = subscriptionService.getAll();
           ArrayList<SubscriptionDTO> dtos = new ArrayList<>();
           for (Subscription sub: subscriptions) {
               System.out.println(sub.getId());
               dtos.add((new SubscriptionDTO(sub.getId(), sub.getType().toString(),sub.getPrice().toString(),Integer.toString(sub.getNumberOfAppointments()))));
           }
            return g.toJson(dtos);
        });
    }
}
