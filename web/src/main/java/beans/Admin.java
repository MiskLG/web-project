package beans;

import java.util.Date;

public class Admin extends User{

	public Admin(String username, String name, String lastname, String password, GenderType gender, Date dateOfBirth) {
		super(username, name, lastname, password, gender, dateOfBirth, UserType.ADMIN);
	}

}
