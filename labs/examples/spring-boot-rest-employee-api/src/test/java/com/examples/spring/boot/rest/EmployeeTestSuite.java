package com.examples.spring.boot.rest;


import com.examples.spring.boot.rest.controller.EmployeeControllerWithMockMvcTest;
import com.examples.spring.boot.rest.controller.EmployeeControllerWithRestTemplateTest;
import com.examples.spring.boot.rest.service.EmployeeServiceTest;


@SuiteClasses({ EmployeeControllerWithMockMvcTest.class, EmployeeServiceTest.class })
public class EmployeeTestSuite {

}
