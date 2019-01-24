package userreviewservice;


import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;

public class UserReviewServiceConfig extends ResourceConfig {

    Logger logger = LoggerFactory.getLogger(UserReviewServiceConfig.class);

    @Inject
    public UserReviewServiceConfig(ServiceLocator locator) {
        ServiceLocatorUtilities.enableImmediateScope(locator);
        System.out.println(System.getProperty("log4j2.properties"));
        logger.info("**********************************");
        logger.info("bootstrapping user review service");
        packages(true, "userreviewservice.resource");
        register(new DIModule());
        register(BodyProcessor.class);
        logger.info("starting user review service on PORT " + System.getenv("PORT"));
        logger.info("**********************************************");
    }
}