package service;

import beans.Buyer;
import beans.Subscription;
import repository.BuyerRepository;
import repository.UserInfoRepository;

public class BuyerService {

    private BuyerRepository buyerRepository;
    private UserInfoRepository userInfoRepository;

    public BuyerService() {
        buyerRepository = BuyerRepository.init();
        userInfoRepository = UserInfoRepository.init();
    }

    public void addSubscription(Buyer buyer, Subscription subscription) {
        buyer.setSubscriptionId(subscription);
        buyerRepository.update(buyer);
    }
    public void add(Buyer buyer) {
        buyerRepository.add(buyer);
        userInfoRepository.generateAndAddUserInfo(buyer);
    }

    public Buyer getById(String id) {
        for (Buyer buyer: buyerRepository.getAll()) {
            if (buyer.getUsername().equalsIgnoreCase(id)) {
                return buyer;
            }
        }
        return null;
    }
}
