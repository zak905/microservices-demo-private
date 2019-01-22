package userreviewservice;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BodyProcessor implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

    private final Gson gson;

    @Inject
    public BodyProcessor(Gson gson) {
        this.gson = gson;
    }

    @Override
    public boolean isReadable(Class <?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class <Object> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream input) throws IOException, WebApplicationException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String body = sb.toString();
            return gson.fromJson(body, aClass);
        } finally {
            br.close();
        }
    }

    @Override
    public boolean isWriteable(Class <?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object o, Class <?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap <String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        outputStream.write(gson.toJson(o).getBytes());
    }
}