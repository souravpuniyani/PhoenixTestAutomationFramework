package com.api.utils;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	
	private AuthTokenProvider() {
		
	}
	
	public static String getToken(Role role) {
		
		
		UserCredentials userCredentials =null;   //pojo class reference ... make object of pojo on basis of condition
		if(role ==Role.FD) {
			userCredentials= new UserCredentials("iamfd","password");
		}
		
		
		else if(role ==Role.SUP) {
			userCredentials= new UserCredentials("iamsup","password");
		}
		
		else if(role== Role.ENG) {
			userCredentials= new UserCredentials("iameng","password");
		}
		
		else if(role== Role.QC) {
			userCredentials= new UserCredentials("iamqc","password");
		}
		
		
		String token=given()
		.and()
		.baseUri(ConfigManager.getPropertyFromFile("BASE_URI"))
		.contentType(ContentType.JSON)
		.body(userCredentials)
		
		.when()
		.post("login")
		
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("message",Matchers.equalTo("Success"))
		.extract().jsonPath().getString("data.token");
		
		return token;

	}

}
