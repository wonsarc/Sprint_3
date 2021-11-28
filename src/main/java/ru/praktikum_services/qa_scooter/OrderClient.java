package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Step;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {
    private static final String ORDER_PATH = "api/v1/orders/";

    @Step
    public int create(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("track");
    }

    @Step
    public HashMap<String, String> getOrder(int orderId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH + "track?t=" + orderId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("order");
    }
}
