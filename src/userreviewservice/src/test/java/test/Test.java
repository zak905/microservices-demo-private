package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import userreviewservice.model.UserReview;

public class Test {


    @org.junit.jupiter.api.Test
    public void test() {
        Gson gson = new GsonBuilder().create();

        System.out.println(gson.fromJson("{\"productId\": \"xxxxx\", \"stars\": 5}", UserReview.class).getProductId());
    }
}
