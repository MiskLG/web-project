package service;

import beans.Coach;
import dto.Credentials;
import repository.CoachRepository;
import repository.UserInfoRepository;

import java.util.ArrayList;

public class CoachService {

    private CoachRepository coachRepository;
    private UserInfoRepository userInfoRepository;
    private UserService userService;

    public CoachService() {
        coachRepository = CoachRepository.init();
        userInfoRepository = UserInfoRepository.init();
        userService = new UserService();
    }

    public void add(Coach coach) {
        if (userService.getUser(new Credentials(coach.getUsername(),coach.getPassword())) == null) {
            coachRepository.add(coach);
            userInfoRepository.generateAndAddUserInfo(coach);
        }
    }

    public ArrayList<Coach> getAll() {
        return coachRepository.getAll();
    }

    public Coach getById(String id) {
        for (Coach coach: coachRepository.getAll()) {
            if (coach.getUsername().equalsIgnoreCase(id)) {
                return coach;
            }
        }
        return null;
    }

    public void update(Coach coach) {
        coachRepository.update(coach);
    }
}
