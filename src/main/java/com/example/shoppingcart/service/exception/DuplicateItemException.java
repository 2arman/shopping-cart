package com.example.shoppingcart.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Arman
 * Date: 4/6/21
 * Time: 7:29 PM
 **/
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Another item with this name exists")
public class DuplicateItemException {
}
