package com.example.shoppingcart.bdd.stepdefs;

import com.example.shoppingcart.bdd.config.CucumberTestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractSteps {

    @Value("server.port")
    private String port;

    public String baseUrl() {
        return "http://localhost:8080";
    }

    public CucumberTestContext getTextContext() {
        return CucumberTestContext.CONTEXT;
    }
}
