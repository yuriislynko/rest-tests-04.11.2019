package tests;

import data.Order;
import data.Status;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.StoreEndpoint;


import static org.hamcrest.core.Is.is;

@RunWith(SerenityRunner.class)
public class StoreTest {

    @Steps
    public StoreEndpoint storeEndpoint;

    private Order order = Order.builder()
            .id(0)
            .petId(0)
            .quantity(0)
            .shipDate("2020-02-08T12:52:20.886Z")
            .status(Status.placed)
            .complete(true)
            .build();

    //private static int orderId;

    @Test
    public void createOrder() {

        ValidatableResponse response = storeEndpoint
                        .createOrder(order)
                        .statusCode(is(200));
        //orderId = response.extract().body().path("id");
    }
}
