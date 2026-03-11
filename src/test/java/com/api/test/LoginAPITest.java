package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	
	
	@Test
	public void loginAPIRequest() throws IOException {
		
		UserCredentials userCredentials = new UserCredentials("iamfd","password"); //POJO Object
		
		given()
		.and()
		.baseUri(getPropertyFromFile("BASE_URI"))  
		.and()
		.contentType(ContentType.JSON)
		.accept(ContentType.ANY)
		.and()
		.body(userCredentials)
		.log().body()
		.log().headers()
		.log().uri()
		.log().method()
		
		.when()
		.post("/login")
		
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(2000L))
		.body("message", equalTo("Success"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseScgema.json"));
		
		
	}
	

}
