package repository;

import beans.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dao.UserLoginInfo;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class UserInfoRepository {

    private ArrayList<UserLoginInfo> userLoginInfo;
    private static UserInfoRepository repo = null;

    private UserInfoRepository() {};

    public static UserInfoRepository init() {
        if(repo == null) {
            repo = new UserInfoRepository();
            repo.userLoginInfo = new ArrayList<UserLoginInfo>();
            repo.read();
        }
        return repo;
    }

    public ArrayList<UserLoginInfo> getInfo() {
        return this.userLoginInfo;
    }

    public void generateAndAddUserInfo(User user) {
        userLoginInfo.add(new UserLoginInfo(user.getUsername(), user.getPassword(), user.getUserType()));
        write();
    }
    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.userCredentials));
            this.userLoginInfo = gson.fromJson(reader, new TypeToken<ArrayList<UserLoginInfo>>() {}.getType());
            reader.close();
            if (userLoginInfo == null) {
                userLoginInfo = new ArrayList<>();
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
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.userCredentials));
            gson.toJson(this.userLoginInfo, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };




}
