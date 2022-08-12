package repository;

import beans.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dao.UserLoginInfo;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class UserRepository {

    private ArrayList<Buyer> buyers;
    private ArrayList<Admin> admins;
    private ArrayList<Manager> managers;
    private ArrayList<Coach> coaches;

    private ArrayList<UserLoginInfo> userLoginInfos;
    private static UserRepository repo = null;

    private UserRepository() {};

    public static UserRepository init() {
        if(repo == null) {
            repo = new UserRepository();
            repo.buyers = new ArrayList<Buyer>();
            repo.managers = new ArrayList<Manager>();
            repo.admins = new ArrayList<Admin>();
            repo.coaches = new ArrayList<Coach>();
            repo.userLoginInfos = new ArrayList<UserLoginInfo>();
        }
        return repo;
    }

    public ArrayList<UserLoginInfo> getInfo() {
        readInfos();
        return this.userLoginInfos;
    }

    public ArrayList<Buyer> getBuyers() {
        readBuyers();
        return this.buyers;
    }

    public ArrayList<Coach> getCoaches() {
        readCoaches();
        return this.coaches;
    }

    public ArrayList<Manager> getManagers() {
        readManagers();
        return this.managers;
    }

    public ArrayList<Admin> getAdmins() {
        readAdmins();
        return this.admins;
    }

    public void add(Buyer buyer) {
        buyers.add(buyer);
        generateAndAddUserInfo(buyer);
        writeBuyers();
    }
    public void add(Manager manager) {
        managers.add(manager);
        generateAndAddUserInfo(manager);
        writeManagers();
    }
    public void add(Coach coach) {
        coaches.add(coach);
        generateAndAddUserInfo(coach);
        writeCoaches();
    }
    public void add(Admin admin) {
        admins.add(admin);
        generateAndAddUserInfo(admin);
        writeAdmins();
    }

    public void generateAndAddUserInfo(User user) {
        userLoginInfos.add(new UserLoginInfo(user.getUsername(), user.getPassword(), user.getUserType()));
        writeInfos();
    }
    public void readInfos() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.userCredentials));
            this.userLoginInfos = gson.fromJson(reader, new TypeToken<ArrayList<UserLoginInfo>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };

    public void readBuyers() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.buyersData));
            this.buyers = gson.fromJson(reader, new TypeToken<ArrayList<Buyer>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    public void readManagers() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.managersData));
            this.managers = gson.fromJson(reader, new TypeToken<ArrayList<Manager>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    public void readCoaches() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.coachesData));
            this.coaches = gson.fromJson(reader, new TypeToken<ArrayList<Coach>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    public void readAdmins() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.adminsData));
            this.admins = gson.fromJson(reader, new TypeToken<ArrayList<Admin>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };

    public void writeInfos(){
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.userCredentials));
            gson.toJson(this.userLoginInfos, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };

    public void writeBuyers(){
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.buyersData));
            gson.toJson(this.buyers, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    public void writeManagers(){
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.userCredentials));
            gson.toJson(this.userLoginInfos, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    public void writeCoaches(){
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.userCredentials));
            gson.toJson(this.userLoginInfos, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
    public void writeAdmins(){
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.userCredentials));
            gson.toJson(this.userLoginInfos, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
