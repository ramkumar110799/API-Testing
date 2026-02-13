package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.userEndpoints;
import io.restassured.response.Response;
import payloads.User;
import utilities.dataProviders;

public class DataDrivenTest {
	
			
	
	@Test(priority= 1 , dataProvider = "excelData" , dataProviderClass = dataProviders.class)
	public void DDCreatUserTest(String id, String username, String firstname, String lastname, String email, String password, String phone , String userstatus){ 
		
		User userpayload = new User();
		userpayload.setId(Integer.parseInt(id));
		userpayload.setUsername(username);
		userpayload.setFirstname(firstname);
		userpayload.setLastname(lastname);
		userpayload.setEmail(email);
		userpayload.setPhone(phone);
		userpayload.setUserstatus(Integer.parseInt(userstatus));
		
		Response response = userEndpoints.CreateUser(userpayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		
	}
	
	
	@Test(priority =2 , dataProvider = "ExcelUserNames" , dataProviderClass = dataProviders.class)
	public void DDGetUserTest(String username) {
		
		Response response = userEndpoints.GetUser(username);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);

		
		
	}
	
	
	
	@Test(priority =3 , dataProvider = "ExcelUserNames" , dataProviderClass = dataProviders.class)
	public void DDDeleteUserTest(String username) {
		
		Response response = userEndpoints.DeleteUser(username);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);

		
		
	}
	
	

}
