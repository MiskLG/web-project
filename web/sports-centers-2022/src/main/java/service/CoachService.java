package service;

import beans.Coach;
import repository.CoachRepository;
import repository.UserInfoRepository;

public class CoachService {

    private CoachRepository coachRepository;
    private UserInfoRepository userInfoRepository;

    public CoachService() {
        coachRepository = CoachRepository.init();
        userInfoRepository = UserInfoRepository.init();
    }

    public void add(Coach coach) {
        coachRepository.add(coach);
        userInfoRepository.generateAndAddUserInfo(coach);
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