package service;

import beans.*;
import dao.UserLoginInfo;
import dto.Credentials;
import repository.*;

public class UserService {

    private UserInfoRepository userInfoRepository;
    private BuyerRepository buyerRepository;
    private AdminRepository adminRepository;
    private CoachRepository coachRepository;
    private ManagerRepository managerRepository;
    public UserService() {
        userInfoRepository = UserInfoRepository.init();
        buyerRepository = BuyerRepository.init();
        adminRepository = AdminRepository.init();
        coachRepository = CoachRepository.init();
        managerRepository = ManagerRepository.init();
    }

    public User getUser(Credentials credentials) {
        User user = null;
        UserLoginInfo userLoginInfo = findUserInfo(credentials);
        if (userLoginInfo != null) {
            user = getUserBasedOnInfo(userLoginInfo);
        }
        return user;
    }

    private User getUserBasedOnInfo(UserLoginInfo info) {
        switch (info.getType()) {
            case ADMIN -> {
                return findAdmin(info);
            }
            case BUYER -> {
                return findBuyer(info);
            }
            case COACH -> {
                return findCoach(info);
            }
            case MANAGER -> {
                return findManager(info);
            }
            default -> {
                return null;
            }
        }
    }

    private User findManager(UserLoginInfo info) {
        for (Manager manager: managerRepository.getAll()) {
            if (info.getUsername().equalsIgnoreCase(manager.getUsername()) && info.getPassword().equals(manager.getPassword())) {
                return manager;
            }
        }
        return null;
    }

    private User findCoach(UserLoginInfo info) {
        for (Coach coach: coachRepository.getAll()) {
            if (info.getUsername().equalsIgnoreCase(coach.getUsername()) && info.getPassword().equals(coach.getPassword())) {
                return coach;
            }
        }
        return null;
    }

    private User findBuyer(UserLoginInfo info) {
        for (Buyer buyer: buyerRepository.getAll()) {
            if (info.getUsername().equalsIgnoreCase(buyer.getUsername()) && info.getPassword().equals(buyer.getPassword())) {
                return buyer;
            }
        }
        return null;
    }

    private User findAdmin(UserLoginInfo info) {
        for (Admin admin: adminRepository.getAll()) {
            if (info.getUsername().equalsIgnoreCase(admin.getUsername()) && info.getPassword().equals(admin.getPassword())) {
                return admin;
            }
        }
        return null;
    }

    public UserLoginInfo findUserInfo(Credentials credentials) {
        for (UserLoginInfo info: userInfoRepository.getInfo()) {
            if (info.getUsername().equalsIgnoreCase(credentials.getUsername()) && info.getPassword().equals(credentials.getPassword()))
            {
                return info;
            }
        }
        return null;
    }
}
