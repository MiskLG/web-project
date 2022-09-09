package repository;

import beans.Workout;
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

public class WorkoutRepository {
    private ArrayList<Workout> workouts;

    private static WorkoutRepository repo = null;
    private WorkoutRepository() {};

    public ArrayList<Workout> getAll() {
        return this.workouts;
    }

    public void add(Workout workout) {
        File file = new File("resources/images/workouts/" + workout.getId()+".png");
        try {
            if(file.createNewFile()) {
                OutputStream iStreamOutput = null;
                iStreamOutput = new FileOutputStream(file);
                byte[] imageBytes = Base64.getDecoder().decode(workout.getPhoto().split(",")[1]);
                iStreamOutput.write(imageBytes);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        workout.setPhoto(file.getPath());
        this.workouts.add(workout);
        write();
    }

    public void update(Workout workout) {
        for (Workout w: getAll()) {
            //TODO if u need it
            break;
        }
        return;
    }

    public static WorkoutRepository init() {
        if(repo == null) {
            repo = new WorkoutRepository();
            repo.workouts = new ArrayList<Workout>();
            repo.read();
        }
        return repo;
    }

    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.workoutData));
            this.workouts = gson.fromJson(reader, new TypeToken<ArrayList<Workout>>() {}.getType());
            reader.close();
            if (workouts == null) {
                workouts = new ArrayList<>();
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
                    if (f.getName().contains("_"))
                    {
                        if (f.getName().contains("__")) {
                            return false;
                        }
                        return true;
                    }
                    return false;
                }

                @Override
                public boolean shouldSkipClass(Class<?> incomingClass) {
                    return false;
                }
            });
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.workoutData));
            gson.toJson(this.workouts, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
