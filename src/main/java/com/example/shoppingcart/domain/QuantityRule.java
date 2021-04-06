package com.example.shoppingcart.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 11:39 AM
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QuantityRule extends Rule {
    @Positive
    private int quantity;
    @Positive
    private double price;
}
