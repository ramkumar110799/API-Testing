package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.userEndpoints;
import io.restassured.response.Response;
import payloads.User;

public class UserTests {
	
	Faker faker;
	User user;
	
	@BeforeClass
	public void SetupData() {
		
		faker = new Faker();
		user = new User();
		user.setId(faker.idNumber().hashCode());
		user.setUsername(faker.name().username());
		user.setFirstname(faker.name().firstName());
		user.setLastname(faker.name().lastName());
		user.setEmail(faker.internet().emailAddress());
		user.setPassword(faker.internet().password(5, 10));
		user.setPhone(faker.phoneNumber().cellPhone());
		user.setUserstatus(0);
		
		
		}
	
	@Test(priority = 1)
	public void CreateUserTest() {
		
		Response response = userEndpoints.CreateUser(user);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	@Test(priority = 2)
	public void GetUserTest() {
		
		Response response = userEndpoints.GetUser(this.user.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		
	}
	
	@Test(priority = 3)
	public void UpdateUserTest() {
		
		
		user.setFirstname(faker.name().firstName());
		user.setLastname(faker.name().lastName());
		user.setEmail(faker.internet().emailAddress());
		
		Response response = userEndpoints.CreateUser(user);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test(priority = 4)
	public void DeleteUser() {
		
		Response response = userEndpoints.DeleteUser(this.user.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	

}
