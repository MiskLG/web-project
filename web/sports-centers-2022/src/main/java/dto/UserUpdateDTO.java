package dto;

public class UserUpdateDTO {
    private String username;
    private String name;
    private String lastname;
    private String password;
    private String password2;
    private String type;

    public UserUpdateDTO(String username, String name, String lastname, String password, String password2, String type) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.password2 = password2;
        this.type = type;
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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
