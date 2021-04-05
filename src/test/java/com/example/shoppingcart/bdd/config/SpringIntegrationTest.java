package com.example.shoppingcart.bdd.config;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
@Slf4j
public class SpringIntegrationTest {


  @Before
  public void setUp() {
    log.info("------------- TEST CONTEXT SETUP -------------");
  }

  @After
  public void tearDown() {
    log.info("------------- TEST CONTEXT TEAR DOWN -------------");
    CucumberTestContext.CONTEXT.reset();
  }

}

