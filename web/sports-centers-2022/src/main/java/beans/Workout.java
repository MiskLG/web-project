package beans;

public class Workout {
	private String id;
	private String name;
	private String type;
	private Double duration;
	private SportsFacility __facility;
	private Coach coach;
	private String description;
	private String photo;
	
	public Workout(String id, String name, String type, Double duration, SportsFacility __facility, Coach coach, String description,
				   String photo) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.duration = duration;
		this.__facility = __facility;
		this.coach = coach;
		this.description = description;
		this.photo = photo;
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
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public SportsFacility get__facility() {
		return __facility;
	}
	public void set__facility(SportsFacility __facility) {
		this.__facility = __facility;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
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
	public void setId(String id) { this.id = id; }
	public String getId() { return id; }

}
