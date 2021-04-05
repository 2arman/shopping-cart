package com.example.shoppingcart.bdd.stepdefs;

import com.example.shoppingcart.service.model.ItemDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ItemSteps extends AbstractSteps implements En {

    private final String itemsUrl = baseUrl() + "/api/v1/bo/items";


    public ItemSteps() {
        Given("BO user wants to create items with the following attributes", (DataTable employeeDt) -> {
            List<ItemDto> items = employeeDt.asList(ItemDto.class);
            testContext().setPayload(items);
        });


        When("the BO user saves new items", () -> {
            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .body(testContext().getPayload())
                            .post(itemsUrl)
                            .andReturn();

            testContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });


        When("the BO user wants to get items", () -> {

            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .get(itemsUrl)
                            .andReturn();

            testContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });


        When("the BO user wants to delete items", () -> {

            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .body(testContext().getPayload())
                            .delete(itemsUrl)
                            .andReturn();

            testContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });

        And("^save item response into payload$",() -> {
            final Response response = testContext().getResponse();
            final var itemsList = response.as(ItemDto[].class);
            testContext().setPayload(itemsList);
        });

        And("^BO user retrieves items with the following attributes$", (DataTable expectedDt) -> {
            final Response response = testContext().getResponse();
            final var itemsList = response.as(ItemDto[].class);
            final var actualDataTable = DataTableMapper.toItemDataTable(Arrays.asList(itemsList));
            expectedDt.unorderedDiff(actualDataTable);
        });
    }

}
