package repository;

import beans.Buyer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BuyerRepository {

    private ArrayList<Buyer> buyers;

    private static BuyerRepository repo = null;

    private BuyerRepository() {};

    public static BuyerRepository init() {
        if(repo == null) {
            repo = new BuyerRepository();
            repo.buyers = new ArrayList<Buyer>();
            repo.read();
        }
        return repo;
    }

    public ArrayList<Buyer> getAll() {
        return this.buyers;
    }

    public void add(Buyer buyer) {
        buyers.add(buyer);
        write();
    }

    public void update(Buyer buyer) {
        for (Buyer buy: getAll()) {
            if (buy.getUsername().equalsIgnoreCase(buyer.getUsername())) {
                buy.setName(buyer.getName());
                buy.setPassword(buyer.getPassword());
                buy.setLastname(buyer.getLastname());
                buy.setGender(buyer.getGender());
                buy.setDateOfBirth(buyer.getDateOfBirth());
                write();
                break;
            }
        }
        return;
    }

    public Buyer getById(String id) {
        for (Buyer buyer: this.getAll()) {
            if (buyer.getUsername().equalsIgnoreCase(id)) {
                return buyer;
            }
        }
        return null;
    }

    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.buyersData));
            this.buyers = gson.fromJson(reader, new TypeToken<ArrayList<Buyer>>() {}.getType());
            reader.close();
            if (buyers == null) {
                buyers = new ArrayList<>();
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
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.buyersData));
            gson.toJson(this.buyers, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
