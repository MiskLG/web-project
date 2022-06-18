package beans;

import java.util.Date;

public class Manager extends User{
	private SportsFacility facility;

	public Manager(String username, String name, String lastname, String password, GenderType gender, Date dateOfBirth,
			UserType userType, SportsFacility facility) {
		super(username, name, lastname, password, gender, dateOfBirth, userType);
		this.facility = facility;
	}

	public SportsFacility getFacility() {
		return facility;
	}

	public void setFacility(SportsFacility facility) {
		this.facility = facility;
	}
	
}
