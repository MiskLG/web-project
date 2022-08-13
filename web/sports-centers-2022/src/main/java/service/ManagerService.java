package service;

import beans.Manager;
import repository.ManagerRepository;
import repository.UserInfoRepository;

public class ManagerService {
    private ManagerRepository managerRepository;
    private UserInfoRepository userInfoRepository;

    public ManagerService() {
        managerRepository = ManagerRepository.init();
        userInfoRepository = UserInfoRepository.init();
    }

    public void add(Manager manager) {
        managerRepository.add(manager);
        userInfoRepository.generateAndAddUserInfo(manager);
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
