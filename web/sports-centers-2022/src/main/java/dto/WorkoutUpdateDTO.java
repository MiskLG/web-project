package dto;

public class WorkoutUpdateDTO {
    private String id;
    private String type;
    private String duration;
    private String description;
    private String coach;

    public WorkoutUpdateDTO(String id, String type, String duration, String description, String coach) {
        this.id = id;
        this.type = type;
        this.duration = duration;
        this.description = description;
        this.coach = coach;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}
