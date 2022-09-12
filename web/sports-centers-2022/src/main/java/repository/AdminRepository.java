package repository;

import beans.Admin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AdminRepository {

    private ArrayList<Admin> admins;

    private static AdminRepository repo = null;
    private AdminRepository() {};

    public static AdminRepository init() {
        if(repo == null) {
            repo = new AdminRepository();
            repo.admins = new ArrayList<Admin>();
            repo.read();
        }
        return repo;
    }
    public ArrayList<Admin> getAll() {
        return this.admins;
    }

    public void update(Admin admin) {
        for (Admin ad: getAll()) {
            if (ad.getUsername().equalsIgnoreCase(admin.getUsername())) {
                ad.setName(admin.getName());
                ad.setPassword(admin.getPassword());
                ad.setLastname(admin.getLastname());
                ad.setGender(admin.getGender());
                ad.setDateOfBirth(admin.getDateOfBirth());
                write();
                break;
            }
        }
        return;
    }

    public Admin getById(String id) {
        for (Admin admin: this.getAll()) {
            if (admin.getUsername().equalsIgnoreCase(id)) {
                return admin;
            }
        }
        return null;
    }

    public void add(Admin admin) {
        admins.add(admin);
        write();
    }

    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.adminsData));
            this.admins = gson.fromJson(reader, new TypeToken<ArrayList<Admin>>() {}.getType());
            reader.close();
            if (admins == null) {
                admins = new ArrayList<>();
            }
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
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.adminsData));
            gson.toJson(this.admins, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
