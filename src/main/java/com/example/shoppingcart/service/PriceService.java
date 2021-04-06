package com.example.shoppingcart.service;

import com.example.shoppingcart.domain.Item;

/**
 * @author Arman
 * Date: 4/6/21
 * Time: 2:41 PM
 **/
public interface PriceService {
    Double calculatePrice(Item item, int quantity);
}
