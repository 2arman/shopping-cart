package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.domain.Cart;
import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.repository.CartRepository;
import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.ItemService;
import com.example.shoppingcart.service.PriceService;
import com.example.shoppingcart.service.exception.CartItemsInsufficientException;
import com.example.shoppingcart.service.mapper.CartMapper;
import com.example.shoppingcart.service.model.CartDto;
import org.springframework.stereotype.Service;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 7:05 PM
 **/
@Service
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;
    private final ItemService itemService;
    private final PriceService priceService;
    private final Cart cachedUserCart;

    public CartServiceImpl(CartRepository cartRepository,
                           CartMapper cartMapper,
                           ItemService itemService,
                           PriceService priceService) {
        this.cartMapper = cartMapper;
        this.itemService = itemService;
        this.priceService = priceService;
        //todo must create shopping-cart based on user session
        this.cachedUserCart = cartRepository.add(new Cart());
    }

    @Override
    public CartDto getCart() {
        return cartMapper.map(cachedUserCart);
    }

    @Override
    public void addItems(Long itemId) {
        final var item = itemService.getItemById(itemId);
        cachedUserCart.getItemQuantityMap()
                .putIfAbsent(item,
                        Cart.QuantityPrice.builder()
                                .price(0.0)
                                .quantity(0)
                                .build());

        cachedUserCart.getItemQuantityMap()
                .computeIfPresent(item, (key, value) -> {
                    final var oldQuantity = value.getQuantity();
                    final var newQuantity = oldQuantity + 1;
                    final var calculatePrice = priceService.calculatePrice(key, newQuantity);
                    return Cart.QuantityPrice.builder().price(calculatePrice).quantity(newQuantity).build();
                });
    }

    @Override
    public void remove(Long itemId) {
        final var item = itemService.getItemById(itemId);
        checkQuantity(item);
        cachedUserCart.getItemQuantityMap()
                .computeIfPresent(item, (key, value) -> {
                    final var oldQuantity = value.getQuantity();
                    final var newQuantity = oldQuantity - 1;
                    final var calculatePrice = priceService.calculatePrice(key, newQuantity);
                    return Cart.QuantityPrice.builder()
                            .price(calculatePrice)
                            .quantity(newQuantity)
                            .build();
                });
    }

    private void checkQuantity(Item item) {
        final var itemValue = cachedUserCart
                .getItemQuantityMap()
                .getOrDefault(item, Cart.QuantityPrice.builder()
                        .price(0.0)
                        .quantity(0)
                        .build());
        if (itemValue.getQuantity() < 1) {
            throw new CartItemsInsufficientException("there is not enough items in shopping-cart");
        }
    }
}
