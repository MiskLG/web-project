package beans;

import java.util.ArrayList;

public class SportsFacility {

	private String id;
	private String _name;
	private String _type;

	private volatile ArrayList<Workout> _content;
	private boolean _status;
	private Location _location;
	private String _logo;
	private Double _rating;
	private int _startTime;
	private int _endTime;
	
	public SportsFacility(String id, String _name, String _type, ArrayList<Workout> _content, boolean _status, Location _location,
						  String _logo, Double _rating, int _startTime, int _endTime) {
		super();
		this.setId(id);
		this._name = _name;
		this._type = _type;
		this._content = _content;
		this._status = _status;
		this._location = _location;
		this._logo = _logo;
		this._rating = _rating;
		this._startTime = _startTime;
		this._endTime = _endTime;
	}
	
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public ArrayList<Workout> get_content() {
		return _content;
	}
	public void set_content(ArrayList<Workout> _content) {
		this._content = _content;
	}
	public boolean is_status() {
		return _status;
	}
	public void set_status(boolean _status) {
		this._status = _status;
	}
	public Location get_location() {
		return _location;
	}
	public void set_location(Location _location) {
		this._location = _location;
	}
	public String get_logo() {
		return _logo;
	}
	public void set_logo(String _logo) {
		this._logo = _logo;
	}
	public Double get_rating() {
		return _rating;
	}
	public void set_rating(Double _rating) {
		this._rating = _rating;
	}
	public int get_startTime() {
		return _startTime;
	}
	public void set_startTime(int _startTime) {
		this._startTime = _startTime;
	}
	public int get_endTime() {
		return _endTime;
	}
	public void set_endTime(int _endTime) {
		this._endTime = _endTime;
	}
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public void addContent(Workout workout) {
		if(this._content == null) {
			this._content = new ArrayList<>();
		}
		this._content.add(workout);
	}

	/*
		Compares location - first city, then street and then street number
	 */
	public int compareToLocation(SportsFacility facility) {
		int value = facility.get_location().getAddress().getCity().compareTo(this.get_location().getAddress().getCity());
		if ( value == 0) {
			value = facility.get_location().getAddress().getStreet().compareTo(this.get_location().getAddress().getStreet());
			if (value == 0) {
				value = facility.get_location().getAddress().getStNumber().compareTo(this.get_location().getAddress().getStNumber());
			}
		}
		return value;
	}

	public int compareToName(SportsFacility facility) {
		return facility.get_name().compareTo(this.get_name());
	}

	public int compareToRating(SportsFacility facility) {
		return facility.get_rating().compareTo(this.get_rating());
	}
}
