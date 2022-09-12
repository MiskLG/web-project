package dto;

public class BuyerSubscribeDTO {
    private SubscriptionExpandedDTO sub;
    private String username;

    private String discountCode;

    public BuyerSubscribeDTO(SubscriptionExpandedDTO sub, String username, String discountCode) {
        this.sub = sub;
        this.username = username;
        this.discountCode = discountCode;
    }

    public SubscriptionExpandedDTO getSub() {
        return sub;
    }

    public void setSub(SubscriptionExpandedDTO sub) {
        this.sub = sub;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
