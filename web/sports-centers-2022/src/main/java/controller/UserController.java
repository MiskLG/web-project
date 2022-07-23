package controller;

import static spark.Spark.get;

public class UserController {
    public static void login(){
        get("rest/login", ((request, response) -> "SUCCESS"));
    }
    public static void logout(){
        get("rest/login", ((request, response) -> "SUCCESS"));
    }
}
