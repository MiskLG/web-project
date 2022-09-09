package dto;

public class WorkoutDTO {
    private String id;
    private String name;
    private String type;
    private String duration;
    private String coachUsername;
    private String coachName;
    private String coachLastname;
    private String description;
    private String photo;

    public WorkoutDTO(String id, String name, String type, String duration, String coachUsername, String coachName, String coachLastname, String description, String photo) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.coachUsername = coachUsername;
        this.coachName = coachName;
        this.coachLastname = coachLastname;
        this.description = description;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCoachUsername() {
        return coachUsername;
    }

    public void setCoachUsername(String coachUsername) {
        this.coachUsername = coachUsername;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachLastname() {
        return coachLastname;
    }

    public void setCoachLastname(String coachLastname) {
        this.coachLastname = coachLastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
