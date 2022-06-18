package beans;

public class Address {
	private String street;
	private String stNumber;
	private String city;
	private String poNumber;
	
	public Address(String street, String stNumber, String city, String poNumber) {
		super();
		this.street = street;
		this.stNumber = stNumber;
		this.city = city;
		this.poNumber = poNumber;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStNumber() {
		return stNumber;
	}
	public void setStNumber(String stNumber) {
		this.stNumber = stNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
	
}
