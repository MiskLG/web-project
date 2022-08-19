package service;

import beans.SportsFacility;
import repository.SportsFacilityRepository;

import java.util.ArrayList;
import java.util.Collections;

public class SportsFacilityService {
    private SportsFacilityRepository facilityRepository;
    public enum SortingParameter {NAME, LOCATION, RATING};
    public enum SortingOrientation {ASC, DESC};

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

    public ArrayList<SportsFacility> getAll() {
        return facilityRepository.getAll();
    }

    public ArrayList<SportsFacility> filter(String type, boolean isOpenFilter) {
        ArrayList<SportsFacility> list = this.getAll();
        if (!type.isEmpty() && type != null) {
            list = filterType(list, type);
        }
        if (true == isOpenFilter) {
            list = filterOpen(list);
        }
        return list;
    }

    public ArrayList<SportsFacility> filterType(ArrayList<SportsFacility> list, String type) {
        ArrayList<SportsFacility> newList = new ArrayList<>();
        for (SportsFacility facility : list) {
            if (facility.getType().equalsIgnoreCase(type)) {
                newList.add(facility);
            }
        }
        return newList;
    }

    public ArrayList<SportsFacility> filterOpen(ArrayList<SportsFacility> list) {
        ArrayList<SportsFacility> newList = new ArrayList<>();
        for (SportsFacility facility : list) {
            if (java.time.LocalTime.now().compareTo(java.time.LocalTime.of(facility.getStartTime(), 0)) < 0 && java.time.LocalTime.now().compareTo(java.time.LocalTime.of(facility.getEndTime(), 0)) > 0) {
                list.add(facility);
            }
        }
        return newList;
    }

    public ArrayList<SportsFacility> sort(SortingParameter parameter, SortingOrientation orientation, ArrayList<SportsFacility> facilities) {
        switch (parameter) {
            case NAME -> {
                facilities.sort(SportsFacility::compareToName);
                break;
            }
            case RATING -> {
                facilities.sort(SportsFacility::compareToRating);
                break;
            }
            case LOCATION -> {
                facilities.sort(SportsFacility::compareToLocation);
                break;
            }
        }
        if (orientation == SortingOrientation.DESC) {
            Collections.reverse(facilities);
        }
        return facilities;
    }

    public void update(SportsFacility sportsFacility) {
        facilityRepository.update(sportsFacility);
    }
}
