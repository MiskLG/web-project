package dto;

public class ManagerInfoDTO {
    private String username;
    private String name;
    private String lastsname;

    public ManagerInfoDTO(String username, String name, String lastsname) {
        this.username = username;
        this.name = name;
        this.lastsname = lastsname;
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

    public String getLastsname() {
        return lastsname;
    }

    public void setLastsname(String lastsname) {
        this.lastsname = lastsname;
    }
}
