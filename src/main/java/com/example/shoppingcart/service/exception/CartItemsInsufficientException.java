package com.example.shoppingcart.service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE;

/**
 * @author Arman
 * Date: 4/6/21
 * Time: 1:30 PM
 **/
@ResponseStatus(value = REQUESTED_RANGE_NOT_SATISFIABLE, reason = "There is not enough of this item in shopping-cart")
public class CartItemsInsufficientException extends RuntimeException {
    public CartItemsInsufficientException(String message) {
        super(message);
    }
}
