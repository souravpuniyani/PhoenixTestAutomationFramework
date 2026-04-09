package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	//no body
	public static RequestSpecification requestSpec() {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getPropertyFromFile("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.BODY)
		.log(LogDetail.HEADERS)
		.build();
		return requestSpecification;
	}
	
	//with body - work for any pojo 
	public static RequestSpecification requestSpec(Object userCredentials) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getPropertyFromFile("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(userCredentials)
				.log(LogDetail.URI)
				.log(LogDetail.BODY)
				.log(LogDetail.HEADERS)
				.build(); 
				
				return requestSpecification;
	}
	
	// for auth
	public static RequestSpecification requestSpecAuth(Role role) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getPropertyFromFile("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.BODY)
				.log(LogDetail.HEADERS)
				.build(); 
				
				return requestSpecification;
	}
	
	
	public static ResponseSpecification responseSpec_ok() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	

	public static ResponseSpecification responseSpec(int statuscode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statuscode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_text(int statuscode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectStatusCode(statuscode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	

	
}
