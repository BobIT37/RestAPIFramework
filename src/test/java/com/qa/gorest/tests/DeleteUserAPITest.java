package com.qa.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.constants.Constants;
import com.qa.listeners.AllureReportListener;
import com.qa.pojo.Users;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Listeners(AllureReportListener.class)
public class DeleteUserAPITest {
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF";

	@Test
	@Description("test case name: delete user api with a new user...")
	@Severity(SeverityLevel.CRITICAL)
	public void deleteUserTest() {

		// create a user: using POST:
		System.out.println("==Creating A new User===");

		Users user = new Users();
		user.setFirst_name("Gulnaz");
		user.setLast_name("Musa");
		user.setGender("female");
		user.setDob("29-03-1980");
		user.setEmail("gulanz4@gmail.com");
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
		
		Response deleteResponse = RestClient.doDelete("JSON", baseURI, basePath + "/" + id, token, true);
		System.out.println(deleteResponse.prettyPrint());
		int responseCode =  deleteResponse.jsonPath().get("_meta.code");
		System.out.println("delete user api respone status code: "+ responseCode);
		Assert.assertEquals(responseCode, Constants.HTTP_STATUS_CODE_204);
		Assert.assertNull(deleteResponse.jsonPath().getString("result"));
		
		int limit =  deleteResponse.jsonPath().get("_meta.rateLimit.limit");
		int remaining =  deleteResponse.jsonPath().get("_meta.rateLimit.remaining");
		int reset =  deleteResponse.jsonPath().get("_meta.rateLimit.reset");

		Assert.assertEquals(remaining, limit-reset);

	}

}
