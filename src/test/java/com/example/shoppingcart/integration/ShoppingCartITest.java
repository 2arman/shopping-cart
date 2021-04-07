package com.example.shoppingcart.integration;

import com.example.shoppingcart.integration.config.SpringIntegrationTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {"json:target/cucumber-report.json"},
        glue = {"com.example.shoppingcart.integration.config",
                "com.example.shoppingcart.integration.stepdefs"})
public class ShoppingCartITest extends SpringIntegrationTest {
}
