package service;

import beans.*;
import dao.UserLoginInfo;
import dto.Credentials;
import repository.*;

import java.util.ArrayList;
import java.util.Collections;

public class UserService {

    public enum SortingParameter {NAME, LASTNAME, USERNAME};
    public enum SortingOrientation {ASC, DESC};

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

    public ArrayList<User> getAll() {
        ArrayList<User> list = new ArrayList<>();

        ArrayList<Buyer> listHelper;
        listHelper = buyerRepository.getAll();
        if(listHelper != null) {
            list.addAll(listHelper);
        }

        ArrayList<Manager> listHelper1;
        listHelper1 = managerRepository.getAll();
        if(listHelper1 != null) {
            list.addAll(listHelper1);
        }
        ArrayList<Coach> listHelper2;
        listHelper2 = coachRepository.getAll();
        if(listHelper2 != null) {
            list.addAll(listHelper2);
        }
        ArrayList<Admin> listHelper3;
        listHelper3 = adminRepository.getAll();
        if(listHelper3 != null) {
            list.addAll(listHelper3);
        }
        return list;
    }

    public ArrayList<User> search(String name, String lastname, String username, ArrayList<User> list) {
        if (!name.isEmpty()) {
            list = searchName(name, list);
        }
        if (!lastname.isEmpty()) {
            list = searchLastname(lastname, list);
        }
        if (!username.isEmpty()) {
            list = searchUsername(username, list);
        }
        return list;
    }

    public ArrayList<User> searchName(String name, ArrayList<User> list) {
        ArrayList<User> newList = new ArrayList<>();
        for (User user : list) {
            if (user.getName().contains(name)) {
                newList.add(user);
            }
        }
        return newList;
    }

    public ArrayList<User> searchLastname(String lastname, ArrayList<User> list) {
        ArrayList<User> newList = new ArrayList<>();
        for (User user : list) {
            if (user.getLastname().contains(lastname)) {
                newList.add(user);
            }
        }
        return newList;
    }

    public ArrayList<User> searchUsername(String username, ArrayList<User> list) {
        ArrayList<User> newList = new ArrayList<>();
        for (User user : list) {
            if (user.getUsername().contains(username)) {
                newList.add(user);
            }
        }
        return newList;
    }

    public ArrayList<User> sort(SortingParameter sortingParameter, SortingOrientation sortingOrientation, ArrayList<User> list) {
        switch (sortingParameter) {
            case NAME -> {
                list.sort(User::compareName);
                break;
            }
            case LASTNAME -> {
                list.sort(User::compareLastname);
                break;
            }
            case USERNAME -> {
                break;
            }
            default -> {
                list.sort(User::compareUsername);
                break;
            }
        }
        if (sortingOrientation == SortingOrientation.DESC) {
            Collections.reverse(list);
        }
        return list;
    }

    public ArrayList<User> filter(User.UserType type, ArrayList<User> list) {
        ArrayList<User> newList = new ArrayList<>();
        for (User user : list) {
            if (user.getUserType().equals(type)) {
                newList.add(user);
            }
        }
        return newList;
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
