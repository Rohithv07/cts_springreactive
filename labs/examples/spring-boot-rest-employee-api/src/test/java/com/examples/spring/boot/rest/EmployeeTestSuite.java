package com.examples.spring.boot.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.examples.spring.boot.rest.controller.EmployeeControllerWithMockMvcTest;
import com.examples.spring.boot.rest.controller.EmployeeControllerWithRestTemplateTest;
import com.examples.spring.boot.rest.service.EmployeeServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ EmployeeControllerWithMockMvcTest.class, EmployeeServiceTest.class })
public class EmployeeTestSuite {

}
