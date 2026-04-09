package com.api.test;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateJobAPITest {

	
	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Sourav","S","7206819755","","sourav@gmail.com","");
		CustomerAddress customer_address = new CustomerAddress("7594", "Nadi Mohalla", "Ambala", "Shukulkund Road", "Amabala", "134003", "India", "Haryana");
		CustomerProduct customer_product = new CustomerProduct("2025-04-06T18:30:00.000Z", "28906060886100", "28906060886100", "28906060886100", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1,"Battery Issue");
		Problems[] problem= new Problems[1];
		problem[0]=problems;
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customer_address, customer_product, problem);
		
		String jobnumber= RestAssured.given()
		.given()
		.spec(SpecUtil.requestSpecAuth(Role.FD, createJobPayload))
		.body(createJobPayload)
		
		.when()
		.log().all()
		.post("/job/create")
		.then()
		.statusCode(200)
		.extract().jsonPath().getString("data.job_number");
		
		System.out.println(jobnumber);
		
	}
}
