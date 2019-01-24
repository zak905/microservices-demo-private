package userreviewservice.model;

import java.text.DecimalFormat;
import java.util.List;

public class UserReviewDto {

    private List<UserReview> userReviews;

    private double average;

    public UserReviewDto(List <UserReview> userReviews) {
        this.userReviews = userReviews;
        this.average = Double.valueOf(new DecimalFormat("0.00").format(userReviews.stream().mapToInt(UserReview::getStars).average().orElse(0)));
    }

    public List <UserReview> getUserReviews() {
        return userReviews;
    }

    public double getAverage() {
        return average;
    }
}
