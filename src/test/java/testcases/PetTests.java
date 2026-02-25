package testcases;

import java.util.Arrays;
import  static 	org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.PetEndpoints;
import io.restassured.response.Response;
import petPayloads.Category;
import petPayloads.Pet;
import petPayloads.Tag;

public class PetTests {
	
	Faker faker;
	Pet pet;
	Category category;
	Tag tag1;
	Tag tag2;

	@BeforeTest
	public void setUpData() {
		faker = new Faker();
		pet = new Pet();
		
		category = new Category();
		category.setId(faker.idNumber().hashCode());
		category.setName("Doggie");
		
		tag1 = new Tag();
		tag1.setId(faker.number().hashCode());
		tag1.setName(faker.name().lastName());
		
		tag2 = new Tag();
		tag2.setId(faker.number().hashCode());
		tag2.setName(faker.name().lastName());
		
		
		
		pet.setId(faker.idNumber().hashCode());
		pet.setName(faker.name().firstName());
		pet.setCategory(category);
		pet.setStatus("available");
		pet.setPhotoUrls(Arrays.asList("Photo1.jpg", "Photo2.jpg"));
		pet.setTags(Arrays.asList(tag1, tag2));
		
		
	}
	
	
	@Test
	public void addNewPet() {
		Response response = PetEndpoints.postPet(pet);
		
		response.then()
		.body("status", equalTo("available") )
		.log()
		.all();
		Pet responsePet = response.as(Pet.class);
		Assert.assertEquals(responsePet.getStatus(), "available");
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json");
		
	}

}
