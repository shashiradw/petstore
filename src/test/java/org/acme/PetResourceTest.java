package org.acme;

import com.example.petstore.Pet;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class PetResourceTest {

	@Test
	void getPets() {
		given()
				.when().get("/v1/pets")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()",notNullValue());
	}

	@Test
	void getPet() {
		given()
				.when().get("/v1/pets/1")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()",notNullValue());
	}

	@Test
	void addPet() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("petType", "Cat");
		requestParams.put("petName", "Persi");
		requestParams.put("petAge", 2);


		given()
				.contentType("application/json")  //another way to specify content type
				.body(requestParams.toString())   // use jsonObj toString method
				.when()
				.post("/v1/pets/addpet")
				.then()
				.assertThat()
				.statusCode(201)
				.body("size()",notNullValue());

	}

	@Test
	void deletePet() {
		given()
				.when().delete("/v1/pets/delete/2")
				.then()
				.assertThat()
				.statusCode(202);
	}

	@Test
	void updatePet() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", 1);
		requestParams.put("petType", "Dog");
		requestParams.put("petName", "Dogy");
		requestParams.put("petAge", 2);


		given()
				.contentType("application/json")  //another way to specify content type
				.body(requestParams.toString())   // use jsonObj toString method
				.when()
				.put("/v1/pets/update")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()",notNullValue());

	}

	@Test
	void getPetByName() {
		given()
				.when().get("/v1/pets/search/name/Kevo")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()",notNullValue());
	}

	@Test
	void getPetByAge() {
		given()
				.when().get("/v1/pets/search/age/2")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()",notNullValue());
	}

	@Test
	void getPetByType() {
		given()
				.when().get("/v1/pets/search/type/Dog")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()",notNullValue());
	}
}