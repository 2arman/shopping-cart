package com.example.shoppingcart.controller;

import com.example.shoppingcart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:09 AM
 **/
@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/v1/cart/items")
public class CartItemsController {

    private final CartService cartService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@NotNull @Positive @RequestParam("itemId") Long itemId) {
        cartService.addItems(itemId);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@NotNull @Positive @RequestParam("itemId") Long itemId) {
        cartService.remove(itemId);
    }
}
