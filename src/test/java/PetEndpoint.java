import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PetEndpoint {
    public final static String CREATE_PET = "/pet";
    public final static String GET_PET = "/pet/{petId}";
    public final static String DELETE_PET = "/pet/{petId}";
    public final static String GET_BY_STATUS = "/pet/findByStatus{extStatus}";

    static {
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL));
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL));
    }

    public RequestSpecification given() {
        return RestAssured
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createPet (String body) {
        return given()
                .body(body)
                .post(CREATE_PET)
                .then();
    }

    public ValidatableResponse getPet (long petId) {
        return given()
                .get(GET_PET, petId)
                .then();
    }

    public ValidatableResponse deletePet (long petId) {
        return given()
                .delete(DELETE_PET, petId)
                .then();
    }

    public ValidatableResponse getPetByStatus (String extStatus) {
        return given()
                .get(GET_BY_STATUS, extStatus)
                .then();
    }
}
