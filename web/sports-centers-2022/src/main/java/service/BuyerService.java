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
        if(buyer.getSubscriptionId() != null) {
            // LEFT FOR TESTING
            buyer.setPoints(buyer.getPoints() + buyer.getSubscriptionId().getPrice()/1000 * 100);
        }
        System.out.println(buyer.getPoints());
        buyer.setSubscriptionId(subscription);
        buyer.setBuyerType(buyerTypeRepository.getBuyerType(buyer.getPoints()));
        buyerRepository.update(buyer);
    }
    public void add(Buyer buyer) {
        if (userService.getUser(new Credentials(buyer.getUsername(),buyer.getPassword())) == null) {
            buyer.setBuyerType(buyerTypeRepository.getBuyerType(buyer.getPoints()));
            buyerRepository.add(buyer);
            userInfoRepository.generateAndAddUserInfo(buyer);
        }
    }

    public boolean useAppointment(Buyer buyer) {
        if (buyer.getSubscriptionId().getNumberOfAppointments() > 0) {
            buyer.getSubscriptionId().setNumberOfAppointments(buyer.getSubscriptionId().getNumberOfAppointments() - 1);
            buyerRepository.update(buyer);
            return true;
        }
        return false;
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

    public void giveAppointment(String username) {
        Buyer buyer = this.getById(username);
        buyer.getSubscriptionId().setNumberOfAppointments(buyer.getSubscriptionId().getNumberOfAppointments() + 1);
        buyerRepository.update(buyer);
    }
}
