package com.examples.spring.boot.rest.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerWithRestTemplateTest {
	
	Logger log = LoggerFactory.getLogger(EmployeeControllerWithRestTemplateTest.class);
	
	@Autowired
	TestRestTemplate restTemp;
	
	@Test
	public void testCreateEmployee() throws URISyntaxException
	{
		// POST /employees
		
		String reqBody = "{\"name\":\"mayala\",\"country\":\"Tanzania\",\"designation\":\"Manager\"}";
		
		log.info("Request Body: {}", reqBody);
		
		// Step 1: Create Request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/employees"));
		
		log.info("Request Entity: {}", request);
		
		// Step 2: Send Request to Endpoint
		// Step 3: Receive the Response
		Object response = restTemp.exchange(request, Object.class);
		
		log.info("Response: {}", response);
		
		// Step 4: Validate the Response
		assertNotNull(response);
		assertTrue(response.toString().contains("Employee Created"));
	}
	
	@Test
	public void testGetAllEmployees() throws Exception
	{
		
		log.info("REST Template: {}", restTemp);
		
		// GET /employees
		
		// REST Template
		// Step 1: Create Request
		// Step 2: Send Request to Endpoint
		// Step 3: Receive the Response
		Object response = restTemp.getForEntity("/employees", Object.class);
		
		log.info("Response: {}", response);
		
		// Step 4: Validate the Response
		assertNotNull(response);
		
		
	}
}
