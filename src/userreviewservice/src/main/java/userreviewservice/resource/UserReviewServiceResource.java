package userreviewservice.resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import userreviewservice.model.UserReview;
import userreviewservice.service.UserReviewService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class UserReviewServiceResource {

    @Inject
    UserReviewService userReviewService;

    Logger logger = LoggerFactory.getLogger(UserReviewServiceResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserReview> getUserReviewList(@QueryParam("productId") String productId) {
        logger.info("getting user reviews for product {}", productId);
        return userReviewService.getUserReviews(productId);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserReview get(@PathParam("id") String id) {
        logger.info("getting user review with id {}", id);
        return userReviewService.getUserReview(id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserReview createUserReview(UserReview userReview) {
        logger.info("creating new user review for user with id {} and product with id {}", userReview.getUserId(), userReview.getProductId());
        return userReviewService.addUserReview(userReview);
    }

/*    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserReview editUserReview(UserReview userReview) {
        return userReviewService.createUserReview(userReview);
    }*/

}