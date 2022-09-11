package org.webproject;

import controller.*;

import java.io.File;
import java.io.IOException;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class Main {
    public static void main(String[] args) throws IOException {
        port(8080);

        staticFiles.externalLocation(new File("./static").getCanonicalPath());
        UserController.init();
        BuyerController.init();
        SportsFacilityController.init();
        ManagerController.init();
        CoachController.init();
        WorkoutController.init();
        SubscriptionController.init();
        PromoCodeController.init();
        WorkoutHistoryController.init();
        //get("/rest", (request, response) -> "HELLO");
    }
}