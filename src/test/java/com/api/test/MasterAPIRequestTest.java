package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;

import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPIRequestTest {
	
	
	@Test(description="Verify Master api is working fine", groups= {"sanity","smoke","regression"})
	public void masterAPITest() {
		
		RestAssured.given()
		.spec(SpecUtil.requestSpecAuth(Role.FD))
		
		.when()
		.post("master")
		
		.then()
		.spec(SpecUtil.responseSpec_ok())
		
		.body("$", Matchers.hasKey("message"))  //bigger json has key message
		.body("$",Matchers.hasKey("data"))
		.body("message", Matchers.equalTo("Success"))
		.body("data", Matchers.notNullValue())
		.body("data",Matchers.hasKey("mst_oem"))  //jkey value match for json array
		.body("data",Matchers.hasKey("mst_model"))
		.body("data.mst_oem.size()", Matchers.greaterThanOrEqualTo(2))  //size of json array
		.body("data.mst_model.size()",Matchers.greaterThanOrEqualTo(0))
		
		.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))  // check for all ids
		.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))  // check for all name 

		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponse.json"));
	}
	
	
	@Test(description="Verify status code for master api when token is missing", groups= {"negative"})
	public void invalidTokenForMasterAPITest() {

		RestAssured.given()
		.spec(SpecUtil.requestSpec())
		
		.when().post("master")
		.then()
		.spec(SpecUtil.responseSpec_text(401));
	}

}
