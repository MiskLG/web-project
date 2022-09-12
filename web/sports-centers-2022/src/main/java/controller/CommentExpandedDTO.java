package controller;

public class CommentExpandedDTO {
    private String user;
    private String userName;
    private String facility;
    private String text;
    private String rating;

    public CommentExpandedDTO(String user, String userName, String facility, String text, String rating) {
        this.user = user;
        this.userName = userName;
        this.facility = facility;
        this.text = text;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
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
