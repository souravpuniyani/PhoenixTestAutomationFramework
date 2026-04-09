package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;


import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	
	private UserCredentials userCredentials;
	
	@BeforeMethod(description="Create the payload for LoginAPIRequest")
	public void setup() {
		 userCredentials = new UserCredentials("iamfd","password"); //POJO Object
	}
	

	
	@Test(description ="Verify loginAPI is working for iamfd user", groups= {"regression", "smoke", "sanity"})
	public void loginAPIRequest() throws IOException {
				
		given()
		.and()
		.spec(SpecUtil.requestSpec(userCredentials))
		
		.when()
		.post("/login")
		
		.then()
		
		.spec(SpecUtil.responseSpec_ok())
		
		.body("message", equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseScgema.json"));
		
		
	}
	

}
