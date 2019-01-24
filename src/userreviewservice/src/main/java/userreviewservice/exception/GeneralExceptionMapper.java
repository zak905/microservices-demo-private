package userreviewservice.exception;


import org.glassfish.jersey.server.validation.ValidationError;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException e) {
        ValidationError error = new ValidationError();
        error.setMessage(e.getMessage());
        return Response.status(400).entity(error).header("Content-Type", "application/json").build();
    }
}
