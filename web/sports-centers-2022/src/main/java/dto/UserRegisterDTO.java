package dto;

public class UserRegisterDTO {
    private String username;
    private String name;
    private String lastname;
    private String password;
    private String gender;

    private String date;
    private String day;
    private String month;
    private String year;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String username, String name, String lastname, String password, String gender, String date) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.gender = gender;
        this.date = date;
        String[] data = date.split("-");
        this.day = data[2];
        this.month = data[1];
        this.year = data[0];
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void fillDate() {
        String[] data = date.split("-");
        this.day = data[2];
        this.month = data[1];
        this.year = data[0];
    }

    public String getDate() {
        return date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

}
