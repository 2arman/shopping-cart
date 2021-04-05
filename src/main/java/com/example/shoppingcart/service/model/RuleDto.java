package com.example.shoppingcart.service.model;

import com.example.shoppingcart.domain.Item;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 11:39 AM
 **/
@Data
public class RuleDto {

    private long ruleId;
    @NotNull
    private Item item;
    @Positive
    private int quantity;
    @Positive
    private double unitPrice;
}
