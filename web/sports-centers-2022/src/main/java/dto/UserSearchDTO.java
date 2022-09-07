package dto;

public class UserSearchDTO {
    private String name;
    private String lastname;
    private String username;
    private String filterType;
    private String sortParameter;
    private String sortOrientation;

    public UserSearchDTO(String name, String lastname, String username, String filterType, String sortParameter, String sortOrientation) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.filterType = filterType;
        this.sortParameter = sortParameter;
        this.sortOrientation = sortOrientation;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getSortParameter() {
        return sortParameter;
    }

    public void setSortParameter(String sortParameter) {
        this.sortParameter = sortParameter;
    }

    public String getSortOrientation() {
        return sortOrientation;
    }

    public void setSortOrientation(String sortOrientation) {
        this.sortOrientation = sortOrientation;
    }
}
