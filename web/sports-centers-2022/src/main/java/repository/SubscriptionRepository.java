package repository;

import beans.Subscription;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SubscriptionRepository {
    private ArrayList<Subscription> subscriptions;

    private static SubscriptionRepository repo = null;
    private SubscriptionRepository() {};

    public ArrayList<Subscription> getAll() {
        read();
        return this.subscriptions;
    }

    public void add(Subscription subscription) {
        subscriptions.add(subscription);
        write();
    }

    public void update(Subscription subscription) {
        for (Subscription sub: getAll()) {
            if (sub.getId().equalsIgnoreCase(subscription.getId())) {
                //TODO
                write();
                break;
            }
        }
        return;
    }

    public static SubscriptionRepository init() {
        if(repo == null) {
            repo = new SubscriptionRepository();
            repo.subscriptions = new ArrayList<Subscription>();
        }
        return repo;
    }

    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.subscriptionsData));
            this.subscriptions = gson.fromJson(reader, new TypeToken<ArrayList<Subscription>>() {}.getType());
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
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.subscriptionsData));
            gson.toJson(this.subscriptions, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
