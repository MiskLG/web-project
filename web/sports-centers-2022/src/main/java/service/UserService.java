package service;

import beans.*;
import dao.UserLoginInfo;
import dto.Credentials;
import repository.UserRepository;

public class UserService {

    private UserRepository userRepository;
    public UserService() {
        userRepository = UserRepository.init();
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
        for (Manager manager: userRepository.getManagers()) {
            if (info.getUsername().equalsIgnoreCase(manager.getUsername()) && info.getPassword().equals(manager.getPassword())) {
                return manager;
            }
        }
        return null;
    }

    private User findCoach(UserLoginInfo info) {
        for (Coach coach: userRepository.getCoaches()) {
            if (info.getUsername().equalsIgnoreCase(coach.getUsername()) && info.getPassword().equals(coach.getPassword())) {
                return coach;
            }
        }
        return null;
    }

    private User findBuyer(UserLoginInfo info) {
        for (Buyer buyer: userRepository.getBuyers()) {
            if (info.getUsername().equalsIgnoreCase(buyer.getUsername()) && info.getPassword().equals(buyer.getPassword())) {
                return buyer;
            }
        }
        return null;
    }

    private User findAdmin(UserLoginInfo info) {
        for (Admin admin: userRepository.getAdmins()) {
            if (info.getUsername().equalsIgnoreCase(admin.getUsername()) && info.getPassword().equals(admin.getPassword())) {
                return admin;
            }
        }
        return null;
    }

    public UserLoginInfo findUserInfo(Credentials credentials) {
        for (UserLoginInfo info: userRepository.getInfo()) {
            if (info.getUsername().equalsIgnoreCase(credentials.getUsername()) && info.getPassword().equals(credentials.getPassword()))
            {
                return info;
            }
        }
        return null;
    }

    public void addUser(User user) {
        switch (user.getUserType()) {
            case MANAGER -> {
                userRepository.add((Manager) user);
            }
            case COACH -> {
                userRepository.add((Coach) user);
            }
            case BUYER -> {
                userRepository.add((Buyer) user);
            }
            case ADMIN -> {
                userRepository.add((Admin) user);
            }
        }
        return;
    }
}
