package com.qa.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.listeners.AllureReportListener;
import com.qa.pojo.Users;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Listeners(AllureReportListener.class)
public class UpdateUserAPITest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF";

	@Test(enabled=true)
	@Description("test case name: update user api with a new user...")
	@Severity(SeverityLevel.CRITICAL)
	public void createUserAPIPUTTest_WithConstructor() {

		// create a user: using POST:
		System.out.println("==Creating A new User===");
		Users user = new Users("Ashu", "Singhu", "male", "01-01-1990", "ashu33@gmail.com", "+1-994-294-0172",
				"http://www.google.com", "test address", "active");

		Response response = RestClient.doPost("JSON", baseURI, basePath, token, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		String id = response.jsonPath().getString("result.id");
		System.out.println("new user id is: " + id);

		System.out.println("=====");

		// update the same user: using PUT :
		user = new Users("Ashu", "Singh", "male", "01-01-1990", "ashu3@gmail.com", "+1-994-294-0172",
				"http://www.google.com", "test address", "inactive");

		Response responseUpdate = RestClient.doPut("JSON", baseURI, basePath + "/" + id, token, true, user);
		System.out.println(responseUpdate.prettyPrint());

		String updatedId = responseUpdate.jsonPath().getString("result.id");
		Assert.assertEquals(updatedId, id);

	}
	
	
	@Test()
	@Description("test case name: update user api with a new user with setter...")
	@Severity(SeverityLevel.CRITICAL)
	public void createUserAPIPUTTest_WithSetter() {

		// create a user: using POST:
		System.out.println("==Creating A new User===");
//		Users user = new Users("Ashu", "Singh", "male", "01-01-1990", "ashu3@gmail.com", "+1-994-294-0172",
//				"http://www.google.com", "test address", "active");
		
		Users user = new Users();
		user.setFirst_name("Gulnaz");
		user.setLast_name("Musa");
		user.setGender("female");
		user.setDob("29-03-1980");
		user.setEmail("gulanz3@gmail.com");
		user.setPhone("+1-222-333-4444");
		user.setWebsite("http://www.xyz.com");
		user.setAddress("test Address");
		user.setStatus("active");

		Response response = RestClient.doPost("JSON", baseURI, basePath, token, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		String id = response.jsonPath().getString("result.id");
		System.out.println("new user id is: " + id);

		System.out.println("=====");

		// update the same user: using PUT :
//		user = new Users("Ashu", "Singh", "male", "01-01-1990", "ashu3@gmail.com", "+1-994-294-0172",
//				"http://www.google.com", "test address", "inactive");
		user.setPhone("+1-999-999-8777");
		user.setStatus("inactive");

		Response responseUpdate = RestClient.doPut("JSON", baseURI, basePath + "/" + id, token, true, user);
		System.out.println(responseUpdate.prettyPrint());

		String updatedId = responseUpdate.jsonPath().getString("result.id");
		Assert.assertEquals(updatedId, id);
		
		

	}

}
