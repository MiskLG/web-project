package controller;

import beans.Location;
import beans.SportsFacility;
import com.google.gson.Gson;
import dto.SportsFacilityAddDTO;
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
            ArrayList<SportsFacility> list = facilityService.getAll();
            if (list == null) {
                res.status(204);
                res.body("No facility centers found");
                return res.raw();
            }

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

            String payload = req.body();
            SportsFacilityAddDTO data = g.fromJson(payload, SportsFacilityAddDTO.class);

            Location location = new Location(Double.parseDouble(data.getLatitude()), Double.parseDouble(data.getLongitude()), data.getStreet(), data.getStNumber(), data.getCity(), data.getPoNumber());

            SportsFacility facility = new SportsFacility("",data.getName(),data.getType().toUpperCase(), null,
                    true, location, data.getImage(), 0.0, Integer.parseInt(data.getStartTime()), Integer.parseInt(data.getEndTime()));

            facilityService.add(facility);
            res.body("Added");
            res.status(200);
            return res.raw();
        });
    }
}
