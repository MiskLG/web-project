package service;

import beans.SportsFacility;
import repository.SportsFacilityRepository;

public class SportsFacilityService {
    private SportsFacilityRepository facilityRepository;

    public SportsFacilityService() {
        facilityRepository = SportsFacilityRepository.init();
    }

    public void add(SportsFacility sportsFacility) {
        facilityRepository.add(sportsFacility);
    }

    public SportsFacility getById(String id) {
        for (SportsFacility sportsFacility: facilityRepository.getAll()) {
            if (sportsFacility.getId().equals(id)) {
                return sportsFacility;
            }
        }
        return null;
    }

    public void update(SportsFacility sportsFacility) {
        facilityRepository.update(sportsFacility);
    }
}
