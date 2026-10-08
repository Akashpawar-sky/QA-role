
	package com.example.qa;

	import org.junit.jupiter.api.BeforeAll;

	import io.restassured.RestAssured;
	import io.restassured.builder.RequestSpecBuilder;
	import io.restassured.builder.ResponseSpecBuilder;
	import io.restassured.specification.RequestSpecification;
	import io.restassured.specification.ResponseSpecification;

	public class BaseTest {

	    protected static RequestSpecification requestSpec;
	    protected static ResponseSpecification responseSpec;

	    @BeforeAll
	    static void setup() {

	        RestAssured.baseURI = "http://localhost:8080";

	        requestSpec = new RequestSpecBuilder()
	                .setContentType("application/json")
	                .build();

	        responseSpec = new ResponseSpecBuilder()
	                .expectContentType("application/json")
	                .build();
	    }

	    protected String generateEmail() {
	        return "test" + System.currentTimeMillis() + "@gmail.com";
	    }
	}

