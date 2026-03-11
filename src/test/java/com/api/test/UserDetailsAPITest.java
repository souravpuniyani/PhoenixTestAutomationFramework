package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;

import static com.api.utils.ConfigManager.*; //static import for utility class 

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class UserDetailsAPITest {
	
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		Header authHeader= new Header("Authorization", AuthTokenProvider.getToken(Role.FD));
		
		given()
		.and()
		.baseUri(getPropertyFromFile("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.accept(ContentType.ANY)
		.and()
		.header(authHeader)
		.log().headers()
		.log().uri()
		.log().method()
		
		.when()
		.get("userdetails")
		
		.then()
		.statusCode(200)
		.time(Matchers.lessThan(2000L))
		.log().all()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsSchema.json"));
		
		
	}

}
