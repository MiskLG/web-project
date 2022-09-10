package service;

import beans.Buyer;
import beans.Subscription;
import dto.Credentials;
import repository.BuyerRepository;
import repository.BuyerTypeRepository;
import repository.UserInfoRepository;

public class BuyerService {

    private BuyerRepository buyerRepository;
    private UserInfoRepository userInfoRepository;

    private BuyerTypeRepository buyerTypeRepository;
    private UserService userService;

    public BuyerService() {
        buyerRepository = BuyerRepository.init();
        userInfoRepository = UserInfoRepository.init();
        buyerTypeRepository = BuyerTypeRepository.init();
        userService = new UserService();
    }

    public void addSubscription(Buyer buyer, Subscription subscription) {
        buyer.setSubscriptionId(subscription);
        buyerRepository.update(buyer);
    }
    public void add(Buyer buyer) {
        if (userService.getUser(new Credentials(buyer.getUsername(),buyer.getPassword())) == null) {
            buyer.setBuyerType(buyerTypeRepository.getBuyerType(buyer.getPoints()));
            buyerRepository.add(buyer);
            userInfoRepository.generateAndAddUserInfo(buyer);
        }
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
