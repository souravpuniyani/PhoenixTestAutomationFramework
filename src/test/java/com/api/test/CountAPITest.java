package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.Header;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;

public class CountAPITest {
	
	
	
	@Test
	public void verifyCountAPIResponse() {
		
		
		given()
		.baseUri(ConfigManager.getPropertyFromFile("BASE_URI"))
		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", Matchers.equalTo("Success"))
		.time(Matchers.lessThan(1000L))
		.body("data",Matchers.notNullValue())
		.body("data.size()",Matchers.equalTo(3)) //size of array should be 3
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0))) // from data, for all items(array) check count value >0
		.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponse.json"))
		.body("data.label", Matchers.containsInAnyOrder("Pending for delivery","Created today","Pending for FST assignment"));
	}
	
	
	@Test
	public void CountAPITest_MissingAuthToken() {
		given()
		.baseUri(ConfigManager.getPropertyFromFile("BASE_URI"))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}
	

}
