package com.example.shoppingcart.controller;

import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.model.CartDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:09 AM
 **/
@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CartDto getCurrentCart() {
        return cartService.getCart();
    }

}
