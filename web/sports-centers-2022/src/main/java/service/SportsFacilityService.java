package service;

import beans.SportsFacility;
import beans.Workout;
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

    public void addContent(SportsFacility facility, Workout workout) {
        facility.addContent(workout);
        facilityRepository.update(facility);
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
        ArrayList<SportsFacility> newList = new ArrayList<>();
        for (SportsFacility facility : facilityRepository.getAll()) {
            if (java.time.LocalTime.now().compareTo(java.time.LocalTime.of(facility.getStartTime(), 0)) < 0 && java.time.LocalTime.now().compareTo(java.time.LocalTime.of(facility.getEndTime(), 0)) > 0) {
                facility.setStatus(true);
            }
            else {
                facility.setStatus(false);
            }
            newList.add(facility);
        }
        return newList;
    }

    public ArrayList<String> getAllTypes() {
        ArrayList<String> list = new ArrayList<>();
        for (SportsFacility facility : this.getAll()) {
           if ( !list.contains(facility.getType()) ) {
               list.add(facility.getType());
           }
        }
        return list;
    }

    public ArrayList<SportsFacility> search(String name, String type, String city, double averageRating, ArrayList<SportsFacility> list) {
        if (!name.isEmpty()) {
            list = searchName(name, list);
        }
        if (!type.isEmpty()) {
            list = searchType(type, list);
        }
        if (!city.isEmpty()) {
            list = searchCity(city, list);
        }
        if (averageRating != -1) {
            searchRating(averageRating,list);
        }
        return list;
    }

    public ArrayList<SportsFacility> searchName(String name, ArrayList<SportsFacility> list) {
        ArrayList<SportsFacility> newList = new ArrayList<>();
        for (SportsFacility facility : list) {
            if (facility.getName().contains(name)) {
                newList.add(facility);
            }
        }
        return newList;
    }
    public ArrayList<SportsFacility> searchCity(String city, ArrayList<SportsFacility> list) {
        ArrayList<SportsFacility> newList = new ArrayList<>();
        for (SportsFacility facility : list) {
            if (facility.getLocation().getAddress().getCity().contains(city)) {
                newList.add(facility);
            }
        }
        return newList;
    }
    public ArrayList<SportsFacility> searchType(String type, ArrayList<SportsFacility> list) {
        ArrayList<SportsFacility> newList = new ArrayList<>();
        for (SportsFacility facility : list) {
            if (facility.getType().contains(type)) {
                newList.add(facility);
            }
        }
        return newList;
    }

    public ArrayList<SportsFacility> searchRating(double rating, ArrayList<SportsFacility> list) {
        ArrayList<SportsFacility> newList = new ArrayList<>();
        for (SportsFacility facility : list) {
            if (facility.getRating() > rating) {
                newList.add(facility);
            }
        }
        return newList;
    }
    public ArrayList<SportsFacility> filter(String type, boolean isOpenFilter, ArrayList<SportsFacility> list) {
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
                newList.add(facility);
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
