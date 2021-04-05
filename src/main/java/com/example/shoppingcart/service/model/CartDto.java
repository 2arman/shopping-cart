package com.example.shoppingcart.service.model;

import lombok.Data;

import java.util.List;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:12 AM
 **/
@Data
public class CartDto {
    private List<ItemDto> items;
    private Double totalPrice;
}
