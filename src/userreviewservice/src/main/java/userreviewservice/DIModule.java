package userreviewservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import userreviewservice.service.DBService;
import userreviewservice.service.UserReviewService;
import userreviewservice.service.defaultimpl.DBServiceDefault;
import userreviewservice.service.defaultimpl.UserReviewServiceDefault;

import javax.inject.Singleton;
import java.text.MessageFormat;
import java.util.Objects;

public class DIModule extends AbstractBinder {

    @Override
    protected void configure() {
        bind(UserReviewServiceDefault.class).to(UserReviewService.class).in(Singleton.class);
        bind(DBServiceDefault.class).to(DBService.class).in(Singleton.class);
        bindFactory(GsonFactory.class).to(Gson.class).in(Singleton.class);
        bindFactory(RedisConnectionFactory.class).to(StatefulRedisConnection.class).in(Singleton.class);
    }

    private static class GsonFactory implements Factory<Gson> {
        @Override
        public Gson provide() {
            return new GsonBuilder().create();
        }

        @Override
        public void dispose(Gson gson) { }
    }

    private static class RedisConnectionFactory implements Factory<StatefulRedisConnection> {
        @Override
        public StatefulRedisConnection provide() {
            String host = Objects.requireNonNull(System.getenv("REDIS_HOST"));
            String port = Objects.requireNonNull(System.getenv("REDIS_PORT"));

            String redisConnectionString = "redis://{0}:{1}";

            RedisClient redisClient = RedisClient
                    .create(MessageFormat.format(redisConnectionString, host, port));
            return redisClient.connect();
        }

        @Override
        public void dispose(StatefulRedisConnection statefulRedisConnection) {
            statefulRedisConnection.close();
        }
    }
}