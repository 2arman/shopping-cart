package com.example.shoppingcart.service;

import com.example.shoppingcart.service.model.CartDto;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:25 AM
 **/
public interface CartService {
    CartDto getCart();

    void addItems(Long itemId);

    void remove(Long itemId);

    void resetCard();
}
