import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;

public class CreatePetTest {

    private PetEndpoint petEndpoint = new PetEndpoint();

    private static long petId;
    private static String status;

    private String body = "{\n" +
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
            "  \"status\": \"Xavailable\"\n" +
            "}";

    @Before
    public void beforeMethod(){

        ValidatableResponse response = petEndpoint
                .createPet(body)
                .statusCode(is(200))
                .body("category.name", is(not("")));
        petId = response.extract().body().path("id");
        status = response.extract().body().path("status");
        ///
        System.out.println(petId);
        System.out.println(status);
    }

    @Test
    public void createPetTest () {

        ValidatableResponse response =
                petEndpoint
                        .createPet(body)
                        .statusCode(is(200))
                        .body("category.name", is(not("")));
    }

    @Test
    public void getPetById(){
        petEndpoint
                .getPet(petId)
                .statusCode(is(200))
                .body("category.name", is(not("")));
    }

    @Test
    public void deletePetById(){
        petEndpoint
                .deletePet(petId)
                .statusCode(is(200));

        petEndpoint
                .getPet(petId)
                .statusCode(is(404))
                .body("message", is("Pet not found"));
    }

    @Test
    public void getPetByStatus(){
        petEndpoint
                .getPetByStatus(status)
                .statusCode(is(200))
                .body("category.name", is(not("")));
    }

    /*@Test
    public void updatePetById(){
        petEndpoint
                .updatePet(petId)
                .statusCode(is(200))
                .body(is(null));
                //.body("category.name", is(not("")));
    }*/

}
