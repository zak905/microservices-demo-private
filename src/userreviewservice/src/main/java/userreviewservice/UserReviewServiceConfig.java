package userreviewservice;


import org.glassfish.jersey.server.ResourceConfig;

public class UserReviewServiceConfig extends ResourceConfig {
    public UserReviewServiceConfig() {
        packages(true, "userreviewservice.resource");
        register(new DIModule());
        register(BodyProcessor.class);
    }
}
