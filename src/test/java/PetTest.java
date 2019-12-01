import data.Pet;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;

public class PetTest {

    private PetEndpoint petEndpoint = new PetEndpoint();

    private Pet pet = new Pet (0, "string", "varan", "booked");

    private Pet updatedPet = new Pet (0, "pets", "dr. varan", "available?");

    private static long petId;

    @Before
    public void beforeMethod(){

        ValidatableResponse response = petEndpoint
                .createPet(pet)
                .statusCode(is(200))
                .body("category.name", is(not("")));
        petId = response.extract().body().path("id");
    }

    @Test
    public void createPet () {

        ValidatableResponse response =
                petEndpoint
                        .createPet(pet)
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
                .getPetByStatus("booked")
                .statusCode(200)
                .body("status[0]", is("booked"));//ToDo: verify each element status
    }

    @Test
    public void updatePet(){
        petEndpoint
                .updatePet(updatedPet)
                .statusCode(200)
                .body("category.name", is("pets"));
    }

    @Test
    public void updatePetById () {
        petEndpoint
                .updatePetById(petId, "dr. varan", "available")
                .statusCode(200);

        petEndpoint
                .getPet(petId)
                .statusCode(200)
                .body("name", is("dr. varan"))
                .body("status", is("available"));
    }

    @Test
    public void uploadPetImage (){
        petEndpoint
                .uploadPetImage(petId, "125521", new File("/home/joe/Изображения/varan.jpg;type=image/jpeg"));
                //.statusCode(200);
    }

}
