package dto;

public class PromoCodeDTO {
    private String id;
    private String numberOfUsage;
    private String discount;

    private String startDate;
    private String day;
    private String month;
    private String year;

    private String endDate;
    private String day2;
    private String month2;
    private String year2;

    public PromoCodeDTO(String id, String numberOfUsage, String discount, String startDate, String endDate) {
        this.id = id;
        this.numberOfUsage = numberOfUsage;
        this.discount = discount;
        String[] data1 = startDate.split("-");
        this.day = data1[2];
        this.month = data1[1];
        this.year = data1[0];
        String[] data2 = endDate.split("-");
        this.day2 = data2[2];
        this.month2 = data2[1];
        this.year2 = data2[0];
    }

    public void fillDate() {
        String[] data1 = startDate.split("-");
        this.day = data1[2];
        this.month = data1[1];
        this.year = data1[0];
        String[] data2 = endDate.split("-");
        this.day2 = data2[2];
        this.month2 = data2[1];
        this.year2 = data2[0];
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumberOfUsage() {
        return numberOfUsage;
    }

    public void setNumberOfUsage(String numberOfUsage) {
        this.numberOfUsage = numberOfUsage;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getMonth2() {
        return month2;
    }

    public void setMonth2(String month2) {
        this.month2 = month2;
    }

    public String getYear2() {
        return year2;
    }

    public void setYear2(String year2) {
        this.year2 = year2;
    }
}
