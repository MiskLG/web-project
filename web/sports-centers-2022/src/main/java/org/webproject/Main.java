package org.webproject;

import controller.UserController;

import java.io.File;
import java.io.IOException;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) throws IOException {
        port(8080);

        staticFiles.externalLocation(new File("./static").getCanonicalPath());
        UserController.login();
        UserController.logout();
        //get("/rest", (request, response) -> "HELLO");
    }
}