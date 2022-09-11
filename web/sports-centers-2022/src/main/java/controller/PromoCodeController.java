package controller;

import beans.PromoCode;
import com.google.gson.Gson;
import dto.PromoCodeDTO;
import service.PromoCodeService;

import java.util.Calendar;
import java.util.Date;

import static spark.Spark.*;

public class PromoCodeController {
    private static Gson g = new Gson();

    private static PromoCodeService service = new PromoCodeService();
    private static String commonPath = "/rest/promocodes";

    public static void init() {
        path(commonPath, () -> {
            add();
            getById();
        });
    }

    public static void add() {
        post("/add",(req, res) -> {
            res.type("application/json");
            String payload = req.body();
            PromoCodeDTO data = g.fromJson(payload, PromoCodeDTO.class);

            if(service.getById(data.getId()) != null) {
                res.body("PromoCodeName is taken");
                res.status(400);
                return res.raw();
            }

            data.fillDate();
            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(data.getYear()), Integer.parseInt(data.getMonth())-1, Integer.parseInt(data.getDay()), 0, 0, 0);
            Date date1 = cal.getTime();

            cal.set(Integer.parseInt(data.getYear2()), Integer.parseInt(data.getMonth2())-1, Integer.parseInt(data.getDay2()), 0, 0, 0);
            Date date2 = cal.getTime();

            service.add(new PromoCode(data.getId(),date1,date2,Integer.parseInt(data.getNumberOfUsage()),Double.parseDouble(data.getDiscount())));
            return res.raw();
        });
    }

    public static void getById() {
        get("/getById", (req, res) -> {
            return g.toJson(service.getById(req.queryParams("id")));
        });
    }
}
