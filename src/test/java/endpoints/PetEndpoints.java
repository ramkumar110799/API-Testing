package endpoints;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import petPayloads.Pet;

public class PetEndpoints {

	
	public static Response postPet(Pet payload) {
	Response response = 	given().
		accept("application/json")
		.contentType("application/json")
		.body(payload)
		.when()
		.post(routes.pet_post_url);
	
	return response;
		
	}
}
