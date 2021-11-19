package org.acme;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

class PetTypeResourceTest {

    @Test
    void getPetTypes() {
        given()
                .when().get("/v1/pettypes")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()",notNullValue());
    }

    @Test
    void getPetType() {
        given()
                .when().get("/v1/pettypes/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()",notNullValue());
    }

    @Test
    void addPetType() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("petType", "Tiger");


        given()
                .contentType("application/json")  //another way to specify content type
                .body(requestParams.toString())   // use jsonObj toString method
                .when()
                .post("/v1/pettypes/addpettype")
                .then()
                .assertThat()
                .statusCode(201)
                .body("size()",notNullValue());
    }

    @Test
    void deletePetType() {
        given()
                .when().delete("/v1/pettypes/delete/2")
                .then()
                .assertThat()
                .statusCode(202);
    }

    @Test
    void updatePet() {
        JSONObject requestParams = new JSONObject();

        requestParams.put("id", 3);
        requestParams.put("petType", "Parrot");

        given()
                .contentType("application/json")  //another way to specify content type
                .body(requestParams.toString())   // use jsonObj toString method
                .when()
                .put("/v1/pettypes/update")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()",notNullValue());
    }
}