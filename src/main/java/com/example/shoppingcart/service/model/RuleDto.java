package com.example.shoppingcart.service.model;

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
    @Positive
    @NotNull
    private Integer quantity;
    @Positive
    @NotNull
    private Double price;
}
