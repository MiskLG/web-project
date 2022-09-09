package repository;

import beans.SportsFacility;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
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
            repo.read();
        }
        return repo;
    }

    public ArrayList<SportsFacility> getAll() {
        return this.facilities;
    }

    public void add(SportsFacility sportsFacility) {
        File file = new File("resources/images/sports_centers/" + sportsFacility.getId()+".png");
        try {
            if(file.createNewFile()) {
                OutputStream iStreamOutput = null;
                iStreamOutput = new FileOutputStream(file);
                byte[] imageBytes = Base64.getDecoder().decode(sportsFacility.get_logo().split(",")[1]);
                iStreamOutput.write(imageBytes);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        sportsFacility.set_logo(file.getPath());
        this.facilities.add(sportsFacility);
        write();
    }

    public void update(SportsFacility facility) {
        for (SportsFacility fac: getAll()) {
            if (fac.getId().equals(facility.getId())) {
                fac.set_name(facility.get_name());
                fac.set_endTime(facility.get_endTime());
                fac.set_location(facility.get_location());
                fac.set_type(facility.get_type());
                fac.set_startTime(facility.get_startTime());
                fac.set_content(facility.get_content());
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
            if (facilities == null) {
                facilities = new ArrayList<>();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };

    public void write(){
        try {
            GsonBuilder gsonBuilder = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getName().contains("__");
                }

                @Override
                public boolean shouldSkipClass(Class<?> incomingClass) {
                    return false;
                }
            });
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
