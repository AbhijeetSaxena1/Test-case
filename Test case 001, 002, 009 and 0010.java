import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Before;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

public class UserScenarios {

    private String path;
    private String validRequest = "{\n" +
            "  \"username\": \"upskills_admin\",\n" +
            "  \"email\": \"some-user@email.com\",\n" +
            "  \"password\": \"Talent4$$!\" \n}";


    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        path = "/users";
    }

    public void createUser() {
        Response response = given()
                .auth()
                .preemptive()
                .basic("required_username", "required_password")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(validRequest)
                .post(path)
                .then().extract().response();

        Assertions.assertEquals(201, response.getStatusCode());
    }