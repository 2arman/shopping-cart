package com.example.shoppingcart.integration.stepdefs;

import io.cucumber.java8.En;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class GlobalSteps extends AbstractSteps implements En {

    public GlobalSteps() {

        Then("the save is SUCCESSFUL", () -> {
            /* Method testContext() is from AbstractSteps class */
            final Response response = getTextContext().getResponse();
            assertThat(response.getStatusCode()).isBetween(200, 201);
        });

        Then("the save FAILS", () -> {
            final Response response = getTextContext().getResponse();
            assertThat(response.getStatusCode()).isBetween(400, 412);
        });

        Then("the( get| delete) is SUCCESSFUL", () -> {
            final Response response = getTextContext().getResponse();
            assertThat(response.getStatusCode()).isEqualTo(200);
        });
    }
}
