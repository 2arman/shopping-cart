package com.example.shoppingcart.integration.stepdefs;

import com.example.shoppingcart.integration.stepdefs.helper.ItemHelper;
import com.example.shoppingcart.service.model.ItemDto;
import com.example.shoppingcart.service.model.RuleDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RuleSteps extends AbstractSteps implements En {

    private final String rulesUrl = baseUrl() + "/api/v1/bo/items/{id}/rules";

    public RuleSteps() {
        Given("BO user wants to create rules with the following attributes", (DataTable rulesDt) -> {
            List<ItemDto> items = rulesDt.asList(RuleDto.class);
            getTextContext().setPayload(items);
        });


        When("^the BO user saves new rule of item (\\d+) of context \"([^\"]*)\"$", (Long itemOrder, String contextKey) -> {

            Long itemId = ItemHelper.getItemFromContext(itemOrder, contextKey);

            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .body(getTextContext().getPayload())
                            .post(rulesUrl.replace("{id}", itemId.toString()))
                            .andReturn();

            getTextContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });


        When("^the BO user wants to get rules of item (\\d+) of context \"([^\"]*)\"$", (Long itemOrder, String contextKey) -> {

            Long id = ItemHelper.getItemFromContext(itemOrder, contextKey);
            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .get(rulesUrl.replace("{id}", id.toString()))
                            .andReturn();

            getTextContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });


        When("^the BO user wants to delete all rules of item (\\d+) of context \"([^\"]*)\"$", (Long itemOrder, String contextKey) -> {

            Long itemId = ItemHelper.getItemFromContext(itemOrder, contextKey);

            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .delete(rulesUrl.replace("{id}", itemId.toString()))
                            .andReturn();

            getTextContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });

        And("^save rules response into payload$", () -> {
            final Response response = getTextContext().getResponse();
            final var itemsList = response.as(RuleDto[].class);
            getTextContext().setPayload(itemsList);
        });

        And("^BO user retrieves rules with the following attributes$", (DataTable expectedDt) -> {
            final Response response = getTextContext().getResponse();
            final var rulesList = response.as(RuleDto[].class);
            final var actualDataTable = DataTableMapper.toRuleDataTable(Arrays.asList(rulesList));
            expectedDt.unorderedDiff(actualDataTable);
        });
    }

}
