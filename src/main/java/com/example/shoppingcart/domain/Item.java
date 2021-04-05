package com.example.shoppingcart.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:11 AM
 **/
@Data
public class Item {
    @NotNull
    private Long Id;
    @NotEmpty
    private String name;
    @Positive
    private double unitPrice;
}
