package com.qa.gorest.tests;


import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.constants.Constants;
import com.qa.listeners.AllureReportListener;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@Listeners(AllureReportListener.class)
public class GetUserAPITest {
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users/";
	String token = "SXbmytsyJxP3DTdBR2hlWUqr4KhKXma1rwoF";
	
	@Test
	@Description("test case name: verify get user api with existing user...")
	@Severity(SeverityLevel.CRITICAL)
	public void getUserListAPITest(){
		
		Response response = RestClient.doGet("JSON", baseURI, basePath, token, true);
		Assert.assertEquals(RestClient.getStatucCode(response), Constants.HTTP_STATUS_CODE_200);
		RestClient.getPrettyResponsePrint(response);
		System.out.println(response.time());
		
		JsonPath js = RestClient.getJsonPath(response);
		System.out.println(js.getString("_meta.message"));
		
		ArrayList results = js.get("result");
		System.out.println(results.size());
		System.out.println(results.get(0));
		
		Map<String, Object>firstUserData = (Map<String, Object>) results.get(0);
		
		for(Map.Entry<String, Object> entry : firstUserData.entrySet()){
			System.out.println("Key = " + entry.getKey() +  ", value = " + entry.getValue());
		}
		
	}
	
	
}
