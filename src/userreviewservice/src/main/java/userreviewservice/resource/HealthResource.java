package userreviewservice.resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthResource {

    Logger logger = LoggerFactory.getLogger(HealthResource.class);

    @GET
    public Response getHealth() {
        logger.info("checking service health: user review service is healthy");
        return Response.ok().build();
    }
}