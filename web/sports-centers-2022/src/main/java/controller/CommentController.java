package controller;

import beans.Buyer;
import beans.Comment;
import beans.SportsFacility;
import com.google.gson.Gson;
import dto.CommentDTO;
import dto.CommentUpdateDTO;
import dto.SportsFacilityDTO;
import service.BuyerService;
import service.CommentService;
import service.SportsFacilityService;
import spark.utils.IOUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;

import static spark.Spark.*;

public class CommentController {
    private static Gson g = new Gson();
    private static CommentService commentService = new CommentService();
    private static SportsFacilityService facilityService = new SportsFacilityService();
    private static BuyerService buyerService = new BuyerService();
    private static String commonPath = "/rest/comments";

    public static void init() {
        path(commonPath, () -> {
            add();
            getByFacilityAll();
            getByFacilityApproved();
            getUnapproved();
            approve();
            disapprove();
            getUncommented();
        });
    }

    public static void add() {
        post("/add",(req, res) -> {
            String payload = req.body();
            CommentExpandedDTO data = g.fromJson(payload, CommentExpandedDTO.class);

            Buyer buyer = buyerService.getById(data.getUser());
            SportsFacility sportsFacility = facilityService.getById(data.getFacility());

            Comment comment = new Comment(0, buyer, sportsFacility, data.getText(), Integer.parseInt(data.getRating()));
            commentService.add(comment);
            return res.raw();
        });
    }

    public static void getByFacilityAll() {
        get("/getByFacilityAll", (req, res) -> {
            res.type("application/json");
            ArrayList<Comment> comments = commentService.getAllForFacilityIdAll(req.queryParams("id"));

            if (comments == null) {
                res.status(204);
                return res.raw();
            }
            if (comments.size() == 0) {
                res.status(204);
                return res.raw();
            }

            ArrayList<CommentDTO> dtos = new ArrayList<>();
            for (Comment comment: comments) {
                dtos.add(new CommentDTO(comment.getBuyer().getName()+" "+comment.getBuyer().getLastname(),comment.getText(),Integer.toString(comment.getRating())));
            }
            return g.toJson(dtos);
        });
    }

    public static void getByFacilityApproved() {
        get("/getByFacilityApproved", (req, res) -> {
            res.type("application/json");
            ArrayList<Comment> comments = commentService.getAllForFacilityIdApproved(req.queryParams("id"));

            if (comments == null) {
                res.status(204);
                return res.raw();
            }
            if (comments.size() == 0) {
                res.status(204);
                return res.raw();
            }

            ArrayList<CommentDTO> dtos = new ArrayList<>();
            for (Comment comment: comments) {
                dtos.add(new CommentDTO(comment.getBuyer().getName()+" "+comment.getBuyer().getLastname(),comment.getText(),Integer.toString(comment.getRating())));
            }
            return g.toJson(dtos);
        });
    }

    public static void getUnapproved() {
        get("/getUnapproved", (req, res) -> {
            res.type("application/json");
            ArrayList<Comment> comments = commentService.getUnapproved();

            ArrayList<CommentExpandedDTO> dtos = new ArrayList<>();
            for (Comment comment: comments) {
                dtos.add(new CommentExpandedDTO(comment.getBuyer().getUsername(),comment.getBuyer().getName()+" "+comment.getBuyer().getLastname(),comment.getFacility().getId(),comment.getText(),Integer.toString(comment.getRating())));
            }
            return g.toJson(dtos);
        });
    }

    public static void approve() {
        put("/approve",(req, res) -> {
            res.type("application/json");
            res.status(200);

            String payload = req.body();
            CommentUpdateDTO data = g.fromJson(payload, CommentUpdateDTO.class);
            commentService.approve(data.getFacilityId(), data.getUserId());

            return g.toJson(res.raw());
        });
    }

    public static void disapprove() {
        put("/disapprove",(req, res) -> {
            res.type("application/json");
            res.status(200);

            String payload = req.body();
            CommentUpdateDTO data = g.fromJson(payload, CommentUpdateDTO.class);
            commentService.disapprove(data.getFacilityId(), data.getUserId());

            return g.toJson(res.raw());
        });
    }
    public static void getUncommented() {
        get("/getUncommented", (req, res) -> {
            res.type("application/json");
            ArrayList<SportsFacility> list = commentService.getUncommentedForUser(req.queryParams("id"));
            ArrayList<SportsFacilityDTO> array = new ArrayList<>();
            if (list == null) {
                res.status(204);
                res.body("No facility centers found");
                return res.raw();
            }

            for (SportsFacility facility: list) {
                System.out.println(facility.get_logo());
                InputStream iSteamReader = new FileInputStream(facility.get_logo());
                byte[] imageBytes = IOUtils.toByteArray(iSteamReader);
                String base64 = Base64.getEncoder().encodeToString(imageBytes);
                base64 = "data:image/png;base64," + base64;
                String boolToString;
                if (facility.is_status()) {
                    boolToString = "Open";
                }
                else {
                    boolToString = "Closed";
                }
                array.add(new SportsFacilityDTO(facility.getId(),facility.get_name(),facility.get_type(),
                        boolToString, facility.get_location().getLatitude().toString(), facility.get_location().getLongitude().toString(),
                        facility.get_location().getAddress().getCity(), facility.get_location().getAddress().getStreet(),
                        facility.get_location().getAddress().getStNumber(), facility.get_location().getAddress().getPoNumber(),
                        base64, facility.get_rating().toString(), facility.get_startTime()/100+":"+facility.get_startTime()%100, facility.get_endTime()/100+":"+facility.get_endTime()%100));
            }

            return g.toJson(array);
        });
    }
}
