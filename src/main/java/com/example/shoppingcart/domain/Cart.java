package com.example.shoppingcart.domain;

import com.example.shoppingcart.domain.base.BaseEntity;
import com.google.common.util.concurrent.AtomicDouble;
import lombok.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:12 AM
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements BaseEntity {
    private Long Id;
    private Map<Item, QuantityPrice> itemQuantityMap = new ConcurrentHashMap<>();

    public double getTotalPrice() {
        AtomicDouble totalPrice = new AtomicDouble(0.0);
        itemQuantityMap.forEach((item, quantityPrice) -> totalPrice.addAndGet(quantityPrice.price));
        return totalPrice.get();
    }

    @RequiredArgsConstructor
    @Builder
    @Getter
    public static class QuantityPrice {
        private final int quantity;
        private final double price;
    }
}
