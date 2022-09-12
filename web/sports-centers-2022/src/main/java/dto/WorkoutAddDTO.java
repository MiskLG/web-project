package dto;

public class WorkoutAddDTO {
    private String name;
    private String type;
    private String duration;
    private String description;
    private String image;
    private String coach;
    private String manager;

    public WorkoutAddDTO(String name, String type, String duration, String description, String image, String coach, String manager) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.description = description;
        this.image = image;
        this.coach = coach;
        this.manager = manager;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
