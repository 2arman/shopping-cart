package com.example.shoppingcart.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Arman
 * Date: 4/6/21
 * Time: 10:12 PM
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemQuantityDto {
    private ItemDto item;
    private int quantity;
}
