package com.example.shoppingcart.service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Arman
 * Date: 4/5/21
 * Time: 12:19 AM
 **/
@ResponseStatus(value = NOT_FOUND, reason = "Could not found this item")
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
