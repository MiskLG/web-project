package repository;

import beans.WorkoutHistory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

public class WorkoutHistoryRepository {
    private ArrayList<WorkoutHistory> workoutHistories;

    private static WorkoutHistoryRepository repo = null;
    private WorkoutHistoryRepository() {};

    public ArrayList<WorkoutHistory> getAll() {
        return this.workoutHistories;
    }

    public void add(WorkoutHistory workoutHistory) {
        workoutHistories.add(workoutHistory);
        write();
    }
    public void checkDates() {
        for (WorkoutHistory wh: this.workoutHistories) {
            if (Calendar.getInstance().getTime().compareTo(wh.getDateOfRegistration()) < 0 ) {
                wh.setStatus(true);
            }
        }
        write();
    }

    public static WorkoutHistoryRepository init() {
        if(repo == null) {
            repo = new WorkoutHistoryRepository();
            repo.workoutHistories = new ArrayList<WorkoutHistory>();
            repo.read();
            repo.checkDates();
        }
        return repo;
    }

    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.workoutHistoryData));
            this.workoutHistories = gson.fromJson(reader, new TypeToken<ArrayList<WorkoutHistory>>() {}.getType());
            reader.close();
            if (workoutHistories == null) {
                workoutHistories = new ArrayList<>();
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
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.workoutHistoryData));
            gson.toJson(this.workoutHistories, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
