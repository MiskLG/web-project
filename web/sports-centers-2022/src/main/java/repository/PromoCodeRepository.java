package repository;

import beans.PromoCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PromoCodeRepository {
    private ArrayList<PromoCode> codes;
    private static PromoCodeRepository repo = null;
    private PromoCodeRepository() {};

    public static PromoCodeRepository init() {
        if(repo == null) {
            repo = new PromoCodeRepository();
            repo.codes = new ArrayList<PromoCode>();
            repo.read();
        }
        return repo;
    }

    public ArrayList<PromoCode> getAll() {
        return this.codes;
    }

    public void add(PromoCode code) {
        codes.add(code);
        write();
    }

    public void update(PromoCode code) {
        for (PromoCode c : codes) {
            if (code.getId().equals(c.getId())) {
                c.setNumberOfUsage(code.getNumberOfUsage());
                c.setDiscount(code.getDiscount());
                break;
            }
        }
        write();
    }

    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.promoCodesData));
            this.codes = gson.fromJson(reader, new TypeToken<ArrayList<PromoCode>>() {}.getType());
            reader.close();
            if (codes == null) {
                codes = new ArrayList<>();
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
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.promoCodesData));
            gson.toJson(this.codes, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
