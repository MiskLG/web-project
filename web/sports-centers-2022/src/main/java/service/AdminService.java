package service;

import beans.Admin;
import repository.AdminRepository;
import repository.UserInfoRepository;

public class AdminService {
    private AdminRepository adminRepository;
    private UserInfoRepository userInfoRepository;

    public AdminService() {
        adminRepository = AdminRepository.init();
        userInfoRepository = UserInfoRepository.init();
    }

    public void add(Admin admin) {
        adminRepository.add(admin);
        userInfoRepository.generateAndAddUserInfo(admin);
    }

    public Admin getById(String id) {
        for (Admin admin: adminRepository.getAll()) {
            if (admin.getUsername().equalsIgnoreCase(id)) {
                return admin;
            }
        }
        return null;
    }
}
