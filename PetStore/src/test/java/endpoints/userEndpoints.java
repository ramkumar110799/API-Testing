package endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.User;

public class userEndpoints {

	public static Response CreateUser(User payload) {

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(routes.post_url);

		return response;

	}

	public static Response GetUser(String username) {

		Response response = 		given()
				.pathParam("username", username)
				.when()
				.get(routes.get_url);

		return response;

	}

	public static Response UpdateUser(String username , User payload) {
		
	Response response = 	given()
		.header("Content-Type" , "application/json")
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.put(routes.put_url);
	
	return response;
	}
	
	public static Response DeleteUser(String username) {
		
		Response response = 		given()
				.pathParam("username", username)
				.when()
				.delete(routes.get_url);

		return response;
		
	}
	
}
