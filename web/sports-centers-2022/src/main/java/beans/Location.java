package beans;

public class Location {
	private Double latitude;
	private Double longitude;
	private Address address;
	
	
	public Location(Double latitude, Double longitude, Address address) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}
	public Location(Double latitude, Double longitude, String street, String stNumber, String city, String poNumber) {
		this(latitude, longitude, new Address(street, stNumber, city, poNumber));
	}
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
