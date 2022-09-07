package beans;

import java.util.Date;

public class Manager extends User{
	private SportsFacility facility;

	public Manager(String username, String name, String lastname, String password, GenderType gender, Date dateOfBirth,
			SportsFacility facility) {
		super(username, name, lastname, password, gender, dateOfBirth, UserType.MANAGER);
		this.facility = facility;
	}

	public Manager(String username, String name, String lastname, String password, GenderType gender, Date dateOfBirth) {
		super(username, name, lastname, password, gender, dateOfBirth, UserType.MANAGER);
	}

	public SportsFacility getFacility() {
		return facility;
	}

	public void setFacility(SportsFacility facility) {
		this.facility = facility;
	}
	
}
