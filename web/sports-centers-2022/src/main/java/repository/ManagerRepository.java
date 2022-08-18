package repository;

import beans.Manager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ManagerRepository {
    private ArrayList<Manager> managers;

    private static ManagerRepository repo = null;
    private ManagerRepository() {};

    public static ManagerRepository init() {
        if(repo == null) {
            repo = new ManagerRepository();
            repo.managers = new ArrayList<Manager>();
        }
        return repo;
    }

    public ArrayList<Manager> getAll() {
        read();
        return this.managers;
    }

    public void add(Manager manager) {
        managers.add(manager);
        write();
    }

    public void update(Manager manager) {
        for (Manager man: getAll()) {
            if (man.getUsername().equalsIgnoreCase(manager.getUsername())) {
                man.setName(manager.getName());
                man.setPassword(manager.getPassword());
                man.setLastname(manager.getLastname());
                man.setGender(manager.getGender());
                man.setDateOfBirth(manager.getDateOfBirth());
                write();
                break;
            }
        }
        return;
    }
    public void read() {
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

    public void write(){
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.managersData));
            gson.toJson(this.managers, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
