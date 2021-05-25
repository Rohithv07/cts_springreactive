package com.examples.spring.boot.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.examples.spring.boot.rest.model.Employee;
import com.examples.spring.boot.rest.repository.EmployeeRepository;
import com.examples.spring.boot.rest.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest
//@WebMvcTest({EmployeeController.class, EmployeeService.class, EmployeeRepository.class})
@AutoConfigureRestDocs(outputDir = "target/snippets", uriHost="127.0.0.1", uriPort=8080, uriScheme="http")
public class EmployeeControllerWithMockMvcTest {
	
	Logger log = LoggerFactory.getLogger(EmployeeControllerWithMockMvcTest.class);	
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean	
	EmployeeService empService;
	
	@Test
	public void testCreateEmployee() throws Exception {
		
		String reqBody = "{\"name\":\"mayala\",\"country\":\"Tanzania\",\"designation\":\"Manager\"}";
		
		mockMvc.perform(post("/employees")
					.contentType(MediaType.APPLICATION_JSON)
					.content(reqBody))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.message").value("Employee Created"))
				.andDo(document("emp-create",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						requestFields(fieldWithPath("name").description("Employee Name"),
								fieldWithPath("designation").description("Employee Designation"),
								fieldWithPath("country").description("Employee Country")),
						responseFields(fieldWithPath("statusCode").description("Status Code"),
								fieldWithPath("status").description("Indicates status of the response"),
								fieldWithPath("id").description("Employee ID which got created"),
								fieldWithPath("message").description("Custom message indicates Employee creation status"))	
						));
	}
	
	@Test
	public void testGetAllEmployees() throws Exception {
		
		log.info("MockMvc: {}", mockMvc);
		
		// Mocking Employee Service list() method implementation
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee());
		Mockito.when(empService.list()).thenReturn(employees);
		
		mockMvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isNotEmpty())
				.andDo(document("emp-list", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

}
