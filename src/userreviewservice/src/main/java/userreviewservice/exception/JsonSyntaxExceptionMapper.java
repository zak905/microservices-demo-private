package userreviewservice.exception;

import com.google.gson.JsonSyntaxException;
import org.glassfish.jersey.server.validation.ValidationError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class JsonSyntaxExceptionMapper implements ExceptionMapper<JsonSyntaxException> {
    @Override
    public Response toResponse(JsonSyntaxException e) {
        ValidationError error = new ValidationError();
        error.setMessage("Invalid Json");
        return Response.status(400).entity(error).header("Content-Type", "application/json").build();
    }
}
