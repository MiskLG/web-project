package repository;

import beans.SportsFacility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

public class SportsFacilityRepository {

    private ArrayList<SportsFacility> facilities;

    private static SportsFacilityRepository repo = null;
    private SportsFacilityRepository() {};

    public static SportsFacilityRepository init() {
        if(repo == null) {
            repo = new SportsFacilityRepository();
            repo.facilities = new ArrayList<SportsFacility>();
        }
        return repo;
    }

    public ArrayList<SportsFacility> getAll() {
        read();
        return this.facilities;
    }

    public void add(SportsFacility sportsFacility) {
        File file = new File("resources/images/" + sportsFacility.getId()+".png");
        try {
            if(file.createNewFile()) {
                OutputStream iStreamOutput = null;
                iStreamOutput = new FileOutputStream(file);
                byte[] imageBytes = Base64.getDecoder().decode(sportsFacility.getLogo().split(",")[1]);
                iStreamOutput.write(imageBytes);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        sportsFacility.setLogo(file.getPath());
        read();
        if (this.facilities == null) {
            this.facilities = new ArrayList<>();
        }
        this.facilities.add(sportsFacility);
        write();
    }

    public void update(SportsFacility facility) {
        for (SportsFacility fac: getAll()) {
            if (fac.getId().equals(facility.getId())) {
                fac.setName(facility.getName());
                fac.setEndTime(facility.getEndTime());
                fac.setLocation(facility.getLocation());
                fac.setType(facility.getType());
                fac.setStartTime(facility.getStartTime());
                fac.setContent(facility.getContent());
                write();
                break;
            }
        }
        return;
    }

    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.sportsFacilitiesData));
            this.facilities = gson.fromJson(reader, new TypeToken<ArrayList<SportsFacility>>() {}.getType());
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
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.sportsFacilitiesData));
            gson.toJson(this.facilities, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
