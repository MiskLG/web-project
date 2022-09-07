package repository;

import beans.Comment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import util.FileNames;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CommentRepository {
    private ArrayList<Comment> comments;

    private static CommentRepository repo = null;
    private CommentRepository() {};

    public ArrayList<Comment> getAll() {
        read();
        return this.comments;
    }

    public void add(Comment comment) {
        comments.add(comment);
        write();
    }

    public void update(Comment comment) {
        for (Comment com: getAll()) {
            if (com.getFacility().getId().equals(comment.getFacility().getId()) && com.getBuyer().getUsername().equals(comment.getBuyer().getUsername())) {
                com.setText(comment.getText());
                com.setRating(comment.getRating());
                write();
                break;
            }
        }
        return;
    }

    public static CommentRepository init() {
        if(repo == null) {
            repo = new CommentRepository();
            repo.comments = new ArrayList<Comment>();
            repo.read();
        }
        return repo;
    }

    public void read() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(FileNames.commentsData));
            this.comments = gson.fromJson(reader, new TypeToken<ArrayList<Comment>>() {}.getType());
            reader.close();
            if (comments == null) {
                comments = new ArrayList<>();
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
            Writer writer = Files.newBufferedWriter(Paths.get(FileNames.commentsData));
            gson.toJson(this.comments, writer);
            writer.flush();
            writer.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    };
}
