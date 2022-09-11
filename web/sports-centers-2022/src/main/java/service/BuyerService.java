package service;

import beans.Buyer;
import beans.Subscription;
import beans.WorkoutHistory;
import dto.Credentials;
import repository.BuyerRepository;
import repository.BuyerTypeRepository;
import repository.UserInfoRepository;

import java.util.ArrayList;

public class BuyerService {

    private BuyerRepository buyerRepository;
    private UserInfoRepository userInfoRepository;

    private BuyerTypeRepository buyerTypeRepository;
    private WorkoutHistoryService workoutHistoryService;
    private UserService userService;

    public BuyerService() {
        buyerRepository = BuyerRepository.init();
        userInfoRepository = UserInfoRepository.init();
        buyerTypeRepository = BuyerTypeRepository.init();
        workoutHistoryService = new WorkoutHistoryService();
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
                ArrayList<String> list = new ArrayList<>();
                for (WorkoutHistory wh : workoutHistoryService.getByBuyerPast(buyer.getUsername())) {
                    if (!buyer.getVisitedFacilities().contains(wh.getWorkout().get__facility().getId())) {
                        list.add(wh.getWorkout().get__facility().getId());
                    }
                }
                buyer.setVisitedFacilities(list);
                buyerRepository.update(buyer);
                return buyer;
            }
        }
        return null;
    }
}
