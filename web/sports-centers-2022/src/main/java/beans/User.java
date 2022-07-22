package beans;

import java.util.Date;

public abstract class User {
	
	public enum GenderType{MALE, FEMALE}
	public enum UserType{ ADMIN, MANAGER, COACH, BUYER}
	
	protected String username;
	protected String name;
	protected String lastname;
	protected String password;
	protected GenderType gender;
	protected Date dateOfBirth;
	protected UserType userType;
	
	public User(String username, String name, String lastname, String password, GenderType gender, Date dateOfBirth,
			UserType userType) {
		super();
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.password = password;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
}
