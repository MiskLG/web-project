package beans;

import java.util.ArrayList;

public class SportsFacility {
	private String id;
	private String name;
	private String type;
	private ArrayList<String> content;
	private boolean status;
	private Location location;
	private String logo;
	private Double rating;
	private int startTime;
	private int endTime;	
	
	public SportsFacility(String id, String name, String type, ArrayList<String> content, boolean status, Location location,
			String logo, Double rating, int startTime, int endTime) {
		super();
		this.setId(id);
		this.name = name;
		this.type = type;
		this.content = content;
		this.status = status;
		this.location = location;
		this.logo = logo;
		this.rating = rating;
		this.startTime = startTime;
		this.endTime = endTime;
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
	public ArrayList<String> getContent() {
		return content;
	}
	public void setContent(ArrayList<String> content) {
		this.content = content;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*
		Compares location - first city, then street and then street number
	 */
	public int compareToLocation(SportsFacility facility) {
		int value = facility.getLocation().getAddress().getCity().compareTo(this.getLocation().getAddress().getCity());
		if ( value == 0) {
			value = facility.getLocation().getAddress().getStreet().compareTo(this.getLocation().getAddress().getStreet());
			if (value == 0) {
				value = facility.getLocation().getAddress().getStNumber().compareTo(this.getLocation().getAddress().getStNumber());
			}
		}
		return value;
	}

	public int compareToName(SportsFacility facility) {
		return facility.getName().compareTo(this.getName());
	}

	public int compareToRating(SportsFacility facility) {
		return facility.getRating().compareTo(this.getRating());
	}
}
