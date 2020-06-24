package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {

    public static Response getApi(String url) {

        RestAssured.baseURI = url;
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json");
        return request.get(url);
    }
}
