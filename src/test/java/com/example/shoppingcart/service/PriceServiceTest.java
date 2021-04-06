package com.example.shoppingcart.service;

import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.domain.QuantityRule;
import com.example.shoppingcart.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Arman
 * Date: 4/6/21
 * Time: 9:04 PM
 **/
class PriceServiceTest {
    private PriceService priceService = new PriceServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void calculatePrice_With3Item_QuantityRuleFor3_MustApplied() {
        Item item = mock(Item.class);
        QuantityRule quantityRule = mock(QuantityRule.class);
        when(quantityRule.getPrice()).thenReturn(130.0);
        when(quantityRule.getQuantity()).thenReturn(3);
        Set<QuantityRule> rules = Collections.singleton(quantityRule);
        when(item.getRules()).thenReturn(rules);
        when(item.getUnitPrice()).thenReturn(50.0);
        when(item.getName()).thenReturn("A");
        final var calculatePrice = priceService.calculatePrice(item, 3);
        Assertions.assertEquals(130.0, calculatePrice);
    }

    @Test
    void calculatePrice_With2Item_QuantityRuleFor3_MustNotApplied() {
        Item item = mock(Item.class);
        QuantityRule quantityRule = mock(QuantityRule.class);
        when(quantityRule.getPrice()).thenReturn(130.0);
        when(quantityRule.getQuantity()).thenReturn(3);
        Set<QuantityRule> rules = Collections.singleton(quantityRule);
        when(item.getRules()).thenReturn(rules);
        when(item.getUnitPrice()).thenReturn(50.0);
        when(item.getName()).thenReturn("A");
        final var calculatePrice = priceService.calculatePrice(item, 2);
        Assertions.assertEquals(100.0, calculatePrice);
    }

    @Test
    void calculatePrice_With3Item_QuantityRuleFor2_MustApplied() {
        Item item = mock(Item.class);
        QuantityRule quantityRule = mock(QuantityRule.class);
        when(quantityRule.getPrice()).thenReturn(45.0);
        when(quantityRule.getQuantity()).thenReturn(2);
        Set<QuantityRule> rules = Collections.singleton(quantityRule);
        when(item.getRules()).thenReturn(rules);
        when(item.getUnitPrice()).thenReturn(30.0);
        when(item.getName()).thenReturn("B");
        final var calculatePrice = priceService.calculatePrice(item, 3);
        Assertions.assertEquals(75.0, calculatePrice);
    }

    @Test
    void calculatePrice_With4Item_QuantityRuleFor2_MustAppliedTwice() {
        Item item = mock(Item.class);
        QuantityRule quantityRule = mock(QuantityRule.class);
        when(quantityRule.getPrice()).thenReturn(45.0);
        when(quantityRule.getQuantity()).thenReturn(2);
        Set<QuantityRule> rules = Collections.singleton(quantityRule);
        when(item.getRules()).thenReturn(rules);
        when(item.getUnitPrice()).thenReturn(30.0);
        when(item.getName()).thenReturn("B");
        final var calculatePrice = priceService.calculatePrice(item, 4);
        Assertions.assertEquals(90.0, calculatePrice);
    }

    @Test
    void calculatePrice_With6Item_QuantityRuleFor2_MustAppliedThreeTimes() {
        Item item = mock(Item.class);
        QuantityRule quantityRule = mock(QuantityRule.class);
        when(quantityRule.getPrice()).thenReturn(45.0);
        when(quantityRule.getQuantity()).thenReturn(2);
        Set<QuantityRule> rules = Collections.singleton(quantityRule);
        when(item.getRules()).thenReturn(rules);
        when(item.getUnitPrice()).thenReturn(30.0);
        when(item.getName()).thenReturn("B");
        final var calculatePrice = priceService.calculatePrice(item, 6);
        Assertions.assertEquals(135.0, calculatePrice);
    }
}