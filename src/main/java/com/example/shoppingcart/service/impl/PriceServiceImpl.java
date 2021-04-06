package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.service.PriceService;
import com.google.common.util.concurrent.AtomicDouble;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Arman
 * Date: 4/6/21
 * Time: 2:44 PM
 **/
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    @Override
    public Double calculatePrice(Item item, int quantity) {
        if (quantity == 0) {
            return 0.0;
        }
        AtomicDouble price = new AtomicDouble(item.getUnitPrice() * quantity);
        item.getRules()
                .stream()
                .filter(quantityRule -> quantity >= quantityRule.getQuantity())
                .findFirst()
                .ifPresent(quantityRule -> {
                    final var ruleAppliedTime = Math.floor(Math.abs((double) quantity / quantityRule.getQuantity()));
                    price.set(
                            (quantityRule.getPrice() * ruleAppliedTime) +
                                    ((quantity - (ruleAppliedTime * quantityRule.getQuantity())) * item.getUnitPrice()));
                });
        return price.get();
    }
}
