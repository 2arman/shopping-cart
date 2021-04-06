package com.example.shoppingcart.service;

import com.example.shoppingcart.domain.Item;
import com.example.shoppingcart.service.model.ItemDto;

import java.util.List;

/**
 * @author Arman
 * Date: 4/3/21
 * Time: 1:25 AM
 **/
public interface ItemService {
    List<ItemDto> getAllItems();

    List<ItemDto> addAll(List<ItemDto> items);

    void removeAll(List<ItemDto> items);

    Item getItemById(Long id);
}
