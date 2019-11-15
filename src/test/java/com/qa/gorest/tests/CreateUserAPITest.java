package com.qa.gorest.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.listeners.AllureReportListener;
import com.qa.pojo.Users;
import com.qa.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("Epic - Create USER")
@Feature("US-101: define the create user feature....")
@Listeners(AllureReportListener.class)
public class CreateUserAPITest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF";

	@DataProvider(parallel = true)
	public Object[][] getUserData() {
		Object userData[][] = ExcelUtil.getTestData("createuser");
		return userData;
	}

	@Test(dataProvider = "getUserData")
	@Description("test case name: verify create user api with a new user...")
	@Severity(SeverityLevel.CRITICAL)
	public void createUserAPIPOSTTest(String firstname, String lastname, String gender, String dob, String email,
			String phoneNumber, String website, String address, String status) {

		Users user = new Users(firstname, lastname, gender, dob, email, phoneNumber, website, address, status);
		Response response = RestClient.doPost("JSON", baseURI, basePath, token, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println("=====");

	}

}
