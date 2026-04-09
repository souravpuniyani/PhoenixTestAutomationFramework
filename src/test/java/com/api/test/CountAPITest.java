package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class CountAPITest {
	
	
	
	@Test(description="Verify count api is working fine", groups= {"regression","smoke","sanity"})
	public void verifyCountAPIResponse() {
		
		
		given()
		.spec(SpecUtil.requestSpecAuth(Role.FD))
		
		.when()
		.get("dashboard/count")
		
		.then()
		.spec(SpecUtil.responseSpec_ok())
		
		.body("message", Matchers.equalTo("Success"))
		.body("data",Matchers.notNullValue())
		.body("data.size()",Matchers.equalTo(3)) //size of array should be 3
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0))) // from data, for all items(array) check count value >0
		.body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponse.json"))
		.body("data.label", Matchers.containsInAnyOrder("Pending for delivery","Created today","Pending for FST assignment"));
	}
	
	
	@Test(description="Verify status code for count api when token is missing", groups= {"negative"})
	public void CountAPITest_MissingAuthToken() {
		given()
		.spec(SpecUtil.requestSpec())
		
		.when()
		.get("dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_text(401));
	}
	

}
