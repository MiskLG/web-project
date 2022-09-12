package service;

import beans.Comment;
import beans.SportsFacility;
import repository.CommentRepository;

import java.util.ArrayList;

public class CommentService {
    private CommentRepository commentRepository;
    private SportsFacilityService facilityService;

    public CommentService() {
        commentRepository = CommentRepository.init();
        facilityService = new SportsFacilityService();
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
        double rating = (comment.getRating() + facility.get_rating()* numberOfRatings)/numberOfRatings+1;
        facilityService.updateRating(rating,comment.getFacility().getId());
        this.commentRepository.add(comment);
    }


    public void approve(String id) {
        Comment comment = this.getById(id);
        comment.setApproved(1);
        commentRepository.update(comment);
    }

    public void disapprove(String id) {
        Comment comment = this.getById(id);
        comment.setApproved(-1);
        commentRepository.update(comment);
    }

    public Comment getById(String id) {
        for (Comment comment : commentRepository.getAll()) {
            if (comment.getFacility().getId().equalsIgnoreCase(id)) {
                return comment;
            }
        }
        return null;
    }
}
