package beans;

import java.util.Date;

public class PromoCode {
    private String id;
    private Date startTime;
    private Date endTime;
    private int numberOfUsage;
    private double discount;

    public PromoCode(String id, Date startTime, Date endTime, int numberOfUsage, double discount) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfUsage = numberOfUsage;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getNumberOfUsage() {
        return numberOfUsage;
    }

    public void setNumberOfUsage(int numberOfUsage) {
        this.numberOfUsage = numberOfUsage;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
