package com.example.shoppingcart.integration.stepdefs;

import com.example.shoppingcart.service.model.CartDto;
import com.example.shoppingcart.service.model.ItemDto;
import com.example.shoppingcart.service.model.ItemQuantityDto;
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
        Given("^user wants to add new item to cart of item (\\d+) of context \"([^\"]*)\"$", (Long itemOrder, String contextKey) -> {
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

        Given("^user wants to reset shopping cart$", () -> {
            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .delete(checkoutUrl)
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
            final var itemQuantitySelected = cartDto.getItemQuantity()
                    .stream()
                    .filter(itemQuantityDto -> itemQuantityDto.getItem().getId().equals(itemId)).findFirst()
                    .orElse(ItemQuantityDto.builder()
                            .item(new ItemDto())
                            .build());
            final var quantity = itemQuantitySelected.getQuantity();
            Assertions.assertEquals(expectedQuantity, quantity);
            Assertions.assertEquals(itemId, itemQuantitySelected.getItem().getId());
        });
        And("^Item types of Shopping cart must equals (\\d+)$", (Integer expectedItemTypes) -> {
            final Response response = getTextContext().getResponse();
            final var cartDto = response.as(CartDto.class);
            Assertions.assertEquals(expectedItemTypes, cartDto.getItemQuantity().size());
        });

        And("^user wants to remove item from cart of item (\\d+) of context \"([^\"]*)\"$", (Long itemOrder, String contextKey)-> {
            Long itemId = getItemFromContext(itemOrder, contextKey);

            final Response response =
                    given()
                            .log()
                            .all()
                            .when()
                            .contentType(ContentType.JSON)
                            .delete(cartItemsUrl.replace("{id}", itemId.toString()))
                            .andReturn();

            getTextContext().setResponse(response);

            response.then()
                    .log()
                    .all();
        });


    }
}
