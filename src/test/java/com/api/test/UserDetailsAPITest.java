package com.api.test;

import org.testng.annotations.Test;
import com.api.constant.Role;
import com.api.utils.SpecUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import java.io.IOException;

public class UserDetailsAPITest {
	
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		
		given()
		.spec(SpecUtil.requestSpecAuth(Role.FD))
		
		.when()
		.get("userdetails")
		
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsSchema.json"));
		
		
	}

}
