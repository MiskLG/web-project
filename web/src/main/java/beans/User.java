package beans;

import java.util.Date;

public abstract class User {
	
	public enum GenderType{MALE, FEMALE}
	public enum UserType{ ADMIN, MANAGER, TRAINER, BUYER}
	
	private String username;
	private String name;
	private String lastname;
	private String password;
	private GenderType gender;
	private Date dateOfBirth;
	private UserType userType;
}
