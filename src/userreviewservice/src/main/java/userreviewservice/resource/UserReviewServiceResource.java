package userreviewservice.resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import userreviewservice.model.UserReview;
import userreviewservice.model.UserReviewDto;
import userreviewservice.service.UserReviewService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserReviewServiceResource {
    @Inject
    UserReviewService userReviewService;

    Logger logger = LoggerFactory.getLogger(UserReviewServiceResource.class);

    @GET
    public UserReviewDto getUserReviewList(@QueryParam("productId") @NotNull @Size(min = 1) String productId) {
        logger.info("getting user reviews for product {}", productId);
        return new UserReviewDto(userReviewService.getUserReviews(productId));
    }

    @Path("/{id}")
    @GET
    public UserReview get(@PathParam("id") @NotNull @Size(min = 1) String id) {
        logger.info("getting user review with id {}", id);
        return userReviewService.getUserReview(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserReview createUserReview(@Valid UserReview userReview) {
        logger.info("creating new user review for user with id {} and product with id {}", userReview.getUserId(), userReview.getProductId());
        return userReviewService.addUserReview(userReview);
    }
}