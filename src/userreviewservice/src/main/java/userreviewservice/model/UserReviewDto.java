package userreviewservice.model;

import java.util.List;

public class UserReviewDto {

    private List<UserReview> userReviews;

    private double average;

    public UserReviewDto(List <UserReview> userReviews) {
        this.userReviews = userReviews;
        this.average = userReviews.stream().mapToInt(UserReview::getStars).average().getAsDouble();
    }

    public List <UserReview> getUserReviews() {
        return userReviews;
    }

    public double getAverage() {
        return average;
    }
}
