package beans;

import java.util.Date;

public abstract class User {
	
	public enum GenderType{MALE, FEMALE}
	public enum UserType{ ADMIN, MANAGER, TRAINER, BUYER}
	
	protected String username;
	protected String name;
	protected String lastname;
	protected String password;
	protected GenderType gender;
	protected Date dateOfBirth;
	protected UserType userType;
}
