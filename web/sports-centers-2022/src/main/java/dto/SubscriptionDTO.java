package dto;

public class SubscriptionDTO {

    private String id;
    private String type;
    private String price;
    private String numberOfAppointments;

    public SubscriptionDTO(String id, String type, String price, String numberOfAppointments) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.numberOfAppointments = numberOfAppointments;
    }

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
}
