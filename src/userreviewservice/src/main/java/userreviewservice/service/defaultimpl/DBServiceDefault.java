package userreviewservice.service.defaultimpl;

import com.google.gson.Gson;
import io.lettuce.core.ScanArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import userreviewservice.model.UserReview;
import userreviewservice.service.DBService;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class DBServiceDefault implements DBService {
    private final RedisCommands<String, String> redisCommands;
    private final Gson gson;

    @Inject
    public DBServiceDefault(StatefulRedisConnection redisConnection, Gson gson) {
        this.redisCommands = redisConnection.sync();
        this.gson = gson;
    }

    @Override
    public UserReview getUserReview(String id) {
        UserReview userReviewJson = gson.fromJson(redisCommands.get(id), UserReview.class);
        return userReviewJson;
    }

    @Override
    public List<UserReview> getUserReviewList(String productId) {
        ScanArgs scanArguments = ScanArgs.Builder.limit(20).match(getUserIdWithPrefix(productId) + "*");
        return redisCommands.scan(scanArguments).getKeys().stream().map(key -> {UserReview review = gson.fromJson(redisCommands.get(key), UserReview.class); review.setId(key); return review;}).collect(Collectors.toList());
    }

    private String getUserIdWithPrefix(String productId) {
        return USER_REVIEW_ENTITY_PREFIX + productId + "-";
    }

    @Override
    public UserReview createReview(UserReview userReview) {
        String idWithoutPrefix = UUID.randomUUID().toString();
        String idWithPrefix = USER_REVIEW_ENTITY_PREFIX +userReview.getProductId()+"-"+idWithoutPrefix;
        userReview.setCreated(System.currentTimeMillis());
        userReview.setModified(System.currentTimeMillis());

        String result = redisCommands.set(idWithPrefix, gson.toJson(userReview));
        userReview.setId(idWithoutPrefix);

        if (Objects.isNull(result)) {
            //throw some exception
        }
        return userReview;
    }
}