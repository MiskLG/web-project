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
        }
        return repo;
    }

    public ArrayList<Buyer> getBuyers() {
        read();
        return this.buyers;
    }

    public void add(Buyer buyer) {
        buyers.add(buyer);
        write();
    }

    public void read() {
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
