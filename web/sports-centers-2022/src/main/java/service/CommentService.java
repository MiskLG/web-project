package service;

import beans.Comment;
import beans.SportsFacility;
import beans.WorkoutHistory;
import repository.CommentRepository;

import java.util.ArrayList;

public class CommentService {
    private CommentRepository commentRepository;
    private SportsFacilityService facilityService;
    private WorkoutHistoryService workoutHistoryService;

    public CommentService() {
        commentRepository = CommentRepository.init();
        facilityService = new SportsFacilityService();
        workoutHistoryService = new WorkoutHistoryService();
    }

    public ArrayList<Comment> getAllForFacilityIdApproved(String id) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (Comment comment : commentRepository.getAll()) {
            if (comment.getFacility().getId().equalsIgnoreCase(id) && comment.getApproved() == 1) {
                comments.add(comment);
            }
        }
        return comments;
    }
    public ArrayList<Comment> getAllForFacilityIdAll(String id) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (Comment comment : commentRepository.getAll()) {
            if (comment.getFacility().getId().equalsIgnoreCase(id)) {
                comments.add(comment);
            }
        }
        return comments;
    }

    public ArrayList<Comment> getUnapproved() {
        ArrayList<Comment> comments = new ArrayList<>();
        for (Comment comment : commentRepository.getAll()) {
            if(comment.getApproved() == 0) {
                comments.add(comment);
            }
        }
        return comments;
    }

    public void add(Comment comment) {
        int numberOfRatings = 0;
        for (Comment cm: commentRepository.getAll()) {
            if (cm.getFacility().getId().equals(comment.getFacility().getId())) {
                numberOfRatings += 1;
            }
        }
        SportsFacility facility = facilityService.getById(comment.getFacility().getId());
        double rating = (comment.getRating() + facility.get_rating()* numberOfRatings)/(numberOfRatings+1);
        facilityService.updateRating(rating,comment.getFacility().getId());
        this.commentRepository.add(comment);
    }


    public void approve(String facilityId, String userId) {
        Comment comment = this.getById(facilityId, userId);
        comment.setApproved(1);
        commentRepository.update(comment);
    }

    public void disapprove(String facilityId, String userId) {
        Comment comment = this.getById(facilityId, userId);
        comment.setApproved(-1);
        commentRepository.update(comment);
    }

    public Comment getById(String facilityId, String userId) {
        for (Comment comment : commentRepository.getAll()) {
            if (comment.getFacility().getId().equals(facilityId) && comment.getBuyer().getUsername().equals(userId)) {
                return comment;
            }
        }
        return null;
    }

    public ArrayList<Comment> getAll() {
        return commentRepository.getAll();
    }

    public ArrayList<SportsFacility> getUncommentedForUser(String id) {
        ArrayList<SportsFacility> sportsFacilities = new ArrayList<>();
        for (WorkoutHistory workoutHistory : workoutHistoryService.getByBuyerPast(id)) {
            if(getById(workoutHistory.getWorkout().get__facility().getId(), id) == null) {
                sportsFacilities.add(facilityService.getById(workoutHistory.getWorkout().get__facility().getId()));
            }
        }
        return sportsFacilities;
    }


}
