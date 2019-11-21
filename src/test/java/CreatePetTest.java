import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;

public class CreatePetTest {

    @Test
    public void createPet() {

        String body = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"varan\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        RestAssured
                .given()
                .body(body)
                .contentType(ContentType.JSON)
                .log().all()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("category.name", is(not("")))
                .log().all();

        Response response = RestAssured
                .given()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .contentType(ContentType.XML)
                .extract()
                .response();

        System.out.println(response);

    }


    @Test
    public void getPetById () {
        RestAssured
                .given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/9216678377732767381")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .body("name", is("varan"))
                .log().all();
    }

}
