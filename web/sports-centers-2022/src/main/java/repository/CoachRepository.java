package repository;

import beans.Coach;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CoachRepository {

    private ArrayList<Coach> coaches;

    private static CoachRepository repo = null;
    private CoachRepository() {};

    public static CoachRepository init() {
        if(repo == null) {
            repo = new CoachRepository();
            repo.coaches = new ArrayList<Coach>();
        }
        return repo;
    }

    public void add(Coach coach) {
        coaches.add(coach);
        write();
    }
    public ArrayList<Coach> getCoaches() {
        read();
        return this.coaches;
    }

    public void read() {
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

    public void write(){
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.userCredentials));
            gson.toJson(this.coaches, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };

}
