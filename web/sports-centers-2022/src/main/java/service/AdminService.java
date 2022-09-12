package service;

import beans.Admin;
import dto.Credentials;
import repository.AdminRepository;
import repository.UserInfoRepository;

public class AdminService {
    private AdminRepository adminRepository;
    private UserInfoRepository userInfoRepository;
    private UserService userService;

    public AdminService() {
        adminRepository = AdminRepository.init();
        userInfoRepository = UserInfoRepository.init();
    }

    public void add(Admin admin) {
        if (userService.getUser(new Credentials(admin.getUsername(),admin.getPassword())) == null) {
            adminRepository.add(admin);
            userInfoRepository.generateAndAddUserInfo(admin);
        }
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
