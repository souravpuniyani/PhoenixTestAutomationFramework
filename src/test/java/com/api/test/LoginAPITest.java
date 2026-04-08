package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;


import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	
	
	@Test
	public void loginAPIRequest() throws IOException {
		
		UserCredentials userCredentials = new UserCredentials("iamfd","password"); //POJO Object
		
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
