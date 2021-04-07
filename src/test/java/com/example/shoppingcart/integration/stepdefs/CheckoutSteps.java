package com.example.shoppingcart.integration.stepdefs;

import com.example.shoppingcart.service.model.CartDto;
import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static com.example.shoppingcart.integration.stepdefs.helper.ItemHelper.getItemFromContext;
import static io.restassured.RestAssured.given;

/**
 * @author Arman
 * Date: 4/6/21
 * Time: 9:36 PM
 **/
public class CheckoutSteps extends AbstractSteps implements En {
    private final String cartItemsUrl = "http://localhost:8080/api/v1/cart/items?itemId={id}";
    private final String checkoutUrl = "http://localhost:8080/api/v1/cart";

    public CheckoutSteps() {
        Given("^user wants to add new item of item (\\d+) of context \"([^\"]*)\"$", (Long itemOrder, String contextKey) -> {
            Long itemId = getItemFromContext(itemOrder, contextKey);

            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .post(cartItemsUrl.replace("{id}", itemId.toString()))
                            .andReturn();

            getTextContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });
        And("^user wants to get the shopping-cart$", () -> {
            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .get(checkoutUrl)
                            .andReturn();

            getTextContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });

        And("^the total price must be (.+)$", (Double expectedTotalPrice) -> {
            final Response response = getTextContext().getResponse();
            final var cartDto = response.as(CartDto.class);
            Assertions.assertEquals(expectedTotalPrice, cartDto.getTotalPrice());
        });

        And("^quantity of item (\\d+) of context \"([^\"]*)\" in cart must be (\\d+)$", (Long itemOrder, String contextKey, Integer expectedQuantity) -> {
            Long itemId = getItemFromContext(itemOrder, contextKey);
            final Response response = getTextContext().getResponse();
            final var cartDto = response.as(CartDto.class);
            Assertions.assertEquals(expectedQuantity, cartDto.getItemQuantity().get(0).getQuantity());
            Assertions.assertEquals(itemId, cartDto.getItemQuantity().get(0).getItem().getId());
        });
        And("^Item types of Shopping cart must be only (\\d+)$", (Integer expectedItemTypes) -> {
            final Response response = getTextContext().getResponse();
            final var cartDto = response.as(CartDto.class);
            Assertions.assertEquals(expectedItemTypes, cartDto.getItemQuantity().size());
        });

    }
}
