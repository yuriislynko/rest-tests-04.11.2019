package steps;

import data.Order;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoreEndpoint {
    public final static String CREATE_ORDER = "/store/order";

    private RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

    @Step
    public ValidatableResponse createOrder (Order order) {
        return given()
                .body(order)
                .post(CREATE_ORDER)
                .then();
    }
}
