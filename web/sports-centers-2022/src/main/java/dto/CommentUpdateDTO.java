package dto;

public class CommentUpdateDTO {
    private String userId;
    private String facilityId;

    public CommentUpdateDTO(String userId, String facilityId) {
        this.userId = userId;
        this.facilityId = facilityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }
}
