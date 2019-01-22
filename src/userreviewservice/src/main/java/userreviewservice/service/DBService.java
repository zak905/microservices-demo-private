package userreviewservice.service;

import userreviewservice.model.UserReview;

import java.util.List;

public interface DBService {

    public final String USER_REVIEW_ENTITY_PREFIX = "ur_" ;

    UserReview getUserReview(String id);

    List<UserReview> getUserReviewList(String productId);

    UserReview createReview(UserReview userReview);
}
