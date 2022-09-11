package dto;

public class BuyerSubscribeDTO {
    private SubscriptionExpandedDTO sub;
    private String username;

    public BuyerSubscribeDTO(SubscriptionExpandedDTO sub, String username) {
        this.sub = sub;
        this.username = username;
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
}
