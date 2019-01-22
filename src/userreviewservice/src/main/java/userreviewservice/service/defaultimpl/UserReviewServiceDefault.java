package userreviewservice.service.defaultimpl;

import userreviewservice.model.UserReview;
import userreviewservice.service.DBService;
import userreviewservice.service.UserReviewService;

import javax.inject.Inject;
import java.util.List;

public class UserReviewServiceDefault implements UserReviewService {
    private final DBService dbService;

    @Inject
    public UserReviewServiceDefault(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public List<UserReview> getUserReviews(String productId) {
        return dbService.getUserReviewList(productId);
    }

    @Override
    public UserReview getUserReview(String id) {
        return dbService.getUserReview(id);
    }

    @Override
    public UserReview addUserReview(UserReview userReview) {
        return dbService.createReview(userReview);
    }
}