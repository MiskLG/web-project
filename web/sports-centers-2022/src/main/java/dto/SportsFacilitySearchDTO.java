package dto;

public class SportsFacilitySearchDTO {
    private String name;
    private String city;
    private String type;
    private String rating;
    private String sortParameter;
    private String sortOrientation;
    private String filterType;
    private String filterStatus;

    public SportsFacilitySearchDTO(String name, String city, String type, String rating, String sortParameter, String sortOrientation, String filterType, String filterStatus) {
        this.name = name;
        this.city = city;
        this.type = type;
        this.rating = rating;
        this.sortParameter = sortParameter;
        this.sortOrientation = sortOrientation;
        this.filterType = filterType;
        this.filterStatus = filterStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterStatus() {
        return filterStatus;
    }

    public void setFilterStatus(String filterStatus) {
        this.filterStatus = filterStatus;
    }
}
