package com.example.shoppingcart.bdd;

import com.example.shoppingcart.bdd.config.SpringIntegrationTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {"json:target/cucumber-report.json"},
        glue = {"com.example.shoppingcart.bdd.config",
                "com.example.shoppingcart.bdd.stepdefs"})
public class CucumberTest extends SpringIntegrationTest {
}
