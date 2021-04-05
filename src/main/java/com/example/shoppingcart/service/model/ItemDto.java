package com.example.shoppingcart.service.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:11 AM
 **/
@Data
public class ItemDto {
    @NotEmpty
    private String name;
    @Positive
    private double unitPrice;
}
