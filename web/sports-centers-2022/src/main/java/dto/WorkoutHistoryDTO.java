package dto;

public class WorkoutHistoryDTO {
    private String facility;
    private WorkoutDTO base;
    private String date;

    private String buyerUsername;
    private String id;

    public WorkoutHistoryDTO(String facility, WorkoutDTO base, String date, String buyerUsername,String id) {
        this.facility = facility;
        this.base = base;
        this.date = date;
        this.buyerUsername = buyerUsername;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public WorkoutDTO getBase() {
        return base;
    }

    public void setBase(WorkoutDTO base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }
}
