package service;

import beans.Manager;
import dto.Credentials;
import repository.ManagerRepository;
import repository.UserInfoRepository;

import java.util.ArrayList;

public class ManagerService {
    private ManagerRepository managerRepository;
    private UserInfoRepository userInfoRepository;
    private UserService userService;

    public ManagerService() {
        managerRepository = ManagerRepository.init();
        userInfoRepository = UserInfoRepository.init();
        userService = new UserService();
    }

    public void add(Manager manager) {
        if (userService.getUser(new Credentials(manager.getUsername(),manager.getPassword())) == null) {
            managerRepository.add(manager);
            userInfoRepository.generateAndAddUserInfo(manager);
        }
    }

    public ArrayList<Manager> getAll() {
        return managerRepository.getAll();
    }

    public ArrayList<Manager> getFree() {
        ArrayList<Manager> list = this.getAll();
        ArrayList<Manager> newList = new ArrayList<>();
        if(list == null) {
            return null;
        }
        for (Manager manager: list) {
            if(manager.getFacility() == null) {
                newList.add(manager);
            }
        }
        return newList;
    }

    public Manager getById(String id) {
        for (Manager manager: managerRepository.getAll()) {
            if (manager.getUsername().equalsIgnoreCase(id)) {
                return manager;
            }
        }
        return null;
    }
}
