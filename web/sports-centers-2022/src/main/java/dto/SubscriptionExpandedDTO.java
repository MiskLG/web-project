package dto;

public class SubscriptionExpandedDTO {
    private String id;
    private String type;
    private String price;
    private String numberOfAppointments;
    private String date1;
    private String date2;
    private String status;

    public SubscriptionExpandedDTO(String id, String type, String price, String numberOfAppointments, String date1, String date2, String status) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.numberOfAppointments = numberOfAppointments;
        this.date1 = date1;
        this.date2 = date2;
        this.status = status;
    }

    public SubscriptionExpandedDTO() {};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumberOfAppointments() {
        return numberOfAppointments;
    }

    public void setNumberOfAppointments(String numberOfAppointments) {
        this.numberOfAppointments = numberOfAppointments;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
