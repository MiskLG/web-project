package dto;

public class ScheduleDTO {
    private String userId;
    private String workoutId;
    private String date;

    public ScheduleDTO(String userId, String workoutId, String date) {
        this.userId = userId;
        this.workoutId = workoutId;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
