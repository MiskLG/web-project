package controller;

import beans.Location;
import beans.Manager;
import beans.SportsFacility;
import com.google.gson.Gson;
import dto.SportsFacilityAddDTO;
import dto.SportsFacilityDTO;
import dto.SportsFacilitySearchDTO;
import service.ManagerService;
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
    private static ManagerService managerService = new ManagerService();
    private static String commonPath = "/rest/centers";

    public static void init() {
        path(commonPath, () -> {
            getAll();
            add();
            getAllTypes();
            search();
            getEmpty();
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

            for (SportsFacility facility: list) {
                InputStream iSteamReader = new FileInputStream(facility.getLogo());
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);
                base64 = "data:image/png;base64," + base64;
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
                        base64, facility.getRating().toString(), facility.getStartTime()/100+":"+facility.getStartTime()%100, facility.getEndTime()/100+":"+facility.getEndTime()%100));
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

            int startTime = Integer.parseInt(data.getStartTime().split(":")[0])*100 + Integer.parseInt(data.getStartTime().split(":")[1]);
            int endTime = Integer.parseInt(data.getEndTime().split(":")[0])*100 + Integer.parseInt(data.getEndTime().split(":")[1]);
            SportsFacility facility = new SportsFacility("",data.getName(),data.getType().toUpperCase(), null,
                    true, location, data.getImage(), 0.0, startTime, endTime);

            facilityService.add(facility);

            Manager manager = managerService.getById(data.getManagerUsername());
            manager.setFacility(facility);
            managerService.update(manager);

            res.body("Added");
            res.status(200);
            return res.raw();
        });
    }

    public static void getAllTypes() {
        get("/getAllTypes", (req, res) -> {
            res.type("application/json");
            return g.toJson(facilityService.getAllTypes());
        });
    }

    public static void search() {
        post("/search", (req, res) -> {
            res.type("application/json");
            String payload = req.body();
            SportsFacilitySearchDTO data = g.fromJson(payload, SportsFacilitySearchDTO.class);

            ArrayList<SportsFacilityDTO> array = new ArrayList<>();
            ArrayList<SportsFacility> list = facilityService.getAll();
            if (list == null) {
                res.status(204);
                res.body("No facility centers found");
                return res.raw();
            }

            Boolean stringToBool;
            if (data.getFilterStatus().equals("Opened")) {
                stringToBool = true;
            }
            else if (data.getFilterStatus().equals("Closed")) {
                stringToBool = false;
            }
            else {
                stringToBool = null;
            }
            if(data.getRating().isEmpty())
            {
                data.setRating("-1");
            }
            SportsFacilityService.SortingParameter parameter;
            if(data.getSortParameter().equals("NAME")) {
                parameter = SportsFacilityService.SortingParameter.NAME;
            } else if (data.getSortParameter().equals("LOCATION")) {
                parameter = SportsFacilityService.SortingParameter.LOCATION;
            } else {
                parameter = SportsFacilityService.SortingParameter.RATING;
            }

            SportsFacilityService.SortingOrientation orientation;
            if(data.getSortOrientation().equals("ASC")) {
                orientation = SportsFacilityService.SortingOrientation.ASC;
            }
            else {
                orientation = SportsFacilityService.SortingOrientation.DESC;
            }

            list = facilityService.search(data.getName(),data.getType(), data.getCity(), Double.parseDouble(data.getRating()),list);
            list = facilityService.sort(parameter, orientation,list);
            list = facilityService.filter(data.getFilterType(),stringToBool,list);

            for (SportsFacility facility: list) {
                InputStream iSteamReader = new FileInputStream(facility.getLogo());
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);
                base64 = "data:image/png;base64," + base64;
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
                        base64, facility.getRating().toString(), facility.getStartTime()/100+":"+facility.getStartTime()%100, facility.getEndTime()/100+":"+facility.getEndTime()%100));
            }

            return g.toJson(array);
        });
    }

    public static void getEmpty() {
        get("/getWithoutManagers", (req, res) -> {
            res.type("application/json");

            ArrayList<SportsFacilityDTO> array = new ArrayList<>();
            ArrayList<SportsFacility> list = facilityService.getWithoutManagers();
            if (list == null) {
                res.status(204);
                res.body("No facility centers found");
                return res.raw();
            }

            for (SportsFacility facility: list) {
                InputStream iSteamReader = new FileInputStream(facility.getLogo());
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);
                base64 = "data:image/png;base64," + base64;
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
                        base64, facility.getRating().toString(), facility.getStartTime()/100+":"+facility.getStartTime()%100, facility.getEndTime()/100+":"+facility.getEndTime()%100));
            }

            return g.toJson(array);
        });
    }
}
