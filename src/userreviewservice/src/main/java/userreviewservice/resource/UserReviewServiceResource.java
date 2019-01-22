package userreviewservice.resource;


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


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserReview> getUserReviewList(@QueryParam("productId") String productId) {
        return userReviewService.getUserReviews(productId);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserReview get(@PathParam("id") String id) {
        return userReviewService.getUserReview(id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserReview createUserReview(UserReview userReview) {
        return userReviewService.addUserReview(userReview);
    }

/*    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserReview editUserReview(UserReview userReview) {
        return userReviewService.createUserReview(userReview);
    }*/

}
