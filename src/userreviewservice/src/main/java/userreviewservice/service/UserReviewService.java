package userreviewservice.service;

import userreviewservice.model.UserReview;

import java.util.List;

public interface UserReviewService {

    List<UserReview> getUserReviews(String productId);

    UserReview getUserReview(String id);

    UserReview addUserReview(UserReview userReview);


}
