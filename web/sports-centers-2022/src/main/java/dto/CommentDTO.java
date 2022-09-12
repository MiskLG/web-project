package dto;

public class CommentDTO {
    private String user;
    private String text;
    private String rating;

    public CommentDTO(String user, String text, String rating) {
        this.user = user;
        this.text = text;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
