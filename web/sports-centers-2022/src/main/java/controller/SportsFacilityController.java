package controller;

import beans.SportsFacility;
import com.google.gson.Gson;
import dto.SportsFacilityDTO;
import service.SportsFacilityService;
import spark.utils.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;

import static spark.Spark.*;

public class SportsFacilityController {
    private static Gson g = new Gson();
    private static SportsFacilityService facilityService = new SportsFacilityService();
    private static String commonPath = "/rest/centers";

    public static void init() {
        path(commonPath, () -> {
            getAll();
            add();
        });
    }

    public static void getAll() {
        get("/getAll", (req, res) -> {
            res.type("application/json");

            ArrayList<SportsFacilityDTO> array = new ArrayList<>();

            for (SportsFacility facility: facilityService.getAll()) {
                InputStream iSteamReader = new FileInputStream(facility.getLogo());
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);
                String boolToString;
                if (facility.isStatus()) {
                    boolToString = "Open";
                }
                else {
                    boolToString = "Closed";
                }
                array.add(new SportsFacilityDTO(facility.getId(),facility.getName(),facility.getType(),
                        boolToString, facility.getLocation().getLatitude().toString(), facility.getLocation().getLongitude().toString(),
                        facility.getLocation().getAddress().getCity(), facility.getLocation().getAddress().getStreet(),
                        facility.getLocation().getAddress().getStNumber(), facility.getLocation().getAddress().getPoNumber(),
                        base64, facility.getRating().toString(), ""+facility.getStartTime(), ""+facility.getEndTime()));
            }

            return g.toJson(array);
        });
    }

    public static void add() {
        post("/add", (req, res) -> {
            res.type("application/json");
            return "";
        });
    }
}
