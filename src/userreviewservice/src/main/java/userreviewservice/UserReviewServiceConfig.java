package userreviewservice;


import org.eclipse.persistence.jaxb.BeanValidationMode;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;

public class UserReviewServiceConfig extends ResourceConfig {

    Logger logger = LoggerFactory.getLogger(UserReviewServiceConfig.class);

    @Inject
    public UserReviewServiceConfig(ServiceLocator locator) {
        ServiceLocatorUtilities.enableImmediateScope(locator);
        logger.info("**********************************");
        logger.info("bootstrapping user review service");
        packages(true, "userreviewservice.resource");
        packages(true, "userreviewservice.exception");
        register(new DIModule());
        register(BodyProcessor.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(MoxyJsonFeature.class);
        register(new MoxyJsonConfig().setFormattedOutput(true)
                .property(MarshallerProperties.BEAN_VALIDATION_MODE, BeanValidationMode.NONE)
                .resolver());
        logger.info("starting user review service on PORT " + System.getenv("PORT"));
        logger.info("**********************************************");
    }
}