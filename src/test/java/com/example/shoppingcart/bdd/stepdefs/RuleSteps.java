package com.example.shoppingcart.bdd.stepdefs;

import com.example.shoppingcart.service.model.ItemDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RuleSteps extends AbstractSteps implements En {

    private final String itemsUrl = baseUrl() + "/api/v1/bo/items";
    ;

    public RuleSteps() {
        Given("BO user wants to create rule with the following attributes", (DataTable employeeDt) -> {
            List<ItemDto> items = employeeDt.asList(ItemDto.class);
            testContext().setPayload(items);
        });


        When("the BO user saves new rule", () -> {

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


        When("the BO user wants to get rules", () -> {

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


        When("the BO user wants to delete rule", () -> {

            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .delete(itemsUrl)
                            .andReturn();

            testContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });

        And("^BO user retrieves rules with the following attributes$", (DataTable expectedDt) -> {
            final Response response = testContext().getResponse();
            final var itemsList = response.as(List.class);
            final var actualDataTable = toItemDataTable(itemsList);
            expectedDt.unorderedDiff(actualDataTable);
        });
    }

    private DataTable toItemDataTable(List<?> itemList) {
        List<List<String>> listOfList = new ArrayList<>();
        listOfList.add(Arrays.asList("name", "unitPrice"));

        itemList.forEach((e) -> {
            if (e instanceof ItemDto) {
                listOfList.add(
                        Arrays.asList(((ItemDto) e).getName(), String.valueOf(((ItemDto) e).getUnitPrice())));
            }
        });

        return DataTable.create(listOfList);
    }
}
