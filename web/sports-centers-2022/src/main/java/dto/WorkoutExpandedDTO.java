package dto;

public class WorkoutExpandedDTO {
    private String facility;
    private WorkoutDTO base;

    public WorkoutExpandedDTO(String facility, WorkoutDTO base) {
        this.facility = facility;
        this.base = base;
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
}
