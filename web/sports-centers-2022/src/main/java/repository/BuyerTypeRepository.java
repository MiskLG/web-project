package repository;

import beans.BuyerType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BuyerTypeRepository {

    private ArrayList<BuyerType> buyerTypes;

    public ArrayList<BuyerType> getBuyerTypes()
    {
        read();
        return this.buyerTypes;
    }

    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.buyerTypeData));
            this.buyerTypes = gson.fromJson(reader, new TypeToken<ArrayList<BuyerType>>() {}.getType());
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
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.buyerTypeData));
            gson.toJson(this.buyerTypes, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
