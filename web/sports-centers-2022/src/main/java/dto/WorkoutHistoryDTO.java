package dto;

public class WorkoutHistoryDTO {
    private String facility;
    private WorkoutDTO base;
    private String date;

    public WorkoutHistoryDTO(String facility, WorkoutDTO base, String date) {
        this.facility = facility;
        this.base = base;
        this.date = date;
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
}
