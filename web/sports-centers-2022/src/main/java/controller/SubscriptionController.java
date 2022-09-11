package controller;

import beans.Subscription;
import com.google.gson.Gson;
import dto.SubscriptionDTO;
import dto.SubscriptionExpandedDTO;
import service.SubscriptionService;

import java.time.LocalDate;
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
            getTemplate();
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
    public static void getTemplate() {
        get("/getTemplate", (req, res) -> {
            res.type("application/json");

            Subscription subscription = subscriptionService.getById(req.queryParams("id"));
            SubscriptionExpandedDTO dto = new SubscriptionExpandedDTO();
            dto.setId(subscription.getId());
            dto.setPrice(subscription.getPrice().toString());
            dto.setNumberOfAppointments(Integer.toString(subscription.getNumberOfAppointments()));
            dto.setType(subscription.getType().toString());
            LocalDate date = LocalDate.now();

            dto.setDate1(date.getDayOfMonth()+ "/" + date.getMonthValue() + "/" + date.getYear());
            switch (subscription.getType()) {
                case WEEKLY -> {
                    date = date.plusDays(7);
                    dto.setDate2(date.getDayOfMonth()+ "/" + date.getMonthValue() + "/" + date.getYear());
                }
                case MONTHLY -> {
                    date = date.plusMonths(1);
                    dto.setDate2(date.getDayOfMonth()+ "/" + date.getMonthValue() + "/" + date.getYear());
                }
                case ANNUAL -> {
                    date = date.plusYears(1);
                    dto.setDate2(date.getDayOfMonth()+ "/" + date.getMonthValue() + "/" + date.getYear());
                }
            }
            return g.toJson(dto);
        });
    }
}
